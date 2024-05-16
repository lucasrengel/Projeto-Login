package DAO;

import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

    public static ArrayList<User> myList = new ArrayList<>();

    public java.sql.Connection getConecction() {

        java.sql.Connection connection = null;

        try {

            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String server = "localhost";
            String database = "projetologin";
            String url = "jdbc:mysql://" + server + ":3306/" + database;
            String user = "root";
            String password = "1234";

            connection = DriverManager.getConnection(url, user, password);

            // Testando..
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NAO CONECTADO!");
            }

            return connection;

        } catch (ClassNotFoundException e) {  //Driver nao encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage());
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    public ArrayList getMyList() {

        myList.clear();

        try {
            Statement stmt = this.getConecction().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_users");
            while (res.next()) {

                int id = res.getInt("id");
                String name = res.getString("name");
                String password = res.getString("password");

                User object = new User(id, name, password);

                myList.add(object);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return myList;
    }

    public int higherID() throws SQLException {

        int maiorID = 0;
        try {
            Statement stmt = this.getConecction().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_users");
            res.next();
            maiorID = res.getInt("id");

            stmt.close();

        } catch (SQLException ex) {
        }

        return maiorID;
    }

    public boolean InsertUserBD(User object) {
        String sql = "INSERT INTO tb_users(id,name,password) VALUES(?,?,?)";

        try {
            PreparedStatement stmt = this.getConecction().prepareStatement(sql);

            stmt.setInt(1, object.getId());
            stmt.setString(2, object.getName());
            stmt.setString(3, object.getPassword());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public boolean SelectUserBD(String name, String password) {
        String sql = "SELECT * FROM tb_users WHERE name = ? AND password = ?";

        try {
            Connection connection = getConecction();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, password);

            ResultSet res = stmt.executeQuery();
            boolean userExists = res.next(); 

            stmt.close();
            connection.close(); 

            return userExists;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}
