package StudentManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Functions {
    private Connection con = null;
    private Scanner sc = null;
    private ResultSet rs;
    private PreparedStatement ps;
    private String query;
    public Functions(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }
    public boolean isContactValid(String contact) {
        return contact.length() == 10;
    }
    public void addStudent() {
        try {
            System.out.print("Enter roll number: ");
            int rno = sc.nextInt();
            System.out.print("Enter name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Enter age: ");
            int age = sc.nextInt();
            System.out.print("Enter email: ");
            sc.nextLine();
            String email = sc.nextLine();
            System.out.print("Enter contact: ");
            String contact = sc.nextLine();
            if (isContactValid(contact)) {
                query = "insert into students(rollno,name,age,email,contact) values (?,?,?,?,?)";
                ps = con.prepareStatement(query);
                ps.setInt(1,rno);
                ps.setString(2,name);
                ps.setInt(3,age);
                ps.setString(4,email);
                ps.setString(5,contact);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    String query1 = "select sid from students where rollno=?";
                    ps = con.prepareStatement(query1);
                    ps.setInt(1,rno);
                    rs = ps.executeQuery();
                    int id = -1;
                    while (rs.next()) {
                        id = rs.getInt("sid");
                    }
                    System.out.println("Student record inserted successfully with student id: "+id);
                } else {
                    System.out.println("insertion failed");
                }
            } else {
                System.out.println("Invalid contact number");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addTeacher() {
        try {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter age: ");
            int age = sc.nextInt();
            System.out.print("Enter qualification: ");
            sc.nextLine();
            String edu = sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            System.out.print("Enter contact: ");
            String contact = sc.nextLine();
            if (isContactValid(contact)) {
                query = "insert into teachers(name,age,education,email,contact) values (?,?,?,?,?)";
                ps = con.prepareStatement(query);
                ps.setString(1,name);
                ps.setInt(2,age);
                ps.setString(3,edu);
                ps.setString(4,email);
                ps.setString(5,contact);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    String query1 = "select tid from teachers where name=? and email=?";
                    ps = con.prepareStatement(query1);
                    ps.setString(1,name);
                    ps.setString(2,email);
                    rs = ps.executeQuery();
                    int id = -1;
                    while (rs.next()) {
                        id = rs.getInt("tid");
                    }
                    System.out.println("Teacher record inserted successfully with teacher id: "+id);
                }
                else System.out.println("insertion failed");
            } else {
                System.out.println("Invalid contact number");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addCourse() {
        try {
            System.out.print("Enter course title: ");
            String title = sc.nextLine();
            System.out.print("Enter course code: ");
            int ccode = sc.nextInt();
            System.out.print("Enter course duration: ");
            sc.nextLine();
            String duration = sc.nextLine();

            query = "insert into courses(title,duration,ccode) values(?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1,title);
            ps.setString(2,duration);
            ps.setInt(3,ccode);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                String query1 = "select cid from courses where ccode=?";
                ps = con.prepareStatement(query1);
                ps.setInt(1,ccode);
                rs = ps.executeQuery();
                int id = -1;
                while (rs.next()) {
                    id = rs.getInt("cid");
                }
                System.out.println("Course added successfully with course id: "+id);
            }
            else System.out.println("something went wrong-- failed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteStudent() {
        try {
            System.out.print("Enter student id: ");
            int id = sc.nextInt();

            query = "delete from students where sid=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) System.out.println("Record with id-"+id+" deleted successfully");
            else System.out.println("Operation failed -- something went wrong");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteTeacher() {
        try {
            System.out.print("Enter teacher id: ");
            int id = sc.nextInt();

            query = "delete from teachers where tid=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) System.out.println("Record with id-"+id+" deleted successfully");
            else System.out.println("Operation failed -- something went wrong");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteCourse() {
        try {
            System.out.print("Enter course code: ");
            int ccode = sc.nextInt();

            query = "delete from courses where ccode=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,ccode);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) System.out.println("Course with code-"+ccode+" deleted successfully");
            else System.out.println("Operation failed -- something went wrong");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void viewUsers() {
        try {
            query = "select * from users";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println();
                System.out.println("userID\t\tName\t\t\trole\t\temail");
                do {
                    int id = rs.getInt("userid");
                    String name = rs.getString("username");
                    String role = rs.getString("role");
                    String email = rs.getString("email");

                    System.out.println(id+"\t\t"+name+"\t\t\t"+role+"\t\t"+email);
                } while (rs.next());
            } else {
                System.out.println("Table is empty");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("something went wrong-- try after sometime");
        }
    }
    public void viewStudents() {
        try {
            query = "select * from students";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println();
                System.out.println("sID\t\troll no\t\tName\t\t\tage\t\temail\t\t\tcontact");
                do {
                    int id = rs.getInt("sid");
                    int rno = rs.getInt("rollno");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String email = rs.getString("email");
                    String contact = rs.getString("contact");

                    System.out.println(id+"\t\t"+rno+"\t\t"+name+"\t\t\t"+age+"\t\t"+email+"\t\t\t"+contact);
                } while (rs.next());
            } else {
                System.out.println("Table is empty");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("something went wrong-- try after sometime");
        }
    }
    public void viewTeachers() {
        try {
            query = "select * from teachers";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println();
                System.out.println("tid\t\tname\t\t\tage\t\teducation\t\t\temail\t\t\tcontact");
                do {
                    int id = rs.getInt("tid");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String edu = rs.getString("education");
                    String email = rs.getString("email");
                    String contact = rs.getString("contact");

                    System.out.println(id+"\t\t"+name+"\t\t\t"+age+"\t\t"+edu+"\t\t\t"+email+"\t\t\t"+contact);
                } while (rs.next());
            } else System.out.println("Table is empty");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong-- try after sometime");
        }
    }
    public void viewCourses() {
        try {
            query = "select * from courses";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println();
                System.out.println("cid\t\ttitle\t\t\tduration");
                do {
                    int id = rs.getInt("cid");
                    String title = rs.getString("title");
                    String duration = rs.getString("duration");

                    System.out.println(id+"\t\t"+title+"\t\t\t"+duration);
                } while (rs.next());
            } else System.out.println("Table is empty");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong-- try after sometime");
        }
    }
    public void searchUser() {
        try {
            System.out.println("Enter user id: ");
            int id = sc.nextInt();
            query = "select username,role,email from users where userid=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String username = "", role = "", email = "";
                do {
                    username = rs.getString("username");
                    role = rs.getString("role");
                    email = rs.getString("email");
                } while (rs.next());
                System.out.println("userid\t\tusername\t\t\trole\t\t\temail");
                System.out.println(id+"\t\t"+username+"\t\t\t"+role+"\t\t\t"+email);
            } else System.out.println("No user found with userid: "+id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void searchStudent() {
        try {
            System.out.print("Enter student id: ");
            int id = sc.nextInt();
            query = "select rollno,name,age,email,contact from students where sid=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int rno = -1, age = -1;
                String name = "", email = "", contact = "";
                do {
                    rno = rs.getInt("rollno");
                    name = rs.getString("name");
                    age = rs.getInt("age");
                    email = rs.getString("email");
                    contact = rs.getString("contact");
                } while (rs.next());
                System.out.println("sid\t\trollno\t\tname\t\t\tage\t\temail\t\t\tcontact");
                System.out.println(id+"\t\t"+rno+"\t\t"+name+"\t\t\t"+age+"\t\t"+email+"\t\t\t"+contact);
            } else System.out.println("No student record found with student id: "+id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void searchTeacher() {
        try {
            System.out.print("Enter teacher id: ");
            int id = sc.nextInt();

            query = "select name,age,education,email,contact from teachers where tid=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int age = -1;
                String name = "", education = "", email = "", contact = "";
                do {
                    name = rs.getString("name");
                    age = rs.getInt("age");
                    education = rs.getString("education");
                    email = rs.getString("email");
                    contact = rs.getString("contact");
                } while (rs.next());
                System.out.println("tid\t\tname\t\t\tage\t\teducation\t\t\temail\t\t\tcontact");
                System.out.println(id+"\t\t"+name+"\t\t\t"+age+"\t\t"+education+"\t\t\t"+email+"\t\t\t"+contact);
            } else System.out.println("No teacher record found with id: "+id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void searchCourse() {
        try {
            System.out.println("Enter course code: ");
            int ccode = sc.nextInt();
            query = "select cid,title,duration from courses where ccode=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,ccode);
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = -1;
                String title = "", duration = "";
                do {
                    id = rs.getInt("cid");
                    title = rs.getString("title");
                    duration = rs.getString("duration");
                } while (rs.next());
                System.out.println("cid\t\ttitle\t\t\tduration\t\tccode");
                System.out.println(id+"\t\t"+title+"\t\t\t"+duration+"\t\t"+ccode);
            } else System.out.println("No course found with course code: "+ccode);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
