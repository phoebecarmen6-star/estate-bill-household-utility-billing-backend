package com.estatebill.estate_bill_household_utility_billing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReader;
import com.estatebill.estate_bill_household_utility_billing.repository.MeterReaderRepository;

@Service
public class MeterReaderService {
    @Autowired
    private MeterReaderRepository repository;

    public String save(MeterReader meterReader) {
        repository.save(meterReader);
        return "Meter reader saved successfully";
    }

    public List<MeterReader> getAll() {
        return repository.findAll();
    }

    public Optional<MeterReader> getById(UUID id) {
        return repository.findById(id);
    }

    public Optional<MeterReader> update(UUID id, MeterReader newData) {
        Optional<MeterReader> found = repository.findById(id);
        if (found.isEmpty()) {
            return found;
        }
        MeterReader existing = found.get();
        existing.setFirstName(newData.getFirstName());
        existing.setLastName(newData.getLastName());
        existing.setPhone(newData.getPhone());
        existing.setBlock(newData.getBlock());
        return Optional.of(repository.save(existing));
    }

    public String delete(UUID id) {
        if (!repository.existsById(id)) {
            return "The meter reader with that id does not exist";
        }
        repository.deleteById(id);
        return "Meter reader deleted successfully";
    }

    public List<MeterReader> getByUtilityType(String name) {
        return repository.findByUtilityTypeName(name);
    }
}