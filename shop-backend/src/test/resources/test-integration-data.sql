INSERT INTO brand (code,description) VALUES ('B-1','Brand Test1');
INSERT INTO product (code,description,price,creation_date,brand_code) VALUES ('P-1','Product Test1',20.8,CURRENT_TIMESTAMP(),'B-1');
INSERT INTO product (code,description,price,creation_date,brand_code) VALUES ('P-12','Product Test12',20.8,CURRENT_TIMESTAMP(),'B-1');
