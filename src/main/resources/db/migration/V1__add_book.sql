create table book (
    id int not null auto_increment,
    title varchar(100) not null,
    writter varchar(100) default null,
    publisher varchar(100) default null,
    price int default null,
    primary key (id)
)