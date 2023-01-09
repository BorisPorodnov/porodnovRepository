package net.porodnov.bank.service;

import lombok.RequiredArgsConstructor;
import net.porodnov.bank.entity.Customer;
import net.porodnov.bank.entity.dto.CustomerRequestDto;
import net.porodnov.bank.repository.CustomerAccountRepository;
import net.porodnov.bank.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private final CustomerRepository customerRepository;
    @Mock
    private final CustomerAccountRepository customerAccountRepository;
    @InjectMocks
    private CustomerService underTest;


    @BeforeEach
    public void setUp() {
        underTest = new CustomerService(customerRepository, customerAccountRepository);
    }

    @Test
    void saveCustomerDetailsTest() {
        // Given
        Customer customerDto = new Customer();
        customerDto.setFirstName("Harry");
        customerDto.setSecondName("Potter");
        customerDto.setSurName("James");
        customerDto.setSecretWord("code");

        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());
        // When
        underTest.create(new CustomerRequestDto());
        // Then
    }
}