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
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    price FLOAT NOT NULL,
    inventory INTEGER NOT NULL,
    CONSTRAINT PK_PRODUCT PRIMARY KEY(id)
);