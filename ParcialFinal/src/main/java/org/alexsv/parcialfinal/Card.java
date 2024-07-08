package org.alexsv.parcialfinal;

public class Card {
    private Integer cardId;
    private String cardNumber;
    private String expirationDate;
    private String cardType;
    private String facilitator;
    private Integer clientId;

    public Card(Integer cardId, String cardNumber, String expirationDate, String cardType, String facilitator, Integer clientId) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
        this.facilitator = facilitator;
        this.clientId = clientId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getFacilitator() {
        return facilitator;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
