import java.sql.*;

public class AccessDatabase {
    public static void main(String[] args) {

    }
    private Connection con;
    private ResultSet rs;

    AccessDatabase(){
        createConnection();
        collectData();
    }

    private void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamerandomizer", "root","my@password21");
            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void collectData(){
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM game");
//            while (rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                System.out.println(id + " " + name);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRs() {
        return rs;
    }
}
