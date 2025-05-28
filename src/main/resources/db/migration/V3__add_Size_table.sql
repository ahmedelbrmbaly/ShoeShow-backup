CREATE TABLE Size (
                      size INT PRIMARY KEY
) ENGINE = InnoDB;

INSERT INTO Size(size) VALUES (35), (36), (37), (38), (39), (40), (41), (42), (43), (44), (45), (46), (47), (48);

ALTER TABLE ProductInfo
    ADD CONSTRAINT fk_product_size
        FOREIGN KEY (size) REFERENCES Size(size);

