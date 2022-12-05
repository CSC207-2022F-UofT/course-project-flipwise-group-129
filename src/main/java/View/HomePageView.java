package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomePageView extends JPanel {

    JLabel homePage;
    public JButton createGroup;
    public JButton joinGroup;

    String username;
    List<String> group_names;
    JButton[] button_array;

    MainWindowView mainWindowView;


    /**
     * Builds the gui for the user home page.
     */
    public HomePageView(String username, List<String> groupNames, MainWindowView mainWindowView) {

        this.username = username;
        this.group_names = groupNames;
        this.mainWindowView = mainWindowView;

        // SetUp JPanel
        setSize(1500, 820);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);

        // Defining JComponents
        homePage = new JLabel("Dashboard");
        createGroup = new JButton("Create Group");
        joinGroup = new JButton("Join Group");

        // Positioning JComponents
        homePage.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel groupButtons = new JPanel();
        groupButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupButtons.add(createGroup);
        groupButtons.add(joinGroup);

        // Generating group buttons
        button_array = createGroupButtons(group_names);
        JPanel groupOfButtons = new JPanel();

        for (int i = 0; i < group_names.size(); i++) {
            button_array[i].addActionListener(mainWindowView);
            groupOfButtons.add(button_array[i]);
        }

        this.add(homePage);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(groupButtons);
        this.add(groupOfButtons);

    }

    /**.
     * Generates a list of JButtons of all the groups that include the user to display
     * on the home page
     * @param Current_Groups the list of all groups that include the user
     * @return list including all inputted groups converted into JButtons
     */
    public JButton[] createGroupButtons(List<String> Current_Groups) {
        JButton[] output = new JButton[Current_Groups.size()];
        for (int i = 0; i < Current_Groups.size(); i++) {
            JButton checkbox_member = new JButton("Enter_" + Current_Groups.get(i));
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

}