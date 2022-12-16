package net.porodnov.bank.controller;

import net.porodnov.bank.entity.Transaction;
import net.porodnov.bank.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/{id}")
    ResponseEntity<List<Transaction>> getInfoBy(@PathVariable Long id) {
        return ResponseEntity.ok(transactionRepository.findTransactionByCustomerAccountId(id));
    }
}
