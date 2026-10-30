package com.estatebill.estate_bill_household_utility_billing.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estatebill.estate_bill_household_utility_billing.model.Block;
import com.estatebill.estate_bill_household_utility_billing.service.BlockService;

@RestController
@RequestMapping("/api/blocks")
public class BlockController{
    @Autowired 
    private BlockService service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Block block) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(block));

    }
    @GetMapping("/all")
    public ResponseEntity<List<Block>> getAll() {
        return ResponseEntity.ok(service.getAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Block> found = service.getById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(found.get());

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The block with that id does not exist");

    }
    @PutMapping("/update/{id}") 
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Block block) {
        Optional<Block> updated = service.update(id, block);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get()); 
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The block with that id does not exist");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = service.delete(id);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/free")
    public ResponseEntity<List<Block>> free() {
        return ResponseEntity.ok(service.getFree());
    }
}
