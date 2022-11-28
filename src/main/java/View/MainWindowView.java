package View;

//import UseCases.UserLogin;

import DataStructures.CreatedGroupInfo;
import DataStructures.JoinedGroupInfo;
import DataStructures.LoggedInInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainWindowView extends JFrame implements ActionListener {
    private final UserLoginView loginView;
    private final UserRegisterView registerView;
    private HomePageView homePageView;

    private LoggedInInfo userInfo;

    private CreatedGroupInfo createdGroupInfo;
    private JoinedGroupInfo joinedGroupInfo;

    /**
     * Generates a window to switch between log in and registration pages.
     */
    public MainWindowView() {

        this.loginView = new UserLoginView();
        this.registerView = new UserRegisterView();

        // SetUp main window
        setSize(1500, 820);
        setVisible(true);
        this.setContentPane(loginView);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginView.getLogin().addActionListener(this);
        loginView.getToRegister().addActionListener(this);

    }

    /**
     * @param evt the event to be processed
     * React to a certain button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());

        if (evt.getActionCommand().equals("Log In")) {
            this.userInfo = loginView.getUserInfo();
            if (userInfo.isStatusBool()) {
                setHomePage(userInfo.getUsername(), getGroupNames(userInfo));
            }

            else {
                JOptionPane.showMessageDialog(this,
                        "Not Successful :(", "", JOptionPane.PLAIN_MESSAGE);
            }
        }

        else if (evt.getActionCommand().equals("New to Flipwise?")) {
            setUserRegisterView(this.registerView);
        }

        else if (evt.getActionCommand().equals("Exit")) {
            this.setContentPane(loginView);
        }

        else if (evt.getActionCommand().equals("Sign Up")){
            if (registerView.final_output) {
                JOptionPane.showMessageDialog(this,
                        "Registration successful! Log in to your new account.", "",
                        JOptionPane.PLAIN_MESSAGE);
                setContentPane(loginView);
            }

            else {
                JOptionPane.showMessageDialog(this,
                        "Not Successful :(", "", JOptionPane.PLAIN_MESSAGE);
            }
        }

        else if (evt.getActionCommand().equals("Create Group")) {
            createdGroupInfo = homePageView.getCreatedGroupInfo();
            setHomePage(userInfo.getUsername(), createdGroupInfo.getAllGroupNames());
        }

        else if (evt.getActionCommand().equals("Join Group")){
            joinedGroupInfo = homePageView.getJoinedGroupInfo();
            setHomePage(userInfo.getUsername(), joinedGroupInfo.getGroupNames());
        }
        else {
            String groupID = getGroupID(getGroupIDs(userInfo),
                getGroupNames(userInfo), evt.getActionCommand());
            setGroupSummery(evt.getActionCommand(), groupID, userInfo.getUsername(),
                getPurchaseListData(userInfo, groupID), getPlanningListData(userInfo, groupID),
                getGroupDebtData(userInfo, groupID), getAllUserNames(userInfo, groupID));

        }
    }

    /**
     * Switches to registration page on the main window.
     * @param registerView the screen in which the user signs up.
     */
    private void setUserRegisterView(UserRegisterView registerView){
        this.setContentPane(registerView);
        registerView.getSignup().addActionListener(this);
        registerView.getExitSignUp().addActionListener(this);
    }

    /**
     * Switches to homepage on the main window.
     * @param user the name of the user that logged in.
     * @param groupnames the list of groups the user is apart of.
     */
    private void setHomePage(String user, List<String> groupnames){
        HomePageView homePageView = new HomePageView(user, groupnames);
        this.setContentPane(homePageView);
        homePageView.getJoinGroup().addActionListener(this);
        homePageView.getCreateGroup().addActionListener(this);
    }

    /**
     * Switches to group summery page on the main window.
     * @param group the name of the group.
     */
    public void setGroupSummery(String group, String groupid, String username,
                                List<List<String>> purchaseListData, List<List<String>> planningListData,
                                List<List<String>> debtData, List<String> groupUserNames) {
//        String groupID = getGroupID(getGroupIDs(userInfo),
//                getGroupNames(userInfo), group);
//        GroupSummaryView selectedGroup = new GroupSummaryView(group, groupID, userInfo.getUsername(),
//                getPurchaseListData(userInfo, groupID), getPlanningListData(userInfo, groupID),
//                getGroupDebtData(userInfo, groupID), getAllUserNames(userInfo, groupID));
        this.dispose();
        GroupSummaryView selectedGroup = new GroupSummaryView(group, groupid, username,
                purchaseListData, planningListData, debtData, groupUserNames);
        selectedGroup.setVisible(true);

    }

    /**
     * @return the name of the user that logged in.
     */
    public String getUsername(LoggedInInfo userInfo){
        return userInfo.getUsername();
    }

    /**
     * @return the ID of the group the user selected.
     */
    public String getGroupID(List<String> groupIDs, List<String> groupnames, String group){
        int index = groupnames.indexOf(group);
        return groupIDs.get(index);
    }

    /**
     * @return the list of names of the groups the user is apart of.
     */
    public List<String> getGroupNames(LoggedInInfo loggedInInfo) {
        List<List<Object>> allGroups = loggedInInfo.getUserAllGroups();
        List<String> output = new ArrayList<>();
        for (int i = 0; i < allGroups.size(); i++) {
            List<Object> currentGroup = allGroups.get(i);
            output.add((String) currentGroup.get(1));
        }
        return output;
    }

    /**
     * @return the list of IDs of the groups the user is apart of.
     */
    public List<String> getGroupIDs(LoggedInInfo loggedInInfo) {
        List<List<Object>> allGroups = loggedInInfo.getUserAllGroups();
        List<String> output = new ArrayList<>();
        for (int i = 0; i < allGroups.size(); i++) {
            List<Object> currentGroup = allGroups.get(i);
            output.add((String) currentGroup.get(0));
        }
        return output;
    }

    /**
     * @return the list of all purchased items of a group.
     */
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

    /**
     * @return the list of all planning items of a group.
     */
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

    /**
     * @return the list of all users a part of a group.
     */
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

    /**
     * @return the list of debt information between members of a group.
     */
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


