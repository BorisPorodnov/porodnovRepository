package net.porodnov.bank.entity;

import lombok.Getter;
import lombok.Setter;
import net.porodnov.bank.entity.enums.ExecutionResult;
import net.porodnov.bank.entity.enums.TransactionType;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "customerAccount_id")
    private CustomerAccount customerAccount;

    @ManyToOne()
    @JoinColumn(name = "cashOrder_id")
    private CashOrder cashOrder; //(заполняется в случае Типа пополнение, снятие)

    @ManyToOne()
    @JoinColumn(name = "customerAccount_id", insertable = false, updatable = false)
    private CustomerAccount customerAccountTransfer; // (заполняется в случае Типа перевод)

    private LocalDate transactionCreationDate;
    private Float sumTransaction;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private ExecutionResult executionResult;

    public Transaction() {
    }
}
