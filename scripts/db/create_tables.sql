DROP TABLE IF EXISTS inv_products, inv_product_categories;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE inv_products (
	"id" uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
	"name" varchar(50) NOT NULL,
	"quantity" int,
	category_id uuid,
	created_on TIMESTAMP WITHOUT TIME ZONE default now()
);

CREATE TABLE inv_product_categories(
	"id" uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
	"name" VARCHAR(50) UNIQUE NOT NULL,
	created_on TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);

ALTER TABLE inv_products
	ADD CONSTRAINT inv_products_category_id_fk 
	FOREIGN KEY (category_id)
	REFERENCES inv_product_categories;
	




	
