package net.porodnov.bank.entity.dto;

import lombok.Getter;
import lombok.Setter;
import net.porodnov.bank.entity.Customer;
import net.porodnov.bank.entity.enums.AccountType;

import java.time.LocalDateTime;

@Getter
@Setter
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
}
