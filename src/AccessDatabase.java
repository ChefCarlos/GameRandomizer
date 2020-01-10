import java.sql.*;

public class AccessDatabase {
    public static void main(String[] args) {
        AccessDatabase access = new AccessDatabase();
        access.createConnection();
        access.collectData();
    }
    Connection con;

    void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamerandomizer", "root","my@password21");
            System.out.println("Connected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void collectData(){
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM game");
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + " " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
