package com.estatebill.estate_bill_household_utility_billing.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReading;

public interface MeterReadingRepository extends JpaRepository<MeterReading, UUID> {

    
}