package Principal;

import DAO.Connection;
import View.GUI;

public class Main {
    public static void main(String[] args) {
        Connection.getConecction();

        GUI.main(args);
    }
}