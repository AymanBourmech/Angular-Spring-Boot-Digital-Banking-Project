package com.esps.ebankingbackend.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.esps.ebankingbackend.enums.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_bankAccount")
public class BankAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length = 50)
	private String id;
	private double balance;
	private Date createdAt;
	@Enumerated(EnumType.STRING)
	private AccountStatus status;
	@OneToMany(mappedBy = "bankAccount")
	private List<AccountOperation> accountOperations;
	@ManyToOne
	private Customer customer;
	
}
