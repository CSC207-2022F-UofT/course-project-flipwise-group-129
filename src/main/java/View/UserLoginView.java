package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLoginView extends JFrame{
    private final JTextField username;
    private final JPasswordField password;
    private final JButton loginButton;
    private final JLabel t1, t2, t3;


        public UserLoginView(){

            // Defining
            t1 = new JLabel("Login");
            Font f = new Font("Arial", Font.BOLD, 24);
            t1.setFont(f);
            t2 = new JLabel("Username");
            username = new JTextField();
            t3 = new JLabel("Password");
            password = new JPasswordField();
            loginButton = new JButton("Continue");

            // setting positions
            t1.setBounds(70, 40, 200, 40);
            t2.setBounds(70, 100, 100, 20);
            username.setBounds(70, 120, 200, 30);
            t3.setBounds(70, 170, 100, 20);
            password.setBounds(70, 190, 200, 30);
            loginButton.setBounds(170, 240, 100, 30);

            // adding
            add(t1);
            add(t2);
            add(username);
            add(t3);
            add(password);
            add(loginButton);

            // JFrame Setup
            setLayout(null);
            setVisible(true);
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(username.getText());
                }
            });

        }

//        public static void main(String[] args) {
//            UserLoginView login = new UserLoginView();
//        }
    }




