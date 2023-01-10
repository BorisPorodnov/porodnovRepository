package net.porodnov.bank.entity.dto;

import lombok.Getter;
import lombok.Setter;
import net.porodnov.bank.util.SecretWordResolver;

@Getter
@Setter
public class CustomerRequestDto {
    private String firstName;
    private String secondName;
    private String surname;
    private String secretWord;

    public String getSecretWord() {
        return SecretWordResolver.encrypt(secretWord);
    }
}
