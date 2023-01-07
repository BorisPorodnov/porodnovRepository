package net.porodnov.bank.dto;

public class TransferAccountDto {
    private String savingAccountNumber;
    private String currentAccountNumber;
    private Float sumTransfer;

    private String secretWord;

    public TransferAccountDto() {
    }

    public String getSavingAccountNumber() {
        return savingAccountNumber;
    }

    public void setSavingAccountNumber(String savingAccountNumber) {
        this.savingAccountNumber = savingAccountNumber;
    }

    public String getCurrentAccountNumber() {
        return currentAccountNumber;
    }

    public void setCurrentAccountNumber(String currentAccountNumber) {
        this.currentAccountNumber = currentAccountNumber;
    }

    public Float getSumTransfer() {
        return sumTransfer;
    }

    public void setSumTransfer(Float sumTransfer) {
        this.sumTransfer = sumTransfer;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }
}
