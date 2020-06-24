DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM meals;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (id, user_id, description, datetime, calories)
VALUES (1, 100000, 'завтрак', '2020-02-20 09:05:30', 500),
       (2, 100000, 'обед', '2020-02-22 13:10:25', 1000),

       (3, 100001, 'завтрак', '2020-05-20 09:20:30', 800);
