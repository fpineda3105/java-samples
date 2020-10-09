--- Drop tables ---
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
--- Create tables ---
CREATE TABLE orders (
    id INTEGER NOT NULL AUTO_INCREMENT,
    product_id INTEGER NOT NULL,
    customer_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    status INTEGER NOT NULL,
    CONSTRAINT PK_ORDER PRIMARY KEY(id)
);
CREATE TABLE products (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    description varchar(100) NOT NULL,
    price decimal(6, 2) NOT NULL,
    inventory integer NOT NULL,
    CONSTRAINT PK_PRODUCT PRIMARY KEY(id)
);