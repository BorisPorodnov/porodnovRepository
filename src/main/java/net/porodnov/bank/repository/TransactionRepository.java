package net.porodnov.bank.repository;

import net.porodnov.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByCustomerAccountId(Long id);
}
