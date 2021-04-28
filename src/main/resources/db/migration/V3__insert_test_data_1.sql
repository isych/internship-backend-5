insert into roles
values ('602f347c-a79e-11eb-bcbc-0242ac130001', 'ROLE_ADMIN');
insert into roles
values ('602f347c-a79e-11eb-bcbc-0242ac130002', 'ROLE_TECH');
insert into roles
values ('602f347c-a79e-11eb-bcbc-0242ac130003', 'ROLE_SUPERADMIN');

insert into employee
values ('7889d51e-a79d-11eb-bcbc-0242ac130001', 'Валентин Эдуардович Мамонтов', 'superadmin-1@fortest.ru',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', '602f347c-a79e-11eb-bcbc-0242ac130003');
insert into employee
values ('7889d51e-a79d-11eb-bcbc-0242ac130002', 'Меркушева Валерия Евгеньевна', 'superadmin-2@fortest.ru',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', '602f347c-a79e-11eb-bcbc-0242ac130003');
insert into employee
values ('7889d51e-a79d-11eb-bcbc-0242ac130003', 'Кулакова Регина Аркадьевна', 'tech-1@fortest.ru',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', '602f347c-a79e-11eb-bcbc-0242ac130002');
insert into employee
values ('7889d51e-a79d-11eb-bcbc-0242ac130004', 'Георгий Арсеньевич Козлов', 'tech-2@fortest.ru',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', '602f347c-a79e-11eb-bcbc-0242ac130002');
insert into employee
values ('7889d51e-a79d-11eb-bcbc-0242ac130005', 'Иванов Иван Иванович', 'admin-1@fortest.ru',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', '602f347c-a79e-11eb-bcbc-0242ac130001');
insert into employee
values ('7889d51e-a79d-11eb-bcbc-0242ac130006', 'Надежда Юрьевна Суханов', 'admin-2@fortest.ru',
        '$2a$10$dvup/KVkxe/puUu6q8xh8OI2noobcb1dQ1bCjcRVI/P1ovDBlVT4e', '602f347c-a79e-11eb-bcbc-0242ac130001');

insert into country
values ('0698ffaa-a79f-11eb-bcbc-0242ac130001', 'Ukraine');
insert into country
values ('0698ffaa-a79f-11eb-bcbc-0242ac130002', 'Belarus');
insert into country
values ('0698ffaa-a79f-11eb-bcbc-0242ac130003', 'Uzbekistan');
insert into country
values ('0698ffaa-a79f-11eb-bcbc-0242ac130004', 'Poland');
insert into country
values ('0698ffaa-a79f-11eb-bcbc-0242ac130005', 'Lithuania');
insert into country
values ('0698ffaa-a79f-11eb-bcbc-0242ac130006', 'Germany');

insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130001', 'Kharkov', '0698ffaa-a79f-11eb-bcbc-0242ac130001');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130002', 'Kyiv', '0698ffaa-a79f-11eb-bcbc-0242ac130001');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130003', 'Minsk', '0698ffaa-a79f-11eb-bcbc-0242ac130002');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130004', 'Brest', '0698ffaa-a79f-11eb-bcbc-0242ac130002');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130005', 'Lviv', '0698ffaa-a79f-11eb-bcbc-0242ac130001');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130006', 'Odesa', '0698ffaa-a79f-11eb-bcbc-0242ac130001');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130007', 'Slutsk', '0698ffaa-a79f-11eb-bcbc-0242ac130002');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130008', 'Dzyarzhynsk', '0698ffaa-a79f-11eb-bcbc-0242ac130002');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130009', 'Tashkent', '0698ffaa-a79f-11eb-bcbc-0242ac130003');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130010', 'Samarqand', '0698ffaa-a79f-11eb-bcbc-0242ac130003');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130011', 'Białystok', '0698ffaa-a79f-11eb-bcbc-0242ac130004');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130012', 'Bytom', '0698ffaa-a79f-11eb-bcbc-0242ac130004');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130013', 'Namangan', '0698ffaa-a79f-11eb-bcbc-0242ac130003');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130014', 'Fergana', '0698ffaa-a79f-11eb-bcbc-0242ac130003');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130015', 'Chełm', '0698ffaa-a79f-11eb-bcbc-0242ac130004');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130016', 'Gdańsk', '0698ffaa-a79f-11eb-bcbc-0242ac130004');

insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130017', 'Vilnius', '0698ffaa-a79f-11eb-bcbc-0242ac130005');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130018', 'Kaunas', '0698ffaa-a79f-11eb-bcbc-0242ac130005');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130019', 'Berlin', '0698ffaa-a79f-11eb-bcbc-0242ac130006');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130020', 'Hamburg', '0698ffaa-a79f-11eb-bcbc-0242ac130006');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130021', 'Alytus', '0698ffaa-a79f-11eb-bcbc-0242ac130005');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130022', 'Jonava', '0698ffaa-a79f-11eb-bcbc-0242ac130005');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130023', 'Munich', '0698ffaa-a79f-11eb-bcbc-0242ac130006');
insert into city
values ('8ba50dc4-a79f-11eb-bcbc-0242ac130024', 'Stuttgart', '0698ffaa-a79f-11eb-bcbc-0242ac130006');


insert into tech
values ('f8fcba8e-a79f-11eb-bcbc-0242ac130001', 'Java');
insert into tech
values ('f8fcba8e-a79f-11eb-bcbc-0242ac130002', 'Spring Stack');
insert into tech
values ('f8fcba8e-a79f-11eb-bcbc-0242ac130003', 'JavaScript');
insert into tech
values ('f8fcba8e-a79f-11eb-bcbc-0242ac130004', 'React');
insert into tech
values ('f8fcba8e-a79f-11eb-bcbc-0242ac130005', 'Vue.js');
insert into tech
values ('f8fcba8e-a79f-11eb-bcbc-0242ac130006', '.Net');
insert into tech
values ('f8fcba8e-a79f-11eb-bcbc-0242ac130007', 'Groovy');
insert into tech
values ('f8fcba8e-a79f-11eb-bcbc-0242ac130008', 'CSS');
insert into tech
values ('f8fcba8e-a79f-11eb-bcbc-0242ac130009', 'Html');
insert into tech
values ('f8fcba8e-a79f-11eb-bcbc-0242ac130010', 'webpack');


insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130001',
        'Java is a high-level programming language developed by Sun Microsystems. The Java syntax is similar to C++, but is strictly an object-oriented programming language. ... For example, most Java programs contain classes, which are used to define objects, and methods, which are assigned to individual classes.',
        '2003-2-1', 'Java & Js 2019', null, null, '2003-1-1',
        'MEETUP', 'PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130002',
        'Java is a high-level programming language developed by Sun Microsystems. The Java syntax is similar to C++, but is strictly an object-oriented programming language. ... For example, most Java programs contain classes, which are used to define objects, and methods, which are assigned to individual classes.',
        '2003-2-1', 'Java 2019', null, null, '2003-1-1',
        'INTERNSHIP', 'PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130003',
        'Being Spring developers, we naturally choose components from the Spring stack to do all the heavy lifting. After all, we have the concept etched out, so we''re already halfway there.',
        '2003-2-1', 'Spring 2019', null, null, '2003-1-1',
        'TRAINING', 'NOT_PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130004',
        'JavaScript® (often shortened to JS) is a lightweight, interpreted, object-oriented language with first-class functions, and is best known as the scripting language for Web pages, but it''s used in many non-browser environments as well. ... JavaScript can function as both a procedural and an object oriented language.',
        '2003-2-1', 'JavaScript 2019', null, null, '2003-1-1',
        'MEETUP', 'PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130005',
        'React (also known as React.js or ReactJS) is an open-source, front end, JavaScript library for building user interfaces or UI components. ',
        '2003-2-1', 'React JS 2019', null, null, '2003-1-1',
        'INTERNSHIP', 'ARCHIVED');

insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130006',
        'Java is a high-level programming language developed by Sun Microsystems. The Java syntax is similar to C++, but is strictly an object-oriented programming language. ... For example, most Java programs contain classes, which are used to define objects, and methods, which are assigned to individual classes.',
        '2003-2-1', 'Java & Js 2020', null, null, '2003-1-1',
        'MEETUP', 'PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130007',
        'Java is a high-level programming language developed by Sun Microsystems. The Java syntax is similar to C++, but is strictly an object-oriented programming language. ... For example, most Java programs contain classes, which are used to define objects, and methods, which are assigned to individual classes.',
        '2003-2-1', 'Java 2020', null, null, '2003-1-1',
        'INTERNSHIP', 'PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130008',
        'Being Spring developers, we naturally choose components from the Spring stack to do all the heavy lifting. After all, we have the concept etched out, so we''re already halfway there.',
        '2003-2-1', 'Spring 2020', null, null, '2003-1-1',
        'TRAINING', 'NOT_PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130009',
        'JavaScript® (often shortened to JS) is a lightweight, interpreted, object-oriented language with first-class functions, and is best known as the scripting language for Web pages, but it''s used in many non-browser environments as well. ... JavaScript can function as both a procedural and an object oriented language.',
        '2003-2-1', 'JavaScript 2020', null, null, '2003-1-1',
        'MEETUP', 'PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130010',
        'React (also known as React.js or ReactJS) is an open-source, front end, JavaScript library for building user interfaces or UI components. ',
        '2003-2-1', 'React JS 2020', null, null, '2003-1-1',
        'INTERNSHIP', 'ARCHIVED');

insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130011',
        'Java is a high-level programming language developed by Sun Microsystems. The Java syntax is similar to C++, but is strictly an object-oriented programming language. ... For example, most Java programs contain classes, which are used to define objects, and methods, which are assigned to individual classes.',
        '2003-2-1', 'Java & Js 2021', null, null, '2003-1-1',
        'MEETUP', 'PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130012',
        'Java is a high-level programming language developed by Sun Microsystems. The Java syntax is similar to C++, but is strictly an object-oriented programming language. ... For example, most Java programs contain classes, which are used to define objects, and methods, which are assigned to individual classes.',
        '2003-2-1', 'Java 2021', null, null, '2003-1-1',
        'INTERNSHIP', 'PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130013',
        'Being Spring developers, we naturally choose components from the Spring stack to do all the heavy lifting. After all, we have the concept etched out, so we''re already halfway there.',
        '2003-2-1', 'Spring 2021', null, null, '2003-1-1',
        'TRAINING', 'NOT_PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130014',
        'JavaScript® (often shortened to JS) is a lightweight, interpreted, object-oriented language with first-class functions, and is best known as the scripting language for Web pages, but it''s used in many non-browser environments as well. ... JavaScript can function as both a procedural and an object oriented language.',
        '2003-2-1', 'JavaScript 2021', null, null, '2003-1-1',
        'MEETUP', 'PUBLISHED');
insert into events
values ('201398a0-a79f-11eb-bcbc-0242ac130015',
        'React (also known as React.js or ReactJS) is an open-source, front end, JavaScript library for building user interfaces or UI components. ',
        '2003-2-1', 'React JS 2021', null, null, '2003-1-1',
        'INTERNSHIP', 'ARCHIVED');

insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130001', 'f8fcba8e-a79f-11eb-bcbc-0242ac130001');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130001', 'f8fcba8e-a79f-11eb-bcbc-0242ac130003');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130002', 'f8fcba8e-a79f-11eb-bcbc-0242ac130001');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130002', 'f8fcba8e-a79f-11eb-bcbc-0242ac130002');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130003', 'f8fcba8e-a79f-11eb-bcbc-0242ac130002');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130004', 'f8fcba8e-a79f-11eb-bcbc-0242ac130003');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130005', 'f8fcba8e-a79f-11eb-bcbc-0242ac130004');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130006', 'f8fcba8e-a79f-11eb-bcbc-0242ac130001');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130006', 'f8fcba8e-a79f-11eb-bcbc-0242ac130003');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130007', 'f8fcba8e-a79f-11eb-bcbc-0242ac130001');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130007', 'f8fcba8e-a79f-11eb-bcbc-0242ac130002');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130008', 'f8fcba8e-a79f-11eb-bcbc-0242ac130002');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130009', 'f8fcba8e-a79f-11eb-bcbc-0242ac130003');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130010', 'f8fcba8e-a79f-11eb-bcbc-0242ac130004');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130011', 'f8fcba8e-a79f-11eb-bcbc-0242ac130001');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130011', 'f8fcba8e-a79f-11eb-bcbc-0242ac130003');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130012', 'f8fcba8e-a79f-11eb-bcbc-0242ac130001');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130012', 'f8fcba8e-a79f-11eb-bcbc-0242ac130002');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130013', 'f8fcba8e-a79f-11eb-bcbc-0242ac130002');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130014', 'f8fcba8e-a79f-11eb-bcbc-0242ac130003');
insert into event_tech
values ('201398a0-a79f-11eb-bcbc-0242ac130015', 'f8fcba8e-a79f-11eb-bcbc-0242ac130004');

