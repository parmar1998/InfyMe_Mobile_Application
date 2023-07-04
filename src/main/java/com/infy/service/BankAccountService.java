package com.infy.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.entity.BankAccount;
import com.infy.entity.User;
import com.infy.repository.BankAccountRepository;

@Service
public class BankAccountService {
	private BankAccountRepository accountRepository;

	@Autowired
	public BankAccountService(BankAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public BankAccount linkAccount(User user, String accountNumber, String accountType) {
		BankAccount existingaccount = accountRepository.findByAccountNumber(accountNumber);
		if (existingaccount != null) {
			throw new IllegalArgumentException("Account number is already linked");
		}
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(accountNumber);
		bankAccount.setAccountType(accountType);
		bankAccount.setUser(user);
		return accountRepository.save(bankAccount);
	}

	public BigDecimal checkBalance(User user, String accountNumber) {
		BankAccount account = getBankAccountByUserAndAccountNumber(user, accountNumber);
		return account.getBalance();
	}

	public BankAccount getBankAccountByUserAndAccountNumber(User user, String accountNumber) {
		BankAccount bankAccount = accountRepository.findByUserAndAccountNumber(user, accountNumber);
		if (bankAccount != null) {
			throw new IllegalArgumentException("bank Account not found");
		}
		return bankAccount;
	}

	
	public boolean transferFunds(BankAccount senderAccount, BankAccount recievedAccount, BigDecimal transferAmount) {
		if (senderAccount.getBalance().compareTo(transferAmount) < 0) {
			return false;
		}
		// deduct the amount
		senderAccount.setBalance(senderAccount.getBalance().subtract(transferAmount));
		accountRepository.save(senderAccount);

		// add the tranfer amount
		recievedAccount.setBalance(recievedAccount.getBalance().add(transferAmount));
		accountRepository.save(recievedAccount);
		return true;
	}


}
