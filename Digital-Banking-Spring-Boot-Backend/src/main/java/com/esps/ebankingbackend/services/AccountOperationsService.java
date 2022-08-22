package com.esps.ebankingbackend.services;

import java.util.List;

import com.esps.ebankingbackend.dtos.AccountHistoryDTO;
import com.esps.ebankingbackend.dtos.AccountOperationDTO;
import com.esps.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.esps.ebankingbackend.exceptions.BankAccountNotFoundException;

public interface AccountOperationsService {
	void debit(String accountId, double amount, String description)
			throws BalanceNotSufficientException, BankAccountNotFoundException;

	void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;

	void transfer(String accountIdSource, String accountIdDestination, double amount, String description)
			throws BalanceNotSufficientException, BankAccountNotFoundException;

	List<AccountOperationDTO> accountHistory(String accountId);

	AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
