package com.mrdinner.domain.customer;

import com.mrdinner.domain.common.Address;
import java.util.Objects;
import java.util.UUID;

/**
 * Customer entity representing a customer in the system
 */
public class Customer {
    private final String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private Address deliveryAddress;
    private CustomerStatus status;

    public Customer(String name, String email, String phoneNumber, Address deliveryAddress) {
        this.customerId = UUID.randomUUID().toString();
        this.name = validateAndTrim(name, "Name");
        this.email = validateEmail(email);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.deliveryAddress = Objects.requireNonNull(deliveryAddress, "Delivery address cannot be null");
        this.status = CustomerStatus.ACTIVE;
    }

    private String validateAndTrim(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        return value.trim();
    }

    private String validateEmail(String email) {
        String trimmed = validateAndTrim(email, "Email");
        if (!trimmed.contains("@") || !trimmed.contains(".")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        return trimmed;
    }

    private String validatePhoneNumber(String phoneNumber) {
        String trimmed = validateAndTrim(phoneNumber, "Phone number");
        if (!trimmed.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Phone number must contain 10-15 digits");
        }
        return trimmed;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateAndTrim(name, "Name");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validateEmail(email);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = validatePhoneNumber(phoneNumber);
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = Objects.requireNonNull(deliveryAddress, "Delivery address cannot be null");
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = Objects.requireNonNull(status, "Status cannot be null");
    }

    public boolean isActive() {
        return status == CustomerStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = CustomerStatus.INACTIVE;
    }

    public void activate() {
        this.status = CustomerStatus.ACTIVE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    @Override
    public String toString() {
        return String.format("Customer{id='%s', name='%s', email='%s', status=%s}", 
            customerId, name, email, status);
    }

    public enum CustomerStatus {
        ACTIVE, INACTIVE, SUSPENDED
    }
}

