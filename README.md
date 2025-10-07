create table users
(
    username   varchar(128) unique,
    firstname  varchar(128) not null,
    lastname   varchar(128) not null,
    birth_date date         not null,
    role       varchar(32),
    primary key (firstname, lastname, birth_date)
);

alter table users
    owner to postgres;
