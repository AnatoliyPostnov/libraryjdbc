create table genre
(
    id   bigserial primary key,
    name varchar(20),

    UNIQUE (name)
);

create table book
(
    id       bigserial primary key,
    name     varchar(20),
    genre_id bigint,

    constraint book_genre_fk foreign key (genre_id)
        references genre (id),

    UNIQUE (name)
);

create table author
(
    id      bigserial primary key,
    name    varchar(20),
    surname varchar(20),

    UNIQUE (name, surname)
);

create table book_author
(
    id        bigserial primary key,
    book_id   bigint,
    author_id bigint,

    constraint book_author_book_fk foreign key (book_id)
        references book (id),

    constraint book_author_author_fk foreign key (author_id)
        references author (id),

    UNIQUE (book_id, author_id)
)