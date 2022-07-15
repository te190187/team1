create table subjects (
    id int not null auto_increment,
    name char(2) not null,
    primary key (id)
)

create table classrooms (
    id int not null auto_increment,
    name char(3) not null,
    subjectCode int not null,
    availableFlag boolean not null,
    primary key (id)
)

create table teacher (
    id int not null auto_increment,
    name varchar(100) not null,
    subjectCode varchar(100) not null,
    primary key (id),
    foreign key subjectCode references subjects(id)
)