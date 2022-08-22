package com.esps.ebankingbackend.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.esps.ebankingbackend.dtos.AccountOperationDTO;
import com.esps.ebankingbackend.dtos.BankAccountDTO;
import com.esps.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.esps.ebankingbackend.dtos.CustomerDTO;
import com.esps.ebankingbackend.dtos.SavingBankAccountDTO;
import com.esps.ebankingbackend.entities.AccountOperation;
import com.esps.ebankingbackend.entities.BankAccount;
import com.esps.ebankingbackend.entities.CurrentAccount;
import com.esps.ebankingbackend.entities.Customer;
import com.esps.ebankingbackend.entities.SavingAccount;

@Service
public class BankAccountMapperImp {
	public CustomerDTO fromCustomer(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);
		return customerDTO;
	}

	public Customer fromCustomerDTO(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		return customer;
	}

	public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {
		SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
		BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
		savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
		savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
		return savingBankAccountDTO;
	}

	public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {
		SavingAccount savingAccount = new SavingAccount();
		BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
		savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
		return savingAccount;
	}

	public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount) {
		CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
		BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
		currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
		currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
		return currentBankAccountDTO;
	}

	public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {
		CurrentAccount currentAccount = new CurrentAccount();
		BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
		currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
		return currentAccount;
	}

	public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
		AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
		BeanUtils.copyProperties(accountOperation, accountOperationDTO);
		return accountOperationDTO;
	}
	public BankAccountDTO fromBankAccount(BankAccount bankAccount) {
		BankAccountDTO bankAccountDTO=new BankAccountDTO();
		BeanUtils.copyProperties(bankAccount, bankAccountDTO);
		return bankAccountDTO;
	}
}
