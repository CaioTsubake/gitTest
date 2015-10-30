# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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

create table user_model (
  id                        varchar(255) not null,
  username                  varchar(255),
  password                  varchar(255),
  repeat_password           varchar(255),
  constraint pk_user_model primary key (id))
;

create sequence book_model_seq;

create sequence comment_model_seq;

create sequence user_model_seq;

alter table comment_model add constraint fk_comment_model_author_1 foreign key (author_id) references user_model (id) on delete restrict on update restrict;
create index ix_comment_model_author_1 on comment_model (author_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists book_model;

drop table if exists comment_model;

drop table if exists trade_model;

drop table if exists trade_record_model;

drop table if exists user_model;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists book_model_seq;

drop sequence if exists comment_model_seq;

drop sequence if exists user_model_seq;

