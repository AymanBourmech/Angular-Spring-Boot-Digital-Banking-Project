package com.esps.ebankingbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esps.ebankingbackend.dtos.CustomerDTO;
import com.esps.ebankingbackend.entities.Customer;
import com.esps.ebankingbackend.exceptions.CustomerNotFoundException;
import com.esps.ebankingbackend.mappers.BankAccountMapperImp;
import com.esps.ebankingbackend.repositories.CustomerRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Transactional
@Service
@Slf4j
public class CustomerServiceImp implements CustomerService {
	private CustomerRepository customerRepository;
	private BankAccountMapperImp dtoMapper;

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		log.info("Saving new Customer");
		Customer customer = dtoMapper.fromCustomerDTO(customerDTO);
		Customer savedCustomer = customerRepository.save(customer);
		return dtoMapper.fromCustomer(savedCustomer);
	}

	/*
	 * @Override public List<Customer> ListCustomers() { return
	 * customerRepository.findAll(); }
	 */

	@Override
	public List<CustomerDTO> ListCustomers() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customersDTOS = customers.stream().map(cust -> dtoMapper.fromCustomer(cust))
				.collect(Collectors.toList());
		return customersDTOS;
		/*
		 * List<CustomerDTO>customerDTOS=new ArrayList<>(); for(Customer
		 * customer:customers) { CustomerDTO
		 * customerDTO=dtoMapper.fromCustomer(customer); customerDTOS.add(customerDTO);
		 * }
		 */
	}

	@Override
	public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		return dtoMapper.fromCustomer(customer);
	}

	@Override

	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
		log.info("Update Customer");
		Customer customer = dtoMapper.fromCustomerDTO(customerDTO);
		Customer savedCustomer = customerRepository.save(customer);
		return dtoMapper.fromCustomer(savedCustomer);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public List<CustomerDTO> searchCustomers(String keyword) {
		List<Customer> customers = customerRepository.searchCustomer(keyword);
		List<CustomerDTO> customerDTOS = customers.stream().map(cust -> dtoMapper.fromCustomer(cust))
				.collect(Collectors.toList());
		return customerDTOS;
	}
}
