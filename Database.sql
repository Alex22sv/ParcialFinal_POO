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
    type VARCHAR(20),
    facilitator VARCHAR(20),
    clientId INT,
    CONSTRAINT FK_client_card FOREIGN KEY (clientId) REFERENCES Client(clientId)
);
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
-- Insert cards
INSERT INTO Card(`cardNumber`, `expirationDate`, `type`, `facilitator`, `clientId`) VALUES ('1212 5988 9822 1234', '2028-09-01', 'Credit card', 'Visa', 1),
('0023 7812 9822 9876', '2026-09-01', 'Credit card', 'Visa', 2), ('0097 5101 7421 4321', '2030-02-01', 'Debit card', 'MasterCard', 1),
('9863 3201 9871 0131', '2026-12-01', 'Debit card', 'Visa', 3), ('0231 9630 1234 6534', '2025-07-01', 'Debit card', 'American', 1);
-- Insert transactions
INSERT INTO Transaction(`purchaseDate`, `totalAmount`, `description`, `clientId`, `cardId`) VALUES ('2024-07-05', 9.99, 'Caja de donas', 1, 11),
('2024-06-23', 4.99, 'Café americano', 2, 12), ('2024-07-01', 20.99, 'Par de zapatos', 3, 14);