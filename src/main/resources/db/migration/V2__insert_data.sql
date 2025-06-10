USE
iti_grad;
--     pass123
--     pass456
--     pass789
INSERT INTO User (name, phone_number, email, password, birthdate, job, credit_limit, interests)
VALUES ('Tom Lee', '01011112222', 'tom@example.com', '$2a$10$aQmNrJURhcUJ6mnhk94AD.zBF4JsI8rNxBUrvFvCAmtWzuyMud5CC', '1999-03-14', 'Engineer', 10000.00,'SNEAKERS,CASUAL'),
       ('Ann Kim', '01033334444', 'ann@example.com', '$2a$10$ALIMK0F/tm9z0N02bcaVueflM6e96xBBx8ctUrWKj7djsXsMg04cW', '1998-08-21', 'Designer', 8000.00,'CLASSIC,SNEAKERS'),
       ('Bob Jay', '01055556666', 'bob@example.com', '$2a$10$DMld26R.lbIzCF3iSvSbMembMuO2SqKjkHaQzf9laMN.75bqxFZIC', '1997-12-01', 'Developer', 12000.00,'SNEAKERS,CLASSIC'),
       ('Lena Howard', '01077778888', 'lena@example.com', '$2a$10$5mPqLqboCgqJx8eN1MYkwe7EzLraK0f7cToDRRrUPT2mLw4IXR0Vu', '1996-06-22', 'Photographer', 9500.00, 'SNEAKERS,SPORTS'),
       ('Samir Youssef', '01099990000', 'samir@example.com', '$2a$10$gJRAh9HHm6gVDF/U7AeR4es4YAXXQHH9xOQ3ETL0PzMPSPBuXhQ6a', '1995-01-10', 'Banker', 11000.00, 'CLASSIC,FORMAL'),
       ('Nora Salem', '01088887777', 'nora@example.com', '$2a$10$zSnY90nD2m67g/AWZBsyQOZkx.HapYFvnIftQUzQnEqp8XFxsdZnW', '2000-11-04', 'Student', 6000.00, 'CASUAL,SNEAKERS'),
       ('Adam Bakr', '01012345678', 'adam@example.com', '$2a$10$eYxkmGpCwItI06I8EyLGL.CSM4BPKsRfwvjEmFfszjNux0oQWBd1C', '1993-09-09', 'Freelancer', 10500.00, 'SPORTS,CASUAL'),
       ('Dana Rizk', '01033332222', 'dana@example.com', '$2a$10$Iq1Dh/qpzBLUMkZxod84pO67EkmYkNfwAHsA2K3z5p2kE4FZ5TghW', '1992-05-30', 'Teacher', 7000.00, 'CLASSIC'),
       ('Maya Adel', '01044445555', 'maya@example.com', '$2a$10$ShnTQW0N7S7OVgqvJ3s2EuIgYnpGcRtq5.QFdN0qjxMC6Dg5R71Oi', '1994-04-18', 'Architect', 8500.00, 'CLASSIC,CASUAL'),
       ('Youssef Kamal', '01022223333', 'youssef@example.com', '$2a$10$OxnCCOJdd8zsv2zgnL70UuN7x43PlXcZxB3V/jFZZF8qxPqfXa5nK', '1990-10-30', 'Lawyer', 15000.00, 'FORMAL,CLASSIC'),
       ('Lara Nabil', '01066667777', 'lara@example.com', '$2a$10$7X7kPOKqDjVDhLzMvL3rMufkFnpVdGAYrhCwKOWzSkqzU6Z70CnHe', '1998-01-12', 'Doctor', 9500.00, 'SNEAKERS,SPORTS'),
       ('Omar Fathy', '01000001111', 'omar@example.com', '$2a$10$1ZlK3kNRDdOxt7tTfq8ODuUwPiLJqKJwqAFmxT19kV5Uc74C3MXDS', '1991-06-03', 'Pilot', 13000.00, 'CASUAL,FORMAL'),
       ('Sandy Elbaz', '01013131313', 'sandy@example.com', '$2a$10$c95ccGyw7bdRz6VE/NsGVO0BqKxFzhZ4kElRP5wzw.KvoYDjY0T2C', '1999-07-07', 'Nutritionist', 7500.00, 'SNEAKERS'),
       ('Hassan Shawky', '01088889999', 'hassan@example.com', '$2a$10$rN1ZqvJkXMZK/O5RAsw6CejbyAGgNoyN3uMf5v7VmLqH7VMTUkDtu', '1989-03-25', 'Photographer', 8000.00, 'CASUAL,CLASSIC'),
       ('Nadine Fares', '01012121212', 'nadine@example.com', '$2a$10$ZAD8ABpnWoYZJpJk6pjVruO3Z8/M/aR4ceVCqYfVXvUAVMmWCVV3e', '1995-12-14', 'Dentist', 8800.00, 'SPORTS,FORMAL');


