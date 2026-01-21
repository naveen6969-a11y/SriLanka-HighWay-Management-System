CREATE DATABASE HighwayMDB;

USE HighwayMDB;

CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(50) NOT NULL
);

CREATE TABLE Cashiers (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(50) NOT NULL
);
INSERT INTO Cashiers(username, password)
VALUES ('cashier', '1234');

INSERT INTO Cashiers(username, password)
VALUES ('naveen', '1122');

INSERT INTO Users (username, password)
VALUES ('user', '1234');
INSERT INTO Users (username, password)
VALUES ('naveen', '1122');
INSERT INTO Users (username, password)
VALUES ('minon', '1111');

INSERT INTO Users (username, password)
VALUES ('malisha', '1111');


SELECT * FROM Users;
SELECT * FROM Cashiers;

CREATE TABLE VehicleEvents (
    EventID INT IDENTITY(1,1) PRIMARY KEY,
    VehicleNumber VARCHAR(20) NOT NULL,
    EntranceLocation VARCHAR(100),
    ExitLocation VARCHAR(100),
    EntranceTime DATETIME DEFAULT GETDATE(),
    ExitTime DATETIME NULL,
    TotalCost DECIMAL(10,2) NULL,
    Cashier VARCHAR(50)
);
ALTER TABLE VehicleEvents
ADD Status VARCHAR(10) DEFAULT 'IN';


-- Vehicle still IN
INSERT INTO VehicleEvents
    (VehicleNumber,EntranceLocation, ExitLocation, EntranceTime, ExitTime, Status, TotalCost)
VALUES
    ('ABC-1234', 'Galle', NULL, GETDATE(), NULL, 'IN', NULL);

-- Vehicle already exited
INSERT INTO VehicleEvents
    (VehicleNumber, EntranceLocation, ExitLocation, EntranceTime, ExitTime, Status, TotalCost)
VALUES
    ('XYZ-5678', 'Gate B', 'Gate C', '2025-11-26 08:00:00', '2025-11-26 10:30:00', 'OUT', 500.0);

CREATE TABLE Tolls (
    TollID INT IDENTITY(1,1) PRIMARY KEY,
    TollName VARCHAR(50) NOT NULL,
    Position INT NOT NULL,
    Cost DECIMAL(10,2) NOT NULL DEFAULT 200
);

INSERT INTO Tolls(TollName,Position)
Values
('Galle',01),
('Matara',02);

CREATE TABLE AccidentReports (
    ReportID INT IDENTITY(1,1) PRIMARY KEY,
    VehicleNumber VARCHAR(20) NOT NULL,
    Location VARCHAR(50) NOT NULL,
    Description VARCHAR(500) NOT NULL,
    TimeReported DATETIME NOT NULL DEFAULT GETDATE(),
    ReportedBy VARCHAR(50) NOT NULL
);

INSERT INTO AccidentReports (VehicleNumber, Location, Description, ReportedBy)
VALUES ('CBA-4455', 'Gate C', 'Minor collision', 'cashier01');

CREATE TABLE Reports (
    ReportID INT IDENTITY(1,1) PRIMARY KEY,
    CashierName VARCHAR(50) NOT NULL,
    ReportType VARCHAR(50) NOT NULL,   -- e.g., "Road Damage", "Accident"
    Description NVARCHAR(MAX) NOT NULL,
    Location VARCHAR(50),
    ReportTime DATETIME DEFAULT GETDATE()
);

INSERT INTO Reports(CashierName,ReportType,Description,Location)
VALUES
('Cashier1','RoadDamage','Road between galle and matara is damaged','Galle');