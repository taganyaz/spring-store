create table profiles
(
    id             bigint           not null
        primary key,
    bio            text    null,
    phone_number   varchar(15)     null,
    date_of_birth  date             null,
    loyalty_points int default 0,
    constraint profiles_users_id_fk
        foreign key (id) references users (id)
);