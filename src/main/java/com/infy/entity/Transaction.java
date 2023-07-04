package com.infy.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	private long trancation_id;
	private String senderAccountNumber;
	private String recieverAccountNumber;
	private BigDecimal amount;
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "uid")
	private User user;

	public long getTrancation_id() {
		return trancation_id;
	}

	public void setTrancation_id(long trancation_id) {
		this.trancation_id = trancation_id;
	}

	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	public String getRecieverAccountNumber() {
		return recieverAccountNumber;
	}

	public void setRecieverAccountNumber(String recieverAccountNumber) {
		this.recieverAccountNumber = recieverAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(long trancation_id, String senderAccountNumber, String recieverAccountNumber, BigDecimal amount,
			LocalDate date, User user) {
		super();
		this.trancation_id = trancation_id;
		this.senderAccountNumber = senderAccountNumber;
		this.recieverAccountNumber = recieverAccountNumber;
		this.amount = amount;
		this.date = date;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Transaction [trancation_id=" + trancation_id + ", senderAccountNumber=" + senderAccountNumber
				+ ", recieverAccountNumber=" + recieverAccountNumber + ", amount=" + amount + ", date=" + date
				+ ", user=" + user + "]";
	}
}
