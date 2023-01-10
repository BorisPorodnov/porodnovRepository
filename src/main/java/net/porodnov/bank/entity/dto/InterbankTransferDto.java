package net.porodnov.bank.entity.dto;

import lombok.Getter;
import lombok.Setter;
import net.porodnov.bank.entity.CustomerAccount;

@Getter
@Setter
public class InterbankTransferDto {
    private CustomerAccount from;
    private CustomerAccount to;
}
