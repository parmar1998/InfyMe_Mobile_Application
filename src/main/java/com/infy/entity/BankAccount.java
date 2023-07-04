package com.infy.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BankAccount {
	@Id
	private Long bankAccount_id;
	private String accountNumber;
	private String accountType;
	private BigDecimal balance;

	@ManyToOne
	@JoinColumn(name = "uid")
	private User user;

	public BankAccount() {
		super();
	}

	public BankAccount(Long bankAccount_id, String accountNumber, String accountType, BigDecimal balance, User user) {
		super();
		this.bankAccount_id = bankAccount_id;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.user = user;
	}

	public Long getBankAccount_id() {
		return bankAccount_id;
	}

	public void setBankAccount_id(Long bankAccount_id) {
		this.bankAccount_id = bankAccount_id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
