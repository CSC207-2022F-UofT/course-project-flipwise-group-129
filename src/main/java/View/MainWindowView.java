package View;

//import UseCases.UserLogin;

import Controllers.UserRegisterController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowView extends JFrame implements ActionListener {
    private final UserLoginView loginView;
    private final UserRegisterView registerView;
    private final UserRegisterController userRegisterController;

    public MainWindowView(UserRegisterController controller) {
        this.userRegisterController = controller;

        this.loginView = new UserLoginView();
        this.registerView = new UserRegisterView();
        setSize(1000,600);
        this.setContentPane(loginView);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.pack();

        loginView.getLoginButton().addActionListener(this);
        loginView.getNewButton().addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());

        if (e.getActionCommand().equals("Continue")){
            this.dispose();
            HomePageView homePageView = new HomePageView();
            homePageView.setVisible(true);
        }

        if (e.getActionCommand().equals("New to Flipwise?")){
            setUserRegisterView();
        }

        if (e.getActionCommand().equals("Exit")){
            this.setContentPane(loginView);
            this.pack();
        }

        //EXCLUSIVE TO REGISTER
        if (e.getActionCommand().equals("Sign up")){
            boolean registration = this.userRegisterController.controlUseCase(registerView.getUsername(),
                    registerView.getPassword1(), registerView.getPassword2());


            if (registration) {
                JOptionPane.showMessageDialog(this, "Registration successful", "", JOptionPane.PLAIN_MESSAGE);
                this.dispose();
                HomePageView homePageView = new HomePageView();
                homePageView.setVisible(true);
            }
        }
        //EXCLUSIVE

//        if (e.getActionCommand().equals()){
//            this.dispose();
//            GroupSummaryView groupSummaryView = new GroupSummaryView();
//            groupSummaryView.setVisible(true);
//        }

    }

    private void setUserRegisterView(){
        this.setContentPane(registerView);
        registerView.getSignUpButton().addActionListener(this);
        registerView.getExitButton().addActionListener(this);
        this.pack();
    }



//    private void setHomepageView(){
//        HomePageView homePageView = new HomePageView();
//        this.setContentPane(homePageView);
//        //
//    }

//    private void setUserLoginView(){
//        UserLoginView userLoginView = new UserLoginView();
//        this.getContentPane(userLoginView);
//
//    }

}
