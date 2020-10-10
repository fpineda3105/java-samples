DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    description varchar(100) NOT NULL,
    price decimal(6, 2) NOT NULL,
    inventory integer NOT NULL,
    CONSTRAINT PK_PRODUCT PRIMARY KEY(id)
);