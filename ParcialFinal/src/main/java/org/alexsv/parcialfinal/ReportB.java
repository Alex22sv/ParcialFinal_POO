package org.alexsv.parcialfinal;

public class ReportB {
    private String clientName;
    private Double totalAmountSpent;

    public ReportB(String clientName, Double totalAmountSpent) {
        this.clientName = clientName;
        this.totalAmountSpent = totalAmountSpent;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getTotalAmountSpent() {
        return totalAmountSpent;
    }

    public void setTotalAmountSpent(Double totalAmountSpent) {
        this.totalAmountSpent = totalAmountSpent;
    }
}
