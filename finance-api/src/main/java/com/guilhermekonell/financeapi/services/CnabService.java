package com.guilhermekonell.financeapi.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.guilhermekonell.financeapi.models.entity.Transaction;
import com.guilhermekonell.financeapi.models.enums.TransactionTypeEnum;
import com.guilhermekonell.financeapi.repositories.TransactionRepository;

@Service
public class CnabService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");

    @Autowired
    private TransactionRepository transactionRepository;

    public void readCnabFile(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();

            List<Transaction> transactions = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .map(line -> {
                    Transaction transaction = new Transaction();

                    transaction.setType(TransactionTypeEnum.getByType(Integer.parseInt(line.substring(0, 1))));
                    transaction.setDate(LocalDate.parse(line.substring(1, 9), DATE_FORMATTER));
                    transaction.setAmount(Integer.parseInt(line.substring(9, 19)) / 100.00);
                    transaction.setCpf(line.substring(19, 30));
                    transaction.setCard(line.substring(30, 42));
                    transaction.setTime(LocalTime.parse(line.substring(42, 48), TIME_FORMATTER).toString());
                    transaction.setStoreOwner(line.substring(48, 62).trim());
                    transaction.setStoreName(line.substring(62, 80).trim());

                    System.out.println(transaction.getDate());

                    return transaction;
                }).toList();

            transactionRepository.saveAll(transactions);
        } catch (IOException exception) {
            throw new RuntimeException();
        }
    }

}
