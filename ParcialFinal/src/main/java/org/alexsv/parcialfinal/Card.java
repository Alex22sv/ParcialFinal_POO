package org.alexsv.parcialfinal;
public class Card { //00067323 Definition of the card class
    private Integer cardId; // 00067323 Declaring private Integer for card's ID
    private String cardNumber; // 00067323 Declaring private String for card number
    private String expirationDate; // 00067323 Declaring private String for expiration date
    private String cardType; // 00067323 Declaring private String for card type
    private String facilitator; // 00067323 Declaring private String for card facilitator
    private Integer clientId; // 00067323 Declaring private Integer for associated client ID

    public Card(Integer cardId, String cardNumber, String expirationDate, String cardType, String facilitator, Integer clientId) { // 00067323 Constructor initializing cardId, cardNumber, expirationDate, cardType, facilitator, clientId
        this.cardId = cardId; // 00067323 Initializing cardId with parameter value
        this.cardNumber = cardNumber; // 00067323 Initializing cardNumber with parameter value
        this.expirationDate = expirationDate; // 00067323 Initializing expirationDate with parameter value
        this.cardType = cardType; // 00067323 Initializing cardType with parameter value
        this.facilitator = facilitator; // 00067323 Initializing facilitator with parameter value
        this.clientId = clientId; // 00067323 Initializing clientId with parameter value
    }

    public Integer getCardId() { // 00024123 Get the card ID
        return cardId; // 00067323 Getter for card's ID
    }

    public void setCardId(Integer cardId) { // 00024123 Set the card ID
        this.cardId = cardId; // 00067323 Setter for card's ID
    }

    public String getCardNumber() { // 00024123 Get the card number
        return cardNumber; // 00067323 Getter for card number
    }

    public void setCardNumber(String cardNumber) { // 00024123 Set the card number
        this.cardNumber = cardNumber; // 00067323 Setter for card number
    }

    public String getExpirationDate() { // 00024123 Get the expiration date
        return expirationDate; // 00067323 Getter for expiration date
    }

    public void setExpirationDate(String expirationDate) { // 00024123 Set the expiration date
        this.expirationDate = expirationDate; // 00067323 Setter for expiration date
    }

    public String getCardType() { // 00024123 Get the card type
        return cardType; // 00067323 Getter for card type
    }

    public void setCardType(String cardType) { // 00024123 Set the card type
        this.cardType = cardType; // 00067323 Setter for card type
    }

    public String getFacilitator() { // 00024123 Get the facilitator
        return facilitator; // 00067323 Getter for card facilitator
    }

    public void setFacilitator(String facilitator) { // 00024123 Set the facilitator
        this.facilitator = facilitator; // 00067323 Setter for card facilitator
    }

    public Integer getClientId() { // 00024123 Get the client ID
        return clientId; // 00067323 Getter for associated client ID
    }

    public void setClientId(Integer clientId) { // 00024123 Set the client ID
        this.clientId = clientId; // 00067323 Setter for associated client ID
    }
}
