package com.estatebill.estate_bill_household_utility_billing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatebill.estate_bill_household_utility_billing.model.Household;
import com.estatebill.estate_bill_household_utility_billing.repository.HouseholdRepository;


@Service 
public class HouseholdService {
    @Autowired 
    private HouseholdRepository repository;

    public String save(Household household) {
            repository.save(household);
            return "Household saved successfully";
        }

        public List<Household> getAll() {
            return repository.findAll();
        }

    public Optional<Household> getById(UUID id) {
        return repository.findById(id);

    }
public Optional<Household> update(UUID id, Household newData) {
        Optional<Household> found = repository.findById(id);
        if (found.isEmpty()) {
            return found;
        }
        Household existing = found.get();
        existing.setOwnerFirstName(newData.getOwnerFirstName());
        existing.setOwnerLastName(newData.getOwnerLastName());
        existing.setUnitCode(newData.getUnitCode());
        existing.setPhone(newData.getPhone());
        return Optional.of(repository.save(existing));
    }

    public String delete(UUID id) {
        if (!repository.existsById(id)) {
            return "The household with that id does not exist";
        }
        repository.deleteById(id);
        return "Household deleted successfully";
    }

    public List<Household> getByOwnerLastName(String ownerLastName) {
        return  repository.findByOwnerLastNameIgnoreCaseOrderByOwnerFirstNameAsc(ownerLastName);
    }

    }

    
