drop table if exists roles cascade;
drop table if exists users cascade;

create table roles (id  serial not null, name varchar(255), primary key (id));
create table users (id  serial not null, email varchar(255), fio varchar(255), login varchar(255), password varchar(255), role_id int4, primary key (id));
alter table users add constraint FK2khye3lldnmbjjkfdug9k3fp4 foreign key (role_id) references roles;
create unique index users_login_uindex on users (login);

insert into roles(name)
values ('ROLE_ADMIN');
insert into roles(name)
values ('ROLE_TECH');
insert into roles(name)
values ('ROLE_SUPERADMIN');

insert into users
values (999997, 'test-admin@fortest.ru', 'Иванов Иван Иванович', 'test-admin',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 1);
insert into users
values (999998, 'test-user@fortest.ru', 'Петров Петр Петрович', 'test-user',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 2);
insert into users
values (999999, 'test-superadmin@fortest.ru', 'Александров Александр Александрович', 'test-superadmin',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 3);
