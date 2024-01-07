
--update product quantity
CREATE TRIGGER UPDATEPRODUCT ON InvoiceDetail
AFTER INSERT,UPDATE
AS 
BEGIN 
    UPDATE Product
    SET quantity = Product.quantity + i.quantity
    FROM inserted i
    WHERE Product.id = i.productid;
END

INSERT INTO InvoiceDetail VALUES (1, 2, 5)
INSERT INTO InvoiceDetail VALUES (2, 2, 5)
INSERT INTO InvoiceDetail VALUES (5, 2, 5)
INSERT INTO InvoiceDetail VALUES (4, 9, 5)

--update price of product
CREATE TRIGGER UpdateProductPrice
ON InvoiceDetail
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE p
    SET p.price = i.importprice * 0.2 + i.importprice
    FROM Product p
    INNER JOIN inserted i ON p.id = i.productid
END;



--update quantity of ProductDetail
go
Create trigger updateOrderDetailQuantity
On ProductDetail after insert
as 
begin
	declare @Productid varchar(20)
	declare @Quantity int
	select @Productid = i.Productid, @Quantity = i.Quantity
	from inserted i
	update Product Set quantity = quantity - @Quantity
	where @Productid = id 
end

--update total in ProductDetail
CREATE TRIGGER UpdateProductDetailTotal
ON ProductDetail
AFTER INSERT
AS
BEGIN

    UPDATE pd
    SET pd.total = i.quantity * p.price
    FROM ProductDetail pd
    INNER JOIN inserted i ON pd.productid = i.productid
    INNER JOIN Product p ON pd.productid = p.id;
END;




-- Update ordertotal
CREATE TRIGGER UpdateOrderTotal
ON ProductDetail
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE MyOrder
    SET ordertotal = (
        SELECT SUM(pd.quantity * p.price)
        FROM ProductDetail pd
        INNER JOIN Product p ON pd.productid = p.id
        WHERE pd.orderid = MyOrder.id
    )
    FROM MyOrder
    INNER JOIN inserted i ON MyOrder.id = i.orderid;
END;


--update ko cho đặt nếu ko đủ sl
go
CREATE TRIGGER checkOrderQuantity ON ProductDetail
FOR INSERT, UPDATE
AS
BEGIN
    IF EXISTS (
            SELECT i.Quantity
            FROM inserted i
            WHERE i.PRODUCTID IN (
                    SELECT od.PRODUCTID
                    FROM ProductDetail od
                    )
                AND i.Quantity > (
                    SELECT p.quantity
                    FROM PRODUCT p
                    WHERE p.id = i.PRODUCTID))
    BEGIN
        PRINT 'Cannot place the order for this product as there is not enough quantity in stock.'
        ROLLBACK
    END
END


