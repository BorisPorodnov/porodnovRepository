package net.porodnov.bank.dto;

import net.porodnov.bank.entity.CustomerAccount;

public class InterbankTransferDto {
    private CustomerAccount from;
    private CustomerAccount to;

    public InterbankTransferDto() {
    }

    public CustomerAccount getFrom() {
        return from;
    }

    public void setFrom(CustomerAccount from) {
        this.from = from;
    }

    public CustomerAccount getTo() {
        return to;
    }

    public void setTo(CustomerAccount to) {
        this.to = to;
    }
}
