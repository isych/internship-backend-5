create unique index user_table_login_uindex on user_table (login);

insert into role_table(name) values ('ROLE_ADMIN');
insert into role_table(name) values ('ROLE_TECH');
	
insert into user_table values (999998, 'test-admin@fortest.ru', 'Иванов Иван Иванович', 'test-admin', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 1);
into into user_table values (999999, 'test-user@fortest.ru', 'Петров Петр Петрович', 'test-user', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 2);
