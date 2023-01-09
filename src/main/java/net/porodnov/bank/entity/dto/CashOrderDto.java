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

    public CashOrderDto(OrderType orderType,
                        Float sum,
                        CustomerAccount customerAccount,
                        ExecutionResult executionResult,
                        LocalDate dateCreation
    ) {
        this.orderType = orderType;
        this.sum = sum;
        this.customerAccount = customerAccount;
        this.executionResult = executionResult;
        this.dateCreation = dateCreation;
    }
}
