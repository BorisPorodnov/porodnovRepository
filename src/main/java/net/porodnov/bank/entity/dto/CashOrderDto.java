package net.porodnov.bank.entity.dto;

import lombok.Getter;
import lombok.Setter;
import net.porodnov.bank.entity.CustomerAccount;
import net.porodnov.bank.entity.enums.ExecutionResult;
import net.porodnov.bank.entity.enums.OrderType;

import java.time.LocalDate;

@Getter
@Setter
public class CashOrderDto {
    private OrderType orderType;
    private Float sum;
    private CustomerAccount customerAccount;
    private ExecutionResult executionResult;
    private LocalDate dateCreation;
}
