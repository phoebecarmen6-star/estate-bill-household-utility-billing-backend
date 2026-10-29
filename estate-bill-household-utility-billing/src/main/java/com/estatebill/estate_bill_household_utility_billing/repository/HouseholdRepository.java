package com.estatebill.estate_bill_household_utility_billing.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.estatebill.estate_bill_household_utility_billing.model.Household;

public interface HouseholdRepository extends JpaRepository<Household, UUID> {

    List<Household> findByOwnerLastNameIgnoreCaseOrderByOwnerFirstNameAsc(String ownerLastName);
}