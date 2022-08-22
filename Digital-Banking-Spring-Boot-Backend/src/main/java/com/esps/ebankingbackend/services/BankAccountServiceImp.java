package com.esps.ebankingbackend.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esps.ebankingbackend.dtos.BankAccountDTO;
import com.esps.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.esps.ebankingbackend.dtos.SavingBankAccountDTO;
import com.esps.ebankingbackend.entities.BankAccount;
import com.esps.ebankingbackend.entities.CurrentAccount;
import com.esps.ebankingbackend.entities.Customer;
import com.esps.ebankingbackend.entities.SavingAccount;
import com.esps.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.esps.ebankingbackend.exceptions.CustomerNotFoundException;
import com.esps.ebankingbackend.mappers.BankAccountMapperImp;
import com.esps.ebankingbackend.repositories.BankAccountRepository;
import com.esps.ebankingbackend.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class BankAccountServiceImp implements BankAccountService {
	private CustomerRepository customerRepository;
	private BankAccountRepository bankAccountRepository;
	private BankAccountMapperImp dtoMapper;

	@Override
	public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId)
			throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found");
		}
		CurrentAccount currentAccount = new CurrentAccount();
		currentAccount.setId(UUID.randomUUID().toString());
		currentAccount.setCreatedAt(new Date());
		currentAccount.setBalance(initialBalance);
		currentAccount.setCustomer(customer);
		currentAccount.setOverDraft(overDraft);

		CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);

		return dtoMapper.fromCurrentBankAccount(savedBankAccount);
	}

	@Override
	public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId)
			throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found");
		}
		SavingAccount savingAccount = new SavingAccount();
		savingAccount.setId(UUID.randomUUID().toString());
		savingAccount.setCreatedAt(new Date());
		savingAccount.setBalance(initialBalance);
		savingAccount.setCustomer(customer);
		savingAccount.setInterestRate(interestRate);

		SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);

		return dtoMapper.fromSavingBankAccount(savedBankAccount);
	}

	@Override
	public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(() -> new BankAccountNotFoundException("BankAccount_not_found"));
		if (bankAccount instanceof SavingAccount) {
			SavingAccount savingAccount = (SavingAccount) bankAccount;
			return dtoMapper.fromSavingBankAccount(savingAccount);
		} else {
			CurrentAccount currentAccount = (CurrentAccount) bankAccount;
			return dtoMapper.fromCurrentBankAccount(currentAccount);
		}

	}

	@Override
	public List<BankAccountDTO> bankAccountList() {
		List<BankAccount> bankAccounts = bankAccountRepository.findAll();
		List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream().map(bankAccount -> {
			if (bankAccount instanceof SavingAccount) {
				SavingAccount savingAccount = (SavingAccount) bankAccount;
				return dtoMapper.fromSavingBankAccount(savingAccount);
			} else {
				CurrentAccount currentAccount = (CurrentAccount) bankAccount;
				return dtoMapper.fromCurrentBankAccount(currentAccount);
			}
		}).collect(Collectors.toList());
		return bankAccountDTOS;
	}

	@Override
	public List<BankAccountDTO> bankAccountListByCustomer(Long customerId) {
		List<BankAccount> bankAccount = bankAccountRepository.findByCustomerId(customerId);
		return bankAccount.stream().map(bank -> dtoMapper.fromBankAccount(bank)).collect(Collectors.toList());

	}

}
