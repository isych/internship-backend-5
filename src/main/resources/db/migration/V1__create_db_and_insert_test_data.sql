drop table if exists role_table cascade;
drop table if exists user_table cascade;

create table role_table (id  serial not null, name varchar(255), primary key (id));
create table user_table (id  serial not null, email varchar(255), fio varchar(255), login varchar(255), password varchar(255), role_id int4, primary key (id));
alter table user_table add constraint FK2khye3lldnmbjjkfdug9k3fp4 foreign key (role_id) references role_table;
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
