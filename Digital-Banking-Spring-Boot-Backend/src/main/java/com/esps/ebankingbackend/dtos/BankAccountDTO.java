package com.esps.ebankingbackend.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class BankAccountDTO {
	private String type;

	private String id;
	private double balance;
	private Date createdAt;

}
