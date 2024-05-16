package Model;

import DAO.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    public static ArrayList<User> myList;

    private int id;
    private String name;
    private String password;
    private final UserDAO dao;

    public User() {
        this.dao = new UserDAO();
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dao = new UserDAO();
    }

    public ArrayList getMyList() {
        return dao.getMyList();
    }

    public boolean insertUserBD(String name, String password) throws SQLException {
        int id = this.higherID() + 1;
        User object = new User(id, name, password);
        dao.InsertUserBD(object);
        return true;
    }

    public boolean selectUserBD(String name, String password) throws SQLException{
        return dao.SelectUserBD(name, password);
    }

    public int higherID() throws SQLException {
        return dao.higherID();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name="
                + name;
    }
}
