package StudentManagementSystem;

import java.util.Scanner;
import java.sql.*;

public class StudentManagementSystem {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "Ro$h@n456";

    public static void main(String[] args) {
        int choice;
        boolean flag;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Scanner sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established");
            Authentication authentication = new Authentication(con, sc);

            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t--------------------------------------------");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t|        Student Management System         |");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t--------------------------------------------");

            do {
                System.out.println();
                System.out.println("******* Login Menu *******");
                System.out.println("Choose your role");
                System.out.println("1. Admin");
                System.out.println("2. Teacher");
                System.out.println("3. Student");
                System.out.println("4. Exit");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println();
                        System.out.println("******* Welcome to Admin Menu *******");
                        flag = authentication.verifyUser();
                        do {
                            if (flag) {
                                Admin admin = new Admin(con, sc);
                                System.out.println();
                                System.out.println("1.  Add student record");
                                System.out.println("2.  Add teacher record");
                                System.out.println("3.  Add course");
                                System.out.println("4.  Create new user account");
                                System.out.println("5.  Delete student record");
                                System.out.println("6.  Delete teacher record");
                                System.out.println("7.  Delete user account");
                                System.out.println("8.  Remove course");
                                System.out.println("9.  View all users");
                                System.out.println("10. View all students");
                                System.out.println("11. View all teachers");
                                System.out.println("12. View all courses");
                                System.out.println("13. Search User");
                                System.out.println("14. Search Student");
                                System.out.println("15. Search Teacher");
                                System.out.println("16. Search Course");
                                System.out.println("17. Log out");
                                choice = sc.nextInt();
                                sc.nextLine();

                                switch (choice) {
                                    case 1:
                                        admin.addStudent();
                                        break;
                                    case 2:
                                        admin.addTeacher();
                                        break;
                                    case 3:
                                        admin.addCourse();
                                        break;
                                    case 4:
                                        admin.createUserAccount();
                                        break;
                                    case 5:
                                        admin.deleteStudent();
                                        break;
                                    case 6:
                                        admin.deleteTeacher();
                                        break;
                                    case 7:
                                        admin.deleteUserAccount();
                                        break;
                                    case 8:
                                        admin.deleteCourse();
                                        break;
                                    case 9:
                                        admin.viewUsers();
                                        break;
                                    case 10:
                                        admin.viewStudents();
                                        break;
                                    case 11:
                                        admin.viewTeachers();
                                        break;
                                    case 12:
                                        admin.viewCourses();
                                        break;
                                    case 13:
                                        admin.searchUser();
                                        break;
                                    case 14:
                                        admin.searchStudent();
                                        break;
                                    case 15:
                                        admin.searchTeacher();
                                        break;
                                    case 16:
                                        admin.searchCourse();
                                        break;
                                    case 17:
                                        System.out.println("Login out");
                                        break;
                                    default:
                                        System.out.println("Invalid choice");
                                }
                            } else {
                                flag = authentication.verifyUser();
                            }
                        } while (choice != 17);
                        break;
                    case 2:
                        System.out.println("******* Welcome to Teacher Menu *******");
                        Teacher teacher = new Teacher(con, sc);
                        flag = authentication.verifyUser();
                        do {
                            if (flag) {
                                System.out.println();
                                System.out.println("1.  Add student record");
                                System.out.println("2.  Add teacher record");
                                System.out.println("3.  Add course");
                                System.out.println("4.  Delete student record");
                                System.out.println("5.  Delete teacher record");
                                System.out.println("6.  Remove course");
                                System.out.println("7.  View all users");
                                System.out.println("8. View all students");
                                System.out.println("9. View all teachers");
                                System.out.println("10. View all courses");
                                System.out.println("11. Search User");
                                System.out.println("12. Search Student");
                                System.out.println("13. Search Teacher");
                                System.out.println("14. Search Course");
                                System.out.println("15. Log out");
                                choice = sc.nextInt();
                                sc.nextLine();

                                switch (choice) {
                                    case 1:
                                        teacher.addStudent();
                                        break;
                                    case 2:
                                        teacher.addTeacher();
                                        break;
                                    case 3:
                                        teacher.addCourse();
                                        break;
                                    case 4:
                                        teacher.deleteStudent();
                                        break;
                                    case 5:
                                        teacher.deleteTeacher();
                                        break;
                                    case 6:
                                        teacher.deleteCourse();
                                        break;
                                    case 7:
                                        teacher.viewUsers();
                                        break;
                                    case 8:
                                        teacher.viewStudents();
                                        break;
                                    case 9:
                                        teacher.viewTeachers();
                                        break;
                                    case 10:
                                        teacher.viewCourses();
                                        break;
                                    case 11:
                                        teacher.searchUser();
                                        break;
                                    case 12:
                                        teacher.searchStudent();
                                        break;
                                    case 13:
                                        teacher.searchTeacher();
                                        break;
                                    case 14:
                                        teacher.searchCourse();
                                        break;
                                    case 15:
                                        System.out.println("Login out");
                                        break;
                                    default:
                                        System.out.println("Invalid choice");
                                }
                            } else {
                                flag = authentication.verifyUser();
                            }
                        } while (choice != 15);
                        break;
                    case 3:
                        System.out.println("******* Welcome to Student Menu *******");
                        Student student = new Student(con, sc);
                        flag = authentication.verifyUser();
                        do {
                            if (flag) {
                                System.out.println();
                                System.out.println("1. View all students");
                                System.out.println("2. View all teachers");
                                System.out.println("3. View all courses");
                                System.out.println("4. Search Student");
                                System.out.println("5. Search Teacher");
                                System.out.println("6. Search Course");
                                System.out.println("7. Log out");
                                choice = sc.nextInt();

                                switch (choice) {
                                    case 1:
                                        student.viewStudents();
                                        break;
                                    case 2:
                                        student.viewTeachers();
                                        break;
                                    case 3:
                                        student.viewCourses();
                                        break;
                                    case 4:
                                        student.searchStudent();
                                        break;
                                    case 5:
                                        student.searchTeacher();
                                        break;
                                    case 6:
                                        student.searchCourse();
                                        break;
                                    case 7:
                                        System.out.println("Login out");
                                        break;
                                    default:
                                        System.out.println("Invalid choice");
                                }
                            } else {
                                flag = authentication.verifyUser();
                            }
                        } while (choice != 7);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice-- enter valid choice");
                }
            } while (choice != 4);
        } catch (SQLException e) {
            System.out.println("Connection failed with server" + e.getMessage());
        }
    }
}
