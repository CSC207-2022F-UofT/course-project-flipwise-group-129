package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView extends JPanel implements ActionListener {

    private JLabel homePage;
    public JPanel groupButtons;
    public JButton createGroup;
    public JButton joinGroup;
    public String[] group_names = new String[]{"Hello"};
    JButton[] button_array;

    private final MainWindowView mainWindow;

//    private final GroupJoinController groupJoinController;
//    private final GroupCreateController groupCreateController;
    /**
     * Builds the gui for the user home page initializes controller.
     */
    public HomePageView(MainWindowView mainWindow){

        this.mainWindow = mainWindow;

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

        //
//        setGroupNames();
        // need
        //

        JPanel group_btns = new JPanel();
        button_array = createGroupButtons(group_names);

        for (int i = 0; i < group_names.length; i++) {
            button_array[i].addActionListener(mainWindow);
            group_btns.add(button_array[i]);
        }

        add(homePage);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(groupButtons);
        add(group_btns);

    }


    public void actionPerformed(ActionEvent e) {

    }

    /**.
     * Generates a list of JButtons of all the groups that include the user to display
     * on the home page.
     * @param Current_Groups the list of all groups that include the user
     * @return list including all inputted groups converted into JButtons
     */
    public JButton[] createGroupButtons(String[] Current_Groups) {
        JButton[] output = new JButton[Current_Groups.length];
        for (int i = 0; i < Current_Groups.length; i++) {
            JButton checkbox_member = new JButton(Current_Groups[i]);
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

//    public setGroupNames(){
//
//    }


}