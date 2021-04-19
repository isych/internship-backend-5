alter table candidate
    alter column cv drop not null;
alter table events
    alter column picture_url drop not null,
    add picture_path varchar(64) null