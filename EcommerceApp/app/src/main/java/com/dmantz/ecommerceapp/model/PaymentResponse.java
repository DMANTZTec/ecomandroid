package com.dmantz.ecommerceapp.model;

public class PaymentResponse {


    private String fee;
    private String description;
    private String createdAt;
    private Integer amountRefunded;
    private String bank;
    private String contact;
    private String currency;
    private String paymentId;
    private String email;
    private Integer amount;
    private String orderId;
    private String entity;
    private String status;

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(Integer amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "PaymentResponse{" +
                "fee=" + fee +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", amountRefunded=" + amountRefunded +
                ", bank=" + bank +
                ", contact='" + contact + '\'' +
                ", currency='" + currency + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", email='" + email + '\'' +
                ", amount=" + amount +
                ", orderId=" + orderId +
                ", entity='" + entity + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
