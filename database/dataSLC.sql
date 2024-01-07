



INSERT INTO Account (Name, Email, Phone, Regionid, TaxCode, Password)
VALUES ('Sony', 'sony@gmail.com', '1234567890', 1, 'EIN-123456', 'sony1');




INSERT INTO Region VALUES ('North America');
INSERT INTO Region VALUES ('Europe');
INSERT INTO Region VALUES ('Asia');
INSERT INTO Region VALUES ('South America');
INSERT INTO Region VALUES ('Africa');
INSERT INTO Region VALUES ('Oceania');




INSERT INTO SupplierCompany VALUES ('Quantum Innovations Ltd', 'USA')
INSERT INTO SupplierCompany VALUES ('Stellar Enterprises Inc', 'USA')
INSERT INTO SupplierCompany VALUES ('Pinnacle Solutions Group', 'Korean')
INSERT INTO SupplierCompany VALUES ('Zenith Synergy Technologies', 'China')
INSERT INTO SupplierCompany VALUES ('Synergy Catalysts Group', 'Thailand')


INSERT INTO Brand VALUES ('ApexWave')
INSERT INTO Brand VALUES ('Fusion')
INSERT INTO Brand VALUES ('ZenithSync')
INSERT INTO Brand VALUES ('SummitFlex')
INSERT INTO Brand VALUES ('Cataly')


INSERT INTO Invoice VALUES ('20231201', 1)
INSERT INTO Invoice VALUES ('20231201' , 2)
INSERT INTO Invoice VALUES ('20231201' ,3)
INSERT INTO Invoice VALUES ('20231201' , 4)
INSERT INTO Invoice VALUES ('20231201' , 5)

INSERT INTO Category VALUES ('Cereals')
INSERT INTO Category VALUES ('Fresh Produce')
INSERT INTO Category VALUES ('Packaged Foods')
INSERT INTO Category VALUES ( 'Cooking Oils')
INSERT INTO Category VALUES ( 'Beverages')
INSERT INTO Category VALUES ('Cooking Ingredients')
INSERT INTO Category VALUES ('Snacks and Sweets')


INSERT INTO Product (name, Image, Price,Categoryid,Description,Quantity,Brandid)
VALUES ('Honey Almond Clusters','https://www.sugarsaltmagic.com/wp-content/uploads/2021/01/Honey-Granola-Clusters-6-720x1080.jpg',0, 1,'A delightful snack that satisfies your sweet cravings and provides a boost of energy', 0, 1);
INSERT INTO Product (Name, Image, Price, Categoryid,Description, Quantity, Brandid)
VALUES ('Crisp Gala Apples','https://www.appleholler.com/wp-content/uploads/2017/09/gala-apple-1.jpg', 0, 2,'Picked at the peak of freshness, these apples make for a wholesome and refreshing snack, or a flavorful addition to your favorite recipes.', 0, 2);
INSERT INTO Product (Name, Image, Price, Categoryid,Description, Quantity, Brandid)
VALUES ('Mushroom Risotto Box','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxEzX_4FkVeUXRoojKPj9dGiOucYDXCKej2U71lApDnp-In3ewHAVSIUYdw75Us5QYqvg&usqp=CAU', 0, 3,'This convenient kit brings together the rich flavors of mushrooms, Arborio rice, and savory spices for a satisfying meal in minutes.', 0, 3);
INSERT INTO Product (Name, Image,Price, Categoryid,Description, Quantity,  Brandid)
VALUES ('Organic Coconut Oil', 'https://s.alicdn.com/@sc04/kf/HTB1PDjNGkOWBuNjSsppq6xPgpXak.jpg_300x300.jpg',0, 4,'Sourced from the finest coconuts, this versatile oil adds a delicate, tropical flavor to your dishes while maintaining the highest organic standards.',0, 4);
INSERT INTO Product (Name, Image, Price, Categoryid,Description, Quantity, Brandid)
VALUES ('Green Tea Infusion','https://cdn.gaiagoodhealth.com/wp-content/uploads/2021/06/20113118/Camomile-Infusion.jpg', 0, 5,'Delicately crafted with premium green tea leaves, this infusion offers a soothing and aromatic experience, perfect for a moment of tranquility.',0, 5);
INSERT INTO Product (Name, Image,Price, Categoryid,Description, Quantity, Brandid)
VALUES ('Gourmet Spice Blend Set', 'https://omnichannelimages.global.ssl.fastly.net/prod/dsv/kg/KG-TGC-TRIOPOULTRYV2-NO_900_2.JPG?width=590&height=767&quality=95&format=webp', 0, 6,'Curated for the discerning chef, this collection of premium spices adds depth and complexity to your dishes, elevating them to a new level of flavor.',0, 4);
INSERT INTO Product (Name, Image,Price, Categoryid,Description, Quantity, Brandid)
VALUES ('Dark Chocolate Almond Clusters', 'https://shopsunridgefarms.com/wp-content/uploads/2021/07/868271_Dark_Choc_Almond_Clusters_2.jpg',0, 7,'A decadent treat that satisfies your chocolate cravings with a delightful nutty twist.',0, 2);
INSERT INTO Product (Name, Image, Price, Categoryid,Description, Quantity, Brandid)
VALUES ('Garden Fresh Broccoli','https://farmgokart.com/wp-content/uploads/2022/05/broccoli-1.png' , 0, 2,'Packed with vitamins and nutrients, these crisp and vibrant broccoli florets make a healthy and delicious addition to any meal.',0, 5);


INSERT INTO InvoiceDetail VALUES (2,1, 18, 5)
INSERT INTO InvoiceDetail VALUES (2,2, 25, 5)
INSERT INTO InvoiceDetail VALUES (1,3, 20, 4)
INSERT INTO InvoiceDetail VALUES (1,4, 18, 8)
INSERT INTO InvoiceDetail VALUES (3,5, 15, 10)
INSERT INTO InvoiceDetail VALUES (5, 6, 13, 9)
INSERT INTO InvoiceDetail VALUES (4,7, 15, 10)
INSERT INTO InvoiceDetail VALUES (3,8, 15, 10)





INSERT INTO Payment  VALUES ('COD')
INSERT INTO Payment  VALUES ('Google Pay')
INSERT INTO Payment  VALUES ('VISA')
INSERT INTO Payment  VALUES ('Debit Card')
INSERT INTO Payment  VALUES ('Bank Transfer')


INSERT INTO MyOrder (ordertotal,DeliveryAddress, Paymentid, email)
VALUES
(0,'123 Main St', 1,'sony@gmail.com')




INSERT INTO ProductDetail(orderid,productid, quantity, total)
VALUES
(1,1, 1,0)

INSERT INTO ProductDetail(orderid,productid, quantity, total)
VALUES
(1,2, 1,0)

INSERT INTO ProductDetail(orderid,productid, quantity, total)
VALUES
(1,5, 1,0)



