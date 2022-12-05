package View;

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
    final JTextField username = new JTextField(25);
    final JPasswordField password = new JPasswordField(15);
    final JPasswordField repeatPassword = new JPasswordField(15);

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
            if (isAlpha(getUsername()) && isPassword(getPassword())) {
                this.final_output = controller.controlUseCase(username.getText(),
                        String.valueOf(password.getPassword()),
                    String.valueOf(repeatPassword.getPassword()));

                if (this.final_output) {
                JOptionPane.showMessageDialog(this,
                        "Registration successful! Log in to your new account.");
                }

                else { JOptionPane.showMessageDialog(this, "Not Successful :("); }
            }
            else { showMessage("Error with inputs!"); }
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * @return the button that allows user to sign up for an account.
     */
    public JButton getSignup() { return signup; }

    /**
     * @return the exit button that brings user back to log in.
     */
    public JButton getExitSignUp() { return exitSignUp; }

    /**
     * @return the password of the user that logged in.
     */
    public String getPassword() { return String.valueOf(this.password.getPassword());}

    /**
     * @return the username of the user that logged in.
     */
    public String getUsername() { return this.username.getText(); }

    /**
     * helper function to confirm whether input added is valid
     * @param name string to be tested
     * @return  whether the string was successful
     */
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z0-9]+");
    }

    /**
     * helper function to confirm whether password added is valid
     * @param password string to be added
     * @return whether password was successful
     */
    public boolean isPassword(String password) {
        return password.matches("[a-zA-Z0-9@ ]+");
    }

    }




