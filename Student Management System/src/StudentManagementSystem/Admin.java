package StudentManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class Admin extends Functions{
    private Connection con = null;
    private Scanner sc = null;
    private ResultSet rs;
    private PreparedStatement ps;
    private String query;
    public Admin(Connection con, Scanner sc) {
        super(con,sc);
        this.con = con;
        this.sc = sc;
    }
    public void createUserAccount() {
        try {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            System.out.print("Enter role: ");
            String role = sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();

            query = "insert into users(username,password,role,email) values (?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,role);
            ps.setString(4,email);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                String query1 = "select userid from users where username=? and password=?";
                ps = con.prepareStatement(query1);
                ps.setString(1,username);
                ps.setString(2,password);
                rs = ps.executeQuery();
                int id = -1;
                while (rs.next()) {
                    id = rs.getInt("userid");
                }
                System.out.println("Account created successfully with user id: "+ id);
            }
            else System.out.println("something went wrong-- failed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteUserAccount() {
        try {
            System.out.println("Enter user id: ");
            int id = sc.nextInt();

            query = "delete from users where userid=?";
            ps =  con.prepareStatement(query);
            ps.setInt(1,id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) System.out.println("Record with id-"+id+" deleted successfully");
            else System.out.println("Operation failed -- something went wrong");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
