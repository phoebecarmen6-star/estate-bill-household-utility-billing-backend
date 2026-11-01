package com.estatebill.estate_bill_household_utility_billing.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.estatebill.estate_bill_household_utility_billing.model.BillPayment;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReading;

public interface BillPaymentRepository extends JpaRepository<BillPayment, UUID> {

    @Query("SELECT p FROM BillPayment p WHERE p.meterReading.household.unitCode = :unitCode ORDER BY p.paidDate DESC")
    List<BillPayment> findByHouseholdUnitCode(@Param("unitCode") String unitCode);

    boolean existsByMeterReading(MeterReading meterReading);
    
}