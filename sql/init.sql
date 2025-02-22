CREATE TABLE order_status (
                              id serial NOT NULL,
                              description varchar NULL,
                              CONSTRAINT order_status_pk PRIMARY KEY (id)
);
CREATE TABLE customer (
                          id serial NOT NULL,
                          name varchar NULL,
                          email varchar NULL,
                          phone varchar NULL,
                          address varchar NULL,
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
CREATE TABLE order_product (
                               id serial NOT NULL,
                               id_order int NULL,
                               id_product int NULL,
                               CONSTRAINT order_product_pk PRIMARY KEY (id),
                               CONSTRAINT order_product_unique UNIQUE (id_order, id_product)
);
CREATE TABLE time_slot (
                           id serial NOT NULL,
                           start_date_time timestamp with time zone NULL,
                           end_date_time timestamp with time zone NULL,
                           order_id int NULL,
                           CONSTRAINT time_slot_pk PRIMARY KEY (id),
                           CONSTRAINT time_slot_order_fk FOREIGN KEY (order_id) REFERENCES "order"(id)
);

