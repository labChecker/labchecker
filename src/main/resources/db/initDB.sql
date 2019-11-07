create table if not exists groups (
    id SERIAL,
    name TEXT not null unique,
    constraint pk_groups primary key (id)
);

create table if not exists students (
    id SERIAL,
    first_name TEXT not null,
    last_name TEXT not null,
    id_group INTEGER not null,
    github_link TEXT not null,
    telegram_id INTEGER not null,
    constraint pk_students primary key (id),
    constraint fk_group_id foreign key (id_group) references groups(id)
);

create table if not exists subjects (
    id SERIAL,
    name TEXT not null,
    constraint pk_subjects primary key (id)
);

create table if not exists teachers (
    id SERIAL,
    first_name TEXT not null,
    last_name TEXT not null,
    email TEXT not null,
    telegram_id INTEGER not null,
    constraint pk_teachers primary key (id)
);

create table if not exists courses (
    id SERIAL,
    id_teacher INTEGER NOT NULL,
    id_subject INTEGER NOT NULL,
    id_group INTEGER not null,
    googlesheet_link TEXT not null,
    constraint pk_lessons primary key (id),
    constraint fk_teacher_id foreign key (id_teacher) references teachers(id),
    constraint fk_subject_id foreign key (id_subject) references subjects(id),
    constraint fk_group_id foreign key (id_group) references groups(id)
);

create table if not exists labs (
    id SERIAL,
    id_course INTEGER not null,
    number INTEGER not null,
    theme TEXT not null,
    constraint pk_labs primary key (id),
    constraint fk_course_id foreign key (id_course) references courses(id)
);

create table if not exists variants (
    id SERIAL,
    id_lab SERIAL not null,
    number INTEGER not null,
    testfile_path TEXT not null,
    constraint pk_variants primary key (id),
    constraint fk_lab_id foreign key (id_lab) references labs(id)
);

create table if not exists lab_results (
    id SERIAL,
    id_lab INTEGER not null,
    id_student INTEGER not null,
    id_variant INTEGER not null,
    github_repository_link TEXT not null,
    mark FLOAT8 not null,
    constraint pk_lab_results primary key (id),
    constraint fk_lab_id foreign key (id_lab) references labs(id),
    constraint fk_student_id foreign key (id_student) references students(id),
    constraint fk_variant_id foreign key (id_variant) references variants(id)
);
