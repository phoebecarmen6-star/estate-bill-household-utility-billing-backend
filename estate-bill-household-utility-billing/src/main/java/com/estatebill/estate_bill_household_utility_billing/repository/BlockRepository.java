package com.estatebill.estate_bill_household_utility_billing.repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.estatebill.estate_bill_household_utility_billing.model.Block;


public interface BlockRepository extends JpaRepository<Block, UUID> {
    @Query("SELECT b FROM Block b WHERE NOT EXISTS (SELECT m FROM MeterReader m WHERE m.block = b) ORDER BY b.blockNumber")
    List<Block> findFreeBlocks();

    Optional<Block> findByBlockNumber(int blockNumber);
    
}