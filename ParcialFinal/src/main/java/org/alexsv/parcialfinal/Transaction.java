package org.alexsv.parcialfinal;


public class Transaction {
    private Integer transactionId;
    private String purchaseDate;
    private String totalAmount;
    private String description;
    private Integer clientId;
    private Integer cardId;

    public Transaction(Integer transactionId, String purchaseDate, String totalAmount, String description, Integer clientId, Integer cardId) {
        this.transactionId = transactionId;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
        this.description = description;
        this.clientId = clientId;
        this.cardId = cardId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}
