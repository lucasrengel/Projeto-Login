package DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {


    private static final String url = "jdbc:mysql://localhost:3306/projetologin";
    private static final String user = "root";
    private static final String password = "1234";

    private static java.sql.Connection connection;


    public static java.sql.Connection getConecction(){

        try {
            if(connection == null){
                connection = DriverManager.getConnection(url, user, password);
                return connection;
            }else{
                return connection;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
