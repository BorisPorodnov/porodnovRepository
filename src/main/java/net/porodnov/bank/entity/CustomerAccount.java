package net.porodnov.bank.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.porodnov.bank.entity.enums.AccountType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class CustomerAccount {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String accountNumber;
    private Float sum;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private LocalDateTime openingDate;
    private LocalDateTime validityOfAccount;

    public CustomerAccount() {
    }
}
