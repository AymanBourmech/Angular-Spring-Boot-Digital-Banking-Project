package com.esps.ebankingbackend.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esps.ebankingbackend.dtos.AccountHistoryDTO;
import com.esps.ebankingbackend.dtos.AccountOperationDTO;
import com.esps.ebankingbackend.entities.AccountOperation;
import com.esps.ebankingbackend.entities.BankAccount;
import com.esps.ebankingbackend.enums.OperationType;
import com.esps.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.esps.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.esps.ebankingbackend.mappers.BankAccountMapperImp;
import com.esps.ebankingbackend.repositories.AccountOperationRepository;
import com.esps.ebankingbackend.repositories.BankAccountRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Transactional
@Service

public class AccountOperationsServiceImp implements AccountOperationsService {
	private BankAccountRepository bankAccountRepository;
	private AccountOperationRepository accountOperationRepository;
	private BankAccountMapperImp dtoMapper;

	@Override
	public void debit(String accountId, double amount, String description)
			throws BalanceNotSufficientException, BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(() -> new BankAccountNotFoundException("BankAccount_not_found"));
		if (bankAccount.getBalance() < amount)
			throw new BalanceNotSufficientException("Balance not sufficient");
		AccountOperation accountOperation = new AccountOperation();
		accountOperation.setType(OperationType.DEBIT);
		accountOperation.setAmount(amount);
		accountOperation.setDescription(description);
		accountOperation.setOperationDate(new Date());
		accountOperation.setBankAccount(bankAccount);
		accountOperationRepository.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance() - amount);
		bankAccountRepository.save(bankAccount);
	}

	@Override
	public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(() -> new BankAccountNotFoundException("BankAccount_not_found"));
		AccountOperation accountOperation = new AccountOperation();
		accountOperation.setType(OperationType.CREDIT);
		accountOperation.setAmount(amount);
		accountOperation.setDescription(description);
		accountOperation.setOperationDate(new Date());
		accountOperation.setBankAccount(bankAccount);
		accountOperationRepository.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance() + amount);
		bankAccountRepository.save(bankAccount);
	}

	@Override
	public void transfer(String accountIdSource, String accountIdDestination, double amount, String description)
			throws BalanceNotSufficientException, BankAccountNotFoundException {
		debit(accountIdSource, amount, "Transfer from" + accountIdDestination);
		credit(accountIdDestination, amount, "Transfer to" + accountIdSource);
	}

	@Override
	public List<AccountOperationDTO> accountHistory(String accountId) {
		List<AccountOperation> accountOperation = accountOperationRepository.findByBankAccountId(accountId);
		return accountOperation.stream().map(op -> dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
	}

	@Override
	public AccountHistoryDTO getAccountHistory(String accountId, int page, int size)
			throws BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
		if (bankAccount == null)
			throw new BankAccountNotFoundException("Account not found");
		Page<AccountOperation> accountOperation = accountOperationRepository
				.findByBankAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
		AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
		List<AccountOperationDTO> accountOperationDTOS = accountOperation.getContent().stream()
				.map(op -> dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
		accountHistoryDTO.setAccountOperationDTO(accountOperationDTOS);
		accountHistoryDTO.setAccountId(bankAccount.getId());
		accountHistoryDTO.setBalance(bankAccount.getBalance());
		accountHistoryDTO.setCurrentPage(page);
		accountHistoryDTO.setPageSize(size);
		accountHistoryDTO.setTotalPages(accountOperation.getTotalPages());
		return accountHistoryDTO;
	}

}
