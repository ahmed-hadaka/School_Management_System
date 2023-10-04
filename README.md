# School_Management_System.

### The system has three main components: Users, Classes and Courses with the corresponding functionalities for each.
### There are two main users: Admin and Student, with Two Roles: ADMIN and STUDENT, every Student must be assigned to a class during registration process and only the admin can change his/her class.
- Admin Permissions:
  - Manipulation with students: view all the students, get a specific student, delete a student from the system.
  - Manipulation with courses: view all Courses, add new course, delete course, view the students enrolled in this course, add students to a course, and delete students from a course.
  - Manipulation with classes: view all classes, add a new class to the system, view all students assigned to this class, and change the student's class to another class.
- Student Permissions:
  - view his/her enroled courses, view the other (unenroled) courses, enrol in a specific course, and withdraw from a course.

- Every action is preserved with auditing functionality; I also added pagination and sorting, custom Authentication provider, custom validations and, Transaction Management.

- Technologies:
  - Backend: **Java, Spring(MVC, AOP, Security, Data JPA), Spring Boot.**
  - FronEnd: **HTML, CSS, Thymeleaf.**
  - DBMS: **Mysql.**
- Tools:
  - **Maven** Build tool, **Lombok** to generate boilerplate code at runtime.

##

- SpringBoot Flow Architecture
![SpringBoot Flow Architecture](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/098eaf16-f3d6-439f-9472-e800d6709e98)



- Entity Relationship Diagram

![SMS-EntityRelationshipDiagram](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/0540083b-a592-401d-a7d2-2932196012f5)


- Project Package Diagram
![SMS-PackageDiagram](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/aa57ee5a-3372-469b-aaa5-943266c469a2)


- RegisterPage
![RegisterPage](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/e1ed18c8-9a4b-4d86-8de0-83cb7ecb6e48)



- loginPage
![loginPage](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/fd3d7582-0490-41b6-9b6c-521a04ffc3d9)



- Admin_dashboardPage
![Admin_dashboardPage](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/a9be91f8-5db8-4a7c-b0e9-6e9fc3bc72cd)



- User_ProfilePage
![User_ProfilePage](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/0a65319d-d533-47a5-af5a-cbdf1ad833a7)



- All_StudentsPage
![All_StudentsPage](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/6d027250-f89b-47b8-b411-be0e0e6cff6e)



- All_Courses
![All_Courses](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/ee544c5f-72fa-48d2-bc24-4a826b1f92c8)



- All_ClassesPage
![All_ClassesPage](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/5db77d3d-0d27-43c2-8076-464120636b33)



- Students-Enrolled in a DB Course
![Students-Enrolled in a Course](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/b60da3c6-88b8-47d2-8e79-856f5ac85950)



- Studnet_dashboard
![Studnet_dashboard](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/95dc0226-4d50-45e5-bbb7-c2e91c8301cd)



- StudentEnrooled Course
![StudentEnrooled Course](https://github.com/ahmed-hadaka/School_Management_System/assets/92885872/5a6a473f-a32e-4f96-8c4b-3ae590584d25)

