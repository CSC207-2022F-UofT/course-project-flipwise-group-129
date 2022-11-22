package View;
import Controllers.GroupCreateController;
import Controllers.GroupJoinController;
import DataAccess.GroupDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
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

public class HomePageView extends JFrame implements ActionListener {
    JButton btn_create;
    JButton btn_join;
    JLabel homePage;
    JPanel group_btns = new JPanel();

    UserLoginView userLoginView = new UserLoginView();
    String[] group_names = new String[]{};

    JButton[] button_array;
    GroupCreateController controllerCreate;
    GroupJoinController controllerJoin;

    public HomePageView() {

        setSize(1000, 600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
        this.setGroupNames(userLoginView.getGroups());
        setHomePageGUI();

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


    }

    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Create Group")){
            String groupName = JOptionPane.showInputDialog("Please enter in Group Name:");
            JOptionPane.showMessageDialog(null, "Your Group Name is "
                    + groupName + ".");

            this.controllerCreate.create(groupName, "PlaceHOLDER"); //PLACEHOLDER

            if (!(groupName.equals("null"))){
                System.out.println("running");
                this.setGroupNames(new String[]{"Hello", "Avi"});
                setHomePageGUI();
            }
            //controller stuff
//            GroupCreateController createController = new GroupCreateController();
            // update homepage
        }
        else if (e.getActionCommand().equals("Join Group")){
            String groupID = JOptionPane.showInputDialog("Please enter in Group ID:");
            JOptionPane.showMessageDialog(null, "The Group ID is "
                    + groupID + ".");

            this.controllerJoin.create(groupID, "SALEH");

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

    public void setHomePageGUI(){

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




}