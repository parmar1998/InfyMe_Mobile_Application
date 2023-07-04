package com.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.entity.BankAccount;
import com.infy.entity.User;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	BankAccount findByAccountNumber(String accountNumber);

	BankAccount findByUserAndAccountNumber(User user, String accountNumber);
}
