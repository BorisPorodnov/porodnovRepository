package net.porodnov.bank.entity.dto;

import lombok.Getter;
import lombok.Setter;
import net.porodnov.bank.entity.CashOrder;
import net.porodnov.bank.entity.CustomerAccount;
import net.porodnov.bank.entity.enums.ExecutionResult;
import net.porodnov.bank.entity.enums.TransactionType;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionResponseDto {
    private CustomerAccount customerAccount;
    private CashOrder cashOrder;
    private CustomerAccount customerAccountTransfer;
    private LocalDate transactionCreationDate;
    private Float sumTransaction;
    private TransactionType transactionType;
    private ExecutionResult executionResult;
}
