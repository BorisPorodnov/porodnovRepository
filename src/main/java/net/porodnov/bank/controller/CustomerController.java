package net.porodnov.bank.controller;

import net.porodnov.bank.dto.CustomerRequestDto;
import net.porodnov.bank.entity.Customer;
import net.porodnov.bank.repository.CustomerRepository;
import net.porodnov.bank.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerService customerService,
                              CustomerRepository customerRepository
    ) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/create")
    public void create(@RequestBody CustomerRequestDto dto) {
        customerService.create(dto);
    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> getBy(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.search(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

}
