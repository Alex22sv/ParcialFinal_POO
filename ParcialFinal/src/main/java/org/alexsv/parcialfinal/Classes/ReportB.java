package org.alexsv.parcialfinal.Classes;

public class ReportB { //00016023 - Defines the public class ReportB
    private String clientName; //00016023 - Declares a private variable of type String for the client name
    private  String totalAmountSpent; //00016023 - Declares a private variable of type  String for the total amount spent

    public ReportB(String clientName,  String totalAmountSpent) { //00016023 - Constructor of the ReportB class
        this.clientName = clientName; //00016023 - Assigns the value of clientName to the instance variable
        this.totalAmountSpent = totalAmountSpent; //00016023 - Assigns the value of totalAmountSpent to the instance variable
    }

    public String getClientName() { //00016023 - Getter method for clientName
        return clientName; //00016023 - Returns the value of clientName
    }

    public void setClientName(String clientName) { //00016023 - Setter method for clientName
        this.clientName = clientName; //00016023 - Sets the value of clientName
    }

    public  String getTotalAmountSpent() { //00016023 - Getter method for totalAmountSpent
        return totalAmountSpent; //00016023 - Returns the value of totalAmountSpent
    }

    public void setTotalAmountSpent( String totalAmountSpent) { //00016023 - Setter method for totalAmountSpent
        this.totalAmountSpent = totalAmountSpent; //00016023 - Sets the value of totalAmountSpent
    }
}
