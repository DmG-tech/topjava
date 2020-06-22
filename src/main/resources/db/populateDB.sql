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

INSERT INTO meals (user_id, description, datetime, calories)
VALUES (100000, 'завтрак', '2020-02-20 09:05:30', 500),
       (100000, 'обед', '2020-02-20 13:10:25', 500),
       (100000, 'ужин', '2020-02-20 19:25:30', 500),

       (100000, 'завтрак', '2020-03-20 10:05:30', 600),
       (100000, 'обед', '2020-03-20 14:10:25', 700),
       (100000, 'ужин', '2020-03-20 16:25:30', 800),

       (100000, 'завтрак', '2020-04-20 08:05:30', 1000),
       (100000, 'обед', '2020-04-20 12:10:25', 300),
       (100000, 'ужин', '2020-04-20 20:25:30', 100),

       (100000, 'завтрак', '2020-05-20 09:20:30', 800),
       (100000, 'обед', '2020-05-20 13:40:25', 900),
       (100000, 'ужин', '2020-05-20 19:50:30', 1000);
