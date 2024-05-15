package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.UserDAO;
import Model.User;

public class GUI implements ActionListener{

    private static JLabel userLabel;
    private static JLabel passwordLabel;
    private static JLabel success;
    private static JTextField userText;
    private static JPasswordField passwordText;
    private static JButton loginButton;


    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(300, 225);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.setTitle("Login");

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 60, 80, 25);
        panel.add(passwordLabel);

        userText = new JTextField();
        userText.setBounds(80, 20, 150, 25);
        panel.add(userText);

        passwordText = new JPasswordField();
        passwordText.setBounds(80, 60, 150, 25);
        panel.add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 100, 80, 25);
        loginButton.addActionListener(new GUI());
        panel.add(loginButton);

        success = new JLabel("");
        success.setBounds(92, 130, 100, 25);
        panel.add(success);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userText.getText();
        String password = passwordText.getText();
        System.out.println(username + ", " + password);

        if (!username.equals("") && !password.equals("")) {
            User user = new User();
            user.setName(username);
            user.setPassword(password);

            try {
                new UserDAO().insertUser(user);
                success.setText("Login successful!");
            } catch (Exception ex) {
                ex.printStackTrace();
                success.setText("Login failed!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Some fields are empty");
        }
    }
}
