package net.porodnov.bank.controller;

import net.porodnov.bank.entity.dto.CustomerAccountDto;
import net.porodnov.bank.entity.dto.InterbankTransferDto;
import net.porodnov.bank.entity.dto.TransferAccountDto;
import net.porodnov.bank.exception.SecretWordException;
import net.porodnov.bank.service.CustomerAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class CustomerAccountController {
    private final CustomerAccountService customerAccountService;
    public CustomerAccountController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    @GetMapping("{id}")
    ResponseEntity<List<CustomerAccountDto>> searchAccountsById(@PathVariable Long id) {
        return ResponseEntity.ok(customerAccountService.searchCustomerAccountsBy(id));
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
