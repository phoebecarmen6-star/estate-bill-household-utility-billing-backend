package com.estatebill.estate_bill_household_utility_billing.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.estatebill.estate_bill_household_utility_billing.model.Household;


public interface HouseholdRepository extends JpaRepository<Household, UUID> {

    List<Household> findByOwnerLastNameIgnoreCaseOrderByOwnerFirstNameAsc(String ownerLastName);

    @Query("SELECT DISTINCT r.household FROM MeterReading r WHERE r.meterReader.id = :meterReaderId")
    List<Household> findByMeterReaderId(@Param("meterReaderId") UUID meterReaderId);

    
}