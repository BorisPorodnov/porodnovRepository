package net.porodnov.bank.service;

import net.porodnov.bank.entity.dto.CashOrderDto;
import net.porodnov.bank.entity.CashOrder;
import net.porodnov.bank.entity.CustomerAccount;
import net.porodnov.bank.entity.Transaction;
import net.porodnov.bank.entity.enums.ExecutionResult;
import net.porodnov.bank.entity.enums.OrderType;
import net.porodnov.bank.entity.enums.TransactionType;
import net.porodnov.bank.exception.SecretWordException;
import net.porodnov.bank.repository.CashOrderRepository;
import net.porodnov.bank.repository.CustomerAccountRepository;
import net.porodnov.bank.repository.TransactionRepository;
import net.porodnov.bank.util.SecretWordResolver;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class CashOrderService {
    private final CustomerAccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CashOrderRepository cashOrderRepository;

    public CashOrderService(
            CustomerAccountRepository accountRepository,
            TransactionRepository transactionRepository,
            CashOrderRepository cashOrderRepository
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.cashOrderRepository = cashOrderRepository;
    }

    public void createOrderCash(CashOrderDto orderDto) throws Exception {
        Transaction transaction = new Transaction();
        CashOrder order = new CashOrder();

        List<CustomerAccount> all = accountRepository.findAll();

        for (CustomerAccount it : all) {
            if (orderDto.getCustomerAccount()
                    .getAccountNumber()
                    .equals(it.getAccountNumber())
            ) {
                CustomerAccount account = accountRepository.findCustomerAccountById(it.getId());
                if (orderDto.getOrderType() == OrderType.WITHDRAWAL) {
                    if (orderDto.getCustomerAccount().getCustomer().getSecretWord()
                            .equals(
                                    SecretWordResolver.decrypt(account.getCustomer().getSecretWord())
                            )
                    ) {
                        account.setSum(account.getSum() - orderDto.getSum());
                        accountRepository.save(account);
                        order.setOrderType(orderDto.getOrderType());
                        order.setDateCreation(LocalDate.now());
                        order.setSum(orderDto.getSum());
                        order.setCustomerAccount(account);
                        order.setExecutionResult(ExecutionResult.SUCCESSFULLY);
                        cashOrderRepository.save(order);
                        transaction.setSumTransaction(account.getSum());
                        transaction.setTransactionCreationDate(LocalDate.now());
                        transaction.setCashOrder(order);
                        transaction.setCustomerAccount(account);
                        transaction.setTransactionType(TransactionType.WITHDRAWAL);
                        transaction.setExecutionResult(ExecutionResult.SUCCESSFULLY);
                        transactionRepository.save(transaction);
                    } else {
                        account.setSum(account.getSum() - orderDto.getSum());
                        accountRepository.save(account);
                        order.setOrderType(orderDto.getOrderType());
                        order.setDateCreation(LocalDate.now());
                        order.setSum(orderDto.getSum());
                        order.setCustomerAccount(account);
                        order.setExecutionResult(ExecutionResult.BAD);
                        cashOrderRepository.save(order);
                        transaction.setSumTransaction(account.getSum());
                        transaction.setTransactionCreationDate(LocalDate.now());
                        transaction.setCashOrder(order);
                        transaction.setCustomerAccount(account);
                        transaction.setTransactionType(TransactionType.WITHDRAWAL);
                        transaction.setExecutionResult(ExecutionResult.THE_SECRET_WORD_DOESNT_MATCH);
                        transactionRepository.save(transaction);
                        throw new SecretWordException(ExecutionResult.THE_SECRET_WORD_DOESNT_MATCH.name());
                    }
                    // Пополнение
                } else if (orderDto.getOrderType() == OrderType.REPLENISHMENT) {
                    account.setSum(account.getSum() + orderDto.getSum());
                    accountRepository.save(account);
                    order.setOrderType(orderDto.getOrderType());
                    order.setDateCreation(LocalDate.now());
                    order.setSum(orderDto.getSum());
                    order.setCustomerAccount(account);
                    order.setExecutionResult(ExecutionResult.SUCCESSFULLY);
                    cashOrderRepository.save(order);
                    transaction.setSumTransaction(account.getSum());
                    transaction.setTransactionCreationDate(LocalDate.now());
                    transaction.setCashOrder(order);
                    transaction.setCustomerAccount(account);
                    transaction.setTransactionType(TransactionType.REPLENISHMENT);
                    transaction.setExecutionResult(ExecutionResult.SUCCESSFULLY);
                    transactionRepository.save(transaction);
                }
            }
        }
    }

    public List<CashOrder> getInfoBy(Long id) {
        List<CashOrder> all = cashOrderRepository.findCashOrderByCustomerAccountId(id);
        if (all.isEmpty()) {
            throw new EntityNotFoundException("Кассовый ордер не существует");
        }
        return all;
    }

}
