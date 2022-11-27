package View;

import DataStructures.CreatedGroupInfo;
import DataStructures.JoinedGroupInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HomePageView extends JPanel implements ActionListener {
    private JLabel homePage;

    public String input_groupName;
    public String input_groupID;
    public JPanel groupButtons;
    public JButton createGroup;
    public JButton joinGroup;

    private String user;
    private Object[] group_names;
    JButton[] button_array;

//    private final MainWindowView mainWindow;

//    private final GroupJoinController groupJoinController;
//    private final GroupCreateController groupCreateController;
    /**
     * Builds the gui for the user home page initializes controller.
     */
    public HomePageView(String user, Object[] group_names){

        this.user = user;
        this.group_names = group_names;

        //        UserDataInterface userData;
//        GroupDataInterface groupData;
//        try {
//            userData = new UserDataAccess();
//            groupData = new GroupDataAccess();
//        } catch (IOException | ParseException e2) {
//            throw new RuntimeException(e2); // Display popup
//        }
//
//        GroupCreatePresenter groupCreatePresenter = new GroupCreatePresenter();
//        GroupCreateBoundaryIn groupCreateUseCase = new GroupCreate(groupCreatePresenter, groupData, userData);
//
//        GroupJoinPresenter groupJoinPresenter = new GroupJoinPresenter();
//        GroupJoinBoundaryIn groupJoinUseCase = new GroupJoin(groupJoinPresenter, groupData, userData);
//
//        this.groupCreateController = new GroupCreateController(groupCreateUseCase);
//        this.groupJoinController = new GroupJoinController(groupJoinUseCase);

        // SetUp JPanel
        setSize(1500, 820);
        setVisible(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Defining JComponents
        homePage = new JLabel("Dashboard");
        createGroup = new JButton("Create Group");
        joinGroup = new JButton("Join Group");

        // Positioning JComponents
        homePage.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupButtons = new JPanel();
        groupButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupButtons.add(createGroup);
        groupButtons.add(joinGroup);

        JPanel group_btns = new JPanel();
        button_array = createGroupButtons();

        for (int i = 0; i < group_names.length; i++) {
            button_array[i].addActionListener(this);
            group_btns.add(button_array[i]);
        }

        add(homePage);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(groupButtons);
        add(group_btns);

    }

    public void actionPerformed(ActionEvent evt) {


        if (evt.getActionCommand().equals("Create Group")){
            input_groupName = JOptionPane.showInputDialog("Please enter in Group Name:");
//            CreatedGroupInfo groupInfo = groupCreateController.create(groupName, userLoginView.getUsername());

        }
        else if (evt.getActionCommand().equals("Join Group")){
            input_groupID = JOptionPane.showInputDialog("Please enter in Group ID:");
//            JoinedGroupInfo groupInfo = groupJoinController.create(groupID, userLoginView.getUsername());

        }

        System.out.println("Clicked " + evt.getActionCommand() + " from Homepage");

    }

    /**.
     * Generates a list of JButtons of all the groups that include the user to display
     * on the home page.
     * @param Current_Groups the list of all groups that include the user
     * @return list including all inputted groups converted into JButtons
     */
    public JButton[] createGroupButtons(Object[] Current_Groups) {
        JButton[] output = new JButton[Current_Groups.length];
        for (int i = 0; i < Current_Groups.length; i++) {
            JButton checkbox_member = new JButton(Current_Groups[i].toString());
            output[i] = checkbox_member;
        }
        return output;
    }

    //    public void setGroupNames(){
//        this.group_names = group_names;
//
//    }

    /**
     * @return the join group button that allows user to join a group.
     */
    public JButton getJoinGroup(){ return this.joinGroup; }

    /**
     * @return the create group button that allows user to create a new group.
     */
    public JButton getCreateGroup(){ return this.createGroup; }

}