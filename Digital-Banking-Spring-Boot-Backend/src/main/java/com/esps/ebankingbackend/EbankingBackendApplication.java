package com.esps.ebankingbackend;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.esps.ebankingbackend.dtos.BankAccountDTO;
import com.esps.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.esps.ebankingbackend.dtos.CustomerDTO;
import com.esps.ebankingbackend.dtos.SavingBankAccountDTO;
import com.esps.ebankingbackend.exceptions.CustomerNotFoundException;
import com.esps.ebankingbackend.services.AccountOperationsService;
import com.esps.ebankingbackend.services.BankAccountService;
import com.esps.ebankingbackend.services.CustomerService;

@SpringBootApplication

public class EbankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService, CustomerService customerService,
			AccountOperationsService accountOperationsService) {
		return args -> {
			Stream.of("Donia", "Ayman", "Anis").forEach(name -> {
				CustomerDTO customer = new CustomerDTO();
				customer.setName(name);
				customer.setEmail(name + "@gmail.com");
				customerService.saveCustomer(customer);
			});
			customerService.ListCustomers().forEach(customer -> {
				try {
					bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, customer.getId());
					bankAccountService.saveSavingBankAccount(Math.random() * 120000, 5.5, customer.getId());

				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				}

			});
			List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
			for (BankAccountDTO bankAccount : bankAccounts) {

				for (int i = 0; i < 10; i++) {
					String accountId;
					if (bankAccount instanceof SavingBankAccountDTO) {
						accountId = ((SavingBankAccountDTO) bankAccount).getId();
					} else {
						accountId = ((CurrentBankAccountDTO) bankAccount).getId();
					}
					accountOperationsService.credit(accountId, 1000 + Math.random() * 120000, "Credit");
					accountOperationsService.debit(accountId, 1000 - Math.random() * 90000, "Debit");
				}

			}

		};
	}
}
