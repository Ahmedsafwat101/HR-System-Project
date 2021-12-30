create table employee
(
    emp_id        bigint auto_increment
        primary key,
    dept_name     varchar(255) null,
    emp_dob       datetime(6)  null,
    emp_gender    varchar(255) null,
    emp_grad_date datetime(6)  null,
    emp_name      varchar(255) null,
    emp_salary    double       null,
    team_id       bigint       null,
    mng_id        bigint       null,
    constraint FK_mng_id
        foreign key (mng_id) references employee (emp_id)
);

create table employee_experties
(
    employee_emp_id bigint       not null,
    emp_experties   varchar(255) null,
    constraint FK_employee_emp_id
        foreign key (employee_emp_id) references employee (emp_id)
);

