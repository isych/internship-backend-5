insert into roles(name) values ('ROLE_ADMIN');
insert into roles(name) values ('ROLE_TECH');
insert into roles(name) values ('ROLE_SUPERADMIN');

insert into employee values (999990, 'Валентин Эдуардович Мамонтов', 'superadmin-1@fortest.ru', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 3);
insert into employee values (999991, 'Меркушева Валерия Евгеньевна', 'superadmin-2@fortest.ru', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 3);
insert into employee values (999992, 'Кулакова Регина Аркадьевна', 'van123_321@mail.ru', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 2);
insert into employee values (999993, 'Георгий Арсеньевич Козлов', 'tech-2@fortest.ru', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 2);
insert into employee values (999994, 'Иванов Иван Иванович', 'van123_321@mail.ru', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 1);
insert into employee values (999995, 'Надежда Юрьевна Суханов', 'admin-2@fortest.ru', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 1);

insert into country values (1,'Ukraine');
insert into country values (2,'Belarus');

insert into city values (1,'Kharkov',1);
insert into city values (2,'Kyiv',1);
insert into city values (3,'Minsk',2);
insert into city values (4,'Brest',2);

insert into tech values (1,'Java');
insert into tech values (2,'Spring Stack');
insert into tech values (3,'JS');
insert into tech values (4,'React');

insert into events values (1001, 'description','2003-2-1','Java & Js 2021',null,null,'2003-1-1','MEETUP','PUBLISHED');
insert into events values (1002, 'description','2003-2-1','Java 2017',null,null,'2003-1-1','INTERNSHIP','PUBLISHED');
insert into events values (1003, 'description','2003-2-1','Spring 2020',null,null,'2003-1-1','TRAINING','NOT_PUBLISHED');
insert into events values (1004, 'description','2003-2-1','JavaScript',null,null,'2003-1-1','MEETUP','PUBLISHED');
insert into events values (1005, 'description','2003-2-1','React JS 2021',null,null,'2003-1-1','INTERNSHIP','ARCHIVED');

insert into event_tech values (1001, 1);
insert into event_tech values (1001, 3);
insert into event_tech values (1002, 1);
insert into event_tech values (1002, 2);
insert into event_tech values (1003, 2);
insert into event_tech values (1004, 3);
insert into event_tech values (1005, 4);

insert into event_city values (1002,3);
insert into event_city values (1002,4);
insert into event_city values (1004,1);
insert into event_city values (1004,2);