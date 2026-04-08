CREATE TABLE userForm (
    userID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    firstName VARCHAR2(10),
    lastName VARCHAR2(10),
    email VARCHAR2(20),
    myComment VARCHAR2(30)
);
    
Describe userForm;
drop table userForm;

CREATE TABLE java_registered_users (
    userID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR2(10),
    email VARCHAR2(20),
    phone VARCHAR2(30),
    address VARCHAR2(40),
    password VARCHAR2(10)
);

DESCRIBE java_userForm;