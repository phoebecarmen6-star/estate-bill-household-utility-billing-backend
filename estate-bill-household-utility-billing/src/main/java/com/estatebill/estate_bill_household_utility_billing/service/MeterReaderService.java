package com.estatebill.estate_bill_household_utility_billing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReader;
import com.estatebill.estate_bill_household_utility_billing.model.UtilityType;
import com.estatebill.estate_bill_household_utility_billing.model.Block;
import com.estatebill.estate_bill_household_utility_billing.repository.BlockRepository;
import com.estatebill.estate_bill_household_utility_billing.repository.MeterReaderRepository;
import com.estatebill.estate_bill_household_utility_billing.repository.UtilityTypeRepository;

@Service
public class MeterReaderService {
    @Autowired
    private MeterReaderRepository repository;

    @Autowired 
    private UtilityTypeRepository utilityTypeRepository;

    @Autowired
    private BlockRepository blockRepository;

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

    public String addUtilityType(UUID meterReaderId, UUID utilityTypeId) {
        Optional<MeterReader> foundReader = repository.findById(meterReaderId);
        if (foundReader.isEmpty()) {
            return "The meter reader with that id does not exist";
        }
        Optional<UtilityType> foundType = utilityTypeRepository.findById(utilityTypeId);
        if (foundType.isEmpty()) {
            return "That utility type does not exist";
        }
        MeterReader reader = foundReader.get();
        UtilityType type = foundType.get();
        boolean alreadyHas = reader.getUtilityTypes().stream() 
                .anyMatch(u -> u.getId().equals(type.getId()));
                if  (alreadyHas) {
                    return "The meter reader already has that utility type";
                }
                reader.getUtilityTypes().add(type);
                repository.save(reader);
                return "Utility type added to the meter reader";

    }

    public String changeBlock(UUID meterReaderId, int BlockNumber) {
        Optional<MeterReader> readerFound = repository.findById(meterReaderId);
        if (readerFound.isEmpty()) {
            return "The meter reader with that id does not exist";
        }
        Optional<Block> blockFound = blockRepository.findByBlockNumber(BlockNumber);
        if (blockFound.isEmpty()) {
            return ("The block with that id does not exist");
        }
        MeterReader reader = readerFound.get();
        Block block = blockFound.get();
        if (repository.existsByBlock(block)) {
            return ("That block is taken by another meter reader");
        }
        reader.setBlock(block);
        repository.save(reader);
        return ("Block changed successfully");
    }
}