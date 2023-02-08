package net.porodnov.bank.service;

import net.porodnov.bank.entity.CashOrder;
import net.porodnov.bank.entity.Customer;
import net.porodnov.bank.entity.CustomerAccount;
import net.porodnov.bank.entity.Transaction;
import net.porodnov.bank.entity.dto.CashOrderDto;
import net.porodnov.bank.entity.enums.ExecutionResult;
import net.porodnov.bank.entity.enums.OrderType;
import net.porodnov.bank.entity.enums.TransactionType;
import net.porodnov.bank.exception.SecretWordException;
import net.porodnov.bank.repository.CashOrderRepository;
import net.porodnov.bank.repository.CustomerAccountRepository;
import net.porodnov.bank.repository.CustomerRepository;
import net.porodnov.bank.repository.TransactionRepository;
import net.porodnov.bank.util.SecretWordResolver;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashOrderService extends ModelMapper {
    private final CustomerAccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CashOrderRepository cashOrderRepository;
    private final CustomerRepository customerRepository;

    public CashOrderService(
            CustomerAccountRepository accountRepository,
            TransactionRepository transactionRepository,
            CashOrderRepository cashOrderRepository,
            CustomerRepository customerRepository
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.cashOrderRepository = cashOrderRepository;
        this.customerRepository = customerRepository;
    }

    public void createOrderCash(CashOrderDto orderDto) throws SecretWordException {
        Transaction transaction = new Transaction();
        CashOrder order = new CashOrder();

        Customer customer = customerRepository.findCustomerById(orderDto.getCustomerAccount().getCustomer().getId());
        if (customer == null){
            throw new EntityNotFoundException();
        }
        CustomerAccount account = accountRepository.getReferenceById(customer.getId());

        if (orderDto.getOrderType() == OrderType.WITHDRAWAL) { //снятие
            if (orderDto.getCustomerAccount().getCustomer().getSecretWord()
                    .equals(SecretWordResolver.decrypt(customer.getSecretWord()))
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

    public List<CashOrderDto> getInfoBy(Long id) {
        return cashOrderRepository.findCashOrderByCustomerAccountId(id)
                .stream()
                .map(it -> map(it, CashOrderDto.class))
                .collect(Collectors.toList());
    }
}