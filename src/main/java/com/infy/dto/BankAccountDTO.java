package com.infy.dto;

public class BankAccountDTO {
    private Long userId;
    private String accountNumber;
    private String accountType;
    private UserLoginDTO userLoginDTO;

    public BankAccountDTO() {
    }

    public UserLoginDTO getUserLoginDTO() {
        return userLoginDTO;
    }

    public void setUserLoginDTO(UserLoginDTO userLoginDTO) {
        this.userLoginDTO = userLoginDTO;
    }

    @Override
    public String toString() {
        return "BankAccountDTO{" +
                "userId=" + userId +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", userLoginDTO=" + userLoginDTO +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
