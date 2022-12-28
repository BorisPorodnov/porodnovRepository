package net.porodnov.bank.entity;

import net.porodnov.bank.enums.AccountType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class CustomerAccount {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String accountNumber;
    private Float sum;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private LocalDateTime openingDate;
    private LocalDateTime validityOfAccount;

    public CustomerAccount() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerAccount)) return false;
        CustomerAccount account = (CustomerAccount) o;
        return Objects.equals(getId(), account.getId()) && Objects.equals(getCustomer(), account.getCustomer()) && Objects.equals(getAccountNumber(), account.getAccountNumber()) && Objects.equals(getSum(), account.getSum()) && getAccountType() == account.getAccountType() && Objects.equals(getOpeningDate(), account.getOpeningDate()) && Objects.equals(getValidityOfAccount(), account.getValidityOfAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getAccountNumber(), getSum(), getAccountType(), getOpeningDate(), getValidityOfAccount());
    }
}
