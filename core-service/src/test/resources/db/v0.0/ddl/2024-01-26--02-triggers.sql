--liquibase formatted sql

--changeset sergey:10
CREATE OR REPLACE FUNCTION update_product_timestamp()
RETURNS TRIGGER AS '
BEGIN
    NEW.updated = CURRENT_TIMESTAMP;
RETURN NEW;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER update_product_trigger
    BEFORE UPDATE ON Products
    FOR EACH ROW
    EXECUTE FUNCTION update_product_timestamp();

--changeset sergey:11
CREATE OR REPLACE FUNCTION update_review_timestamp()
RETURNS TRIGGER AS '
BEGIN
    NEW.updated = CURRENT_TIMESTAMP;
RETURN NEW;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER update_review_trigger
    BEFORE UPDATE ON Reviews
    FOR EACH ROW
    EXECUTE FUNCTION update_review_timestamp();

