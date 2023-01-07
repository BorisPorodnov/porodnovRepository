package net.porodnov.bank.dto;

import net.porodnov.bank.util.SecretWordResolver;

public class CustomerRequestDto {
    private String firstName;
    private String secondName;
    private String surName;
    private String secretWord;

    public CustomerRequestDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) throws Exception {
        SecretWordResolver secretWordResolver = new SecretWordResolver();
        this.secretWord = secretWordResolver.encrypt(secretWord);
    }
}
