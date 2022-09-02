drop table class_reservation;
drop table desired_date;
drop table student_entry;

create table desired_date(
    id int not null auto_increment,
    teacher_id int not null,
    dt date not null,
    frame_id int not null,
    primary key (id),
    foreign key (teacher_id) references teachers(id) on delete cascade,
    foreign key (frame_id) references subject_frame(id) on delete cascade
);

create table student_entry(
    id int not null auto_increment,
    grade_id int not null,
    name varchar(255) not null,
    dt date not null,
    subject_id int not null,
    entry_dt date not null,
    primary key (id),
    foreign key (grade_id) references grade(id) on delete cascade,
    foreign key (subject_id) references subjects(id) on delete cascade
);

create table class_reservation(
    id int not null auto_increment,
    classroom_id int not null,
    teacher_id int not null,
    student_entry_id int not null,
    frame_id int not null,
    reservation_date date not null,
    primary key (id),
    foreign key (classroom_id) references classrooms(id) on delete cascade,
    foreign key (teacher_id) references teachers(id) on delete cascade,
    foreign key (student_entry_id) references student_entry(id) on delete cascade,
    foreign key (frame_id) references subject_frame(id) on delete cascade
);
