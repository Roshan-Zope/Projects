package StudentManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authentication {
    private Connection con = null;
    private Scanner sc = null;
    public Authentication(Connection con, Scanner sc) {
        this.con = con;
        this.sc = sc;
    }
    public boolean checkPassword(String password, String pass) {
        return password.equals(pass);
    }
    public boolean verifyUser() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        try {
            String query = "select userid,password from Users where username = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String pass = rs.getString("password");

                if (checkPassword(password, pass)) {
                    int id = rs.getInt("userId");
                    System.out.println("Authentication successful with userId: "+id+" :: username: "+username);
                    return true;
                } else {
                    System.out.println("Authentication fail -- wrong credentials");
                }
            } else {
                System.out.println("User not found!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong. Try after some time");
        }
        return false;
    }
}
