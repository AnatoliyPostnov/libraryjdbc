create table genre
(
    id   bigint primary key,
    name varchar(20)
);

create table book
(
    id       bigint primary key,
    name     varchar(20),
    genre_id bigint,

    constraint book_genre_fk foreign key (genre_id)
        references genre (id)
);

create table author
(
    id   bigint primary key,
    name varchar(20)

);

create table book_author
(
    id        bigint primary key,
    book_id   bigint,
    author_id bigint,

    constraint book_author_book_fk foreign key (book_id)
        references book (id),

    constraint book_author_author_fk foreign key (author_id)
        references author (id)
)