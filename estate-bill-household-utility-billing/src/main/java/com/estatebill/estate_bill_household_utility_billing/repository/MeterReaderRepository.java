package com.estatebill.estate_bill_household_utility_billing.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.estatebill.estate_bill_household_utility_billing.model.MeterReader;

public interface MeterReaderRepository extends JpaRepository<MeterReader, UUID> {

    @Query("SELECT DISTINCT m FROM MeterReader m JOIN m.utilityTypes u WHERE LOWER(u.name) = LOWER(:name)")
    List<MeterReader> findByUtilityTypeName(@Param("name") String name);

}
