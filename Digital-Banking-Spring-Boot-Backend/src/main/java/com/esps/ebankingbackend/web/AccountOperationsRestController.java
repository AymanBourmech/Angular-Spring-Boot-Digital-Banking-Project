package com.esps.ebankingbackend.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esps.ebankingbackend.dtos.AccountHistoryDTO;
import com.esps.ebankingbackend.dtos.AccountOperationDTO;
import com.esps.ebankingbackend.dtos.CreditDTO;
import com.esps.ebankingbackend.dtos.DebitDTO;
import com.esps.ebankingbackend.dtos.TransferRequestDTO;
import com.esps.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.esps.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.esps.ebankingbackend.services.AccountOperationsServiceImp;

import lombok.AllArgsConstructor;
@RequestMapping("/api/acc")
@AllArgsConstructor
@RestController
@CrossOrigin("*")
public class AccountOperationsRestController {
	private AccountOperationsServiceImp accountOperationsService;

	@GetMapping("/accounts/{accountId}/pageOperations")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {
		return accountOperationsService.getAccountHistory(accountId, page, size);
	}

	@PostMapping("/accounts/debit")
	public DebitDTO debit(@RequestBody DebitDTO debitDTO)
			throws BankAccountNotFoundException, BalanceNotSufficientException {
		this.accountOperationsService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
		return debitDTO;
	}

	@PostMapping("/accounts/credit")
	public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
		this.accountOperationsService.credit(creditDTO.getAccountId(), creditDTO.getAmount(),
				creditDTO.getDescription());
		return creditDTO;
	}

	@PostMapping("/accounts/transfer")
	public void transfer(@RequestBody TransferRequestDTO transferRequestDTO)
			throws BalanceNotSufficientException, BankAccountNotFoundException {
		this.accountOperationsService.transfer(transferRequestDTO.getAccountSource(),
				transferRequestDTO.getAccountDestination(), transferRequestDTO.getAmount(),
				transferRequestDTO.getDescription());
		;

	}

	@GetMapping("/accounts/{accountId}/operations")
	public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
		return accountOperationsService.accountHistory(accountId);
	}

}
