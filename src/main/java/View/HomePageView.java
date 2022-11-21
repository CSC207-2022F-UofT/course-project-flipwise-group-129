package View;
import Controllers.GroupCreateController;

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

    //DUMMY
    String[] group_names = new String[]{"Hello"};
    //DUMMY

    JButton[] button_array = createGroupButtons(group_names);

//    ButtonGroup TRUE_BUTTON_GROUP = new ButtonGroup();

    public HomePageView() {

        setSize(1000, 600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);

        homePage = new JLabel("HomePage");
        homePage.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel homepageDetails = new JPanel();
        homepageDetails.add(homePage);

        // Algorithm implementation
        for (int i = 0; i < group_names.length; i++) {
            button_array[i].addActionListener(this);
            group_btns.add(button_array[i]);
//            TRUE_BUTTON_GROUP.add(button_array[i]);
        }

        //DUMMY
//        JLabel test = new JLabel("This is a group's area");
//        JPanel new_test = new JPanel();
//        new_test.add(test);
//
//        JLabel test2 = new JLabel("This is another group's area");
//        JPanel new_test_2 = new JPanel();
//        new_test_2.add(test2);
        //DUMMY

        btn_create = new JButton("Create Group");
        btn_join = new JButton("Join Group");
        JPanel groupButtons = new JPanel();
        groupButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupButtons.add(btn_create);
        groupButtons.add(btn_join);


        add(homePage);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(groupButtons);
        add(group_btns);

        btn_create.addActionListener(this);
        btn_join.addActionListener(this);

        // create join items
    }

    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Create Group")){
            String groupName = JOptionPane.showInputDialog("Please enter in Group Name:");
            JOptionPane.showMessageDialog(null, "Your Group Name is  "
                    + groupName + ".");
            //controller stuff
            GroupCreateController createController = new GroupCreateController();
            // update homepage
        }
        else if (e.getActionCommand().equals("Join Group")){
            String groupID = JOptionPane.showInputDialog("Please enter in Group ID:");
            JOptionPane.showMessageDialog(null, "The Group ID is  "
                    + groupID + ".");


            //controller stuff
            //update homepage
        }
        else {
            String groupSelected = e.getActionCommand();
//            navigate to group
            GroupSummaryView selectedGroupView = new GroupSummaryView(groupSelected, groupSelected);
            this.setContentPane(selectedGroupView);
        }

        // group buttons
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



}