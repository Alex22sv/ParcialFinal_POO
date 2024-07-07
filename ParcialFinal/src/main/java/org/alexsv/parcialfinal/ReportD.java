package org.alexsv.parcialfinal;

public class ReportD {
        private Integer transactionId;
    private String clientName;
    private Integer totalPurchases;
    private Double totalAmountSpent;

    public ReportD(Integer transactionId, String clientName, Integer totalPurchases, Double totalAmountSpent) {
        this.transactionId = transactionId;
        this.clientName = clientName;
        this.totalPurchases = totalPurchases;
        this.totalAmountSpent = totalAmountSpent;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getTotalPurchases() {
        return totalPurchases;
    }

    public void setTotalPurchases(Integer totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    public Double getTotalAmountSpent() {
        return totalAmountSpent;
    }

    public void setTotalAmountSpent(Double totalAmountSpent) {
        this.totalAmountSpent = totalAmountSpent;
    }
}
