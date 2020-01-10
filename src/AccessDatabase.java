import java.sql.*;

class AccessDatabase {

    private Connection con;
    private Statement stmt;
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
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String getRs() {
        pickNewGame();
        try {
            if (rs.next()) {
                return rs.getString("name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void pickNewGame(){
        try {
            rs = stmt.executeQuery("SELECT * FROM game ORDER BY RAND() LIMIT 1;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
