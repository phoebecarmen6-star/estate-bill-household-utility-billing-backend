package com.estatebill.estate_bill_household_utility_billing.model;
import jakarta.persistence.*;
import java.util.UUID;


@Entity 
@Table(name = "block" )
public class Block {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int blockNumber;
    private String name;
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public int getBlockNumber() { return blockNumber; }
    public void setBlockNumber(int blockNumber) { this.blockNumber = blockNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
