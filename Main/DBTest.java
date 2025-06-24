package Main;

import util.DBConnection;
import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connected to MySQL!");
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
