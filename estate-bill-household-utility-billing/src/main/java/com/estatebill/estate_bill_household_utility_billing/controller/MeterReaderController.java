package com.estatebill.estate_bill_household_utility_billing.controller;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReader;
import com.estatebill.estate_bill_household_utility_billing.service.MeterReaderService;

@RestController
@RequestMapping("/api/meter-readers")
public class MeterReaderController {
    @Autowired
    private MeterReaderService service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MeterReader meterReader) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(meterReader));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MeterReader>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<MeterReader> found = service.getById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(found.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The meter reader with that id does not exist");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody MeterReader meterReader) {
        Optional<MeterReader> updated = service.update(id, meterReader);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The meter reader with that id does not exist");
    }

    @PutMapping("/{meterReaderId}/utility-types/{utilityTypeId}")
    public ResponseEntity<String> addUtilityType(@PathVariable UUID meterReaderId, @PathVariable UUID utilityTypeId) {
        String message = service.addUtilityType(meterReaderId, utilityTypeId);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }
        if (message.contains("already has")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = service.delete(id);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/by-utility-type")
    public ResponseEntity<List<MeterReader>> byUtilityType(@RequestParam String name) {
        return ResponseEntity.ok(service.getByUtilityType(name));
    }
}