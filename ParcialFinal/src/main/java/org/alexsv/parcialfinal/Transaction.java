package org.alexsv.parcialfinal;
public class Transaction { // 00067323 Definition of the Transaction class
    private Integer transactionId; // 00067323 Declaring private Integer for transaction's ID
    private String purchaseDate; // 00067323 Declaring private String for purchase date
    private String totalAmount; // 00067323 Declaring private String for total amount
    private String description; // 00067323 Declaring private String for description
    private Integer clientId; // 00067323 Declaring private Integer for associated client ID
    private Integer cardId; // 00067323 Declaring private Integer for associated card ID

    public Transaction(Integer transactionId, String purchaseDate, String totalAmount, String description, Integer clientId, Integer cardId) {         // 00067323 Constructor initializing transactionId, purchaseDate, totalAmount, description, clientId, cardId
        this.transactionId = transactionId; // 00067323 Initializing transactionId with parameter value
        this.purchaseDate = purchaseDate; // 00067323 Initializing purchaseDate with parameter value
        this.totalAmount = totalAmount; // 00067323 Initializing totalAmount with parameter value
        this.description = description; // 00067323 Initializing description with parameter value
        this.clientId = clientId; // 00067323 Initializing clientId with parameter value
        this.cardId = cardId; // 00067323 Initializing cardId with parameter value
    }

    public Integer getTransactionId() { // 00024123 Get the transaction ID
        return transactionId; // 00067323 Getter for transaction's ID
    }

    public void setTransactionId(Integer transactionId) { // 00024123 Set the transaction ID
        this.transactionId = transactionId; // 00067323 Setter for transaction's ID
    }

    public String getPurchaseDate() { // 00024123 Get the purchase date
        return purchaseDate; // 00067323 Getter for purchase date
    }

    public void setPurchaseDate(String purchaseDate) { // 00024123 Set the purchase date
        this.purchaseDate = purchaseDate; // 00067323 Setter for purchase date
    }

    public String getTotalAmount() { // 00024123 Get the total amount
        return totalAmount; // 00067323 Getter for total amount
    }

    public void setTotalAmount(String totalAmount) { // 00024123 Set the total amount
        this.totalAmount = totalAmount; // 00067323 Setter for total amount
    }

    public String getDescription() { // 00024123 Get the description
        return description; // 00067323 Getter for description
    }

    public void setDescription(String description) { // 00024123 Set the description
        this.description = description; // 00067323 Setter for description
    }

    public Integer getClientId() { // 00024123 Get the client ID
        return clientId; // 00067323 Getter for associated client ID
    }

    public void setClientId(Integer clientId) { // 00024123 Set the client ID
        this.clientId = clientId; // 00067323 Setter for associated client ID
    }

    public Integer getCardId() { // 00024123 Get the card ID
        return cardId; // 00067323 Getter for associated card ID
    }

    public void setCardId(Integer cardId) { // 00024123 Set the card ID
        this.cardId = cardId; // 00067323 Setter for associated card ID
    }
}
