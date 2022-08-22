package com.esps.ebankingbackend.services;

import java.util.List;

import com.esps.ebankingbackend.dtos.CustomerDTO;
import com.esps.ebankingbackend.exceptions.CustomerNotFoundException;

public interface CustomerService {
	CustomerDTO saveCustomer(CustomerDTO customerDTO);

	List<CustomerDTO> ListCustomers();

	CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

	CustomerDTO updateCustomer(CustomerDTO customerDTO);

	void deleteCustomer(Long customerId);

	List<CustomerDTO> searchCustomers(String keyword);

}
