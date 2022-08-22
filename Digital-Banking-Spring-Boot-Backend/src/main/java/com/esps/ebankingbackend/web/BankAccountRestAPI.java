package com.esps.ebankingbackend.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esps.ebankingbackend.dtos.BankAccountDTO;
import com.esps.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.esps.ebankingbackend.services.BankAccountServiceImp;

import lombok.AllArgsConstructor;
@RequestMapping("/api/acc")
@AllArgsConstructor
@RestController
@CrossOrigin("*")
public class BankAccountRestAPI {
	private BankAccountServiceImp bankAccountService;

	@GetMapping("/accounts/{accountId}")
	public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
		return bankAccountService.getBankAccount(accountId);
	}

	@GetMapping("/accounts")
	public List<BankAccountDTO> ListAccounts() {
		return bankAccountService.bankAccountList();
	}

	@GetMapping("/accounts/customers/{customerId}")
	public List<BankAccountDTO> bankAccountListByCustomer(@PathVariable Long customerId) {
		return bankAccountService.bankAccountListByCustomer(customerId);
	}

}
