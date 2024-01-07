CREATE DATABASE SlcProject
go
USE SlcProject
GO


CREATE TABLE Category
(
	id INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	name NVARCHAR(200)
)

CREATE TABLE Brand
(
	id INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	name NVARCHAR(200)
)


CREATE TABLE SupplierCompany
(
	id INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	name NVARCHAR(200),
	nation NVARCHAR(50)
)

CREATE TABLE Region
(
	id INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	name NVARCHAR(200),
)

CREATE TABLE Invoice
(
	id INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	invoicedate DATE DEFAULT GETDATE(), 
	supplierid INT,
	CONSTRAINT FK_Invoice_Supplierid FOREIGN KEY (Supplierid) REFERENCES SupplierCompany(Id)
)



CREATE TABLE Product
(
	id INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	name NVARCHAR(200),
	image varchar(MAX),
	price DECIMAL(10,2),
	categoryid INT,
	description nvarchar(200),
	quantity INT,
	brandid INT,
	CONSTRAINT FK_Product_categoryid FOREIGN KEY (categoryid) REFERENCES Category(id),
	CONSTRAINT FK_Product_brandid FOREIGN KEY (brandid) REFERENCES Brand(id)

)



CREATE TABLE InvoiceDetail
(
	invoiceid int NOT NULL,
	productid int NOT NULL,
	importprice DECIMAL(10,2),
	quantity int,
	CONSTRAINT PK_InvoiceDetail_Invoiceid_Productid PRIMARY KEY (Invoiceid,Productid),
	CONSTRAINT FK_InvoiceDetail_Invoiceid FOREIGN KEY (Invoiceid) REFERENCES Invoice(id),
	CONSTRAINT FK_InvoiceDetail_Productid FOREIGN KEY (Productid) REFERENCES Product(id)
)




CREATE TABLE Account (
	name NVARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL primary key,
    phone VARCHAR(12) NOT NULL UNIQUE,  
	regionid int ,
    taxcode VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(255) 
    CONSTRAINT FK_Order_region FOREIGN KEY (regionid) REFERENCES Region(id),

)

CREATE TABLE Payment
(
	id INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	paymentform VARCHAR(50)
)

CREATE TABLE MyOrder
(
    id INT PRIMARY KEY NOT NULL IDENTITY(1,1),
    orderdate DATE NOT NULL DEFAULT GETDATE(),
    ordertotal DECIMAL(10,2),
    deliverydate DATE DEFAULT DATEADD(day, 4, GETDATE()), -- Set default value 4 days from OrderDate
    orderstatus VARCHAR(50),
	deliveryaddress VARCHAR(100),
	paymentid INT,
    email varchar(60),
    CONSTRAINT FK_Order_Customerid FOREIGN KEY (email) REFERENCES Account(email),
    CONSTRAINT FK_Order_Paymentid FOREIGN KEY (Paymentid) REFERENCES Payment(id),

);

CREATE TABLE ProductDetail
(
	productid INT NOT NULL,
	orderid int NOT NULL,
	quantity INT,
	total DECIMAL(10,2),
	CONSTRAINT FK_OrderDetail_Orderid FOREIGN KEY (orderid) REFERENCES MyOrder(id),
	CONSTRAINT FK_OrderDetail_Productid FOREIGN KEY (productid) REFERENCES Product(id)
)

Create Table Message(
	  id INT PRIMARY KEY NOT NULL IDENTITY(1,1),
	  email varchar(60),
	  subject varchar(100),
	  body varchar(500)
)


