create schema railway;

use railway;
CREATE TABLE State (
                       state VARCHAR(50) PRIMARY KEY
)ENGINE = InnoDB;

CREATE TABLE User (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      phone_number VARCHAR(15) NOT NULL,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      birthdate DATE NULL DEFAULT NULL,
                      job VARCHAR(50) NULL,
                      credit_limit DECIMAL(10, 2) NOT NULL,
                      interests TEXT NULL,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE = InnoDB;
CREATE INDEX user_idx_email ON User(email);

CREATE TABLE UserAddress (
                             address_id INT AUTO_INCREMENT PRIMARY KEY,
                             user_id INT NOT NULL,
                             state VARCHAR(50) NOT NULL,
                             street TEXT NOT NULL,
                             building_number INT NOT NULL,
                             is_default BOOLEAN NOT NULL DEFAULT FALSE,
                             CONSTRAINT fk_user
                                 FOREIGN KEY (user_id) REFERENCES User(user_id)
                                     ON DELETE CASCADE
                                     ON UPDATE CASCADE,
                             CONSTRAINT fk_state
                                 FOREIGN KEY (state) REFERENCES State(state)
                                     ON DELETE RESTRICT
                                     ON UPDATE CASCADE
)ENGINE = InnoDB;

CREATE TABLE Product (
                         product_id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT NULL,
                         category ENUM('SNEAKERS', 'CLASSIC', 'CASUAL') NOT NULL,
                         gender ENUM('MALE', 'FEMALE') NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         brand VARCHAR(25) NOT NULL,
                         added_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         sold INT NOT NULL DEFAULT 0,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)ENGINE = InnoDB;

CREATE TABLE ProductInfo (
                             product_info_id INT AUTO_INCREMENT PRIMARY KEY,
                             product_id INT NOT NULL,
                             size INT NOT NULL,
                             color VARCHAR(20) NOT NULL,
                             quantity INT NOT NULL,
                             CONSTRAINT fk_product
                                 FOREIGN KEY (product_id) REFERENCES Product(product_id)
                                     ON DELETE CASCADE
                                     ON UPDATE CASCADE,
                             CONSTRAINT uq_product_variant UNIQUE (product_id, size, color)
)ENGINE = InnoDB;

CREATE TABLE ProductImg (
                            image_id INT AUTO_INCREMENT PRIMARY KEY,
                            product_id INT NOT NULL,
                            img VARCHAR(100) NOT NULL,
                            is_default BOOLEAN NOT NULL DEFAULT FALSE,
                            CONSTRAINT fk_product_img
                                FOREIGN KEY (product_id) REFERENCES Product(product_id)
                                    ON DELETE CASCADE
                                    ON UPDATE CASCADE
)ENGINE = InnoDB;

CREATE TABLE ShoppingCart (
                              item_id INT AUTO_INCREMENT PRIMARY KEY,
                              user_id INT NOT NULL,
                              product_info_id INT NOT NULL,
                              quantity INT NOT NULL,
                              added_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT fk_product_info
                                  FOREIGN KEY (product_info_id) REFERENCES ProductInfo(product_info_id)
                                      ON DELETE CASCADE
                                      ON UPDATE CASCADE,
                              CONSTRAINT fk_user_cart
                                  FOREIGN KEY (user_id) REFERENCES User(user_id)
                                      ON DELETE CASCADE
                                      ON UPDATE CASCADE,
                              CONSTRAINT uq_cart_item_variant UNIQUE (product_info_id, user_id)
)ENGINE = InnoDB;

CREATE TABLE `Order` (
                         order_id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id INT NOT NULL,
                         total_amount DECIMAL(10, 2) NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         address_id INT NOT NULL,
                         order_status ENUM('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED', 'RETURNED')
        NOT NULL DEFAULT 'PENDING',
                         CONSTRAINT fk_order_user
                             FOREIGN KEY (user_id) REFERENCES User(user_id)
                                 ON DELETE RESTRICT
                                 ON UPDATE CASCADE,
                         CONSTRAINT fk_order_address
                             FOREIGN KEY (address_id) REFERENCES UserAddress(address_id)
                                 ON DELETE RESTRICT
                                 ON UPDATE CASCADE
)ENGINE = InnoDB;

CREATE TABLE OrderItem (
                           item_id INT AUTO_INCREMENT PRIMARY KEY,
                           order_id INT NOT NULL,
                           product_info_id INT NOT NULL,
                           quantity INT NOT NULL,
                           price_at_purchase DECIMAL(10, 2) NOT NULL,
                           CONSTRAINT fk_order
                               FOREIGN KEY (order_id) REFERENCES `Order`(order_id)
                                   ON DELETE CASCADE
                                   ON UPDATE CASCADE,
                           CONSTRAINT fk_product_info_order_item
                               FOREIGN KEY (product_info_id) REFERENCES ProductInfo(product_info_id)
                                   ON DELETE RESTRICT
                                   ON UPDATE CASCADE,
                           CONSTRAINT uq_order_item_variant UNIQUE (product_info_id, order_id)
)ENGINE = InnoDB;

CREATE TABLE Wishlist (
                          item_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT NOT NULL,
                          product_id INT NOT NULL,
                          CONSTRAINT fk_user_wishlist
                              FOREIGN KEY (user_id) REFERENCES User(user_id)
                                  ON DELETE CASCADE
                                  ON UPDATE CASCADE,
                          CONSTRAINT fk_product_wishlist
                              FOREIGN KEY (product_id) REFERENCES Product(product_id)
                                  ON DELETE CASCADE
                                  ON UPDATE CASCADE,
                          CONSTRAINT uq_wishlist_item UNIQUE (user_id, product_id)
)ENGINE = InnoDB;



INSERT INTO State (state) VALUES
                              ('Cairo'),
                              ('Giza'),
                              ('Alexandria'),
                              ('Dakahlia'),
                              ('Red Sea'),
                              ('Beheira'),
                              ('Fayoum'),
                              ('Gharbia'),
                              ('Ismailia'),
                              ('Menofia'),
                              ('Minya'),
                              ('Qaliubiya'),
                              ('New Valley'),
                              ('Suez'),
                              ('Aswan'),
                              ('Assiut'),
                              ('Beni Suef'),
                              ('Port Said'),
                              ('Damietta'),
                              ('Sharkia'),
                              ('South Sinai'),
                              ('Kafr El Sheikh'),
                              ('Matrouh'),
                              ('Luxor'),
                              ('Qena'),
                              ('North Sinai'),
                              ('Sohag');










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
       
       
       
       CREATE TABLE Size (
                      size INT PRIMARY KEY
) ENGINE = InnoDB;

INSERT INTO Size(size) VALUES (35), (36), (37), (38), (39), (40), (41), (42), (43), (44), (45), (46), (47), (48);

ALTER TABLE ProductInfo
    ADD CONSTRAINT fk_product_size
        FOREIGN KEY (size) REFERENCES Size(size);


CREATE TABLE admin (
   admin_id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(100) NOT NULL,
   email VARCHAR(100) UNIQUE NOT NULL,
   password VARCHAR(255) NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO admin (name, email, password)
VALUES 
  ('Hashim', 'hashim@example.com', '$2a$10$LakzprBDYoHhqS0MuqcrAeJ3V91KltFCUDVppigxj.WLQbMGBkFym'), -- hashim
  ('Yasser', 'yasser@example.com', '$2a$10$gNqAPW0e4Yc6KFv3aGIHiuJPzz9ahcRqD9DCU6Sk1Noq2EOlfmvMS'), -- yasser
  ('Mariam', 'mariam@example.com', '$2a$10$H72lAfItj1FK/qT4G5e2Tuzd4ytKFkBcY1gn46iRCUJnY.5PkujjC'), -- mariam
  ('Nada', 'nada@example.com', '$2a$2a$10$usMVDclFZ/ZwTo2Chf5Ei.6tfCmAsiK3jP/rQEnEjaijcJ.GvEAzC'), -- nada
  ('Ibrahim', 'ibrahim@example.com', '$2a$10$NKNaVoEyP4nR1uyZdvmaNe0yQ9SagyGPF.JtdGWgsG.Gz2XtU4XUG'), -- ibrahim
  ('Youssef', 'youssef@example.com', '$2a$10$GQg13Xd04yv74usOmhEi7uuO4z/EhUPxWeSoFOr7/9Pch2BHsAxRu'); -- youssef


-- V6__insert_orders_2025.sql
-- Insert orders and order items for 2025 (January through June)
-- Based on sold products data from V2__insert_data.sql

-- January 2025 Orders
INSERT INTO `Order` (user_id, total_amount, created_at, address_id, order_status)
VALUES
    (1, 4400.00, '2025-01-02 08:15:32', 1, 'DELIVERED'),
    (3, 7500.00, '2025-01-05 09:22:45', 3, 'DELIVERED'),
    (5, 3200.00, '2025-01-07 12:30:18', 5, 'DELIVERED'),
    (7, 5600.00, '2025-01-09 14:45:23', 7, 'DELIVERED'),
    (9, 2800.00, '2025-01-12 11:05:41', 9, 'DELIVERED'),
    (11, 4500.00, '2025-01-15 13:20:54', 11, 'DELIVERED'),
    (13, 3700.00, '2025-01-17 15:10:37', 13, 'DELIVERED'),
    (2, 5200.00, '2025-01-19 10:30:12', 2, 'DELIVERED'),
    (4, 3900.00, '2025-01-22 13:45:29', 4, 'DELIVERED'),
    (6, 4800.00, '2025-01-24 16:20:33', 6, 'DELIVERED'),
    (8, 5300.00, '2025-01-27 09:15:42', 8, 'DELIVERED'),
    (10, 6200.00, '2025-01-30 12:40:51', 10, 'DELIVERED');

-- January 2025 Order Items
INSERT INTO OrderItem (order_id, product_info_id, quantity, price_at_purchase)
VALUES
    (1, 1, 2, 2200.00),  -- Nike Daily Dash
    (2, 3, 3, 2500.00),  -- Adidas Sprint She
    (3, 26, 1, 3200.00), -- Nike Casual Ace
    (4, 6, 2, 2800.00),  -- Sketchers Power Trainer
    (5, 24, 1, 2800.00), -- Gucci Formal Luxe
    (6, 17, 3, 1500.00), -- Nike Timeless Chic
    (7, 13, 2, 1850.00), -- Gucci Glide Femme
    (8, 4, 2, 2600.00),  -- Nike Daily Dash
    (9, 10, 3, 1300.00), -- Adidas Sprint She
    (10, 21, 2, 2400.00), -- Merrel Precision Trainer
    (11, 27, 1, 5300.00), -- Sketchers Pro Cleats
    (12, 14, 2, 3100.00); -- Adidas Casual Champion

-- February 2025 Orders
INSERT INTO `Order` (user_id, total_amount, created_at, address_id, order_status)
VALUES
    (3, 3600.00, '2025-02-01 09:10:22', 3, 'DELIVERED'),
    (5, 5000.00, '2025-02-03 13:45:37', 5, 'DELIVERED'),
    (7, 4200.00, '2025-02-05 11:22:15', 7, 'DELIVERED'),
    (9, 6000.00, '2025-02-08 15:30:42', 9, 'DELIVERED'),
    (11, 3000.00, '2025-02-10 10:15:19', 11, 'DELIVERED'),
    (13, 5400.00, '2025-02-12 14:05:33', 13, 'DELIVERED'),
    (15, 2400.00, '2025-02-15 16:40:51', 15, 'DELIVERED'),
    (1, 4800.00, '2025-02-18 11:25:18', 1, 'DELIVERED'),
    (2, 5200.00, '2025-02-20 13:40:22', 2, 'DELIVERED'),
    (4, 3800.00, '2025-02-22 09:55:37', 4, 'DELIVERED'),
    (6, 6500.00, '2025-02-25 15:10:45', 6, 'DELIVERED'),
    (8, 4200.00, '2025-02-27 12:30:19', 8, 'DELIVERED'),
    (10, 3400.00, '2025-02-28 17:50:28', 10, 'DELIVERED');

-- February 2025 Order Items
INSERT INTO OrderItem (order_id, product_info_id, quantity, price_at_purchase)
VALUES
    (13, 8, 2, 1800.00),   -- Merrel Swift She
    (14, 10, 2, 2500.00),  -- Adidas Sprint She
    (15, 21, 2, 2100.00), -- Merrel Precision Trainer
    (16, 23, 2, 3000.00), -- Gucci Elite Classic
    (17, 14, 1, 3000.00), -- Adidas Casual Champion
    (18, 27, 2, 2700.00), -- Sketchers Pro Cleats
    (19, 19, 2, 1200.00), -- Nike Formal Essential
    (20, 24, 2, 2400.00), -- Gucci Formal Luxe
    (21, 36, 2, 2600.00), -- Merrel Classic Prime
    (22, 3, 2, 1900.00),  -- Adidas Sprint She
    (23, 17, 3, 2166.67), -- Nike Timeless Chic
    (24, 30, 2, 2100.00), -- Sketchers Sprint She II
    (25, 8, 2, 1700.00);  -- Merrel Swift She

-- March 2025 Orders
INSERT INTO `Order` (user_id, total_amount, created_at, address_id, order_status)
VALUES
    (2, 5100.00, '2025-03-01 10:30:45', 2, 'DELIVERED'),
    (4, 3400.00, '2025-03-03 14:15:28', 4, 'DELIVERED'),
    (6, 6800.00, '2025-03-05 09:45:33', 6, 'DELIVERED'),
    (8, 2000.00, '2025-03-07 16:20:42', 8, 'DELIVERED'),
    (10, 4800.00, '2025-03-09 11:35:19', 10, 'DELIVERED'),
    (12, 3000.00, '2025-03-11 13:50:37', 12, 'DELIVERED'),
    (14, 4500.00, '2025-03-13 15:25:14', 14, 'DELIVERED'),
    (1, 5500.00, '2025-03-15 08:45:30', 1, 'DELIVERED'),
    (3, 3200.00, '2025-03-17 12:15:25', 3, 'DELIVERED'),
    (5, 4800.00, '2025-03-19 16:35:42', 5, 'DELIVERED'),
    (7, 6200.00, '2025-03-21 10:25:18', 7, 'DELIVERED'),
    (9, 3700.00, '2025-03-23 14:50:33', 9, 'DELIVERED'),
    (11, 4900.00, '2025-03-25 11:20:27', 11, 'DELIVERED'),
    (13, 5800.00, '2025-03-27 15:45:19', 13, 'DELIVERED'),
    (15, 3300.00, '2025-03-30 09:30:41', 15, 'DELIVERED');

-- March 2025 Order Items
INSERT INTO OrderItem (order_id, product_info_id, quantity, price_at_purchase)
VALUES
    (26, 30, 3, 1700.00), -- Sketchers Sprint She II
    (27, 11, 2, 1700.00), -- Sketchers Sprint She II
    (28, 24, 2, 3400.00), -- Gucci Formal Luxe
    (29, 33, 1, 2000.00), -- Sketchers Style Glide
    (30, 38, 2, 2400.00), -- Merrel Classic Prime
    (31, 14, 1, 3000.00), -- Adidas Casual Champion
    (32, 17, 3, 1500.00), -- Nike Timeless Chic
    (33, 1, 2, 2750.00),  -- Nike Daily Dash
    (34, 3, 2, 1600.00),  -- Adidas Sprint She
    (35, 26, 1, 4800.00), -- Nike Casual Ace
    (36, 6, 2, 3100.00),  -- Sketchers Power Trainer
    (37, 24, 1, 3700.00), -- Gucci Formal Luxe
    (38, 13, 3, 1633.33), -- Gucci Glide Femme
    (39, 10, 2, 2900.00), -- Adidas Sprint She
    (40, 4, 3, 1100.00);  -- Nike Daily Dash

-- April 2025 Orders
INSERT INTO `Order` (user_id, total_amount, created_at, address_id, order_status)
VALUES
    (2, 4800.00, '2025-04-01 09:30:15', 2, 'DELIVERED'),
    (4, 5600.00, '2025-04-03 13:25:42', 4, 'DELIVERED'),
    (6, 3800.00, '2025-04-05 11:40:33', 6, 'DELIVERED'),
    (8, 5200.00, '2025-04-07 15:15:27', 8, 'DELIVERED'),
    (10, 6400.00, '2025-04-09 10:50:19', 10, 'DELIVERED'),
    (12, 2500.00, '2025-04-11 14:35:38', 12, 'DELIVERED'),
    (14, 4000.00, '2025-04-13 16:55:21', 14, 'DELIVERED'),
    (1, 5800.00, '2025-04-15 08:20:30', 1, 'DELIVERED'),
    (3, 3400.00, '2025-04-17 12:45:37', 3, 'DELIVERED'),
    (5, 4700.00, '2025-04-19 15:10:45', 5, 'DELIVERED'),
    (7, 5900.00, '2025-04-21 09:55:22', 7, 'DELIVERED'),
    (9, 3200.00, '2025-04-23 14:15:33', 9, 'DELIVERED'),
    (11, 4300.00, '2025-04-25 11:40:19', 11, 'DELIVERED'),
    (13, 6200.00, '2025-04-27 16:30:28', 13, 'DELIVERED'),
    (15, 4500.00, '2025-04-29 10:25:44', 15, 'DELIVERED');

-- April 2025 Order Items
INSERT INTO OrderItem (order_id, product_info_id, quantity, price_at_purchase)
VALUES
    (41, 36, 2, 2400.00), -- Merrel Classic Prime
    (42, 6, 2, 2800.00),  -- Sketchers Power Trainer
    (43, 33, 2, 1900.00), -- Gucci Relax Max
    (44, 10, 2, 2600.00), -- Adidas Sprint She
    (45, 26, 2, 3200.00), -- Nike Casual Ace
    (46, 35, 1, 2500.00), -- Nike Classic Elegance
    (47, 14, 4, 1000.00), -- Adidas Casual Champion (smaller items)
    (48, 4, 2, 2900.00),  -- Nike Daily Dash
    (49, 8, 2, 1700.00),  -- Merrel Swift She
    (50, 21, 2, 2350.00), -- Merrel Precision Trainer
    (51, 27, 2, 2950.00), -- Sketchers Pro Cleats
    (52, 19, 2, 1600.00), -- Nike Formal Essential
    (53, 17, 3, 1433.33), -- Nike Timeless Chic
    (54, 24, 2, 3100.00), -- Gucci Formal Luxe
    (55, 30, 3, 1500.00); -- Sketchers Sprint She II

-- May 2025 Orders
INSERT INTO `Order` (user_id, total_amount, created_at, address_id, order_status)
VALUES
    (1, 3800.00, '2025-05-01 10:45:32', 1, 'DELIVERED'),
    (3, 4200.00, '2025-05-02 14:30:47', 3, 'DELIVERED'),
    (5, 5000.00, '2025-05-04 09:20:18', 5, 'DELIVERED'),
    (7, 3600.00, '2025-05-06 16:10:53', 7, 'DELIVERED'),
    (9, 4400.00, '2025-05-08 11:55:22', 9, 'DELIVERED'),
    (11, 6600.00, '2025-05-10 13:40:35', 11, 'DELIVERED'),
    (13, 3000.00, '2025-05-12 15:05:49', 13, 'DELIVERED'),
    (15, 4900.00, '2025-05-14 08:35:28', 15, 'DELIVERED'),
    (2, 5200.00, '2025-05-16 12:20:41', 2, 'DELIVERED'),
    (4, 3500.00, '2025-05-18 16:50:19', 4, 'DELIVERED'),
    (6, 6300.00, '2025-05-20 10:15:37', 6, 'DELIVERED'),
    (8, 4600.00, '2025-05-22 14:40:45', 8, 'DELIVERED'),
    (10, 5500.00, '2025-05-24 11:05:22', 10, 'SHIPPED'),
    (12, 3800.00, '2025-05-26 15:30:18', 12, 'SHIPPED'),
    (14, 4200.00, '2025-05-28 09:55:27', 14, 'SHIPPED'),
    (1, 6100.00, '2025-05-30 13:25:33', 1, 'SHIPPED');

-- May 2025 Order Items
INSERT INTO OrderItem (order_id, product_info_id, quantity, price_at_purchase)
VALUES
    (56, 13, 2, 1900.00), -- Gucci Glide Femme
    (57, 21, 2, 2100.00), -- Merrel Precision Trainer
    (58, 27, 2, 2500.00), -- Sketchers Pro Cleats
    (59, 18, 2, 1800.00), -- Merrel Swift She
    (60, 1, 2, 2200.00),  -- Nike Daily Dash
    (61, 23, 2, 3300.00), -- Gucci Elite Classic
    (62, 38, 2, 1500.00), -- Merrel Chic Classic
    (63, 3, 2, 2450.00),  -- Adidas Sprint She
    (64, 6, 2, 2600.00),  -- Sketchers Power Trainer
    (65, 14, 1, 3500.00), -- Adidas Casual Champion
    (66, 36, 3, 2100.00), -- Merrel Classic Prime
    (67, 26, 2, 2300.00), -- Nike Casual Ace
    (68, 8, 2, 2750.00),  -- Merrel Swift She
    (69, 17, 2, 1900.00), -- Nike Timeless Chic
    (70, 24, 2, 2100.00), -- Gucci Formal Luxe
    (71, 10, 2, 3050.00); -- Adidas Sprint She

-- June 2025 Orders (current month)
INSERT INTO `Order` (user_id, total_amount, created_at, address_id, order_status)
VALUES
    (2, 4600.00, '2025-06-01 09:15:23', 2, 'SHIPPED'),
    (4, 3200.00, '2025-06-02 13:50:37', 4, 'SHIPPED'),
    (6, 5400.00, '2025-06-03 11:25:43', 6, 'SHIPPED'),
    (8, 2800.00, '2025-06-04 15:45:19', 8, 'SHIPPED'),
    (10, 4000.00, '2025-06-05 10:30:25', 10, 'SHIPPED'),
    (12, 6000.00, '2025-06-06 08:20:33', 12, 'CONFIRMED'),
    (14, 3500.00, '2025-06-06 10:15:41', 14, 'CONFIRMED'),
    (1, 5200.00, '2025-06-06 14:45:22', 1, 'CONFIRMED'),
    (3, 4800.00, '2025-06-07 09:30:36', 3, 'CONFIRMED'),
    (5, 3600.00, '2025-06-07 12:55:19', 5, 'CONFIRMED'),
    (7, 6500.00, '2025-06-07 16:20:45', 7, 'PENDING'),
    (9, 4200.00, '2025-06-08 11:05:28', 9, 'PENDING'),
    (11, 5800.00, '2025-06-08 15:35:33', 11, 'PENDING'),
    (13, 3900.00, '2025-06-09 08:50:17', 13, 'PENDING'),
    (15, 4500.00, '2025-06-09 10:25:42', 15, 'PENDING');

-- June 2025 Order Items
INSERT INTO OrderItem (order_id, product_info_id, quantity, price_at_purchase)
VALUES
    (72, 4, 2, 2300.00),  -- Nike Daily Dash
    (73, 8, 2, 1600.00),  -- Merrel Swift She
    (74, 14, 2, 2700.00), -- Adidas Casual Champion
    (75, 24, 1, 2800.00), -- Gucci Formal Luxe
    (76, 30, 2, 2000.00), -- Sketchers Sprint She II
    (77, 26, 2, 3000.00), -- Nike Casual Ace
    (78, 35, 1, 3500.00), -- Nike Classic Elegance
    (79, 1, 2, 2600.00),  -- Nike Daily Dash
    (80, 13, 2, 2400.00), -- Gucci Glide Femme
    (81, 27, 2, 1800.00), -- Sketchers Pro Cleats
    (82, 36, 2, 3250.00), -- Merrel Classic Prime
    (83, 10, 2, 2100.00), -- Adidas Sprint She
    (84, 21, 2, 2900.00), -- Merrel Precision Trainer
    (85, 17, 3, 1300.00), -- Nike Timeless Chic
    (86, 33, 2, 2250.00); -- Sketchers Style Glide
    
    





       