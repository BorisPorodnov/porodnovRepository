package net.porodnov.bank.entity;

import lombok.Getter;
import lombok.Setter;
import net.porodnov.bank.entity.enums.ExecutionResult;
import net.porodnov.bank.entity.enums.OrderType;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class CashOrder {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private Float sum;
    @ManyToOne
    @JoinColumn(name = "customerAccount_id")
    private CustomerAccount customerAccount;
    @Enumerated(EnumType.STRING)
    private ExecutionResult executionResult;
    private LocalDate dateCreation;
}
