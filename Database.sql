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
    CONSTRAINT FK_client_card FOREIGN KEY (clientId) REFERENCES Client(clientId) ON DELETE CASCADE
);
-- Create Transaction table
CREATE TABLE Transaction(
    transactionId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    purchaseDate DATE NOT NULL,
    totalAmount DECIMAL(18, 2) NOT NULL,
    description VARCHAR(50) NOT NULL,
    clientId INT,
    cardId INT,
    CONSTRAINT FK_client_transaction FOREIGN KEY (clientId) REFERENCES Client(clientId) ON DELETE CASCADE,
    CONSTRAINT FK_card_transaction FOREIGN KEY (cardId) REFERENCES Card(cardID) ON DELETE CASCADE
);
-- Insert clients
INSERT INTO Client (`name`, `address`, `phoneNumber`) VALUES 
('Carlos Hernández', 'San Salvador', '1234-5678'),
('María López', 'Santa Tecla', '8765-4321'),
('José Martínez', 'Soyapango', '2345-6789'),
('Ana Gómez', 'Santa Ana', '7654-3210'),
('Juan Pérez', 'San Miguel', '3456-7890'),
('Laura Ramírez', 'Sonsonate', '6543-2109'),
('Miguel Sánchez', 'Usulután', '4567-8901'),
('Sofía Rodríguez', 'Chalatenango', '5432-1098'),
('David González', 'Ahuachapán', '6789-0123'),
('Elena Vargas', 'San Vicente', '7890-1234'),
('Paulo Escobar', 'Columbia', '9876-3210');
-- Insert cards
INSERT INTO Card(`cardNumber`, `expirationDate`, `type`, `facilitator`, `clientId`) VALUES 
('1234 5678 9012 3456', '2027-01-01', 'Credit card', 'Visa', 1),
('2345 6789 0123 4567', '2028-02-01', 'Debit card', 'MasterCard', 1),
('3456 7890 1234 5678', '2029-03-01', 'Credit card', 'American Express', 2),
('4567 8901 2345 6789', '2030-04-01', 'Credit card', 'Visa', 2),
('5678 9012 3456 7890', '2027-05-01', 'Debit card', 'MasterCard', 2),
('6789 0123 4567 8901', '2028-06-01', 'Credit card', 'American Express', 3),
('7890 1234 5678 9012', '2029-07-01', 'Debit card', 'Visa', 3),
('8901 2345 6789 0123', '2030-08-01', 'Credit card', 'MasterCard', 4),
('9012 3456 7890 1234', '2027-09-01', 'Debit card', 'Visa', 4),
('0123 4567 8901 2345', '2028-10-01', 'Credit card', 'American Express', 5),
('1234 5678 9012 3456', '2029-11-01', 'Debit card', 'MasterCard', 5),
('2345 6789 0123 4567', '2030-12-01', 'Credit card', 'Visa', 6),
('3456 7890 1234 5678', '2027-01-01', 'Debit card', 'American Express', 6),
('4567 8901 2345 6789', '2028-02-01', 'Credit card', 'MasterCard', 7),
('5678 9012 3456 7890', '2029-03-01', 'Debit card', 'Visa', 7),
('6789 0123 4567 8901', '2030-04-01', 'Credit card', 'American Express', 8),
('7890 1234 5678 9012', '2027-05-01', 'Debit card', 'MasterCard', 8),
('8901 2345 6789 0123', '2028-06-01', 'Credit card', 'Visa', 9),
('9012 3456 7890 1234', '2029-07-01', 'Debit card', 'American Express', 9),
('0123 4567 8901 2345', '2030-08-01', 'Credit card', 'MasterCard', 10),
('1234 5678 9012 3456', '2027-09-01', 'Debit card', 'Visa', 10);
-- Insert transactions
INSERT INTO Transaction (`purchaseDate`, `totalAmount`, `description`, `clientId`, `cardId`) VALUES 
('2024-01-15', 4.99, 'Coffee', 1, 1),
('2024-02-20', 15.00, 'Lunch', 2, 3),
('2024-03-05', 25.50, 'Groceries', 3, 6),
('2024-04-10', 8.99, 'Book', 4, 8),
('2024-05-22', 99.99, 'Electronics', 5, 11),
('2024-06-15', 45.00, 'Shoes', 6, 13),
('2024-07-18', 30.00, 'Clothing', 7, 15),
('2024-08-24', 120.00, 'Dinner', 8, 17),
('2024-09-12', 60.00, 'Gadgets', 9, 19),
('2024-10-05', 10.50, 'Snacks', 10, 21),
('2024-11-20', 75.00, 'Concert Ticket', 1, 2),
('2024-12-01', 7.99, 'Movie', 2, 4),
('2024-02-14', 20.00, 'Flowers', 2, 5),
('2024-03-28', 150.00, 'Furniture', 4, 9),
('2024-04-07', 50.00, 'Toys', 6, 12),
('2024-05-11', 5.00, 'Soda', 7, 14),
('2024-06-23', 18.00, 'Pizza', 8, 16),
('2024-07-30', 200.00, 'Travel', 9, 18),
('2024-08-19', 3.50, 'Candy', 10, 20),
('2024-09-26', 95.00, 'Perfume', 10, 21),
('2024-10-14', 13.00, 'Burger', 1, 1),
('2024-11-09', 250.00, 'Watch', 2, 3),
('2024-12-20', 40.00, 'Sports Equipment', 3, 6),
('2024-01-29', 10.00, 'Ice Cream', 4, 8),
('2024-02-05', 85.00, 'Backpack', 5, 11),
('2024-03-22', 25.00, 'Fitness', 6, 13),
('2024-04-15', 15.50, 'Stationery', 7, 15),
('2024-05-27', 7.00, 'Breakfast', 8, 17),
('2024-06-06', 12.99, 'Fast Food', 9, 19),
('2024-07-25', 110.00, 'Gym Membership', 10, 20);