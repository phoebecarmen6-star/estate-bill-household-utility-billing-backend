package com.estatebill.estate_bill_household_utility_billing.repository;

import java.util.List;
import java.util.UUID;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReader;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReading;
import com.estatebill.estate_bill_household_utility_billing.model.ReadingStatus;

public interface MeterReadingRepository extends JpaRepository<MeterReading, UUID> {

    @Query("SELECT r.status, COUNT(r) FROM MeterReading r GROUP BY r.status")
    List<Object[]> countByStatusGrouped();

    List<MeterReading> findByStatusOrderByReadingDateAsc(ReadingStatus status);

    List<MeterReading> findByReadingDateBetweenOrderByReadingDateAsc(LocalDate start, LocalDate end);

    boolean existsByMeterReaderAndReadingDateAndStatusNot(MeterReader metReader, LocalDate readingDate, ReadingStatus status);
    

    @Modifying
    @Transactional 
    @Query("UPDATE MeterReading r SET r.status = :rejected WHERE r.meterReader.id = :meterReaderId AND r.readingDate = :date AND r.status <> :billed")
    int cancelDay(@Param("meterReaderId") UUID meterReaderId,
                  @Param("date") LocalDate date,
                  @Param("rejected") ReadingStatus rejected, 
                  @Param("billed") ReadingStatus billed);                                               



}