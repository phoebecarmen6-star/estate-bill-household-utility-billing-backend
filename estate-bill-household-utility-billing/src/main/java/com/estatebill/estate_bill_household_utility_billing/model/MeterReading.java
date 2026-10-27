package com.estatebill.estate_bill_household_utility_billing.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "meter_reading")
public class MeterReading {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate readingDate;

    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

    private String meterType;
    private int unitsUsed;

    @ManyToOne
    @JoinColumn(name = "household_id")
    private Household household;

    @ManyToOne
    @JoinColumn(name = "meter_reader_id")
    private MeterReader meterReader;

    public UUID getid() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDate getReadingDate() { return readingDate; }
    public void setReadingDate(LocalDate readingDate) { this. readingDate = readingDate; }

    public ReadingStatus getStatus() { return status; }
    public void setStatus(ReadingStatus status) { this.status = status; }

    public String  getMeterType() { return meterType; }
    public void setMeterType(String meterType) { this. meterType = meterType; }

    public int getUnitsUsed() { return unitsUsed; }
    public void setUnitsUsed(int unitsUsed) { this.unitsUsed = unitsUsed; }

    public Household getHousehold() { return household; }
    public void setHousehold(Household household) { this.household = household; }

    public MeterReader getMeterReader() { return meterReader; }
    public void setMeterReader(MeterReader meterReader) { this.meterReader = meterReader; }
    
    
}