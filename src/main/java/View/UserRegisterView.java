package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegisterView extends JPanel implements ActionListener{
    private final JButton signupButton;
    private final JTextField username;
    private final JPasswordField p1, p2;
    private final JLabel t1, t2, t3, t4;
    private JButton exitButton;

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
        signupButton = new JButton("Continue");
        exitButton = new JButton("Exit");

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
        setVisible(true);
        setSize(450, 450);

        signupButton.addActionListener(this);
        exitButton.addActionListener(this);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Continue")){
//            controller.callUserLoginInteractor(email.getText(), String.valueOf(password.getPassword()));
//            try {
////                userRegisterController.create(email.getText(),
////                        String.valueOf(password.getPassword()),
////                        String.valueOf(repeatPassword.getPassword()),
////                        String.valueOf(name.getText()));
////                String output = String.format("%s Individual created.", name.getText());
////                JOptionPane.showMessageDialog(this, output);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, e.getMessage());
//            }
        }
  }
    public JButton getSignUpButton() { return signupButton; }
    public JButton getExitButton() { return exitButton; }

}

