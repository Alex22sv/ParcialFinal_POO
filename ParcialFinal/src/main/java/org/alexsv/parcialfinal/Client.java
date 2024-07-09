package org.alexsv.parcialfinal;

public class Client { //Definition of the client class
    private Integer clientId; // 00067323 Declaring private Integer for client's ID
    private String name; // 00067323 Declaring private String for client's name
    private String address; // 00067323 Declaring private String for client's address
    private String phoneNumber; // 00067323 Declaring private String for client's phone number

    public Client(Integer clientId, String name, String address, String phoneNumber) { // 00067323 Constructor initializing clientId, name, address, phoneNumber
        this.clientId = clientId; // 00067323 Initializing clientId with parameter value
        this.name = name; // 00067323 Initializing name with parameter value
        this.address = address; // 00067323 Initializing address with parameter value
        this.phoneNumber = phoneNumber; // 00067323 Initializing phoneNumber with parameter value
    }

    public Integer getClientId() { // 00024123 Get the client's ID
        return clientId; // 00067323 Getter for client's ID
    }

    public void setClientId(Integer clientId) { // 00024123 Set the client's ID
        this.clientId = clientId; // 00067323 Setter for client's ID
    }

    public String getName() { // 00024123 Get the client's name
        return name; // 00067323 Getter for client's name
    }

    public void setName(String name) { // 00024123 Set the client's name
        this.name = name; // 00067323 Setter for client's name
    }

    public String getAddress() { // 00024123 Get the client's address
        return address; // 00067323 Getter for client's address
    }

    public void setAddress(String address) { // 00024123 Set the client's address
        this.address = address; // 00067323 Setter for client's address
    }

    public String getPhoneNumber() { // 00024123 Get the client's phone number
        return phoneNumber; // 00067323 Getter for client's phone number
    }

    public void setPhoneNumber(String phoneNumber) { // 00024123 Set the client's phone number
        this.phoneNumber = phoneNumber; // 00067323 Setter for client's phone number
    }
}