-- EVENT-CITY
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130002', '8ba50dc4-a79f-11eb-bcbc-0242ac130003');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130002', '8ba50dc4-a79f-11eb-bcbc-0242ac130004');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130002', '8ba50dc4-a79f-11eb-bcbc-0242ac130021');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130002', '8ba50dc4-a79f-11eb-bcbc-0242ac130022');

insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130004', '8ba50dc4-a79f-11eb-bcbc-0242ac130023');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130004', '8ba50dc4-a79f-11eb-bcbc-0242ac130024');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130004', '8ba50dc4-a79f-11eb-bcbc-0242ac130001');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130004', '8ba50dc4-a79f-11eb-bcbc-0242ac130002');

insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130006', '8ba50dc4-a79f-11eb-bcbc-0242ac130005');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130006', '8ba50dc4-a79f-11eb-bcbc-0242ac130006');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130006', '8ba50dc4-a79f-11eb-bcbc-0242ac130002');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130006', '8ba50dc4-a79f-11eb-bcbc-0242ac130001');

insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130008', '8ba50dc4-a79f-11eb-bcbc-0242ac130007');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130008', '8ba50dc4-a79f-11eb-bcbc-0242ac130008');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130008', '8ba50dc4-a79f-11eb-bcbc-0242ac130005');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130008', '8ba50dc4-a79f-11eb-bcbc-0242ac130002');

insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130010', '8ba50dc4-a79f-11eb-bcbc-0242ac130004');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130010', '8ba50dc4-a79f-11eb-bcbc-0242ac130001');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130010', '8ba50dc4-a79f-11eb-bcbc-0242ac130013');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130010', '8ba50dc4-a79f-11eb-bcbc-0242ac130014');

insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130012', '8ba50dc4-a79f-11eb-bcbc-0242ac130011');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130012', '8ba50dc4-a79f-11eb-bcbc-0242ac130012');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130012', '8ba50dc4-a79f-11eb-bcbc-0242ac130015');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130012', '8ba50dc4-a79f-11eb-bcbc-0242ac130016');

insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130014', '8ba50dc4-a79f-11eb-bcbc-0242ac130017');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130014', '8ba50dc4-a79f-11eb-bcbc-0242ac130018');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130014', '8ba50dc4-a79f-11eb-bcbc-0242ac130019');
insert into event_city
values ('201398a0-a79f-11eb-bcbc-0242ac130014', '8ba50dc4-a79f-11eb-bcbc-0242ac130020');

insert into employee_timeslot
values ('7c6dd816-a80f-11eb-bcbc-0242ac130001', 10, 11, '7889d51e-a79d-11eb-bcbc-0242ac130001');
insert into employee_timeslot
values ('7c6dd816-a80f-11eb-bcbc-0242ac130002', 11, 12, '7889d51e-a79d-11eb-bcbc-0242ac130002');
insert into employee_timeslot
values ('7c6dd816-a80f-11eb-bcbc-0242ac130003', 12, 13, '7889d51e-a79d-11eb-bcbc-0242ac130003');
insert into employee_timeslot
values ('7c6dd816-a80f-11eb-bcbc-0242ac130004', 13, 14, '7889d51e-a79d-11eb-bcbc-0242ac130004');
insert into employee_timeslot
values ('7c6dd816-a80f-11eb-bcbc-0242ac130005', 14, 15, '7889d51e-a79d-11eb-bcbc-0242ac130005');
insert into employee_timeslot
values ('7c6dd816-a80f-11eb-bcbc-0242ac130006', 15, 16, '7889d51e-a79d-11eb-bcbc-0242ac130006');
insert into employee_timeslot
values ('7c6dd816-a80f-11eb-bcbc-0242ac130007', 16, 17, '7889d51e-a79d-11eb-bcbc-0242ac130001');
insert into employee_timeslot
values ('7c6dd816-a80f-11eb-bcbc-0242ac130008', 17, 18, '7889d51e-a79d-11eb-bcbc-0242ac130002');
insert into employee_timeslot
values ('7c6dd816-a80f-11eb-bcbc-0242ac130009', 15, 16, '7889d51e-a79d-11eb-bcbc-0242ac130003');


