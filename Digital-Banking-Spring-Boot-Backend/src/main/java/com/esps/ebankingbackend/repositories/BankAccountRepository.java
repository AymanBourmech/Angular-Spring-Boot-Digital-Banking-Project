package com.esps.ebankingbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esps.ebankingbackend.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
	List<BankAccount> findByCustomerId(Long customerId);
}
