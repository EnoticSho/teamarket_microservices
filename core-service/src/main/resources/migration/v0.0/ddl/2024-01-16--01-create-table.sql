--liquibase formatted sql

--changeset sergey:1
CREATE TABLE Categories
(
    category_id SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE Products
(
    product_id     SERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    description    TEXT,
    price          DECIMAL(10, 2),
    category_id    INT,
    stock_quantity INT,
    effect         TEXT,
    created        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES Categories (category_id)
);


CREATE TABLE ProductImages
(
    image_id   SERIAL PRIMARY KEY,
    product_id INT,
    image_url  VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES Products (product_id) ON DELETE CASCADE
);


CREATE TABLE Roles
(
    role_id   SERIAL PRIMARY KEY,
    role_name VARCHAR(15) NOT NULL
);

CREATE TABLE Users
(
    user_id       SERIAL PRIMARY KEY,
    email         VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255)        NOT NULL,
    full_name     VARCHAR(255),
    address       TEXT,
    role_id       INT REFERENCES Roles (role_id)
);

CREATE TABLE Orders
(
    order_id    SERIAL PRIMARY KEY,
    user_email  VARCHAR(255) NOT NULL,
    order_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status      VARCHAR(255) CHECK (status IN ('REGISTERED', 'PAID')),
    total_price DECIMAL(10, 2)
);


CREATE TABLE OrderDetails
(
    order_detail_id SERIAL PRIMARY KEY,
    order_id        INT,
    product_id      INT,
    quantity        INT,
    price           DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES Orders (order_id) ON DELETE CASCADE
);

CREATE TABLE Reviews
(
    review_id   SERIAL PRIMARY KEY,
    product_id  INT                                     NOT NULL,
    Email       VARCHAR(255)                            NOT NULL,
    rating      INT CHECK (rating >= 0 AND rating <= 5) NOT NULL,
    comment     TEXT,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
