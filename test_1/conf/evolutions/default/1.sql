# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book_comment_model (
  id                        varchar(255) not null,
  author_id                 varchar(255),
  posted_at                 timestamp,
  content                   varchar(255),
  book_page_id              integer,
  constraint pk_book_comment_model primary key (id))
;

create table book_model (
  id                        varchar(255) not null,
  title                     varchar(255),
  isbn                      varchar(255),
  constraint pk_book_model primary key (id))
;

create table comment_model (
  id                        varchar(255) not null,
  author_id                 varchar(255),
  posted_at                 timestamp,
  content                   varchar(255),
  constraint pk_comment_model primary key (id))
;

create table trade_model (
  time_of_trade             timestamp)
;

create table trade_record_model (
  time_of_trade             timestamp)
;

create table user_comment_model (
  id                        varchar(255) not null,
  author_id                 varchar(255),
  posted_at                 timestamp,
  content                   varchar(255),
  user_page_id              integer,
  constraint pk_user_comment_model primary key (id))
;

create table user_model (
  id                        varchar(255) not null,
  username                  varchar(255),
  password                  varchar(255),
  repeat_password           varchar(255),
  constraint pk_user_model primary key (id))
;


alter table book_comment_model modify pk_book_comment_model integer AUTO_INCREMENT primary key;

alter table book_model modify pk_book_model integer AUTO_INCREMENT primary key;

alter table comment_model modify pk_comment_model integer AUTO_INCREMENT primary key;

alter table user_model modify pk_user_comment_model integer AUTO_INCREMENT primary key;








alter table book_comment_model add constraint fk_book_comment_model_author_1 foreign key (author_id) references user_model (id) on delete restrict on update restrict;
create index ix_book_comment_model_author_1 on book_comment_model (author_id);
alter table comment_model add constraint fk_comment_model_author_2 foreign key (author_id) references user_model (id) on delete restrict on update restrict;
create index ix_comment_model_author_2 on comment_model (author_id);
alter table user_comment_model add constraint fk_user_comment_model_author_3 foreign key (author_id) references user_model (id) on delete restrict on update restrict;
create index ix_user_comment_model_author_3 on user_comment_model (author_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists book_comment_model;

drop table if exists book_model;

drop table if exists comment_model;

drop table if exists trade_model;

drop table if exists trade_record_model;

drop table if exists user_comment_model;

drop table if exists user_model;

SET REFERENTIAL_INTEGRITY TRUE;


