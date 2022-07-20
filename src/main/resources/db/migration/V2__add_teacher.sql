create table subjects (
    id int not null auto_increment,
    name char(2) not null,
    primary key (id)
);

create table classrooms (
    id int not null auto_increment,
    name char(3) not null,
    capacity int not null,
    availableFlag boolean not null,
    primary key (id)
);

create table teachers (
    id int not null auto_increment,
    name varchar(100) not null,
    subjectCode int,
    primary key (id),
    foreign key (subjectCode) references subjects(id)
);