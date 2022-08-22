package com.esps.ebankingbackend.dtos;

import java.util.List;

import lombok.Data;

@Data
public class AccountHistoryDTO {
	private List<AccountOperationDTO> accountOperationDTO;
	private String accountId;
	private double balance;
	private int currentPage;
	private int totalPages;
	private int pageSize;
}
