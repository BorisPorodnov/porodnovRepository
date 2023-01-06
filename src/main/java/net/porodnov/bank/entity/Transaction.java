package net.porodnov.bank.entity;

import net.porodnov.bank.entity.enums.ExecutionResult;
import net.porodnov.bank.entity.enums.TransactionType;

import javax.persistence.*;
import java.time.LocalDate;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public CashOrder getCashOrder() {
        return cashOrder;
    }

    public void setCashOrder(CashOrder cashOrder) {
        this.cashOrder = cashOrder;
    }

    public CustomerAccount getCustomerAccountTransfer() {
        return customerAccountTransfer;
    }

    public void setCustomerAccountTransfer(CustomerAccount customerAccountTransfer) {
        this.customerAccountTransfer = customerAccountTransfer;
    }

    public LocalDate getTransactionCreationDate() {
        return transactionCreationDate;
    }

    public void setTransactionCreationDate(LocalDate transactionCreationDate) {
        this.transactionCreationDate = transactionCreationDate;
    }

    public Float getSumTransaction() {
        return sumTransaction;
    }

    public void setSumTransaction(Float sumTransaction) {
        this.sumTransaction = sumTransaction;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public ExecutionResult getExecutionResult() {
        return executionResult;
    }

    public void setExecutionResult(ExecutionResult executionResult) {
        this.executionResult = executionResult;
    }
}
