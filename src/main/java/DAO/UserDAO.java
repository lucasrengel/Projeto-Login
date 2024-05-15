package DAO;

import Model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    public void insertUser(User user){

        String sql = "INSERT INTO users (name, password) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Connection.getConecction().prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
