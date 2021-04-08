create table event_label (event_id int4 not null, label_id int4 not null, primary key (event_id, label_id));
create table event_stack (id  serial not null, description varchar(100) not null, name varchar(30) not null, event_id int4 not null, primary key (id));
create table events (id  serial not null, description varchar(256) not null, end_date timestamp, name varchar(64) not null, start_date timestamp, type int4 not null, primary key (id));
create table labels (id  serial not null, name varchar(64) not null, primary key (id));
alter table event_label add constraint FKe1knmju6euui4o829q9qoo7x9 foreign key (label_id) references labels;
alter table event_label add constraint FKcdhxhcduqangg1dmdhgcuh3gh foreign key (event_id) references events;
alter table event_stack add constraint FKq8bbj7cbjmebobdn4t0ftnykx foreign key (event_id) references events;

insert into labels values (1, 'java');
insert into labels values (2, 'js');
insert into labels values (3, 'internship');
insert into labels values (4, 'vue.js');
insert into labels values (5, '.net');
insert into labels values (6, 'agile');

insert into events values (1, 'description-text', '2003-1-1', 'event 1', '2003-1-1', 1);
insert into events values (2, 'description-text', '2003-1-1', 'event 2', '2003-1-1', 2);
insert into events values (3, 'description-text', '2003-1-1', 'event 3', '2003-1-1', 3);

insert into event_label values (1, 1);
insert into event_label values (1, 2);
insert into event_label values (1, 3);

insert into event_label values (2, 4);
insert into event_label values (2, 5);
insert into event_label values (2, 6);

insert into event_label values (3, 6);
insert into event_label values (3, 3);
insert into event_label values (3, 1);

insert into event_stack values (1,'frontend', 'tech stack description',1);
insert into event_stack values (2,'backend', 'tech stack description',1);
insert into event_stack values (3,'frontend', 'tech stack description',2);
insert into event_stack values (4,'backend', 'tech stack description',2);
insert into event_stack values (5,'fullstack', 'tech stack description',3);