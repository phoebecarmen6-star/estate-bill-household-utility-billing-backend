package com.estatebill.estate_bill_household_utility_billing.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReading;
import com.estatebill.estate_bill_household_utility_billing.model.ReadingStatus;
import com.estatebill.estate_bill_household_utility_billing.service.MeterReadingService;

@RestController
@RequestMapping("/api/meter-readings")
public class MeterReadingController {
    @Autowired
    private MeterReadingService service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MeterReading meterReading) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(meterReading));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MeterReading>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<MeterReading>> getByStatus(@RequestParam ReadingStatus status) {
        return ResponseEntity.ok(service.getByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<MeterReading> found = service.getById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(found.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The meter reading with that id does not exist");
    }

    @GetMapping("/between")
    public ResponseEntity<List<MeterReading>> getBetween(@RequestParam String start, @RequestParam String end) {
         LocalDate startDate = LocalDate.parse(start);
         LocalDate endDate = LocalDate.parse(end);
         return ResponseEntity.ok(service.getBetween(startDate, endDate));
    }
         

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody MeterReading meterReading) {
        Optional<MeterReading> updated = service.update(id, meterReading);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The meter reading with that id does not exist");
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
