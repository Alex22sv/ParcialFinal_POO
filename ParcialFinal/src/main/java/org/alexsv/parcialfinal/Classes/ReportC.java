package org.alexsv.parcialfinal.Classes;

public class ReportC { //00016023 - Defines the public class ReportC
    private String creditCard; //00016023 - Declares a private variable of type String for the credit card
    private String debitCard; //00016023 - Declares a private variable of type String for the debit card

    public ReportC(String creditCard, String debitCard) { //00016023 - Constructor of the ReportC class
        this.creditCard = creditCard; //00016023 - Assigns the value of creditCard to the instance variable
        this.debitCard = debitCard; //00016023 - Assigns the value of debitCard to the instance variable
    }

    public String getCreditCard() { //00016023 - Getter method for creditCard
        return creditCard; //00016023 - Returns the value of creditCard
    }

    public void setCreditCard(String creditCard) { //00016023 - Setter method for creditCard
        this.creditCard = creditCard; //00016023 - Sets the value of creditCard
    }

    public String getDebitCard() { //00016023 - Getter method for debitCard
        return debitCard; //00016023 - Returns the value of debitCard
    }

    public void setDebitCard(String debitCard) { //00016023 - Setter method for debitCard
        this.debitCard = debitCard; //00016023 - Sets the value of debitCard
    }
}
