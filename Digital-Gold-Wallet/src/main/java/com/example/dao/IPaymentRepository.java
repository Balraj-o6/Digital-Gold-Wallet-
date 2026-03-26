package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {

}
