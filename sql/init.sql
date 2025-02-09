CREATE TABLE order_status (
                                   id serial NOT NULL,
                                   description varchar NULL,
                                   CONSTRAINT order_status_pk PRIMARY KEY (id)
);
CREATE TABLE customer (
                                 id serial NOT NULL,
                                 "name" varchar NULL,
                                 CONSTRAINT customer_pk PRIMARY KEY (id)
);
CREATE TABLE order (
                                id serial NOT NULL,
                                customer int NULL,
                                status int NULL,
                                CONSTRAINT order_pk PRIMARY KEY (id),
                                CONSTRAINT order_customer_fk FOREIGN KEY (customer) REFERENCES customer(id),
                                CONSTRAINT order_order_status_fk FOREIGN KEY (status) REFERENCES order_status(id)
);
CREATE TABLE product (
                                id serial NOT NULL,
                                "name" varchar NULL,
                                CONSTRAINT product_pk PRIMARY KEY (id)
);
