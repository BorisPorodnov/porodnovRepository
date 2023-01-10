package net.porodnov.bank.service;

import net.porodnov.bank.entity.Customer;
import net.porodnov.bank.entity.CustomerAccount;
import net.porodnov.bank.entity.Transaction;
import net.porodnov.bank.entity.dto.CustomerAccountDto;
import net.porodnov.bank.entity.dto.InterbankTransferDto;
import net.porodnov.bank.entity.dto.TransferAccountDto;
import net.porodnov.bank.entity.enums.AccountType;
import net.porodnov.bank.entity.enums.ExecutionResult;
import net.porodnov.bank.entity.enums.TransactionType;
import net.porodnov.bank.exception.SecretWordException;
import net.porodnov.bank.repository.CustomerAccountRepository;
import net.porodnov.bank.repository.CustomerRepository;
import net.porodnov.bank.util.SecretWordResolver;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerAccountService extends ModelMapper {
    private final CustomerAccountRepository customerAccountRepository;
    private final TransactionService transactionService;
    private final CustomerRepository customerRepository;

    public CustomerAccountService(CustomerAccountRepository customerAccountRepository,
                                  TransactionService transactionService,
                                  CustomerRepository customerRepository
    ) {
        this.customerAccountRepository = customerAccountRepository;
        this.transactionService = transactionService;
        this.customerRepository = customerRepository;
    }

    public void createTransferAccount(TransferAccountDto transfer, Long id) throws SecretWordException {
        Transaction transaction = new Transaction();

        Map<String, CustomerAccount> accountMap = customerAccountRepository.findCustomerAccountByCustomerId(id)
                .stream().collect(Collectors.toMap(CustomerAccount::getAccountNumber, customerAccount -> customerAccount));

        if (transfer.getSecretWord().equals(
                SecretWordResolver.decrypt(accountMap.get(transfer.getCurrentAccountNumber())
                        .getCustomer().getSecretWord()))
        ) {
            transactionService.creteTransaction(transfer, transaction, accountMap);
        } else {
            CustomerAccount fromAccount = accountMap.get(transfer.getCurrentAccountNumber());
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setCustomerAccount(fromAccount);
            transaction.setSumTransaction(transfer.getSumTransfer());
            transaction.setExecutionResult(ExecutionResult.THE_SECRET_WORD_DOESNT_MATCH);
            transaction.setTransactionCreationDate(LocalDate.now());
            transaction.setCustomerAccountTransfer(fromAccount);
            transactionService.saveNew(transaction);
            throw new SecretWordException(ExecutionResult.THE_SECRET_WORD_DOESNT_MATCH.name());
        }
    }

    public void createTransfer(InterbankTransferDto interbankTransferDto) throws SecretWordException {
        Transaction transaction = new Transaction();

        Map<String, CustomerAccount> map = new HashMap<>();

        for (CustomerAccount customerAccount : customerAccountRepository.findAll()) {
            if (map.put(customerAccount.getAccountNumber(), customerAccount) != null) {
                throw new IllegalStateException("Duplicate key");
            }
        }

        CustomerAccount from = map.get(interbankTransferDto.getFrom().getAccountNumber());
        CustomerAccount to = map.get(interbankTransferDto.getTo().getAccountNumber());
        from.setSum(from.getSum() - interbankTransferDto.getFrom().getSum());
        to.setSum(to.getSum() + interbankTransferDto.getFrom().getSum());

        customerAccountRepository.save(from);

        if (interbankTransferDto.getFrom().getCustomer().getSecretWord().equals(
                SecretWordResolver.decrypt(map.get(interbankTransferDto.getFrom().getAccountNumber())
                        .getCustomer().getSecretWord()))
        ) {
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setCustomerAccount(from);
            transaction.setSumTransaction(interbankTransferDto.getFrom().getSum());
            transaction.setExecutionResult(ExecutionResult.SUCCESSFULLY);
            transaction.setTransactionCreationDate(LocalDate.now());
            transaction.setCustomerAccountTransfer(from);
            transaction.setExecutionResult(ExecutionResult.SUCCESSFULLY);
            transactionService.saveNew(transaction);
        } else {
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setCustomerAccount(from);
            transaction.setSumTransaction(interbankTransferDto.getFrom().getSum());
            transaction.setExecutionResult(ExecutionResult.BAD);
            transaction.setTransactionCreationDate(LocalDate.now());
            transaction.setCustomerAccountTransfer(from);
            transaction.setExecutionResult(ExecutionResult.THE_SECRET_WORD_DOESNT_MATCH);
            transactionService.saveNew(transaction);
            throw new SecretWordException(ExecutionResult.THE_SECRET_WORD_DOESNT_MATCH.name());
        }
    }

    public void createNewCustomerAccountBy(Long id) {
        Customer customerById = customerRepository.findCustomerById(id);
        if (customerById != null) {
            CustomerAccount account = new CustomerAccount();
            account.setAccountType(AccountType.SAVINGS_ACCOUNT);
            account.setCustomer(customerById);
            account.setValidityOfAccount(LocalDateTime.now());
            account.setOpeningDate(LocalDateTime.now());
            account.setAccountNumber(UUID.randomUUID().toString());
            account.setSum(Float.valueOf(String.valueOf(Math.round(Math.random() * 1000))));
            customerAccountRepository.save(account);
        } else throw new EntityNotFoundException("createNewCustomerAccountBy: Клиент не найден по id: " + id);
    }

    public List<CustomerAccountDto> searchCustomerAccountsBy(Long id) {
        return customerAccountRepository.findCustomerAccountByCustomerId(id).stream()
                .map(it -> map(it,CustomerAccountDto.class)).collect(Collectors.toList());
    }
}
