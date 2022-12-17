package net.porodnov.bank.controller;

import liquibase.pro.packaged.C;
import net.porodnov.bank.dto.CashOrderDto;
import net.porodnov.bank.dto.CustomerAccountDto;
import net.porodnov.bank.dto.InterbankTransferDto;
import net.porodnov.bank.dto.TransferAccountDto;
import net.porodnov.bank.entity.CashOrder;
import net.porodnov.bank.entity.CustomerAccount;
import net.porodnov.bank.exception.SecretWordException;
import net.porodnov.bank.repository.CustomerAccountRepository;
import net.porodnov.bank.service.CustomerAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class CustomerAccountController {
    private final CustomerAccountService customerAccountService;
    private final CustomerAccountRepository customerAccountRepository;

    public CustomerAccountController(CustomerAccountService customerAccountService,
                                     CustomerAccountRepository customerAccountRepository
    ) {
        this.customerAccountService = customerAccountService;
        this.customerAccountRepository = customerAccountRepository;
    }

    @GetMapping("{id}")
    ResponseEntity<List<CustomerAccount>> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(customerAccountRepository.findCustomerAccountByCustomerId(id));
    }

    @PostMapping("/create/{id}")
    void createNewAccountBy(@PathVariable Long id) {
        customerAccountService.createNewCustomerAccountBy(id);
    }

    @PostMapping("/transfer/{id}")
    ResponseEntity<String> createMoneyTransfer(@RequestBody TransferAccountDto transferAccountDto,
                                               @PathVariable Long id) {
        try {
            customerAccountService.createTransferAccount(transferAccountDto, id);
        } catch (SecretWordException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/transfer/another")
    ResponseEntity<String> createMoneyTransferMoneyToAnotherAccount(@RequestBody InterbankTransferDto interbankTransferDto) {
        try {
            customerAccountService.createTransfer(interbankTransferDto);
        } catch (SecretWordException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
