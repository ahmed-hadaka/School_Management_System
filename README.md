# School_Management_System

### The system has three main components: Users, Classes and Courses with the corresponding functionalities for each.
### There are two main users: Admin and Student, with Two Roles: ADMIN and STUDENT, every Student must be assigned to a class during registration process and only the admin can change his/her class.
- Admin Permissions:
  - Manipulation with students: view all the students, get a specific student, delete a student from the system.
  - Manipulation with courses: view all Courses, add new course, delete course, view the students enrolled in this course, add students to a course, and delete students from a course.
  - Manipulation with classes: view all classes, add a new class to the system, view all students assigned to this class, and change the student's class to another class.
- Student Permissions:
  - view his/her enroled courses, view the other (unenroled) courses, enrol in a specific course, and withdraw from a course.

- Every action is preserved with auditing functionality. I also added pagination and sorting, custom Authentication provider, custom validations, Transaction Management, i18n and, Uploading files(personal photo).

- Technologies:
  - Backend: **Java, Spring(MVC, AOP, Security, Data JPA), Spring Boot.**
  - FronEnd: **HTML, CSS, Thymeleaf.**
  - DBMS: **Mysql.**
- Tools:
  - **Maven** Build tool, **Lombok** to generate boilerplate code at runtime.

##

- SpringBoot Flow Architecture
- 
![SpringBoot Flow Architecture](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/098eaf16-f3d6-439f-9472-e800d6709e98)





- Entity Relationship Diagram

![School_System_ERD](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/4676bce7-2353-45b8-89de-aacbb4fb986c)




- Video Demo

[screen-capture.webm](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/20e178df-ce84-458a-80fb-0ad3d0733ded)


