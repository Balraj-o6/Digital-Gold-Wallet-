package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.VendorBranch;

@Repository
public interface IVendorBranchRepository extends JpaRepository<VendorBranch, Integer> {

}
