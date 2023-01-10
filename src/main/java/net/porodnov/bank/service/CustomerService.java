package net.porodnov.bank.service;

import net.porodnov.bank.entity.Customer;
import net.porodnov.bank.entity.CustomerAccount;
import net.porodnov.bank.entity.dto.CustomerRequestDto;
import net.porodnov.bank.entity.dto.CustomerResponseDto;
import net.porodnov.bank.entity.enums.AccountType;
import net.porodnov.bank.repository.CustomerAccountRepository;
import net.porodnov.bank.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService extends ModelMapper{
    private final CustomerRepository customerRepository;
    private final CustomerAccountRepository customerAccountRepository;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerAccountRepository customerAccountRepository) {
        this.customerRepository = customerRepository;
        this.customerAccountRepository = customerAccountRepository;
    }

    public void create(CustomerRequestDto dto) {
        Customer customer = new Customer();
        CustomerAccount account = new CustomerAccount();

        customer.setFirstName(dto.getFirstName());
        customer.setSecondName(dto.getSecondName());
        customer.setSurname(dto.getSurname());
        customer.setSecretWord(dto.getSecretWord());

        account.setAccountNumber(UUID.randomUUID().toString());
        account.setAccountType(AccountType.CURRENT_ACCOUNT);
        account.setCustomer(customer);
        account.setValidityOfAccount(LocalDateTime.now());
        account.setOpeningDate(LocalDateTime.now());
        account.setSum(200F);

        customerRepository.save(customer);
        customerAccountRepository.save(account);
    }

    public CustomerResponseDto search(Long id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer != null) {
            return map(customer, CustomerResponseDto.class);
        }
        throw new EntityNotFoundException("search: Клиент не существует под даким id: " + id);
    }

    public List<CustomerResponseDto> findAllCustomers() {
        return customerRepository.findAll().stream().map(
                it -> map(it, CustomerResponseDto.class)
        ).collect(Collectors.toList());
    }
}
