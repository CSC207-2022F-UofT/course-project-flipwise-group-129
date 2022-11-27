package View;

//import UseCases.UserLogin;

import Controllers.UserRegisterController;
import DataStructures.LoggedInInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainWindowView extends JFrame implements ActionListener {
    private final UserLoginView loginView;
    private final UserRegisterView registerView;
 //   private final HomePageView homePageView;
    private LoggedInInfo userInfo;

    public MainWindowView() {

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

        if (e.getActionCommand().equals("Login")) {
            this.userInfo = loginView.getUserInfo();
            this.dispose();
            HomePageView homePageView = new HomePageView(getUsername(userInfo), getGroupNames(userInfo));
            homePageView.setVisible(true);
            //after user logins, all the log-in information is immediately stored
        }

        if (e.getActionCommand().equals("New to Flipwise?")) {
            setUserRegisterView();
        }

        if (e.getActionCommand().equals("Exit")) {
            this.setContentPane(loginView);
            this.pack();
        }

        //EXCLUSIVE TO REGISTER
        if (e.getActionCommand().equals("Sign up")){
            boolean registration = registerView.getFinalOutput();
            if (registration) {
                JOptionPane.showMessageDialog(this, "Registration successful", "", JOptionPane.PLAIN_MESSAGE);
                this.dispose();
                HomePageView homePageView = new HomePageView(getUsername(userInfo), getGroupNames(userInfo));
                homePageView.setVisible(true);
            }
        }
        //EXCLUSIVE



    }

    private void setUserRegisterView(){
        this.setContentPane(registerView);
        registerView.getSignUpButton().addActionListener(this);
        registerView.getExitButton().addActionListener(this);
        this.pack();
    }

    public String getUsername(LoggedInInfo loggedInInfo) {
        return loggedInInfo.getUsername();
    }

    public List<String> getGroupNames(LoggedInInfo loggedInInfo) {
        List<List<Object>> allGroups = loggedInInfo.getUserAllGroups();
        List<String> output = new ArrayList<>();
        for (int i = 0; i < allGroups.size(); i++) {
            List<Object> currentGroup = allGroups.get(i);
            output.add((String) currentGroup.get(1));
        }
        return output;
    }

    public List<String> getGroupIDs(LoggedInInfo loggedInInfo) {
        List<List<Object>> allGroups = loggedInInfo.getUserAllGroups();
        List<String> output = new ArrayList<>();
        for (int i = 0; i < allGroups.size(); i++) {
            List<Object> currentGroup = allGroups.get(i);
            output.add((String) currentGroup.get(0));
        }
        return output;
    }

    public List<List<String>> getPurchaseListData(LoggedInInfo loggedInInfo, String groupID) {
        List<List<Object>> allGroups = loggedInInfo.getUserAllGroups();
        List<List<String>> output = new ArrayList<>();
        for (int i = 0; i < allGroups.size(); i++) {
            List<Object> currentGroup = allGroups.get(i);
            if (currentGroup.get(0) == groupID) {
                output = (List<List<String>>) currentGroup.get(3);
            }
        }
        return output;
    }

    public List<List<String>> getPlanningListData(LoggedInInfo loggedInInfo, String groupID) {
        List<List<Object>> allGroups = loggedInInfo.getUserAllGroups();
        List<List<String>> output = new ArrayList<>();
        for (int i = 0; i < allGroups.size(); i++) {
            List<Object> currentGroup = allGroups.get(i);
            if (currentGroup.get(0) == groupID) {
                output = (List<List<String>>) currentGroup.get(2);
            }
        }
        return output;
    }

    public List<String> getAllUserNames(LoggedInInfo loggedInInfo, String groupID) {
        List<List<Object>> allGroups = loggedInInfo.getUserAllGroups();
        List<String> output = new ArrayList<>();
        for (int i = 0; i < allGroups.size(); i++) {
            List<Object> currentGroup = allGroups.get(i);
            if (currentGroup.get(0) == groupID) {
                output = (List<String>) currentGroup.get(4);
            }
        }
        return output;
    }

    public List<List<String>> getGroupDebtData(LoggedInInfo loggedInInfo, String groupID) {
        List<List<Object>> allGroups = loggedInInfo.getUserAllGroups();
        List<List<String>> output = new ArrayList<>();
        for (int i = 0; i < allGroups.size(); i++) {
            List<Object> currentGroup = allGroups.get(i);
            if (currentGroup.get(2) == groupID) {
                output = (List<List<String>>) currentGroup.get(5);
            }
        }
        return output;
    }





}
