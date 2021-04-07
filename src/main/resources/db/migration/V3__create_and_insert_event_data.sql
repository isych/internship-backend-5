create table event_label (event_id int4 not null, label_id int4 not null, primary key (event_id, label_id));
create table event_stack_table (id  serial not null, description varchar(100) not null, name varchar(30) not null, event_id int4 not null, primary key (id));
create table event_table (id  serial not null, description varchar(256) not null, end_date timestamp, name varchar(64) not null, start_date timestamp, type int4 not null, primary key (id));
create table label_table (id  serial not null, name varchar(64) not null, primary key (id));
alter table event_label add constraint FKe1knmju6euui4o829q9qoo7x9 foreign key (label_id) references label_table;
alter table event_label add constraint FKcdhxhcduqangg1dmdhgcuh3gh foreign key (event_id) references event_table;
alter table event_stack_table add constraint FKq8bbj7cbjmebobdn4t0ftnykx foreign key (event_id) references event_table;

insert into label_table values (1, 'java');
insert into label_table values (2, 'js');
insert into label_table values (3, 'internship');
insert into label_table values (4, 'vue.js');
insert into label_table values (5, '.net');
insert into label_table values (6, 'agile');

insert into event_table values (1, 'description-text', '2003-1-1', 'event 1', '2003-1-1', 1);
insert into event_table values (2, 'description-text', '2003-1-1', 'event 2', '2003-1-1', 2);
insert into event_table values (3, 'description-text', '2003-1-1', 'event 3', '2003-1-1', 3);

insert into event_label values (1, 1);
insert into event_label values (1, 2);
insert into event_label values (1, 3);

insert into event_label values (2, 4);
insert into event_label values (2, 5);
insert into event_label values (2, 6);

insert into event_label values (3, 6);
insert into event_label values (3, 3);
insert into event_label values (3, 1);

insert into event_stack_table values (1,'frontend', 'tech stack description',1);
insert into event_stack_table values (2,'backend', 'tech stack description',1);
insert into event_stack_table values (3,'frontend', 'tech stack description',2);
insert into event_stack_table values (4,'backend', 'tech stack description',2);
insert into event_stack_table values (5,'fullstack', 'tech stack description',3);