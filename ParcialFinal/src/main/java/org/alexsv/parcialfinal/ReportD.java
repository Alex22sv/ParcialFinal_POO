package org.alexsv.parcialfinal;

public class ReportD { //00016023 - Defines the public class ReportD
    private Integer transactionId; //00016023 - Declares a private variable of type Integer for the transaction ID
    private String clientName; //00016023 - Declares a private variable of type String for the client name
    private Integer totalPurchases; //00016023 - Declares a private variable of type Integer for the total purchases
    private Double totalAmountSpent; //00016023 - Declares a private variable of type Double for the total amount spent

    public ReportD(Integer transactionId, String clientName, Integer totalPurchases, Double totalAmountSpent) { //00016023 - Constructor of the ReportD class
        this.transactionId = transactionId; //00016023 - Assigns the value of transactionId to the instance variable
        this.clientName = clientName; //00016023 - Assigns the value of clientName to the instance variable
        this.totalPurchases = totalPurchases; //00016023 - Assigns the value of totalPurchases to the instance variable
        this.totalAmountSpent = totalAmountSpent; //00016023 - Assigns the value of totalAmountSpent to the instance variable
    }

    public Integer getTransactionId() { //00016023 - Getter method for transactionId
        return transactionId; //00016023 - Returns the value of transactionId
    }

    public void setTransactionId(Integer transactionId) { //00016023 - Setter method for transactionId
        this.transactionId = transactionId; //00016023 - Sets the value of transactionId
    }

    public String getClientName() { //00016023 - Getter method for clientName
        return clientName; //00016023 - Returns the value of clientName
    }

    public void setClientName(String clientName) { //00016023 - Setter method for clientName
        this.clientName = clientName; //00016023 - Sets the value of clientName
    }

    public Integer getTotalPurchases() { //00016023 - Getter method for totalPurchases
        return totalPurchases; //00016023 - Returns the value of totalPurchases
    }

    public void setTotalPurchases(Integer totalPurchases) { //00016023 - Setter method for totalPurchases
        this.totalPurchases = totalPurchases; //00016023 - Sets the value of totalPurchases
    }

    public Double getTotalAmountSpent() { //00016023 - Getter method for totalAmountSpent
        return totalAmountSpent; //00016023 - Returns the value of totalAmountSpent
    }

    public void setTotalAmountSpent(Double totalAmountSpent) { //00016023 - Setter method for totalAmountSpent
        this.totalAmountSpent = totalAmountSpent; //00016023 - Sets the value of totalAmountSpent
    }
}
