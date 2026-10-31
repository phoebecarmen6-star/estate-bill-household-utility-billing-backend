package com.estatebill.estate_bill_household_utility_billing.repository;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReader;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReading;
import com.estatebill.estate_bill_household_utility_billing.model.ReadingStatus;

public interface MeterReadingRepository extends JpaRepository<MeterReading, UUID> {
    List<MeterReading> findByStatusOrderByReadingDateAsc(ReadingStatus status);

    List<MeterReading> findByReadingDateBetweenOrderByReadingDateAsc(LocalDate start, LocalDate end);

    boolean existsByMeterReaderAndReadingDateAndStatusNot(MeterReader metReader, LocalDate readingDate, ReadingStatus status);
    
}