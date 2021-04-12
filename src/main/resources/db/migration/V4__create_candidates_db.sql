create table candidate (id int4 not null, cv varchar(255) not null, email varchar(255) not null, full_name varchar(255) not null, interview_process varchar(255), phone varchar(255) not null, preferred_time varchar(255), skype varchar(255) not null, status varchar(255) not null, summary varchar(100), city_id int4 not null, event_stack_id int4 not null, primary key (id));

create table city (id int4 not null, name varchar(255) not null, country_id int4 not null, primary key (id));

create table country (id int4 not null, name varchar(255) not null, primary key (id));

create table event_city (event_id int4 not null, city_id int4 not null, primary key (event_id, city_id));

alter table events add column picture_url varchar(256);

create sequence hibernate_sequence start 1 increment 1;

alter table candidate add constraint FKdcxytaubmielyryuyetjxmwg1 foreign key (city_id) references city;

alter table candidate add constraint FK1n83c5lnh7r6fummsm8hu6mpl foreign key (event_stack_id) references event_stack;

alter table city add constraint FKrpd7j1p7yxr784adkx4pyepba foreign key (country_id) references country;

alter table event_city add constraint FKjnaaecnahb2hwkn6lmrfi40ml foreign key (city_id) references city;

alter table event_city add constraint FK1i41i67vcs56ibl4uu2s3o6fw foreign key (event_id) references events;