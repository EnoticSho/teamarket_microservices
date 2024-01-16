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
    FOREIGN KEY (category_id) REFERENCES Categories (category_id)
);

CREATE TABLE ProductImages
(
    image_id   SERIAL PRIMARY KEY,
    product_id INT,
    image_url  VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES Products (product_id)
);

CREATE TABLE Users
(
    user_id       SERIAL PRIMARY KEY,
    email         VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255)        NOT NULL,
    full_name     VARCHAR(255),
    address       TEXT
);

CREATE TABLE Roles
(
    role_id   SERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL
);

CREATE TABLE Users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

CREATE TABLE Orders
(
    order_id    SERIAL PRIMARY KEY,
    user_id     INT,
    order_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status      VARCHAR(255),
    total_price DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES Users (user_id)
);


CREATE TABLE OrderDetails
(
    order_detail_id SERIAL PRIMARY KEY,
    order_id        INT,
    product_id      INT,
    quantity        INT,
    price           DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES Orders (order_id),
    FOREIGN KEY (product_id) REFERENCES Products (product_id)
);

CREATE TABLE Reviews
(
    review_id   SERIAL PRIMARY KEY,
    product_id  INT,
    user_id     INT,
    rating      INT,
    comment     TEXT,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES Products (product_id),
    FOREIGN KEY (user_id) REFERENCES Users (user_id)
);
