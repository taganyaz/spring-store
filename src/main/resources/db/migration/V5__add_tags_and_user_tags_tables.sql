create table tags
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null
);

create table user_tags
(
    user_id bigint not null,
    tag_id  bigint not null,
    constraint user_tags_pk
        primary key (user_id, tag_id),
    constraint user_tags_tags_id_fk
        foreign key (tag_id) references tags (id) on delete cascade ,
    constraint user_tags_users_id_fk
        foreign key (user_id) references users (id) on delete cascade
);