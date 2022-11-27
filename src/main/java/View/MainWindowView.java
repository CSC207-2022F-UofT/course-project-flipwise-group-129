//package View;
//import Controllers.GroupCreateController;
//import Controllers.GroupJoinController;
//import DataAccess.GroupDataAccess;
//import DataAccess.UserDataAccess;
//import DataAccessInterface.GroupDataInterface;
//import DataAccessInterface.UserDataInterface;
//import DataStructures.JoinGroupRequest;
//import DataStructures.JoinedGroupInfo;
//import DataStructures.LoggedInInfo;
//import InputBoundary.GroupCreateBoundaryIn;
//import InputBoundary.GroupJoinBoundaryIn;
//import InputBoundary.UserLoginBoundaryIn;
//import Presenters.GroupCreatePresenter;
//import Presenters.GroupJoinPresenter;
//import Presenters.UserLoginPresenter;
//import UseCases.GroupCreate;
//import UseCases.GroupJoin;
//import UseCases.UserLogin;
//import org.json.simple.parser.ParseException;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainWindowView extends JFrame implements ActionListener {
//
//
//    /**
//     * Generates main window to switch between home and group summery pages.
//     */
//    public MainWindowView() {
//
////        homePageView = new HomePageView(this);
//
//        System.out.print("Main window");
//
//        // SetUp main window
//        setSize(1500, 820);
//        setVisible(true);
////        this.setContentPane(homePageView);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
////        homePageView.getCreateGroup().addActionListener(this);
////        homePageView.getJoinGroup().addActionListener(this);
//
//    }
//
//    /**.
//     * @param evt the event to be processed
//     * React to a certain button click that results in evt.
//     */
//    public void actionPerformed(ActionEvent evt){
//        if (evt.getActionCommand().equals("Return to Groups")){
//            setContentPane(homePageView);
//        }
//
//        else if (evt.getActionCommand().equals("Create Group")){
//            String groupName = JOptionPane.showInputDialog("Please enter in Group Name:");
//            System.out.println(groupName);
////            this.groupCreateController.create(groupName, userLoginView.getUsername());
//
//            JButton boo = new JButton("boo");
//            setContentPane(homePageView);
//
//
//        }
//        else if (evt.getActionCommand().equals("Join Group")){
//            String groupID = JOptionPane.showInputDialog("Please enter in Group ID:");
//
////            this.groupJoinController.create(groupID, userLoginView.getUsername());
//
//        }
//
//        else{
//            setGroupSummery(evt.getActionCommand());
//        }
//
//        System.out.println("Clicked " + evt.getActionCommand() + " from Main");
//
//
//    }
//
//    public String getGroupID(){
//        return "hello";
//    }
//    public void setGroupSummery(String group) {
//        GroupSummaryView selectedGroup = new GroupSummaryView(group, getGroupID());
//        selectedGroup.toHomepage.addActionListener(this);
//        setContentPane(selectedGroup);
//    }
//
//
//}
