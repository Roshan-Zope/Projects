package StudentManagementSystem;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
    private Connection con = null;
    private Scanner sc = null;
    private PreparedStatement ps;
    private String query;
    private ResultSet rs;
    public Student(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
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
    public void searchStudent() {
        try {
            System.out.print("Enter student id: ");
            int id = sc.nextInt();
            query = "select rollno,name,age,email,contact from students where sid=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int rno = rs.getInt("rollno");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String contact = rs.getString("contact");
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
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String education = rs.getString("education");
                String email = rs.getString("email");
                String contact = rs.getString("contact");

                System.out.println("tid\t\tname\t\t\tage\t\teducation\t\t\temail\t\t\tcontact");
                System.out.println(id+"\t\t"+name+"\t\t\t"+age+"\t\t"+education+"\t\t\t"+email+"\t\t\t"+contact);
            } else System.out.println("No teacher record found with id: "+id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void searchCourse() {
        try {
            System.out.print("Enter course code: ");
            int ccode = sc.nextInt();
            query = "select cid,title,duration from courses where ccode=?";
            ps = con.prepareStatement(query);
            ps.setInt(1,ccode);
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("cid");
                String title = rs.getString("title");
                String duration = rs.getString("duration");

                System.out.println("cid\t\ttitle\t\t\tduration\t\tccode");
                System.out.println(id+"\t\t"+title+"\t\t\t"+duration+"\t\t"+ccode);
            } else System.out.println("No course found with course code: "+ccode);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
