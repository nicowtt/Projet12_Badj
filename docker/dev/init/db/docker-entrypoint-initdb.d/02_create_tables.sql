
CREATE TABLE badj.addresses (
                           id INT AUTO_INCREMENT NOT NULL,
                           street VARCHAR(100) NOT NULL,
                           postal_code INT NOT NULL,
                           city VARCHAR(100) NOT NULL,
                           PRIMARY KEY (id)
);


CREATE TABLE badj.sales (
                       id INT AUTO_INCREMENT NOT NULL,
                       type VARCHAR(100) NOT NULL,
                       description VARCHAR(100),
                       date_begin DATE NOT NULL,
                       date_end DATE NOT NULL,
                       address_id INT NOT NULL,
                       PRIMARY KEY (id)
);


CREATE TABLE badj.users (
                       id INT AUTO_INCREMENT NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,
                       password VARCHAR(150) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       phone VARCHAR(50) NOT NULL,
                       is_voluntary BOOLEAN NOT NULL,
                       is_responsible BOOLEAN NOT NULL,
                       address_id INT NOT NULL,
                       PRIMARY KEY (id)
);


CREATE UNIQUE INDEX users_idx
    ON badj.users
        ( email );

CREATE TABLE badj.articles (
                          id INT AUTO_INCREMENT NOT NULL,
                          category VARCHAR(100) NOT NULL,
                          type VARCHAR(100) NOT NULL,
                          sale_number INT NOT NULL,
                          price DECIMAL(7,2) NOT NULL,
                          date_record DATETIME NOT NULL,
                          is_validate_to_sell BOOLEAN NOT NULL,
                          is_sold BOOLEAN NOT NULL,
                          is_stolen BOOLEAN NOT NULL,
                          is_return_owner BOOLEAN NOT NULL,
                          user_id INT NOT NULL,
                          sale_id INT NOT NULL,
                          PRIMARY KEY (id)
);


CREATE TABLE badj.objects (
                         article_id INT NOT NULL,
                         brand VARCHAR(100) NOT NULL,
                         color VARCHAR(100) NOT NULL,
                         comment VARCHAR(200),
                         PRIMARY KEY (article_id)
);


CREATE TABLE badj.books (
                       article_id INT NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       author VARCHAR(100) NOT NULL,
                       comment VARCHAR(200),
                       PRIMARY KEY (article_id)
);


CREATE TABLE badj.toys (
                      article_id INT NOT NULL,
                      brand VARCHAR(100) NOT NULL,
                      color VARCHAR(100) NOT NULL,
                      comment VARCHAR(200),
                      PRIMARY KEY (article_id)
);


CREATE TABLE badj.clothes (
                         article_id INT NOT NULL,
                         size VARCHAR(50) NOT NULL,
                         gender VARCHAR(50) NOT NULL,
                         material VARCHAR(100),
                         color VARCHAR(100) NOT NULL,
                         comment VARCHAR(200),
                         PRIMARY KEY (article_id)
);


ALTER TABLE badj.users ADD CONSTRAINT addresses_users_fk
    FOREIGN KEY (address_id)
        REFERENCES addresses (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION;

ALTER TABLE badj.sales ADD CONSTRAINT addresses_sales_fk
    FOREIGN KEY (address_id)
        REFERENCES addresses (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE badj.articles ADD CONSTRAINT sales_articles_fk
    FOREIGN KEY (sale_id)
        REFERENCES sales (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE badj.articles ADD CONSTRAINT users_articles_fk
    FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE badj.clothes ADD CONSTRAINT articles_clothes_fk
    FOREIGN KEY (article_id)
        REFERENCES articles (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE badj.toys ADD CONSTRAINT articles_toys_fk
    FOREIGN KEY (article_id)
        REFERENCES articles (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE badj.books ADD CONSTRAINT articles_books_fk
    FOREIGN KEY (article_id)
        REFERENCES articles (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

ALTER TABLE badj.objects ADD CONSTRAINT articles_objects_fk
    FOREIGN KEY (article_id)
        REFERENCES articles (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;
