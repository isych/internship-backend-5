insert into roles(name) values ('ROLE_ADMIN');
insert into roles(name) values ('ROLE_TECH');
insert into roles(name) values ('ROLE_SUPERADMIN');

insert into users values (999989, 'test2-superadmin@fortest.ru', 'Меркушева Валерия Евгеньевна', 'test2-superadmin', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 3);
insert into users values (999990, 'test1-superadmin@fortest.ru', 'Валентин Эдуардович Мамонтов', 'test1-superadmin', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 3);
insert into users values (999991, 'test3-user@fortest.ru', 'Кулакова Регина Аркадьевна', 'test3-user', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 2);
insert into users values (999992, 'test2-user@fortest.ru', 'Георгий Арсеньевич Козлов', 'test2-user', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 2);
insert into users values (999993, 'test1-user@fortest.ru', 'Кузнецова Арина Вадимовна', 'test1-user', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 2);
insert into users values (999994, 'test3-admin@fortest.ru', 'Елизавета Денисовна Зайцева', 'test3-admin', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 1);
insert into users values (999995, 'test2-admin@fortest.ru', 'Александр Лукич Гаврилов', 'test2-admin', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 1);
insert into users values (999996, 'test1-admin@fortest.ru', 'Надежда Юрьевна Суханова', 'test1-admin', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 1);
insert into users values (999997, 'test-admin@fortest.ru', 'Иванов Иван Иванович', 'test-admin', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 1);
insert into users values (999998, 'test-user@fortest.ru', 'Петров Петр Петрович', 'test-user', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 2);
insert into users values (999999, 'test-superadmin@fortest.ru', 'Александров Александр Александрович', 'test-superadmin', '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', 3);

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

insert into events values (1001, 'description','2003-2-1','Java & Js 2021','pic_name','pic_url','2003-1-1','MEETUP');
insert into events values (1002, 'description','2003-2-1','Java 2017','pic_name','pic_url','2003-1-1','INTERNSHIP');
insert into events values (1003, 'description','2003-2-1','Spring 2020','pic_name','pic_url','2003-1-1','TRAINING');
insert into events values (1004, 'description','2003-2-1','JavaScript','pic_name','pic_url','2003-1-1','MEETUP');
insert into events values (1005, 'description','2003-2-1','React JS 2021','pic_name','pic_url','2003-1-1','INTERNSHIP');

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