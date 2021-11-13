insert into users (id, username, email, password, role) values
(1, 'Admin', 'admin@molodec.ru', '$2a$10$cG9F9n9PwmHGxhUVJ9IGq.ou8Z8gE4ESzdGm8aBDF0UXYMHEDXWHG', 'ROLE_MANAGER'),
(2, 'Бадридзе Ашот', 'ashot@mega.ru', '$2a$10$cG9F9n9PwmHGxhUVJ9IGq.ou8Z8gE4ESzdGm8aBDF0UXYMHEDXWHG', 'ROLE_MANAGER'),
(3, 'Смит Джон', 'j_smith@rosatom.ru', '$2a$10$cG9F9n9PwmHGxhUVJ9IGq.ou8Z8gE4ESzdGm8aBDF0UXYMHEDXWHG', 'ROLE_WORKER'),
(4, 'Юзер Кот', 'user@kot.ru', '$2a$10$cG9F9n9PwmHGxhUVJ9IGq.ou8Z8gE4ESzdGm8aBDF0UXYMHEDXWHG', 'ROLE_WORKER'),
(5, 'Соловьёв Дмитрий Ксандрович', 'sdx@big.data', '$2a$10$cG9F9n9PwmHGxhUVJ9IGq.ou8Z8gE4ESzdGm8aBDF0UXYMHEDXWHG', 'ROLE_MANAGER');


insert into companies (id, name, CEO_id, address) values
(1, 'Base company', 1, ''),
(2, 'Cofi', 2, 'Калужская область, город Серебряные Пруды, въезд Ломоносова, 88'),
(3, 'Просрочка', 5, 'Калининградская область, город Подольск, бульвар Балканская, 92');

update users set company = 'Base company' where id = 1;
update users set company = 'Cofi' where id = 2;
update users set company = 'Cofi' where id = 3;
update users set company = 'Cofi' where id = 4;
update users set company = 'Просрочка' where id = 5;

insert into positions (id, name) values
(1, 'CEO'),
(2, 'Developer');

insert into projects (id, name, company_id, start_date, status) values
(1, 'CofiNYA', 2, date '2001-10-23', 'ACTIVE'),
(2, '3Metra', 3, date '2020-09-28', 'ACTIVE');

insert into users_on_projects (user_id, project_id, position_id, rate, base_salary) values
(2, 1, 1, 1.0, 75000.0),
(4, 1, 2, 0.75, 50000.0),
(5, 2, 1, 1.0, 150000.0);
