package com.estatebill.estate_bill_household_utility_billing.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estatebill.estate_bill_household_utility_billing.model.Household;
import com.estatebill.estate_bill_household_utility_billing.service.HouseholdService;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {
    @Autowired
    private HouseholdService service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Household household) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(household));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Household>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/by-owner-last-name")
    public ResponseEntity<List<Household>> getByOwnerLastName(@RequestParam String ownerLastName) {
        return ResponseEntity.ok(service.getByOwnerLastName(ownerLastName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Household> found = service.getById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(found.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The household with that id does not exist");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Household household) {
        Optional<Household> updated = service.update(id, household);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The household with that id does not exist");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = service.delete(id);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

        @GetMapping("/of-meter-reader/{meterReaderId}")
    public ResponseEntity<Object> ofMeterReader(@PathVariable UUID meterReaderId) {
        Optional<List<Household>> result = service.getOfMeterReader(meterReaderId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The meter reader with that id does not exist");
    }
}
