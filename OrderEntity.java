
package com.artforyou.store.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String mobile;
   

    @Column(columnDefinition = "text")
    private String address;

    private String city;
    private String state;
    private String pincode;

    @Column(columnDefinition = "text")
    private String items;

    private Integer totalAmount;
    private Integer advanceAmount;

    private String status = "PENDING";
    private String trackingId;
    private String paymentTransactionId;

    private String paymentScreenshotPath;
    @Column(columnDefinition = "text")
    private String preparedPhotos;
    private String paymentStatus = "NOT_SUBMITTED";
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getMobile() { return mobile; }
   
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getPincode() { return pincode; }
    public String getItems() { return items; }
    public Integer getTotalAmount() { return totalAmount; }
    public Integer getAdvanceAmount() { return advanceAmount; }
    public String getStatus() { return status; }
    public String getTrackingId() {
        return trackingId;
    }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public String getPaymentScreenshotPath() {
        return paymentScreenshotPath;
    }
    public String getPreparedPhotos() {
        return preparedPhotos;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentTransactionId(String v) {
        paymentTransactionId = v;
    }

    public void setPaymentScreenshotPath(String v) {
        paymentScreenshotPath = v;
    }

    public void setPaymentStatus(String v) {
        paymentStatus = v;
    }

    public void setCustomerName(String v) { customerName = v; }
    public void setMobile(String v) { mobile = v; }
   
    public void setAddress(String v) { address = v; }
    public void setCity(String v) { city = v; }
    public void setState(String v) { state = v; }
    public void setPincode(String v) { pincode = v; }
    public void setItems(String v) { items = v; }
    public void setTotalAmount(Integer v) { totalAmount = v; }
    public void setAdvanceAmount(Integer v) { advanceAmount = v; }
    public void setTrackingId(String v) {
        trackingId = v;
    }
    public void setStatus(String v) { status = v; }
    public void setPreparedPhotos(String v) {
        preparedPhotos = v;
    }
}
