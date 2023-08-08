package com.guilhermekonell.financeapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guilhermekonell.financeapi.models.dto.StoreTransactionDto;
import com.guilhermekonell.financeapi.services.StoreService;

@Controller
@RequestMapping("v1/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("transactions")
    public ResponseEntity<List<StoreTransactionDto>> getTransactions() {
        return ResponseEntity.ok(storeService.getStoreTransactions());
    }
    
}
