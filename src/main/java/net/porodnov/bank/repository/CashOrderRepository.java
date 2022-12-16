package net.porodnov.bank.repository;

import net.porodnov.bank.entity.CashOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashOrderRepository extends JpaRepository<CashOrder, Long> {
    CashOrder findCashOrderById(Long id);
}
