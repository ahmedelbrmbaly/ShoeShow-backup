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