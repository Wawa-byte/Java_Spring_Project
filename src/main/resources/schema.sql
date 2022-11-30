create table director (
    director_id int primary key,
    fName varchar(100),
    lName varchar(100),
    stillActive BOOLEAN
);

create table movie (
    movie_id     int primary key,
    title        varchar(100),
    yearReleased smallint,
    takings      smallint,
    director_id int,
    foreign key (director_id) references director(director_id) on delete cascade
);