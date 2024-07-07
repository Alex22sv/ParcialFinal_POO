package org.alexsv.parcialfinal;

public class ReportC {
    private String creditCard;
    private String debitCard;

    public ReportC(String creditCard, String debitCard) {
        this.creditCard = creditCard;
        this.debitCard = debitCard;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(String debitCard) {
        this.debitCard = debitCard;
    }
}
