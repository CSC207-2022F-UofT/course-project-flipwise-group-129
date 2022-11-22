package View;

import Controllers.UserRegisterController;
import DataAccess.UserDataAccess;
import DataAccessInterface.UserDataInterface;
import DataStructures.RegisterCredentials;
import InputBoundary.UserRegisterBoundaryIn;
import Presenters.UserRegisterPresenter;
import UseCases.UserRegister;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserRegisterView extends JPanel implements ActionListener{
    private final JButton signupButton;
    private final JTextField username;
    private final JPasswordField p1, p2;
    private final JLabel t1, t2, t3, t4;
    private JButton exitButton;
    private UserRegisterController controller;
    private boolean final_output;

    public UserRegisterView(){

        // Defining JComponents
        t1 = new JLabel("Create an Account");
        Font f = new Font("Arial", Font.BOLD, 20);
        t1.setFont(f);
        t2 = new JLabel("Username");
        username = new JTextField();
        t3 = new JLabel("Password");
        p1 = new JPasswordField();
        t4 = new JLabel("Re-enter Password");
        p2 = new JPasswordField();
        signupButton = new JButton("Sign Up");
        exitButton = new JButton("Exit");

        UserDataInterface userData;
        try {
            userData = new UserDataAccess();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e); // Display popup
        }

        UserRegisterPresenter presenter = new UserRegisterPresenter();
        UserRegisterBoundaryIn useCase = new UserRegister(presenter, userData);

        this.controller = new UserRegisterController(useCase);

        // Setting positions of JComponents
        t1.setBounds(70, 40, 300, 40);
        t2.setBounds(70, 100, 200, 20);
        username.setBounds(70, 120, 300, 30);
        t3.setBounds(70, 170, 200, 20);
        p1.setBounds(70, 190, 300, 30);
        t4.setBounds(70, 220, 200, 20);
        p2.setBounds(70, 240, 300, 30);
        signupButton.setBounds(200, 300, 100, 30);
        exitButton.setBounds(70, 300, 100, 30);

        // Adding JComponents
        add(t1);
        add(t2);
        add(username);
        add(t3);
        add(p1);
        add(t4);
        add(p2);
        add(signupButton);
        add(exitButton);

        // JPanel Setup
        setLayout(null);
        setSize(1000, 600);
        setVisible(true);


        signupButton.addActionListener(this);
        exitButton.addActionListener(this);


    }

    public void actionPerformed(ActionEvent e) {

            String output_username = getUsername();
            String output_password1 = getPassword1();
            String output_password2 = getPassword2();

            this.final_output = controller.controlUseCase(output_username, output_password1, output_password2);

            //this.final_output indicates whether account creation was successful or not

            if (this.final_output) {
                JOptionPane.showMessageDialog(this, "Registration successful", "", JOptionPane.PLAIN_MESSAGE);
            }
    }

    public JButton getSignUpButton() { return signupButton; }
    public JButton getExitButton() { return exitButton; }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword1() {
        return new String(p1.getPassword());
    }

    public String getPassword2() {
        return new String(p2.getPassword());
    }

    public boolean getFinalOutput() {
        return this.final_output;
    }

}

