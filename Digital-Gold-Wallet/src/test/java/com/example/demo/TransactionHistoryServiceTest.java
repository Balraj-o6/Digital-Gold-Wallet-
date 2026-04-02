package com.example.demo;


import com.example.dao.ITransactionHistoryRepository;
import com.example.dto.TransactionHistoryDTO;
import com.example.entity.TransactionHistory;
import com.example.entity.User;
import com.example.entity.Vendor;
import com.example.entity.VendorBranch;
import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;
import com.example.exception.TransactionNotFoundException;
import com.example.service.TransactionHistoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransactionHistoryService - getTransactionsByStatus() Tests")
public class TransactionHistoryServiceTest {

    @Mock
    private ITransactionHistoryRepository transactionHistoryRepository;

    @InjectMocks
    private TransactionHistoryService transactionHistoryService;

    private TransactionHistory mockTransaction;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUserId(1);
        user.setName("Rahul Verma");

        Vendor vendor = new Vendor();
        vendor.setVendorName("GoldMart India");

        VendorBranch branch = new VendorBranch();
        branch.setBranchId(100);
        branch.setVendor(vendor);

        mockTransaction = new TransactionHistory();
        mockTransaction.setTransactionId(1001);
        mockTransaction.setUser(user);
        mockTransaction.setVendorBranch(branch);
        mockTransaction.setTransactionType(TransactionType.BUY);
        mockTransaction.setTransactionStatus(TransactionStatus.Success);
        mockTransaction.setQuantity(new BigDecimal("2.50"));
        mockTransaction.setAmount(new BigDecimal("33650.00"));
        mockTransaction.setCreatedAt(LocalDateTime.now());
    }

    @Test
    @DisplayName("Positive: Returns DTO list when transactions exist for given status")
    void getTransactionsByStatus_ShouldReturnDTOList_WhenStatusIsSuccess() {
        when(transactionHistoryRepository.findByTransactionStatus(TransactionStatus.Success))
                .thenReturn(List.of(mockTransaction));

        List<TransactionHistoryDTO> result = transactionHistoryService.getTransactionsByStatus("Success");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Rahul Verma", result.get(0).getUserName());
        assertEquals("Success", result.get(0).getStatus());
        verify(transactionHistoryRepository, times(1)).findByTransactionStatus(TransactionStatus.Success);
    }

    @Test
    @DisplayName("Negative: Throws TransactionNotFoundException when no transactions found")
    void getTransactionsByStatus_ShouldThrowException_WhenNoTransactionsFound() {
        when(transactionHistoryRepository.findByTransactionStatus(TransactionStatus.Failed))
                .thenReturn(Collections.emptyList());

        TransactionNotFoundException ex = assertThrows(
                TransactionNotFoundException.class,
                () -> transactionHistoryService.getTransactionsByStatus("Failed")
        );

        assertTrue(ex.getMessage().contains("Failed"));
        verify(transactionHistoryRepository, times(1)).findByTransactionStatus(TransactionStatus.Failed);
    }
}
