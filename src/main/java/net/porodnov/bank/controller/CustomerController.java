package net.porodnov.bank.controller;

import net.porodnov.bank.aspect.LogExecutionTime;
import net.porodnov.bank.entity.dto.CustomerRequestDto;
import net.porodnov.bank.entity.dto.CustomerResponseDto;
import net.porodnov.bank.repository.CustomerRepository;
import net.porodnov.bank.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService,
                              CustomerRepository customerRepository
    ) {
        this.customerService = customerService;
    }

    @LogExecutionTime
    @PostMapping("/create")
    public void create(@RequestBody CustomerRequestDto dto) {
        customerService.create(dto);
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponseDto> getBy(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.search(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponseDto>> getAll() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

}
