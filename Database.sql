-- Create the database
CREATE DATABASE BCN;
-- Use the database
USE BCN;
-- Create Client table
CREATE TABLE Client(
    clientId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(12) NOT NULL
);
-- Create Card table
CREATE TABLE Card(
    cardId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cardNumber VARCHAR(19) NOT NULL,
    expirationDate Date NOT NULL,
    type VARCHAR(10),
    facilitator VARCHAR(20),
    clientId int,
    CONSTRAINT FK_client_card FOREIGN KEY (clientId) REFERENCES Client(clientId)
);
ALTER TABLE Card 
-- Forgot to add clientId column in Card table
ALTER TABLE Card ADD COLUMN clientId INT;
ALTER TABLE Card ADD CONSTRAINT FK_client_card FOREIGN KEY (clientId) REFERENCES Client(clientId);
-- Create Transaction table
CREATE TABLE Transaction(
    transactionId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    purchaseDate DATE NOT NULL,
    totalAmount DECIMAL(18, 2) NOT NULL,
    description VARCHAR(50) NOT NULL,
    clientId INT,
    cardId INT,
    CONSTRAINT FK_client_transaction FOREIGN KEY (clientId) REFERENCES Client(clientId),
    CONSTRAINT FK_card_transaction FOREIGN KEY (cardId) REFERENCES Card(cardID)
);
-- Insert clients
INSERT INTO Client (`name`, `address`, `phoneNumber`) VALUES ('Alexander Efraín Morales Pineda', 'San Salvador Centro, Mejicanos', '1234-5678'), 
('Diego Alejandro Iraheta Monterrosa', 'La Libertad, Centro', '9876-5413'), ('César Isaac Tovar Jovel', 'La Libertad Oeste', '9982-5482'), 
('Alejandro Abram FLores Vásquez', 'San Salvador Centro, Mejicanos', '9942-0261');
