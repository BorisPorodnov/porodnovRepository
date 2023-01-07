package net.porodnov.bank.dto;

import net.porodnov.bank.entity.Customer;
import net.porodnov.bank.entity.enums.AccountType;

import java.time.LocalDateTime;

public class CustomerAccountDto {
    private Long id;
    private Customer customer;

    private String accountNumber;
    private Float sum;
    private AccountType accountType;
    private LocalDateTime openingDate;
    private LocalDateTime validityOfAccount;

    public CustomerAccountDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDateTime getValidityOfAccount() {
        return validityOfAccount;
    }

    public void setValidityOfAccount(LocalDateTime validityOfAccount) {
        this.validityOfAccount = validityOfAccount;
    }
}
