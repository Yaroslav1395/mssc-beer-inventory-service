CREATE TABLE BEER_INVENTORY_SERVICE.beer_inventories (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    version BIGINT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    beer_id UUID,
    upc VARCHAR(255),
    quantity_on_hand INT DEFAULT 0
);