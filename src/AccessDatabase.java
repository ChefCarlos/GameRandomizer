import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDatabase {
    public static void main(String[] args) {
        AccessDatabase access = new AccessDatabase();
        access.createConnection();
    }


    void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamerandomizer", "root","my@password21");
            System.out.println("Connected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
