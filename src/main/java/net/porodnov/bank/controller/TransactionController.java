package net.porodnov.bank.controller;

import net.porodnov.bank.entity.dto.TransactionResponseDto;
import net.porodnov.bank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    ResponseEntity<List<TransactionResponseDto>> getInfoBy(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.searchTransactionBy(id));
    }
}