INSERT INTO UserAddress (user_id, state, street, building_number, is_default)
VALUES (1, 'Cairo', 'Tahrir St.', 5, true),
       (2, 'Giza', 'Mohandessin Ave.', 3, true),
       (3, 'Alexandria', 'Stanley Beach Rd.', 10, true),
       (4, 'Giza', 'El Gomhoria St.', 12, true),
       (5, 'Cairo', 'Nasr City Blvd.', 7, true),
       (6, 'Giza', 'Dokki St.', 14, true),
       (7, 'Giza', 'Corniche El Nile', 2, true),
       (8, 'Cairo', 'El Sa’aa Square', 6, true),
       (9, 'Cairo', 'Heliopolis St.', 15, true),
       (10, 'Giza', 'Faisal St.', 9, true),
       (11, 'Alexandria', 'Gleem Rd.', 20, true),
       (12, 'Cairo', 'Sheraton Rd.', 5, true),
       (13, 'Alexandria', 'Tayaran St.', 6, true),
       (14, 'Cairo', 'Maadi Ring Rd.', 18, true),
       (15, 'Cairo', 'El Shohadaa St.', 11, true);

INSERT INTO Product (name, description, category, gender, price, brand, sold)
VALUES ('Nike Daily Dash (Male)', 'Daily sneakers.', 'SNEAKERS', 'MALE', 2200.00, 'Nike', 10),
       ('Adidas Sprint She (Female)', 'Running shoes.', 'SNEAKERS', 'FEMALE', 2500.00, 'Adidas', 37),
       ('Merrel Swift She (Female)', 'Running shoes.', 'SNEAKERS', 'FEMALE', 1800.00, 'Merrel', 22),
       ('Gucci Glide Femme (Female)', 'Daily sneakers.', 'SNEAKERS', 'FEMALE', 1600.00, 'Gucci', 17),
       ('Sketchers Power Trainer (Male)', 'Training sneakers.', 'SNEAKERS', 'MALE', 2800.00, 'Sketchers', 29),

       ('Merrel Precision Trainer (Male)', 'Training sneakers.', 'SNEAKERS', 'MALE', 2100.00, 'Merrel', 14),
       ('Adidas Casual Champion (Male)', 'Comfortable casual wear.', 'CASUAL', 'MALE', 3000.00, 'Adidas', 42),
       ('Gucci Relax Max (Male)', 'Comfortable casual wear.', 'CASUAL', 'MALE', 1900.00, 'Gucci', 11),
       ('Sketchers Sprint She II (Female)', 'Running shoes.', 'SNEAKERS', 'FEMALE', 1700.00, 'Sketchers', 25),
       ('Nike Timeless Chic (Female)', 'Affordable classic formal.', 'CLASSIC', 'FEMALE', 1500.00, 'Nike', 30),

       ('Merrel Classic Prime (Male)', 'Affordable classic formal.', 'CLASSIC', 'MALE', 2400.00, 'Merrel', 18),
       ('Gucci Elite Classic (Male)', 'Formal classic shoes.', 'CLASSIC', 'MALE', 3000.00, 'Gucci', 38),
       ('Nike Casual Ace (Male)', 'Soft casual style.', 'CASUAL', 'MALE', 3200.00, 'Nike', 40),
       ('Nike Formal Essential (Male)', 'Affordable classic formal.', 'CLASSIC', 'MALE', 1200.00, 'Nike', 21),
       ('Sketchers Pro Cleats (Male)', 'High-performance cleats.', 'CASUAL', 'MALE', 2700.00, 'Sketchers', 34),

       ('Sketchers Style Glide (Male)', 'Soft casual style.', 'CASUAL', 'MALE', 2000.00, 'Sketchers', 16),
       ('Nike Classic Elegance (Female)', 'Formal classic shoes.', 'CLASSIC', 'FEMALE', 2500.00, 'Nike', 27),
       ('Merrel Chic Classic (Female)', 'Affordable classic formal.', 'CLASSIC', 'FEMALE', 1500.00, 'Merrel', 13),
       ('Gucci Formal Luxe (Female)', 'Formal classic shoes.', 'CLASSIC', 'FEMALE', 2800.00, 'Gucci', 32);

