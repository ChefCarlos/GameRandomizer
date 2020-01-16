import java.sql.*;
import java.util.ArrayList;

import io.github.cdimascio.dotenv.Dotenv;

class AccessDatabase {

    private Statement stmt;
    private String user;
    private String pass;

    //gets the username and password for the database from .env file
    AccessDatabase(){
        Dotenv dotenv = Dotenv.load();
        user = dotenv.get("SQLUSERNAME");
        pass = dotenv.get("SQLPASSWORD");
        createConnection();
    }

    //starts the connection to the database, and starts the statement to be used later
    private void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamerandomizer", user, pass);
            stmt = con.createStatement();
            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    //grabs a random entry from the database and returns the name
    String getRs() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM game ORDER BY RAND() LIMIT 1;");
            if (rs.next()) {
                return rs.getString("name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }



    public void addToDatabase(ArrayList listToAdd){

    }

    public void wipeDatabase(){

    }
}
