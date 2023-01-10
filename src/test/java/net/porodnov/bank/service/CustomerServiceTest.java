package net.porodnov.bank.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    /*@Mock
    private final CustomerRepository customerRepository;
    @Mock
    private final CustomerAccountRepository customerAccountRepository;
    private F
    @InjectMocks
    private CustomerService underTest;


    @BeforeEach
    public void setUp() {
        underTest = new CustomerService(customerRepository, customerAccountRepository, mapper);
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
    }*/
}