-- Product 1 (CU-1)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (1, 42, 'White', 10),
       (1, 43, 'Cream', 12);

-- Product 2 (CU-2)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (2, 38, 'Black', 7),
       (2, 39, 'Cream', 5);

-- Product 3 (CU-3)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (3, 42, 'Red', 6),
       (3, 43, 'White', 10);

-- Product 4 (CU-4)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (4, 37, 'Blue', 4),
       (4, 38, 'Black', 6);

-- Product 5 (CU-5)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (5, 42, 'Grey', 8),
       (5, 43, 'Black', 3);

-- Product 6 (LX-6)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (6, 42, 'Brown', 5),
       (6, 43, 'Cream', 4);

-- Product 7 (LX-7)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (7, 38, 'Grey', 9),
       (7, 39, 'White', 6),
       (7, 39, 'Green', 10);

-- Product 8 (LX-8)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (8, 39, 'White', 7),
       (8, 40, 'Black', 5),
       (8, 40, 'Brown', 10);

-- Product 9 (LX-9)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (9, 41, 'White', 5),
       (9, 41, 'Grey', 10),
       (9, 42, 'Blue', 8);

-- Product 10 (LX-10)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (10, 41, 'Black', 5),
       (10, 42, 'Brown', 7);

-- Product 11 (UI-11)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (11, 42, 'White', 10),
       (11, 43, 'Cream', 12),
       (11, 43, 'Brown', 12);

-- Product 12 (UI-12)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (12, 38, 'Black', 7),
       (12, 39, 'Cream', 5);

-- Product 13 (UI-3)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (13, 43, 'Black', 10),
       (13, 42, 'Red', 6),
       (13, 43, 'White', 10);

-- Product 14 (UI-14)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (14, 37, 'Blue', 4),
       (14, 38, 'Black', 6);

-- Product 15 (CU-15)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (15, 42, 'Grey', 8),
       (15, 42, 'Brown', 8),
       (15, 43, 'Black', 3);

-- Product 16 (RT-16)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (16, 42, 'Blue', 5),
       (16, 43, 'Grey', 4);

-- Product 17 (RT-17)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (17, 38, 'Black', 9),
       (17, 39, 'White', 6),
       (17, 39, 'Green', 10);

-- Product 18 (RT-18)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (18, 39, 'White', 7),
       (18, 40, 'Cream', 5),
       (18, 40, 'Brown', 10);

-- Product 19 (RT-19)
INSERT INTO ProductInfo (product_id, size, color, quantity)
VALUES (19, 41, 'White', 5),
       (19, 41, 'Grey', 10),
       (19, 42, 'Blue', 8);


-- Product 1
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (1, 'uploads/product-1/item1-1.jpg', TRUE),
       (1, 'uploads/product-1/item1-2.jpg', FALSE),
       (1, 'uploads/product-1/item1-3.jpg', FALSE),
       (1, 'uploads/product-1/item1-4.jpg', FALSE);

-- Product 2
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (2, 'uploads/product-2/item2-1.jpg', TRUE),
       (2, 'uploads/product-2/item2-2.jpg', FALSE),
       (2, 'uploads/product-2/item2-3.jpg', FALSE),
       (2, 'uploads/product-2/item2-4.jpg', FALSE);

-- Product 3
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (3, 'uploads/product-3/item3-1.jpg', TRUE),
       (3, 'uploads/product-3/item3-2.jpg', FALSE),
       (3, 'uploads/product-3/item3-3.jpg', FALSE),
       (3, 'uploads/product-3/item3-4.jpg', FALSE);

-- Product 4
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (4, 'uploads/product-4/item4-1.jpg', TRUE),
       (4, 'uploads/product-4/item4-2.jpg', FALSE),
       (4, 'uploads/product-4/item4-3.jpg', FALSE),
       (4, 'uploads/product-4/item4-4.jpg', FALSE);

-- Product 5 (repeat from item-1.jpg)
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (5, 'uploads/product-5/item5-1.jpg', FALSE),
       (5, 'uploads/product-5/item5-2.jpg', TRUE),
       (5, 'uploads/product-5/item5-3.jpg', FALSE),
       (5, 'uploads/product-5/item5-4.jpg', FALSE);

