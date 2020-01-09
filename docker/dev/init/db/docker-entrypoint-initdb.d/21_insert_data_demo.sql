-- Remplissage base de données--

-- 1/adresse (1-4)-- ->id1 -> responsable

INSERT INTO badj.addresses
(street, postal_code, city)
    VALUE
    ('2 route de l''ouest', 34300, 'Agde');

-- 1/adresse (2-4)-- ->id2 -> benevole

INSERT INTO badj.addresses
(street, postal_code, city)
    VALUE
    ('5 boulevard marechal', 34300, 'Agde');

-- 1/adresse (3-4)-- ->id3 -> client
INSERT INTO badj.addresses
(street, postal_code, city)
    VALUE
    ('15 allée marc aurèle', 34300, 'Agde');

-- 1/adresse (4-4)-- ->id4 -> bourse(sale)
INSERT INTO badj.addresses
(street, postal_code, city)
    VALUE
    ('6 rue brescou', 34300, 'Agde');

-- 2/utilisateur (1-3)-- ->id1 -> responsable

INSERT INTO badj.users
(name, last_name, password, email, phone, is_voluntary, is_responsible, address_id)
VALUES
('bruce', 'lee','$2a$10$ZrNev/FCEyfKp3.Zc/irx.OrtFuqL7X6t.tJytIOiYLQ458k2jasO', 'bruce.lee@gmail.com', '0612121212', false, true, 1);

-- 2/utilisateur (2-3)-- ->id2 -> benevole

INSERT INTO badj.users
(name, last_name, password, email, phone, is_voluntary, is_responsible, address_id)
VALUES
('jason', 'statam','$2a$10$Loe8.FEvR6CQL6GwhzX2ieJKd0SwkZi1ChwEY36i1fQxh.7zfiyTS', 'jason.statam@gmail.com', '0615151515', false, false, 2);

-- 2/utilisateur (3-3) ->id3 -> cliente

INSERT INTO badj.users
(name, last_name, password, email, phone, is_voluntary, is_responsible, address_id)
VALUES
('julia', 'roberts','$2a$10$izLvX7nRBB6qohlBCiGEzOHwlCLRoUwAJ0hChn.JAnhXDZp2MT3P.', 'julia.roberts@gmail.com', '0620202020', true, false, 3);

-- 3/bourse(sales) (1-3) ->id1

INSERT INTO badj.sales
(type, description, date_begin, date_end, address_id)
VALUES
('Bourse de printemps', 'Vêtements enfants','2020-03-09', '2020-03-13', 4);
-- 3/bourse(sales) (2-3) ->id2

INSERT INTO badj.sales
(type, description, date_begin, date_end, address_id)
VALUES
('Bourse de printemps', 'Vêtements adulte','2020-04-13', '2020-04-17', 4);

-- 3/bourse(sales) (3-3) ->id3

INSERT INTO badj.sales
(type, description, date_begin, date_end, address_id)
VALUES
('Bourse de noël', 'jouets Livres Cadeaux Bijoux', '2020-11-16', '2020-11-20', 4);

-- 4-1-1/Article () ->id1 (un benevole d'id 2 enregistre un article de type vetement dans la bourse d'id 1)

INSERT INTO badj.articles
(category, type, sale_number, price, date_record, is_validate_to_sell, is_sold, is_stolen, is_return_owner, user_id, sale_id)
VALUES
('vêtement', 'jean', 1, 15, '2019-11-25 15:16:25', true, false, false, false, 2, 1);

-- 4-1-2/vetement () ->id1 (un benevole d'id 2 enregistre un article de type vetement dans la bourse d'id 1)

INSERT INTO badj.clothes
(article_id, size, gender, material, color, comment)
VALUES
(1, '9 ans', 'garçons', 'jean', 'bleu', null);

-- 4-2-1/Article () ->id2 (un benevole d'id 2 enregistre un autre article de type vetement dans la bourse d'id 1)

INSERT INTO badj.articles
(category, type, sale_number, price, date_record, is_validate_to_sell, is_sold, is_stolen, is_return_owner, user_id, sale_id)
VALUES
('chaussure', 'basket', 2, 10, '2019-11-25 15:30:32', true, false, false, false, 2, 1);

-- 4-2-2/vetement () ->id2 (un benevole d'id 2 enregistre un article de type vetement dans la bourse d'id 1)

INSERT INTO badj.clothes
(article_id, size, gender, material, color, comment)
VALUES
(2, '9 ans', 'garçons', 'cuir', 'rouge', null);

-- 4-3-1/Article () ->id3 (un benevole d'id 2 enregistre un autre article de type jouet dans la bourse d'id 3)

INSERT INTO badj.articles
(category, type, sale_number, price, date_record, is_validate_to_sell, is_sold, is_stolen, is_return_owner, user_id, sale_id)
VALUES
('jouet', 'cheval à bascule', 1, 50, '2019-11-27 18:30:32', true, false, false, false, 2, 3);

-- 4-2-2/vetement () ->id2 (un benevole d'id 2 enregistre un article de type vetement dans la bourse d'id 1)

INSERT INTO badj.toys
(article_id, brand, color, comment)
VALUES
(3, 'elCamino', 'marron', null);
