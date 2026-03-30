package com.example.controller;

import com.example.dto.PhysicalGoldTransactionDTO;
import com.example.dto.TransactionHistoryDTO;
import com.example.service.IPhysicalGoldTransactionService;
import com.example.service.ITransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private IPhysicalGoldTransactionService physicalGoldTransactionService;

    @GetMapping("/physical/branch/{branchId}")
    public ResponseEntity<List<PhysicalGoldTransactionDTO>> getPhysicalTransactionsByBranchId(
            @PathVariable Integer branchId) {

        List<PhysicalGoldTransactionDTO> transactions =
                physicalGoldTransactionService.getPhysicalTransactionsByBranchId(branchId);

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}