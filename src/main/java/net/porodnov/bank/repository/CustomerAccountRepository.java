package net.porodnov.bank.repository;

import net.porodnov.bank.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    List<CustomerAccount> findCustomerAccountByCustomerId(Long id);
    CustomerAccount findCustomerAccountById(Long id);
}
