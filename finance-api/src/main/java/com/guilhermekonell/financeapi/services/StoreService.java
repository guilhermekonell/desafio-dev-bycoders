package com.guilhermekonell.financeapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermekonell.financeapi.models.dto.StoreTransactionDto;
import com.guilhermekonell.financeapi.models.dto.TransactionDto;
import com.guilhermekonell.financeapi.models.entity.Transaction;
import com.guilhermekonell.financeapi.repositories.TransactionRepository;

@Service
public class StoreService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<StoreTransactionDto> getStoreTransactions() {
        List<StoreTransactionDto> storeTransactionsDto = new ArrayList<>();

        List<Transaction> allTransactions = transactionRepository.findAll();
        Map<String, List<Transaction>> storeTransactions = allTransactions.stream().collect(Collectors.groupingBy(Transaction::getStoreName));

        storeTransactions.forEach((store, transactions) -> {
            StoreTransactionDto storeTransactionDto = new StoreTransactionDto();
            storeTransactionDto.setStoreName(store);
            storeTransactionDto.setTransactions(
                transactions.stream().map(t -> {
                    TransactionDto dto = modelMapper.map(t, TransactionDto.class);
                    dto.setType(t.getType().getDescription());
                    return dto;
                })
                .toList()
            );

            Double positiveAmount = transactions
                .stream()
                .filter(t -> t.getType().getSignal() == '+')
                .map(Transaction::getAmount)
                .reduce(0.0, Double::sum);

            Double negativeAmount = transactions
                .stream()
                .filter(t -> t.getType().getSignal() == '-')
                .map(Transaction::getAmount)
                .reduce(0.0, Double::sum);

            storeTransactionDto.setTotalAmount(positiveAmount - negativeAmount);
            
            storeTransactionsDto.add(storeTransactionDto);
        });

        return storeTransactionsDto;
    }
    
}
