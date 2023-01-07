package net.porodnov.bank.service;

import net.porodnov.bank.entity.dto.CustomerRequestDto;
import net.porodnov.bank.entity.Customer;
import net.porodnov.bank.entity.CustomerAccount;
import net.porodnov.bank.entity.enums.AccountType;
import net.porodnov.bank.repository.CustomerAccountRepository;
import net.porodnov.bank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerAccountRepository customerAccountRepository;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerAccountRepository customerAccountRepository
    ) {
        this.customerRepository = customerRepository;
        this.customerAccountRepository = customerAccountRepository;
    }

    public void create(CustomerRequestDto dto) {
        Customer customer = new Customer();
        CustomerAccount account = new CustomerAccount();

        customer.setFirstName(dto.getFirstName());
        customer.setSecondName(dto.getSecondName());
        customer.setSurName(dto.getSurName());
        customer.setSecretWord(dto.getSecretWord());
        account.setAccountNumber(String.valueOf(Math.round(Math.random() * 2000000000)));
        account.setAccountType(AccountType.CURRENT_ACCOUNT);
        account.setCustomer(customer);
        account.setValidityOfAccount(LocalDateTime.now());
        account.setOpeningDate(LocalDateTime.now());
        account.setSum(200F);
        customerRepository.save(customer);
        customerAccountRepository.save(account);
    }

    public Customer search(Long id) {
        Customer customerById1 = customerRepository.findCustomerById(id);
        if (customerById1 != null) {
            return customerById1;
        }
        throw new EntityNotFoundException("Клиент не существует под даким id: " + id);
    }
}
