create table attributes
(
    id   int auto_increment primary key,
    name text not null
);

create table projects
(
    id   int auto_increment primary key,
    name text not null
);

create table project_attributes
(
    id           int auto_increment primary key,
    attribute_id int  not null references attributes,
    project_id   int  not null references projects,
    value        text not null
);