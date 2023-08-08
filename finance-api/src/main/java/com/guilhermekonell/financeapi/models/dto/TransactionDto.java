package com.guilhermekonell.financeapi.models.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TransactionDto {

    private Long id;
    private String type;
    private LocalDate date;
    private Double amount;
    private String cpf;
    private String card;
    private String time;
    private String storeOwner;
    
}
