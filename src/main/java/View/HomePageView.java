package View;
import Controllers.GroupCreateController;
import Controllers.GroupJoinController;
import DataAccess.GroupDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.CreatedGroupInfo;
import DataStructures.JoinedGroupInfo;
import InputBoundary.GroupCreateBoundaryIn;
import InputBoundary.GroupJoinBoundaryIn;
import Presenters.GroupCreatePresenter;
import Presenters.GroupJoinPresenter;
import UseCases.GroupCreate;
import UseCases.GroupJoin;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePageView extends JPanel implements ActionListener {

    JLabel homePage;
    public JButton createGroup;
    public JButton joinGroup;
    public String input_groupName;
    public String input_groupID;

    String username;
    List<String> group_names;
    JButton[] button_array;
    GroupCreateController controllerCreate;
    GroupJoinController controllerJoin;

    private CreatedGroupInfo createdGroupInfo;
    private JoinedGroupInfo joinedGroupInfo;

    /**
     * Builds the gui for the user home page and initializes controllers.
     */
    public HomePageView(String username, List<String> groupNames) {

        this.username = username;
        this.group_names = groupNames;

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

        // SetUp JPanel
        setSize(1500, 820);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);

        // Defining JComponents
        homePage = new JLabel("Dashboard");
        createGroup = new JButton("Create Group");
        joinGroup = new JButton("Join Group");
        //        this.setGroupNames(userLoginView.getGroups());

        // Positioning JComponents
        homePage.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel groupButtons = new JPanel();
        groupButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupButtons.add(createGroup);
        groupButtons.add(joinGroup);

        // Generating group buttons
        button_array = createGroupButtons(group_names);
        JPanel group_btns = new JPanel();
        // Algorithm implementation
        for (int i = 0; i < group_names.size(); i++) {
            button_array[i].addActionListener(this);
            group_btns.add(button_array[i]);
        }

        this.add(homePage);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(groupButtons);
        this.add(group_btns);

        createGroup.addActionListener(this);
        joinGroup.addActionListener(this);

    }

    /**
     * Calls the controller with the user's entered inputs as arguments.
     * @param evt the event to be processed
     */
    public void actionPerformed(ActionEvent evt){
        if (evt.getActionCommand().equals("Create Group")){
            String groupName = JOptionPane.showInputDialog("Please enter in Group Name:");
            JOptionPane.showMessageDialog(null, "Your Group Name is "
                    + groupName + ".");

            createdGroupInfo = this.controllerCreate.create(groupName, this.username); //FIXX

        }
        else if (evt.getActionCommand().equals("Join Group")){
            String groupID = JOptionPane.showInputDialog("Please enter in Group ID:");
            JOptionPane.showMessageDialog(null, "The Group ID is "
                    + groupID + ".");

            joinedGroupInfo = this.controllerJoin.create(groupID, this.username);
        }

    }

    /**.
     * Generates a list of JButtons of all the groups that include the user to display
     * on the home page.
     * @param Current_Groups the list of all groups that include the user
     * @return list including all inputted groups converted into JButtons
     */
    public JButton[] createGroupButtons(List<String> Current_Groups) {
        JButton[] output = new JButton[Current_Groups.size()];
        for (int i = 0; i < Current_Groups.size(); i++) {
            JButton checkbox_member = new JButton(Current_Groups.get(i));
            output[i] = checkbox_member;
        }
        return output;
    }

    /**
     * @return the join group button that allows user to join a group.
     */
    public JButton getJoinGroup(){ return this.joinGroup; }

    /**
     * @return the create group button that allows user to create a new group.
     */
    public JButton getCreateGroup(){ return this.createGroup; }

    public CreatedGroupInfo getCreatedGroupInfo() {
        return createdGroupInfo;
    }

    public JoinedGroupInfo getJoinedGroupInfo(){
        return joinedGroupInfo;
    }
}