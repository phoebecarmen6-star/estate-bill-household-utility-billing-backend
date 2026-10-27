package com.estatebill.estate_bill_household_utility_billing.model;
import jakarta.persistence.*;
import java.util.UUID;

@Entity 
@Table(name ="household" )
 public class Household {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String ownerFirstName;
    private String ownerLastName;
    @Column(unique = true)
    private String unitCode;
    private String phone;

public UUID getID() { return id; }
public void setID(UUID id) { this.id = id; }

public String getOwnerFirstName() { return ownerFirstName; }
public void setOwnerFirstName(String ownerFirstName) { this.ownerFirstName = ownerFirstName; }

public String getOwnerLastName() { return ownerLastName; }
public void setOwnerLastName(String ownerLastname) { this.ownerLastName = ownerLastname; }

public String getUnitCode() { return unitCode; }
public void setUnitCode(String unitCode) {this.unitCode = unitCode; }

public String getPhone() { return phone; }
public void setPhone(String phone) {this. phone = phone; }

 }

