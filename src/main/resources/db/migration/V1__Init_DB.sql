
create table roles(
    role_id int auto_increment primary key,
    role_name varchar(32) not null,
    constraint ROLE_NAME_UQ unique(role_name));

create table comments(
    comment_id int auto_increment primary key,
    commented_at timestamp,
    user_fk int not null,
    news_fk int not null,
    comment_body varchar(500) not null);

create table news(
    news_id int auto_increment primary key,
    news_title varchar(32) not null,
    news_body varchar(500) not null,
    constraint NEWS_TITLE_UQ unique(news_title));

create table users(
    user_id int auto_increment primary key,
    user_name varchar(32) not null,
    user_password varchar(255) not null,
    constraint USER_NAME_UQ unique(user_name));

create table moderated_news(
    moderator_fk int not null,
    news_fk int not null,
    moderated_news_id int auto_increment primary key,
    foreign key (moderator_fk) references users(user_id),
    foreign key (news_fk) references news(news_id),
    constraint MODERATED_NEWS_PAIR_FK_UQ unique(moderator_fk,news_fk));

create table user_role(
    user_fk int not null,
    role_fk int not null,
    foreign key (user_fk) references users(user_id),
    foreign key (role_fk) references roles(role_id),
    constraint USER_ROLE_UQ unique(user_fk,role_fk));
