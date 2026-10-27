package com.estatebill.estate_bill_household_utility_billing.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "utility_type")
public class UtilityType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany(mappedBy = "utilityTypes")
    @JsonIgnore
    private List<MeterReader> meterReaders;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name ) { this.name = name; }

    public List <MeterReader> getMeterReaders() { return meterReaders; }
    public void setMeterReaders(List<MeterReader> meterReaders) {this.meterReaders = meterReaders; }
    
}