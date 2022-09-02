create table class_reservation(
    id int not null auto_increment,
    classroom_id int not null,
    teacher_id int not null,
    student_entry_id int not null,
    frame_id int not null,
    reservation_date date not null,
    primary key (id),
    foreign key (classroom_id) references classrooms(id),
    foreign key (teacher_id) references teachers(id),
    foreign key (student_entry_id) references student_entry(id),
    foreign key (frame_id) references subject_frame(id)
);
