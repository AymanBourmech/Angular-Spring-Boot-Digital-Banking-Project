package com.esps.ebankingbackend.dtos;

import java.util.Date;

import com.esps.ebankingbackend.enums.AccountStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CurrentBankAccountDTO extends BankAccountDTO {

	private String id;
	private double balance;
	private Date createdAt;
	private AccountStatus status;
	private CustomerDTO customerDTO;
	private double overDraft;

}
