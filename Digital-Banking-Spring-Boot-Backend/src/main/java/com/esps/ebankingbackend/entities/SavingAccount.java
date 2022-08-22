package com.esps.ebankingbackend.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("SA")
@Table(name = "t_savingAccount")
public class SavingAccount extends BankAccount {

	private static final long serialVersionUID = 1L;
	private double interestRate;
}
