package View;

import Controllers.GroupCreateController;
import Controllers.GroupJoinController;
import Controllers.UserLoginController;
import DataAccess.GroupDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.CreatedGroupInfo;
import DataStructures.JoinedGroupInfo;
import DataStructures.LoggedInInfo;
import InputBoundary.GroupCreateBoundaryIn;
import InputBoundary.GroupJoinBoundaryIn;
import InputBoundary.UserLoginBoundaryIn;
import Presenters.GroupCreatePresenter;
import Presenters.GroupJoinPresenter;
import Presenters.UserLoginPresenter;
import UseCases.GroupCreate;
import UseCases.GroupJoin;
import UseCases.UserLogin;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.swing.JOptionPane.NO_OPTION;

public class MainWindowView extends JFrame implements ActionListener {
    private final UserLoginView loginView;
    private final UserRegisterView registerView;
    private final GroupCreateController controllerCreate;
    private final GroupJoinController controllerJoin;

    private LoggedInInfo userInfo;

    private List<List<Object>> userGroups;
    private final UserLoginController userLoginController;


    /**
     * Generates a window to switch between log in and registration pages.
     */
    public MainWindowView() {

        UserDataInterface UserInterface;

        GroupDataInterface GroupInterface;
        try {
            GroupInterface = new GroupDataAccess();
            UserInterface = new UserDataAccess();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        //GroupCreate
        GroupCreatePresenter presenterCreate = new GroupCreatePresenter();
        GroupCreateBoundaryIn useCaseCreate = new GroupCreate(presenterCreate, GroupInterface, UserInterface);

        this.controllerCreate = new GroupCreateController(useCaseCreate);

        //GroupJoin
        GroupJoinPresenter presenterJoin = new GroupJoinPresenter();
        GroupJoinBoundaryIn useCaseJoin = new GroupJoin(presenterJoin, GroupInterface, UserInterface);

        this.controllerJoin = new GroupJoinController(useCaseJoin);

        UserLoginPresenter userLoginPresenter = new UserLoginPresenter();
        UserLoginBoundaryIn useCase = new UserLogin(userLoginPresenter, UserInterface, GroupInterface);

        this.userLoginController = new UserLoginController(useCase);

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
//        System.out.println("Click " + evt.getActionCommand());

        if (evt.getActionCommand().equals("Log In")) {
            if (isAlpha(loginView.getUsername()) && isAlpha(loginView.getPassword())) {
                this.userInfo = this.userLoginController.controlUseCase(loginView.getUsername(),
                        loginView.getPassword());
                this.userGroups = userInfo.getUserAllGroups();
                if (userInfo.statusBool()) {
                    setHomePage(userInfo.getUsername(), getGroupNames(userGroups));
                }
                else { showMessage("Not Successful :("); }
            }
            else { showMessage("Error with inputs!"); }
        }
        else if (evt.getActionCommand().equals("New to Flipwise?")) {
            setUserRegisterView(this.registerView);
        }

        else if (evt.getActionCommand().equals("Exit")) {
            this.setContentPane(loginView);
        }

        else if (evt.getActionCommand().equals("Sign Up")){
            System.out.println("From main " + registerView.getFinalOutput());
           setContentPane(loginView);
        }

        else if (evt.getActionCommand().equals("Create Group")) {

            String groupName = JOptionPane.showInputDialog("Please enter in Group Name:");
            showMessage("The name of the group is " + groupName + ".");

            if (groupName.matches("[A-Za-z0-9 ]+")) {
                CreatedGroupInfo createdGroupInfo = this.controllerCreate.create(userInfo.getUsername(), groupName);

                System.out.println(createdGroupInfo);
                if (createdGroupInfo.getError() == null) {
                    setHomePage(userInfo.getUsername(), createdGroupInfo.getAllGroupNames());
                    addCreateGroup(this.userGroups, createdGroupInfo);
                } else { showMessage(createdGroupInfo.getError()); }

            } else { showMessage("Error with input!"); }
        }

        else if (evt.getActionCommand().equals("Join Group")) {

            String groupID = JOptionPane.showInputDialog("Please enter in Group ID:");
            JOptionPane.showMessageDialog(null, "The ID of the group is "
                    + groupID + ".");

            JoinedGroupInfo joinedGroupInfo = this.controllerJoin.create(groupID, userInfo.getUsername());
            if (joinedGroupInfo.getError() == null) {
                setHomePage(userInfo.getUsername(), joinedGroupInfo.getGroupNames());
                addJoinGroup(this.userGroups, joinedGroupInfo, groupID);
                System.out.println("joined group added " + userGroups);
            } else { showMessage(joinedGroupInfo.getError()); }
        }

        else if (evt.getActionCommand().equals("Return to Groups")) {
            setHomePage(userInfo.getUsername(), getGroupNames(userGroups));
        }

        else {
            String groupname = filterGroupName( evt.getActionCommand());
            String groupID = getGroupID(getGroupIDs(userGroups), getGroupNames(userGroups), groupname);
            setGroupSummary(groupname, groupID, userInfo.getUsername(), getPurchaseListData(userGroups, groupID),
                    getPlanningListData(userGroups, groupID), getGroupDebtData(userGroups, groupID),
                    getAllUserNames(userGroups, groupID), new HomePageView(userInfo.getUsername(),
                            getGroupNames(userGroups), this));
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
     * @param groupnames the list of groups the user is involved in.
     */
    private void setHomePage(String user, List<String> groupnames){
        System.out.println("set homepage");
        HomePageView homePageView = new HomePageView(user, groupnames, this);
        this.setContentPane(homePageView);
        homePageView.getJoinGroup().addActionListener(this);
        homePageView.getCreateGroup().addActionListener(this);
    }

    /**
     * Switches to group summery page on the main window.
     * @param group the name of the group.
     */
    public void setGroupSummary(String group, String groupid, String username,
                                List<List<String>> purchaseListData, List<List<String>> planningListData,
                                List<List<String>> debtData, List<String> groupUserNames, HomePageView homePageView) {
        System.out.println("This is group " + group);
        System.out.println("This is groupID " + groupid);
        System.out.println("This is username " + username);
        System.out.println("This is planning " + planningListData);
        System.out.println("This is purchase " + purchaseListData);
        System.out.println("This is debt " + debtData);
        System.out.println("This is members " + groupUserNames);
        GroupSummaryView selectedGroup = new GroupSummaryView(group, groupid, username,
                purchaseListData, planningListData, debtData, groupUserNames, this);
        setContentPane(selectedGroup);
        selectedGroup.getToHomepage().addActionListener(this);

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
        System.out.println(groupnames);
        System.out.println(groupIDs);
        System.out.println(index);
        return groupIDs.get(index);
    }

    /**
     * @return the list of names of the groups the user is included in.
     */
    public List<String> getGroupNames(List<List<Object>> allGroups) {
        List<String> output = new ArrayList<>();
        for (List<Object> currentGroup : allGroups) {
            output.add((String) currentGroup.get(1));
        }
        return output;
    }

    /**
     * @return the list of IDs of the groups the user is included in.
     */
    public List<String> getGroupIDs(List<List<Object>> allGroups) {
        List<String> output = new ArrayList<>();
        for (List<Object> currentGroup : allGroups) {
            output.add((String) currentGroup.get(0));
        }
        return output;
    }

    /**
     * @return the list of all purchased items of a group.
     */
    public List<List<String>> getPurchaseListData(List<List<Object>> allGroups, String groupID) {
        List<List<String>> output = new ArrayList<>();
        for (List<Object> currentGroup : allGroups) {
            if (currentGroup.get(0) == groupID) {
                output = (List<List<String>>) currentGroup.get(3);
            }
        }
        return output;
    }

    /**
     * @return the list of all planning items of a group.
     */
    public List<List<String>> getPlanningListData(List<List<Object>> allGroups, String groupID) {
        List<List<String>> output = new ArrayList<>();
        for (List<Object> currentGroup : allGroups) {
            if (currentGroup.get(0) == groupID) {
                output = (List<List<String>>) currentGroup.get(2);
            }
        }
        return output;
    }

    /**
     * @return the list of all users a part of a group.
     */
    public List<String> getAllUserNames(List<List<Object>> allGroups, String groupID) {
        List<String> output = new ArrayList<>();
        for (List<Object> currentGroup : allGroups) {
            if (currentGroup.get(0) == groupID) {
                output = (List<String>) currentGroup.get(4);
            }
        }
        return output;
    }

    /**
     * @return the list of debt information between members of a group.
     */
    public List<List<String>> getGroupDebtData(List<List<Object>> allGroups, String groupID) {
        List<List<String>> output = new ArrayList<>();
        for (List<Object> currentGroup : allGroups) {
            if (currentGroup.get(2) == groupID) {
                output = (List<List<String>>) currentGroup.get(5);
            }
        }
        return output;
    }

    public void addJoinGroup(List<List<Object>> userGroups, JoinedGroupInfo joinedGroupInfo, String groupID) {
        List<Object> newGroup = new ArrayList<>();
        List<String> names = joinedGroupInfo.getGroupNames();
        String groupname = joinedGroupInfo.getGroupNames().get((joinedGroupInfo.getGroupNames().size() - 1));
        newGroup.add(groupID);
        newGroup.add(groupname);
        newGroup.add(joinedGroupInfo.getPlanningList());
        newGroup.add(joinedGroupInfo.getPurchasedList());
        newGroup.add(joinedGroupInfo.getUsersInGroup());
        newGroup.add(null);
        userGroups.add(newGroup);
    }

    public void addCreateGroup(List<List<Object>> userGroups, CreatedGroupInfo createdGroupInfo){
        List<Object> newGroup = new ArrayList<>();

        newGroup.add(createdGroupInfo.getId());
        newGroup.add(createdGroupInfo.getGroupName());
        newGroup.add(null);
        newGroup.add(null);
        newGroup.add(null);
        newGroup.add(null);
        userGroups.add(newGroup);
    }

    /**
     * displays message dialog when input fails.
     * @param message the message to be displayed to the user after the user inputs.
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String filterGroupName(String groupButton){
        String[] name = groupButton.split("_");
        List<Object> temp = List.of(Arrays.stream(name).toArray());
        return (String) temp.get(1);
    }

    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z0-9]+");
    }



}


