package org.alexsv.parcialfinal.Classes;
import java.util.Date;

public class ReportA { //00016023 - Defines the public class ReportA
    private Date purchaseDate; //00016023 - Declares a private variable of type Date for the purchase date
    private Double totalAmount; //00016023 - Declares a private variable of type Double for the total amount
    private String description; //00016023 - Declares a private variable of type String for the description
    private String cardNumber; //00016023 - Declares a private variable of type String for the card number

    public ReportA(Date purchaseDate, Double totalAmount, String description, String cardNumber) { //00016023 - Constructor of the ReportA class
        this.purchaseDate = purchaseDate; //00016023 - Assigns the value of purchaseDate to the instance variable
        this.totalAmount = totalAmount; //00016023 - Assigns the value of totalAmount to the instance variable
        this.description = description; //00016023 - Assigns the value of description to the instance variable
        this.cardNumber = cardNumber; //00016023 - Assigns the value of cardNumber to the instance variable
    }

    public Date getPurchaseDate() { //00016023 - Getter method for purchaseDate
        return purchaseDate; //00016023 - Returns the value of purchaseDate
    }

    public void setPurchaseDate(Date purchaseDate) { //00016023 - Setter method for purchaseDate
        this.purchaseDate = purchaseDate; //00016023 - Sets the value of purchaseDate
    }

    public Double getTotalAmount() { //00016023 - Getter method for totalAmount
        return totalAmount; //00016023 - Returns the value of totalAmount
    }

    public void setTotalAmount(Double totalAmount) { //00016023 - Setter method for totalAmount
        this.totalAmount = totalAmount; //00016023 - Sets the value of totalAmount
    }

    public String getDescription() { //00016023 - Getter method for description
        return description; //00016023 - Returns the value of description
    }

    public void setDescription(String description) { //00016023 - Setter method for description
        this.description = description; //00016023 - Sets the value of description
    }

    public String getCardNumber() { //00016023 - Getter method for cardNumber
        return cardNumber; //00016023 - Returns the value of cardNumber
    }

    public void setCardNumber(String cardNumber) { //00016023 - Setter method for cardNumber
        this.cardNumber = cardNumber; //00016023 - Sets the value of cardNumber
    }
}