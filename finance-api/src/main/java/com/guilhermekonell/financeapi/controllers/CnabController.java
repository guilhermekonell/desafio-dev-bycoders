package com.guilhermekonell.financeapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.guilhermekonell.financeapi.services.CnabService;

@RestController
@RequestMapping("v1/cnab")
public class CnabController {

    @Autowired
    private CnabService cnabService;

    @PostMapping("upload")
    public ResponseEntity<Void> uploadCnabFile(@RequestParam("file") MultipartFile file) {
        try {
            cnabService.readCnabFile(file);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    
        return ResponseEntity.ok().build();
    }
    
}
