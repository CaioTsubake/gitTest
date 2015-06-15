# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment_model (
  id                        varchar(255) not null,
  posted_at                 timestamp,
  content                   varchar(255),
  constraint pk_comment_model primary key (id))
;

create table user_model (
  id                        varchar(255) not null,
  username                  varchar(255),
  password                  varchar(255),
  repeat_password           varchar(255),
  constraint pk_user_model primary key (id))
;

create sequence comment_model_seq;

create sequence user_model_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists comment_model;

drop table if exists user_model;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists comment_model_seq;

drop sequence if exists user_model_seq;

