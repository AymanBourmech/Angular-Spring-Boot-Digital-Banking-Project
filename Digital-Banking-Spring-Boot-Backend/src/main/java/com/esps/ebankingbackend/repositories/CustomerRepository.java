package com.esps.ebankingbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esps.ebankingbackend.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("select c from Customer c where c.name like :kw")
List<Customer> searchCustomer(@Param("kw")String keyword);
}
