package net.porodnov.bank.service;

import net.porodnov.bank.dto.TransferAccountDto;
import net.porodnov.bank.entity.CustomerAccount;
import net.porodnov.bank.entity.Transaction;
import net.porodnov.bank.entity.enums.ExecutionResult;
import net.porodnov.bank.entity.enums.TransactionType;
import net.porodnov.bank.repository.CustomerAccountRepository;
import net.porodnov.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public void creteTransaction(TransferAccountDto transfer, Transaction createTransaction, Map<String, CustomerAccount> accountMap) {
        CustomerAccount fromAccount = accountMap.get(transfer.getCurrentAccountNumber());
        CustomerAccount toAccount = accountMap.get(transfer.getSavingAccountNumber());
        fromAccount.setSum(fromAccount.getSum() - transfer.getSumTransfer());
        toAccount.setSum(toAccount.getSum() + transfer.getSumTransfer());
        customerAccountRepository.save(fromAccount);
        createTransaction.setTransactionType(TransactionType.TRANSFER);
        createTransaction.setCustomerAccount(fromAccount);
        createTransaction.setSumTransaction(transfer.getSumTransfer());
        createTransaction.setExecutionResult(ExecutionResult.SUCCESSFULLY);
        createTransaction.setTransactionCreationDate(LocalDate.now());
        createTransaction.setCustomerAccountTransfer(fromAccount);
        transactionRepository.save(createTransaction);
    }

    public void saveNew(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
