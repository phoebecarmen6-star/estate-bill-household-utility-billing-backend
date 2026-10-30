package com.estatebill.estate_bill_household_utility_billing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatebill.estate_bill_household_utility_billing.model.Block;
import com.estatebill.estate_bill_household_utility_billing.repository.BlockRepository;


@Service
public class BlockService {
    @Autowired 
    private BlockRepository repository;

    public String save(Block block) {
        repository.save(block);
        return "Block saved successfully";


    }

    public List<Block> getAll() {
        return repository.findAll();

    }

    public Optional<Block> getById(UUID id) {
        return repository.findById(id);

    }

    public Optional<Block> update(UUID id, Block newData) {
        Optional<Block> found = repository.findById(id);
        if (found.isEmpty()) {
            return found;
        }
        Block existing = found.get();
        existing.setBlockNumber(newData.getBlockNumber());
        existing.setName(newData.getName());
        return Optional.of(repository.save(existing));

    }

    public String delete(UUID id) {
        if (!repository.existsById(id)) {
            return "The block with that id does not exist";

        }
        repository.deleteById(id);
        return "Block deleted successfully";
        
    }

    public List<Block> getFree() {
        return repository.findFreeBlocks();
    }
}

