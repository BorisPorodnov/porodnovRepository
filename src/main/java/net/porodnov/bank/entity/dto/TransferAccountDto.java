package net.porodnov.bank.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferAccountDto {
    private String savingAccountNumber;
    private String currentAccountNumber;
    private Float sumTransfer;

    private String secretWord;

    public TransferAccountDto() {
    }
}
