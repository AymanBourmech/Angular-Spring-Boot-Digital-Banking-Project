package com.esps.ebankingbackend.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esps.ebankingbackend.dtos.CustomerDTO;
import com.esps.ebankingbackend.exceptions.CustomerNotFoundException;
import com.esps.ebankingbackend.services.CustomerServiceImp;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cust")
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerRestController {
	private CustomerServiceImp customerService;

	@GetMapping("/customers")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<CustomerDTO> customers() {
		return customerService.ListCustomers();
	}

	@GetMapping("/customers/search")
	public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		return customerService.searchCustomers("%" + keyword + "%");
	}

	@GetMapping("/customers/{id}")

	public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
		return customerService.getCustomer(customerId);
	}

	@PostMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.saveCustomer(customerDTO);
	}

	@PutMapping("/customers/{customerId}")

	public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
		customerDTO.setId(customerId);
		return customerService.updateCustomer(customerDTO);
	}

	@DeleteMapping("/customers/{id}")

	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}
}
