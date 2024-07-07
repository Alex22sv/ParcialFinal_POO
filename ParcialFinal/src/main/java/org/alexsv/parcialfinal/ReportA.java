package org.alexsv.parcialfinal;

import java.util.Date;

public class ReportA {
    private Date purchaseDate;
    private Double totalAmount;
    private String description;
    private String cardNumber;

    public ReportA(Date purchaseDate, Double totalAmount, String description, String cardNumber) {
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
        this.description = description;
        this.cardNumber = cardNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
