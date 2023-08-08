package com.guilhermekonell.financeapi.models.dto;

import java.util.List;

import lombok.Data;

@Data
public class StoreTransactionDto {

    String storeName;
    List<TransactionDto> transactions;
    Double totalAmount;
    
}
