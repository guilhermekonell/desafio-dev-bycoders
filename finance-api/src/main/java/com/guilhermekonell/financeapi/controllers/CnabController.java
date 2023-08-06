package com.guilhermekonell.financeapi.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.guilhermekonell.financeapi.models.Transaction;
import com.guilhermekonell.financeapi.services.CnabService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("v1/cnab")
public class CnabController {

    @Autowired
    private CnabService cnabService;

    @PostMapping("upload")
    public ResponseEntity<List<Transaction>> uploadCnabFile(@RequestParam("file") MultipartFile file) throws IOException {
        List<Transaction> transactions = cnabService.readCnabFile(file);
    
        return ResponseEntity.ok(transactions);
    }

    @GetMapping()
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.ok(cnabService.findAll());
    }
    
    
    
}
