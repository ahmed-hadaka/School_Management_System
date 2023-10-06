DROP DATABASE IF EXISTS school_system;

CREATE DATABASE school_system;

USE school_system;

CREATE TABLE IF NOT EXISTS class (
    class_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS address (
    address_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    address1 VARCHAR(200) NOT NULL,
    address2 VARCHAR(200) DEFAULT NULL,
    city VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    zip_code VARCHAR(5) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS role (
    role_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS person (
    person_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    mobile_number VARCHAR(11) NOT NULL,
	photo LONGBLOB DEFAULT NULL, 
    class_id INT NOT NULL,
    address_id INT DEFAULT NULL,
    role_id INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(50) DEFAULT NULL,
    FOREIGN KEY (class_id)
        REFERENCES class (class_id),
    FOREIGN KEY (address_id)
        REFERENCES address (address_id),
    FOREIGN KEY (role_id)
        REFERENCES role (role_id)
);

CREATE TABLE IF NOT EXISTS course (
    course_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS person_course (
    person_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (person_id)
        REFERENCES person (person_id),
    FOREIGN KEY (course_id)
        REFERENCES course (course_id),
    PRIMARY KEY (person_id , course_id)
);

INSERT INTO class (name, created_at, created_by) VALUES("CLASS_1",'2023-9-12','DBA');


INSERT INTO role (name) VALUES("ADMIN");
INSERT INTO role (name) VALUES("STUDENT" );


INSERT INTO person (name,email,password,mobile_number,role_id, class_id, created_at, created_by)
  VALUES ('ahmed','ahmed@gmail.com','$2a$12$eEmvdhAF88WTo9eGOMOTQ.v7YC8eZxqu0k1Nmye2UdHIHg289K/FW','12345678994', 1 , 1,'2023-9-12','DBA' );


SELECT * FROM person;
SELECT * FROM course;
SELECT * FROM person_course;
SELECT * FROM role;
SELECT * FROM class;
SELECT * FROM address;
