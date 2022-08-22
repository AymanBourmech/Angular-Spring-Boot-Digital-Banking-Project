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
@DiscriminatorValue("CA")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_currentAccount")
public class CurrentAccount extends BankAccount {

	private static final long serialVersionUID = 1L;
	private double overDraft;
}
