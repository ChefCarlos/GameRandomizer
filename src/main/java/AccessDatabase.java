import java.sql.*;
import java.util.ArrayList;

import io.github.cdimascio.dotenv.Dotenv;

class AccessDatabase {

    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private String user;
    private String pass;

    AccessDatabase(){
        Dotenv dotenv = Dotenv.load();
        user = dotenv.get("SQLUSERNAME");
        pass = dotenv.get("SQLPASSWORD");
        createConnection();
        collectData();
    }

    private void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamerandomizer", user ,pass);
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

    public void addToDatabase(ArrayList listToAdd){

    }

    public void wipeDatabase(){

    }
}
