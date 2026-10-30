package com.estatebill.estate_bill_household_utility_billing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatebill.estate_bill_household_utility_billing.model.UtilityType;
import com.estatebill.estate_bill_household_utility_billing.repository.UtilityTypeRepository;


@Service
public class UtilityTypeService {
    @Autowired 
    private UtilityTypeRepository repository;

    public String save(UtilityType utilityType) {
        repository.save(utilityType);
        return  "Utility type saved successfully";

    }
    
    public List<UtilityType> getAll() {
        return repository.findAll();
    }
    
    public Optional<UtilityType> getById(UUID id) {
        return repository.findById(id);

    }
    public Optional<UtilityType> update(UUID id, UtilityType newData) {
        Optional<UtilityType> found = repository.findById(id);
        if (found.isEmpty()) {
            return found;
        }
        UtilityType existing = found.get();
        existing.setName(newData.getName());
        return Optional.of(repository.save(existing));

       }

       public String delete (UUID id) {
        if (!repository.existsById(id)) {
            return "The Utility type with that id does not exist";
        }
        repository.deleteById(id);
        return "The utility type deleted successfully";
       }

       public List<UtilityType> getUnused() {
        return repository.findUnused();

       }
    
}
