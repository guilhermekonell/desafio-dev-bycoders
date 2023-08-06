package com.guilhermekonell.financeapi.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionTypeEnum {

    DEBIT(1, "Débito", "Entrada", '+'),
    BANK_SLIP(2, "Boleto", "Saída", '-'),
    FINANCING(3, "Financiamento", "Saída", '-'),
    CREDIT(4, "Crédito", "Entrada", '+'),
    LOAN_RECEIPT(5, "Recebimento Empréstimo", "Entrada", '+'),
    SALES(6, "Vendas", "Entrada", '+'),
    TED_RECEIPT(7, "Recebimento TED", "Entrada", '+'),
    DOC_RECEIPT(8, "Recebimento DOC", "Entrada", '+'),
    RENT(9, "Aluguel", "Saída", '-');

    private final int type;
    private final String description;
    private final String nature;
    private final char signal;
    
    public static TransactionTypeEnum getByType(int type) {
        for (TransactionTypeEnum transactionType : values()) {
            if (transactionType.getType() == type) {
                return transactionType;
            }
        }
        throw new IllegalArgumentException("Invalid TransactionType type: " + type);
    }

}
