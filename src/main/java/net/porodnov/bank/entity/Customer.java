package net.porodnov.bank.entity;

import lombok.Getter;
import lombok.Setter;
import net.porodnov.bank.entity.dto.CustomerResponseDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String surName;
    private String secretWord;

    public Customer() {
    }

    public CustomerResponseDto toCustomerResponseDto() {
        return new CustomerResponseDto(
                firstName, secondName, surName, secretWord
        );
    }
}