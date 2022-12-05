package View;

import Controllers.UserLoginController;
import DataAccess.GroupDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.LoggedInInfo;
import InputBoundary.UserLoginBoundaryIn;
import Presenters.UserLoginPresenter;
import UseCases.UserLogin;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class UserLoginView extends JPanel {
// implements ActionListener
    JLabel title, t1, t2;
    private final JTextField username = new JTextField(25);
    private final JPasswordField password = new JPasswordField(15);
    public JButton login;
    public JButton toRegister;
    private LoggedInInfo userInfo;

    /**
     * Builds the gui for the user login page.
     */
    public UserLoginView(){

        UserDataInterface userData;
        GroupDataInterface groupData;
        try {
            userData = new UserDataAccess();
            groupData = new GroupDataAccess();
        } catch (IOException | ParseException e2) {
            throw new RuntimeException(e2); // Display popup
        }

        UserLoginPresenter presenter = new UserLoginPresenter();
        UserLoginBoundaryIn useCase = new UserLogin(presenter, userData, groupData);

        UserLoginController controller = new UserLoginController(useCase);

        // Defining JComponents
        title = new JLabel("Welcome Back!");
        Font f = new Font("Arial", Font.BOLD, 24);
        title.setFont(f);
        t1 = new JLabel("Username");
        t2 = new JLabel("Password");
        login = new JButton("Log In");
        toRegister = new JButton("New to Flipwise?");


        // Setting positions of JComponents
        title.setBounds(70, 40, 300, 40);
        t1.setBounds(70, 100, 200, 20);
        username.setBounds(70, 120, 300, 30);
        t2.setBounds(70, 170, 200, 20);
        password.setBounds(70, 190, 300, 30);
        login.setBounds(240, 240, 130, 30);
        toRegister.setBounds(70, 240, 130, 30);

        // Adding JComponents to JPanel
        this.add(title);
        this.add(t1);
        this.add(username);
        this.add(t2);
        this.add(password);
        this.add(login);
        this.add(toRegister);

        // JPanel SetUp
        this.setLayout(null);
        setSize(1500, 820);
        setVisible(true);
    }


    /**
     * @return the username of the user that logged in.
     */
    public String getUsername(){
        return this.username.getText();
    }

    /**
     * @return the password of the user that logged in.
     */
    public String getPassword(){
        return String.valueOf(this.password.getPassword());
    }

    /**
     * @return the button that allows the user to log in.
     */
    public JButton getLogin(){ return login; }

    /**
     * @return the button that brings user to registration page.
     */
    public JButton getToRegister(){ return toRegister; }

    /**
     * @return the data of the user.
     */
    public LoggedInInfo getUserInfo() { return this.userInfo; }

}




