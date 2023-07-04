package com.infy.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.BankAccountDTO;
import com.infy.dto.FundTransferDTO;
import com.infy.dto.UserLoginDTO;
import com.infy.dto.UserRegistrationDTO;
import com.infy.entity.BankAccount;
import com.infy.entity.User;
import com.infy.repository.BankAccountRepository;
import com.infy.service.BankAccountService;
import com.infy.service.UserService;

@RestController
@RequestMapping("/api")
public class MobileBankingController {
    private UserService userService;
    private BankAccountService bankAccountService;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public MobileBankingController(BankAccountRepository bankAccountRepository, UserService service,
                                   BankAccountService bankAccountService) {
        this.userService = service;
        this.bankAccountService = bankAccountService;
        this.bankAccountRepository = bankAccountRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO userDTO) {
        User user = userService.registerUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLoginDTO userDTO) {
        User user = userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/account/link")
    public ResponseEntity<BankAccount> linkAccount(@RequestBody BankAccountDTO accountDTO) {
        try {
            // Authenticate the user
            boolean isAuthenticated = authenticateUser(accountDTO.getUserLoginDTO().getUsername(), accountDTO.getUserLoginDTO().getPassword());
            if (!isAuthenticated) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // Retrieve the user
            User user = userService.getUserByUsername(accountDTO.getUserLoginDTO().getUsername());

            // Link the bank account
            BankAccount bankAccount = bankAccountService.linkAccount(user, accountDTO.getAccountNumber(), accountDTO.getAccountType());

            return ResponseEntity.status(HttpStatus.CREATED).body(bankAccount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/accounts/{userId}/{accountNumber}/balance")
    public ResponseEntity<BigDecimal> checkBalance(@PathVariable Long userId, @PathVariable String accountNumber) {
        User user = userService.getUserById(userId);
        BigDecimal balance = bankAccountService.checkBalance(user, accountNumber);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody FundTransferDTO transferDTO) {
        Long senderAccountId = transferDTO.getSenderAccountId();
        Long receiverAccountId = transferDTO.getReceiverAccountId();
        BigDecimal transferAmount = transferDTO.getTransferAmount();
        BankAccount senderAccount = bankAccountRepository.findById(senderAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Sender account not found"));
        BankAccount receiverAccount = bankAccountRepository.findById(receiverAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver account not found"));
        boolean transferSuccessful = bankAccountService.transferFunds(senderAccount, receiverAccount, transferAmount);
        if (transferSuccessful) {
            return ResponseEntity.ok("Fund transfer successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to transfer funds");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Retrieve the user based on the provided username
        User user = userService.getUserByUsername(username);

        // Check if the user exists and if the provided password matches the user's password
        if (user != null && user.getPassword().equals(password)) {
            return true; // Authentication successful
        }

        return false; // Authentication failed
    }
}
