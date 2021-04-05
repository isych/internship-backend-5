create unique index user_table_login_uindex on user_table (login);

insert into role_table(name)
values ('ROLE_ADMIN');
insert into role_table(name)
values ('ROLE_TECH');
insert into role_table(name)
values ('ROLE_SUPERADMIN');

insert into user_table
values (999997, 'test-admin@fortest.ru', 'Иванов Иван Иванович', 'test-admin',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 1);
insert into user_table
values (999998, 'test-user@fortest.ru', 'Петров Петр Петрович', 'test-user',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 2);
insert into user_table
values (999999, 'test-superadmin@fortest.ru', 'Александров Александр Александрович', 'test-superadmin',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 3);

insert into label_table
values (1, 'java');
insert into label_table
values (2, 'js');
insert into label_table
values (3, 'internship');
insert into label_table
values (4, 'vue.js');
insert into label_table
values (5, '.net');
insert into label_table
values (6, 'agile');

insert into event_table
values (1, 'description-text', '2003-1-1', 'event 1', '2003-1-1', 'meetup');

insert into event_table
values (2, 'description-text', '2003-1-1', 'event 2', '2003-1-1', 'internship');

insert into event_table
values (3, 'description-text', '2003-1-1', 'event 3', '2003-1-1', 'training');

insert into event_label values (1, 1);
insert into event_label values (1, 2);
insert into event_label values (1, 3);

insert into event_label values (2, 4);
insert into event_label values (2, 5);
insert into event_label values (2, 6);

insert into event_label values (3, 6);
insert into event_label values (3, 3);
insert into event_label values (3, 1);
