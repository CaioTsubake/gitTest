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

create table following_list_model (
  id                        integer not null,
  constraint pk_following_list_model primary key (id))
;

create table trade_model (
  sender_id                 varchar(255) not null,
  time_of_trade             timestamp,
  constraint pk_trade_model primary key (sender_id))
;

create table trade_record_model (
  sender_id                 varchar(255) not null,
  time_of_trade             timestamp,
  constraint pk_trade_record_model primary key (sender_id))
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
  user_following_list_id    integer,
  constraint pk_user_model primary key (id))
;


create table followingList_following (
  following_list_model_id        integer not null,
  user_model_id                  varchar(255) not null,
  constraint pk_followingList_following primary key (following_list_model_id, user_model_id))
;

create table user_model_following_list_model (
  user_model_id                  varchar(255) not null,
  following_list_model_id        integer not null,
  constraint pk_user_model_following_list_model primary key (user_model_id, following_list_model_id))
;
create sequence book_comment_model_seq;

create sequence book_model_seq;

create sequence comment_model_seq;

create sequence following_list_model_seq;

create sequence trade_model_seq;

create sequence trade_record_model_seq;

create sequence user_comment_model_seq;

create sequence user_model_seq;

alter table book_comment_model add constraint fk_book_comment_model_author_1 foreign key (author_id) references user_model (id) on delete restrict on update restrict;
create index ix_book_comment_model_author_1 on book_comment_model (author_id);
alter table comment_model add constraint fk_comment_model_author_2 foreign key (author_id) references user_model (id) on delete restrict on update restrict;
create index ix_comment_model_author_2 on comment_model (author_id);
alter table user_comment_model add constraint fk_user_comment_model_author_3 foreign key (author_id) references user_model (id) on delete restrict on update restrict;
create index ix_user_comment_model_author_3 on user_comment_model (author_id);
alter table user_model add constraint fk_user_model_userFollowingLis_4 foreign key (user_following_list_id) references following_list_model (id) on delete restrict on update restrict;
create index ix_user_model_userFollowingLis_4 on user_model (user_following_list_id);



alter table followingList_following add constraint fk_followingList_following_fo_01 foreign key (following_list_model_id) references following_list_model (id) on delete restrict on update restrict;

alter table followingList_following add constraint fk_followingList_following_us_02 foreign key (user_model_id) references user_model (id) on delete restrict on update restrict;

alter table user_model_following_list_model add constraint fk_user_model_following_list__01 foreign key (user_model_id) references user_model (id) on delete restrict on update restrict;

alter table user_model_following_list_model add constraint fk_user_model_following_list__02 foreign key (following_list_model_id) references following_list_model (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists book_comment_model;

drop table if exists book_model;

drop table if exists comment_model;

drop table if exists following_list_model;

drop table if exists followingList_following;

drop table if exists trade_model;

drop table if exists trade_record_model;

drop table if exists user_comment_model;

drop table if exists user_model;

drop table if exists user_model_following_list_model;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists book_comment_model_seq;

drop sequence if exists book_model_seq;

drop sequence if exists comment_model_seq;

drop sequence if exists following_list_model_seq;

drop sequence if exists trade_model_seq;

drop sequence if exists trade_record_model_seq;

drop sequence if exists user_comment_model_seq;

drop sequence if exists user_model_seq;

