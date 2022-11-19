package View;

import UseCases.UserLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowView extends JFrame implements ActionListener {
    private final UserLoginView loginView;
    public MainWindowView() {

        this.loginView = new UserLoginView();

        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setContentPane(loginView);

        this.pack();

        loginView.getLoginButton().addActionListener(this);
        loginView.getNewButton().addActionListener(this);
        int R = this.getBackground().getRed();
        int G = this.getBackground().getGreen();
        int B = this.getBackground().getBlue();

        System.out.println("cOLOUR IS " + R + " " + G + " " + B);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());

        if (e.getActionCommand().equals("Continue")){
            setHomepageView();
        }

        if (e.getActionCommand().equals("New to Flipwise?")){
            setUserRegisterView();
        }

        if (e.getActionCommand().equals("Exit")){
            this.setContentPane(loginView);
            this.pack();
        }
    }

    private void setUserRegisterView(){
        UserRegisterView registerView = new UserRegisterView();
        this.setContentPane(registerView);
        registerView.getSignUpButton().addActionListener(this);
        registerView.getExitButton().addActionListener(this);
        this.pack();
    }

    private void setHomepageView(){
        HomePageView homePageView = new HomePageView();
        this.setContentPane(homePageView);
    }

//    private void setUserLoginView(){
//        UserLoginView userLoginView = new UserLoginView();
//        this.getContentPane(userLoginView);
//
//    }

    private void setGroupSummeryView(){
        GroupSummaryView groupSummaryView = new GroupSummaryView();
    }
}
