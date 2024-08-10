package org.example.demo.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerNumber;
    private String customerName;
    private Double initialDeposit;
    private Integer years;
    private String savingsType;

    // Constructor with all fields
    public Customer(Long id, String customerNumber, String customerName, Double initialDeposit, Integer years, String savingsType) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.initialDeposit = initialDeposit;
        this.years = years;
        this.savingsType = savingsType;
    }

    // Default constructor (required by JPA)
    public Customer() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(Double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getSavingsType() {
        return savingsType;
    }

    public void setSavingsType(String savingsType) {
        this.savingsType = savingsType;
    }
}
