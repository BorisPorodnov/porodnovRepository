package net.porodnov.bank.entity.dto;

import lombok.Getter;
import lombok.Setter;
import net.porodnov.bank.entity.Customer;
import net.porodnov.bank.entity.enums.AccountType;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerAccountDto {
    private Customer customer;
    private String accountNumber;
    private Float sum;
    private AccountType accountType;
    private LocalDateTime openingDate;
    private LocalDateTime validityOfAccount;

    public CustomerAccountDto(Customer customer,
                              String accountNumber,
                              Float sum, AccountType accountType,
                              LocalDateTime openingDate,
                              LocalDateTime validityOfAccount
    ) {
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.sum = sum;
        this.accountType = accountType;
        this.openingDate = openingDate;
        this.validityOfAccount = validityOfAccount;
    }
}
