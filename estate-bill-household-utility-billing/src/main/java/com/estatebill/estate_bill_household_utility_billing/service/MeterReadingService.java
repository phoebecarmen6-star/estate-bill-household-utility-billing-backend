package com.estatebill.estate_bill_household_utility_billing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReading;
import com.estatebill.estate_bill_household_utility_billing.repository.MeterReadingRepository;

@Service
public class MeterReadingService {
    @Autowired
    private MeterReadingRepository repository;

    public String save(MeterReading reading) {
        repository.save(reading);
        return "Meter reading saved successfully";
    }

    public List<MeterReading> getAll() {
        return repository.findAll();
    }

    public Optional<MeterReading> getById(UUID id) {
        return repository.findById(id);
    }

    public Optional<MeterReading> update(UUID id, MeterReading newData) {
        Optional<MeterReading> found = repository.findById(id);
        if (found.isEmpty()) {
            return found;
        }
        MeterReading existing = found.get();
        existing.setReadingDate(newData.getReadingDate());
        existing.setMeterType(newData.getMeterType());
        existing.setUnitsUsed(newData.getUnitsUsed());
        existing.setHousehold(newData.getHousehold());
        existing.setMeterReader(newData.getMeterReader());
        return Optional.of(repository.save(existing));
    }

    public String delete(UUID id) {
        if (!repository.existsById(id)) {
            return "The meter reading with that id does not exist";
        }
        repository.deleteById(id);
        return "Meter reading deleted successfully";
    }
}