package View;

import DataStructures.AccountStatus;
import View.ViewInterface;
import Controllers.UserRegisterController;
import DataAccess.UserDataAccess;
import DataAccessInterface.UserDataInterface;
import InputBoundary.UserRegisterBoundaryIn;
import Presenters.UserRegisterPresenter;
import UseCases.UserRegister;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserRegisterView extends JPanel implements ActionListener {
    JLabel title, t1, t2, t3;
    JTextField username = new JTextField(25);
    JPasswordField password = new JPasswordField(15);
    JPasswordField repeatPassword = new JPasswordField(15);

    public JButton signup;
    public JButton exitSignUp;

    UserRegisterController controller;
    public boolean final_output;

    /**
     * Builds the gui for the registration page.
     */
    public UserRegisterView() {

        UserDataInterface userData;
        try {
            userData = new UserDataAccess();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e); // Display popup
        }

        UserRegisterPresenter presenter = new UserRegisterPresenter();
        UserRegisterBoundaryIn useCase = new UserRegister(presenter, userData);

        this.controller = new UserRegisterController(useCase);

        // Defining JComponents
        title = new JLabel("Create an Account");
        Font f = new Font("Arial", Font.BOLD, 20);
        title.setFont(f);
        t1 = new JLabel("Username");
        t2 = new JLabel("Password");
        t3 = new JLabel("Re-enter Password");
        signup = new JButton("Sign Up");
        exitSignUp = new JButton("Exit");

        // Setting positions of JComponents
        title.setBounds(70, 40, 300, 40);
        t1.setBounds(70, 100, 200, 20);
        username.setBounds(70, 120, 300, 30);
        t2.setBounds(70, 170, 200, 20);
        password.setBounds(70, 190, 300, 30);
        t3.setBounds(70, 220, 200, 20);
        repeatPassword.setBounds(70, 240, 300, 30);
        signup.setBounds(200, 300, 100, 30);
        exitSignUp.setBounds(70, 300, 100, 30);

        // Adding JComponents
        add(title);
        add(t1);
        add(username);
        add(t2);
        add(password);
        add(t3);
        add(repeatPassword);
        add(signup);
        add(exitSignUp);

        // JPanel Setup
        setLayout(null);
        setSize(1500, 820);
        setVisible(true);

        signup.addActionListener(this);
        exitSignUp.addActionListener(this);

    }

    /**
     * Calls the controller with the entered username, password, and repeated password as arguments.
     * @param e the event to be processed
     */
    public void actionPerformed (ActionEvent e){
        if (e.getActionCommand().equals("Sign Up")){
            System.out.println("Collecting stuff from signup");
            this.final_output = controller.controlUseCase(username.getText(), String.valueOf(password.getPassword()),
                String.valueOf(repeatPassword.getPassword()));
        }
    }

    /**
     * @return the sign up button that brings user to homepage.
     */
    public JButton getSignup() { return signup; }

    /**
     * @return the exit button that brings user back to log in.
     */
    public JButton getExitSignUp() { return exitSignUp; }

//    public String getUsername() { return this.username.getText(); }
//
//    public String getPassword1() { return new String(password.getPassword()); }
//
//    public String getPassword2() { return new String(repeatPassword.getPassword()); }
//
//    public boolean getFinalOutput() { return this.final_output; }

    }




