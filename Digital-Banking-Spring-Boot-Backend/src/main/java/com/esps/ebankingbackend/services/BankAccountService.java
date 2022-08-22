package com.esps.ebankingbackend.services;

import java.util.List;

import com.esps.ebankingbackend.dtos.BankAccountDTO;
import com.esps.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.esps.ebankingbackend.dtos.SavingBankAccountDTO;
import com.esps.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.esps.ebankingbackend.exceptions.CustomerNotFoundException;

public interface BankAccountService {

	CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId)
			throws CustomerNotFoundException;

	SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId)
			throws CustomerNotFoundException;

	BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

	List<BankAccountDTO> bankAccountList();

	List<BankAccountDTO> bankAccountListByCustomer(Long customerId);
}
