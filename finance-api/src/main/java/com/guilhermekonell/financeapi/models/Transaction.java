package com.guilhermekonell.financeapi.models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.guilhermekonell.financeapi.models.enums.TransactionTypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TransactionTypeEnum type;

    private LocalDate date;

    private Double amount;

    private String cpf;

    private String card;

    private LocalTime time;

    private String storeOwner;

    private String storeName;
    
}
