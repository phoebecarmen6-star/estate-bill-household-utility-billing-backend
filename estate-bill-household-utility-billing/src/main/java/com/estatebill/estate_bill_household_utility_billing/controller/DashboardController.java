package com.estatebill.estate_bill_household_utility_billing.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estatebill.estate_bill_household_utility_billing.service.DashboardService;


@RestController 
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired 
    private DashboardService service;

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Long>> summary() {
        return ResponseEntity.ok(service.getSummary());
    }
}