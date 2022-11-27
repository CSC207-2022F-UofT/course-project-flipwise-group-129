package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindowView extends JFrame implements ActionListener {

    private final UserRegisterView registerView;
    private final UserLoginView loginView;

    /**
     * Generates a window to switch between log in and registration pages.
     */
    public WelcomeWindowView(UserLoginView loginView, UserRegisterView registerView) {
        this.registerView = registerView;
        this.loginView = loginView;

        // SetUp main window
        setSize(1500, 820);
        setVisible(true);
        this.setContentPane(loginView);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginView.toRegister.addActionListener(this);
        loginView.login.addActionListener(this);

    }

    /**
     * @param evt the event to be processed
     * React to a certain button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {

        if (evt.getActionCommand().equals("Sign Up") || evt.getActionCommand().equals("Log In")) {
//          if (loginView.userInfo.isStatusBool() || registerView.final_output){
            if (true) {
                setMainWindow();
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "Not Successful", "", JOptionPane.PLAIN_MESSAGE);
            }
        }

        else if (evt.getActionCommand().equals("New to Flipwise?")) {
            setUserRegisterView(registerView);
        }

        else if (evt.getActionCommand().equals("Exit")) {
            this.setContentPane(loginView);
        }



        System.out.println("Clicked " + evt.getActionCommand() + " from Welcome");

    }

    /**
     * Switches to registration page on the window.
     * @param registerView the screen in which the user signs up.
     */
    private void setUserRegisterView(UserRegisterView registerView){
        this.setContentPane(registerView);
        registerView.getExitSignUp().addActionListener(this);
        registerView.getSignupButton().addActionListener(this);
    }

    /**
     * Disposes current window and generates another window.
     */
    private void setMainWindow(){
        this.dispose();
        MainWindowView mainWindowView = new MainWindowView();
        mainWindowView.setVisible(true);
    }

}
