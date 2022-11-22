package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Controllers.UserLoginController;
import Controllers.UserRegisterController;
import DataAccess.GroupDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.LoggedInInfo;
import InputBoundary.UserLoginBoundaryIn;
import Presenters.UserLoginPresenter;
import UseCases.UserLogin;
import View.ViewInterface;
import org.json.simple.parser.ParseException;


public class UserLoginView extends JPanel implements ActionListener{

    private JLabel title;

    private final JTextField username;
    private final JPasswordField password;
    private final JButton loginButton;
    private JButton newButton;
    private final JLabel t1, t2, t3;
    private UserLoginController controller;


    public UserLoginView(){

            // Defining JComponents
            t1 = new JLabel("Login");
            Font f = new Font("Arial", Font.BOLD, 24);
            t1.setFont(f);
            t2 = new JLabel("Username");
            username = new JTextField(30);
            t3 = new JLabel("Password");
            password = new JPasswordField(20);
            loginButton = new JButton("Continue");
            newButton = new JButton("New to Flipwise?");


            // Setting positions of JComponents
            t1.setBounds(70, 40, 300, 40);
            t2.setBounds(70, 100, 200, 20);
            username.setBounds(70, 120, 300, 30);
            t3.setBounds(70, 170, 200, 20);
            password.setBounds(70, 190, 300, 30);
            loginButton.setBounds(240, 240, 130, 30);
            newButton.setBounds(70, 240, 130, 30);

            // Adding JComponents to JPanel
            this.add(t1);
            this.add(t2);
            this.add(username);
            this.add(t3);
            this.add(password);
            this.add(loginButton);
            this.add(newButton);

            // JPanel SetUp
            this.setLayout(null);
            this.setSize(1000, 600);
            setVisible(true);

            loginButton.addActionListener(this);
            newButton.addActionListener(this);

        }

//    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Continue")) {
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

            this.controller = new UserLoginController(useCase);

            LoggedInInfo userInfo = this.controller.controlUseCase(getUsername(), String.valueOf(password.getPassword()));

        }
    }

    /**
     * displays message dialog when login fails.
     * @param errorMessage te message to be displayed to the user after failed log in.
     */
    public void showFailureLoginMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    public JButton getLoginButton(){
        return loginButton;
    }

    public JButton getNewButton(){
        return newButton;
    }

    public String[] getGroups(){
        // Filter through alexs code here
        // testing
        String[] s = new String[]{"Saleh"};
        return s;
    }

    public String getUsername(){
        return this.username.getText();
    }

}




