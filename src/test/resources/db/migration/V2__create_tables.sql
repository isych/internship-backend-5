create table candidate (id int4 not null, cv varchar(255), cv_path varchar(255), email varchar(255) not null, full_name varchar(255) not null, interview_process varchar(255), phone varchar(255) not null, preferred_time varchar(255), skype varchar(255) not null, status varchar(255) not null, summary varchar(100), city_id int4 not null, event_id int4 not null, tech_id int4 not null, primary key (id));
create table city (id int4 not null, name varchar(255) not null, country_id int4 not null, primary key (id));
create table country (id int4 not null, name varchar(255) not null, primary key (id));
create table event_city (event_id int4 not null, city_id int4 not null, primary key (event_id, city_id));
create table event_tech (event_id int4 not null, tech_id int4 not null, primary key (event_id, tech_id));
create table events (id int4 not null, description varchar(256) not null, end_date timestamp, name varchar(64) not null, picture_name varchar(256), picture_path varchar(256), start_date timestamp, type varchar(255) not null, status varchar(255), primary key (id));
create table roles (id  serial not null, name varchar(255), primary key (id));
create table tech (id int4 not null, name varchar(64) not null, primary key (id));
create table users (id  serial not null, email varchar(255), fio varchar(255), login varchar(255), password varchar(255), role_id int4, primary key (id));

create table interview (id int4 not null, end_time timestamp, feedback varchar(100), start_time timestamp, candidate_id int4 not null, employee_id int4 not null, primary key (id));
create table employee (id int4 not null, full_name varchar(20) not null, email varchar(255) not null, password varchar(255) not null, role_id int4 not null, primary key (id));
create table employee_timeslot (id int4 not null, end_time int4 not null, start_time int4 not null, employee_id int4 not null, primary key (id));
alter table employee add constraint FKjod0wwyxabi7qyx9fmlntsxq4 foreign key (role_id) references roles;
alter table interview add constraint FKjod0wwyxvbi7qyx9cmlnt8xq4 foreign key (candidate_id) references candidate;
alter table interview add constraint FK5amdvskvlsj31qxv5aceawoye foreign key (employee_id) references employee;
alter table employee_timeslot add constraint FKclg70w7gqslwgjo279a0qqg68 foreign key (employee_id) references employee;

alter table city add constraint UK_qsstlki7ni5ovaariyy9u8y79 unique (name);
alter table country add constraint UK_llidyp77h6xkeokpbmoy710d4 unique (name);
alter table events add constraint UK_fn2r8jg0sm5v6vhoa7yqw55vy unique (name);
alter table tech add constraint UK_61sbcb7t9ej3nihlu4wtynsio unique (name);
create sequence hibernate_sequence start 1 increment 1;
alter table candidate add constraint FKdcxytaubmielyryuyetjxmwg1 foreign key (city_id) references city;
alter table candidate add constraint FKnaweph5ackknb9k31bs0a0e4u foreign key (event_id) references events;
alter table candidate add constraint FKhwj8x9hvlphp55ygikbm19lxr foreign key (tech_id) references tech;
alter table city add constraint FKrpd7j1p7yxr784adkx4pyepba foreign key (country_id) references country;
alter table event_city add constraint FKjnaaecnahb2hwkn6lmrfi40ml foreign key (city_id) references city;
alter table event_city add constraint FK1i41i67vcs56ibl4uu2s3o6fw foreign key (event_id) references events;
alter table event_tech add constraint FK8dedk0dsrwr014gjryanipo6a foreign key (tech_id) references tech;
alter table event_tech add constraint FKgn9qh4d9xbmb7bocomed33vpw foreign key (event_id) references events;
alter table users add constraint FKp56c1712k691lhsyewcssf40f foreign key (role_id) references roles;
