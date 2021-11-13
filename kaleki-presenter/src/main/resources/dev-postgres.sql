insert into users (id, username, email, password, role) values
(1, 'Бадридзе Ашот', 'ashot@mega.ru', '$2a$10$cG9F9n9PwmHGxhUVJ9IGq.ou8Z8gE4ESzdGm8aBDF0UXYMHEDXWHG', 'ROLE_MANAGER'),
(2, 'Смит Джон', 'j_smith@rosatom.ru', '$2a$10$cG9F9n9PwmHGxhUVJ9IGq.ou8Z8gE4ESzdGm8aBDF0UXYMHEDXWHG', 'ROLE_WORKER'),
(3, 'Юзер Кот', 'user@kot.ru', '$2a$10$cG9F9n9PwmHGxhUVJ9IGq.ou8Z8gE4ESzdGm8aBDF0UXYMHEDXWHG', 'ROLE_WORKER'),
(4, 'Соловьёв Дмитрий Ксандрович', 'sdx@big.data', '$2a$10$cG9F9n9PwmHGxhUVJ9IGq.ou8Z8gE4ESzdGm8aBDF0UXYMHEDXWHG', 'ROLE_MANAGER');

insert into companies (id, name, CEO_id, address) values
(1, 'Cofi', 1, 'Калужская область, город Серебряные Пруды, въезд Ломоносова, 88'),
(2, 'Просрочка', 4, 'Калининградская область, город Подольск, бульвар Балканская, 92');

insert into positions (id, name) values
(1, 'CEO'),
(2, 'Developer');

insert into projects (id, name, company_id, start_date, status) values
(1, 'CofiNYA', 1, date '2001-10-23', 'ACTIVE'),
(2, '3Metra', 2, date '2020-09-28', 'ACTIVE');

insert into users_projects (user_id, projects_id) values
(1, 1),
(2, 1),
(3, 1),
(4, 2);

insert into users_companies (user_id, companies_id) values
(1, 1),
(2, 1),
(3, 1),
(4, 2);

insert into users_on_projects (user_id, project_id, position_id, rate, base_salary) values
(1, 1, 1, 1.0, 75000.0),
(3, 1, 2, 0.75, 50000.0),
(4, 2, 1, 1.0, 150000.0);

