package org.alexsv.parcialfinal;

public class Client {
    private Integer clientId;
    private String name;
    private String address;
    private String phoneNumber;

    public Client(Integer clientId, String name, String address, String phoneNumber) {
        this.clientId = clientId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
