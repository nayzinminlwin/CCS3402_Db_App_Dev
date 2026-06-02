-- INSERT INTO staff (fname, lname, salary) VALUES ('John', 'Doe', 50000);
-- INSERT INTO staff (fname, lname, salary) VALUES ('Jane', 'Smith', 62000);

insert into staff(fname, lname, salary) values ('James', 'Dean', 10000);
insert into staff(fname, lname, salary) values ('Mawar', 'Adenan', 12500);
insert into staff(fname, lname, salary) values ('Adam', 'Gray', 17000);
insert into staff(fname, lname, salary) values ('Steve', 'Jobs', 25000);

insert into department(dept_name, address, phone) values 
    ('Department of Computer Science','Block C, FSKTM, UPM','03-89477435');
insert into department(dept_name, address, phone) values 
    ('Department of Software Engineering','Block A, FSKTM, UPM','03-89477436');
insert into department(dept_name, address, phone) values 
    ('Department of Multimedia','Block C, FSKTM, UPM','03-89477437');
insert into department(dept_name, address, phone) values 
    ('Department of Network','Block A, FSKTM, UPM','03-89477438');

insert into staff(fname, lname, salary) values ('James', 'Dean', 10000);
insert into staff(fname, lname, salary) values ('Mawar', 'Adenan', 12500);
insert into staff(fname, lname, salary) values ('Adam', 'Gray', 17000);
insert into staff(fname, lname, salary) values ('Steve', 'Jobs', 25000);

update staff set dept_id = 1
where staff_id = 1;

update staff set dept_id = 2
where staff_id = 2;

update staff set dept_id = 1
where staff_id = 3;

update staff set dept_id = 4
where staff_id = 4;
