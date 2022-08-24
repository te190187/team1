create table subject_frame(
    id int not null,
    time varchar(100) not null,
    primary key (id)
);

insert into subject_frame values(1,'10:00~11:00');
insert into subject_frame values(2,'11:15~12:15');
insert into subject_frame values(3,'13:15~14:15');
insert into subject_frame values(4,'14:30~15:30');
insert into subject_frame values(5,'15:45~16:45');
insert into subject_frame values(6,'16:00~17:00');
insert into subject_frame values(7,'17:15~18:15');
insert into subject_frame values(8,'18:30~19:30');


create table desired_date(
    id int not null auto_increment,
    teacher_id int not null,
    dt date not null,
    frame_id int not null,
    primary key (id)
);
