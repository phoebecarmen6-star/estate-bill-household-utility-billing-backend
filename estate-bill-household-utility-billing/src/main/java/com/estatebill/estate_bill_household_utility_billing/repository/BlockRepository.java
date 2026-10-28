package com.estatebill.estate_bill_household_utility_billing.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.estatebill.estate_bill_household_utility_billing.model.Block;

public interface BlockRepository extends JpaRepository<Block, UUID> {
    
}