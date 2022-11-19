package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.ViewInterface;


public class UserLoginView extends JPanel implements ActionListener{

    private JLabel title;

    private final JTextField username;
    private final JPasswordField password;
    private final JButton loginButton;
    private JButton newButton;
    private final JLabel t1, t2, t3;


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
            setVisible(true);
            this.setSize(1000, 1000);

            loginButton.addActionListener(this);
            newButton.addActionListener(this);

        }

//    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Continue")) {
//            controller.callUserLoginInteractor(email.getText(), String.valueOf(password.getPassword()));
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

}




