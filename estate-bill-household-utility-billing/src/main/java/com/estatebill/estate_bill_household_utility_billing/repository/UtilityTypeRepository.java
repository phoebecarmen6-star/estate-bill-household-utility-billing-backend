package com.estatebill.estate_bill_household_utility_billing.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.estatebill.estate_bill_household_utility_billing.model.UtilityType;
public interface UtilityTypeRepository extends JpaRepository<UtilityType, UUID> {
    @Query("SELECT u FROM UtilityType u WHERE u.meterReaders IS EMPTY")
    List<UtilityType> findUnused();
}
