package net.porodnov.bank.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {
    private String firstName;
    private String secondName;
    private String surname;
    private String secretWord;
}
