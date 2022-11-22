package View;
import Controllers.GroupCreateController;
import Controllers.GroupJoinController;
import DataAccess.GroupDataAccess;
import DataAccessInterface.UserDataInterface;
import DataStructures.JoinGroupRequest;
import DataStructures.JoinedGroupInfo;
import InputBoundary.GroupJoinBoundaryIn;
import Presenters.GroupCreatePresenter;
import Presenters.GroupJoinPresenter;
import UseCases.GroupJoin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HomePageView extends JFrame implements ActionListener {
    JButton btn_create;
    JButton btn_join;
    JLabel homePage;
    JPanel group_btns = new JPanel();

    UserLoginView userLoginView = new UserLoginView();
    String[] group_names;
    JButton[] button_array;
    GroupJoinController groupJoinController;

   GroupCreateController groupCreateController;

    public HomePageView(String[] group_names) {

        this.groupCreateController = groupCreateController;
        this.groupJoinController = groupJoinController;

        setSize(1000, 600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);

        setGroupNames(userLoginView.getGroups());
        button_array = createGroupButtons(group_names);
        homePage = new JLabel("HomePage");
        homePage.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel homepageDetails = new JPanel();
        homepageDetails.add(homePage);

        // Algorithm implementation
        for (int i = 0; i < group_names.length; i++) {
            button_array[i].addActionListener(this);
            group_btns.add(button_array[i]);
        }

        btn_create = new JButton("Create Group");
        btn_join = new JButton("Join Group");
        JPanel groupButtons = new JPanel();
        groupButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupButtons.add(btn_create);
        groupButtons.add(btn_join);

        this.add(homePage);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(groupButtons);
        this.add(group_btns);

        btn_create.addActionListener(this);
        btn_join.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Create Group")){
            String groupName = JOptionPane.showInputDialog("Please enter in Group Name:");
            JOptionPane.showMessageDialog(null, "Your Group Name is "
                    + groupName + ".");

            if (!(groupName.equals("null"))){


                this.setGroupNames(new String[]{"Hello", "Avi"});

            }
        }
        else if (e.getActionCommand().equals("Join Group")){
            String groupID = JOptionPane.showInputDialog("Please enter in Group ID:");
            JOptionPane.showMessageDialog(null, "The Group ID is "
                    + groupID + ".");



            if (!(groupID.equals("null"))){
                // Filter through mishs code and change group namess
//                this.group_names.add("Saleh");
//                this.repaint();
            }

        }
        else {
            String groupSelected = e.getActionCommand();
//            navigate to group
            GroupSummaryView selectedGroupView = new GroupSummaryView(groupSelected, groupSelected);
            this.setContentPane(selectedGroupView);
        }

    }

//        public void getNumberOfGroups(){ return }

    public JButton[] createGroupButtons(String[] Current_Groups) {
        JButton[] output = new JButton[Current_Groups.length];
        for (int i = 0; i < Current_Groups.length; i++) {
            JButton checkbox_member = new JButton(Current_Groups[i]);
            output[i] = checkbox_member;
        }
        return output;
    }

    public void setGroupNames(String[] group_names){
        this.group_names = group_names;

    }






}