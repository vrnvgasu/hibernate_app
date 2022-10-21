create table person
(
    id   int primary key,
    name varchar(100),
    age  int
);

insert into person (id, name, age)
values (1, 'Test', 18);