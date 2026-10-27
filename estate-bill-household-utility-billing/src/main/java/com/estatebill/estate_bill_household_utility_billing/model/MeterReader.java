package com.estatebill.estate_bill_household_utility_billing.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "meter_reader")
public class MeterReader {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private Block block;

    @ManyToMany
    @JoinTable(name = "meter_reader_utility_type",
            joinColumns = @JoinColumn(name = "meter_reader_id"),
            inverseJoinColumns = @JoinColumn(name = "utility_type_id"))
    private List<UtilityType> utilityTypes = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName ) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName ) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone ) { this.phone = phone; }

    public Block getBlock() { return block; }
    public void setBlock(Block block ) { this.block = block; }

    public List<UtilityType> getUtilityTypes() { return utilityTypes; }
    public void setUtilityTypes(List<UtilityType> utilityTypes) { this.utilityTypes = utilityTypes; }

}