-- Product 6
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (6, 'uploads/product-6/item6-1.jpg', TRUE),
       (6, 'uploads/product-6/item6-2.jpg', FALSE),
       (6, 'uploads/product-6/item6-3.jpg', FALSE),
       (6, 'uploads/product-6/item6-4.jpg', FALSE);

-- Product 7
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (7, 'uploads/product-7/item7-1.jpg', TRUE),
       (7, 'uploads/product-7/item7-2.jpg', FALSE),
       (7, 'uploads/product-7/item7-3.jpg', FALSE),
       (7, 'uploads/product-7/item7-4.jpg', FALSE);

-- Product 8
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (8, 'uploads/product-8/item8-1.jpg', TRUE),
       (8, 'uploads/product-8/item8-2.jpg', FALSE),
       (8, 'uploads/product-8/item8-3.jpg', FALSE),
       (8, 'uploads/product-8/item8-4.jpg', FALSE);

-- Product 9
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (9, 'uploads/product-9/item9-1.jpg', TRUE),
       (9, 'uploads/product-9/item9-2.jpg', FALSE),
       (9, 'uploads/product-9/item9-3.jpg', FALSE),
       (9, 'uploads/product-9/item9-4.jpg', FALSE);

-- Product 10
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (10, 'uploads/product-10/item10-1.jpg', TRUE),
       (10, 'uploads/product-10/item10-2.jpg', FALSE),
       (10, 'uploads/product-10/item10-3.jpg', FALSE),
       (10, 'uploads/product-10/item10-4.jpg', FALSE);

-- Product 11
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (11, 'uploads/product-11/item11-1.jpg', FALSE),
       (11, 'uploads/product-11/item11-2.jpg', FALSE),
       (11, 'uploads/product-11/item11-3.jpg', TRUE),
       (11, 'uploads/product-11/item11-4.jpg', FALSE);

-- Product 12
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (12, 'uploads/product-12/item12-1.jpg', FALSE),
       (12, 'uploads/product-12/item12-2.jpg', TRUE),
       (12, 'uploads/product-12/item12-3.jpg', FALSE),
       (12, 'uploads/product-12/item12-4.jpg', FALSE);

-- Product 13
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (13, 'uploads/product-13/item13-1.jpg', FALSE),
       (13, 'uploads/product-13/item13-2.jpg', TRUE),
       (13, 'uploads/product-13/item13-3.jpg', FALSE),
       (13, 'uploads/product-13/item13-4.jpg', FALSE);

-- Product 14
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (14, 'uploads/product-14/item14-1.jpg', FALSE),
       (14, 'uploads/product-14/item14-2.jpg', FALSE),
       (14, 'uploads/product-14/item14-3.jpg', TRUE),
       (14, 'uploads/product-14/item14-4.jpg', FALSE);

-- Product 15
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (15, 'uploads/product-15/item15-1.jpg', FALSE),
       (15, 'uploads/product-15/item15-2.jpg', TRUE),
       (15, 'uploads/product-15/item15-3.jpg', FALSE),
       (15, 'uploads/product-15/item15-4.jpg', FALSE);

-- Product 16
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (16, 'uploads/product-16/item16-1.jpg', FALSE),
       (16, 'uploads/product-16/item16-2.jpg', TRUE),
       (16, 'uploads/product-16/item16-3.jpg', FALSE),
       (16, 'uploads/product-16/item16-4.jpg', FALSE);

-- Product 17
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (17, 'uploads/product-17/item17-1.jpg', TRUE),
       (17, 'uploads/product-17/item17-2.jpg', FALSE),
       (17, 'uploads/product-17/item17-3.jpg', FALSE),
       (17, 'uploads/product-17/item17-4.jpg', FALSE);

-- Product 18
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (18, 'uploads/product-18/item18-1.jpg', TRUE),
       (18, 'uploads/product-18/item18-2.jpg', FALSE),
       (18, 'uploads/product-18/item18-3.jpg', FALSE),
       (18, 'uploads/product-18/item18-4.jpg', FALSE);

-- Product 19
INSERT INTO ProductImg (product_id, img, is_default)
VALUES (19, 'uploads/product-19/item19-1.jpg', TRUE),
       (19, 'uploads/product-19/item19-2.jpg', FALSE),
       (19, 'uploads/product-19/item19-3.jpg', FALSE),
       (19, 'uploads/product-19/item19-4.jpg', FALSE);