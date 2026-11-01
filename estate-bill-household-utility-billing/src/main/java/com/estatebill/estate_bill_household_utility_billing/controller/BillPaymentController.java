package com.estatebill.estate_bill_household_utility_billing.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estatebill.estate_bill_household_utility_billing.model.BillPayment;
import com.estatebill.estate_bill_household_utility_billing.service.BillPaymentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/api/bill-payments")
public class BillPaymentController {
    @Autowired 
    private BillPaymentService service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID meterReadingId, @RequestBody BillPayment billPayment) {
        String message = service.save(meterReadingId, billPayment);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.startsWith("Bill payments are only"))  {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/household/{unitCode}")
    public ResponseEntity<List<BillPayment>> byHousehold(@PathVariable String unitCode) {
        return ResponseEntity.ok(service.getByHousehold(unitCode));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = service.delete(id);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }
    
}

