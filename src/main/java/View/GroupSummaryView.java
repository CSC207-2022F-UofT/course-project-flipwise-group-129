package View;

import Controllers.AddToPlanningController;
import DataAccess.GroupDataAccess;
import DataAccess.ItemDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataStructures.UpdatedLists;
import Entities.PurchaseList;
import InputBoundary.AddToPlanningBoundaryIn;
import Presenters.AddToPlanningPresenter;
import UseCases.AddToPlanningList;
import View.BalanceView;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class GroupSummaryView extends JFrame implements ActionListener {

    private HomePageView homePageView;
    private final String groupname;
    private final String groupid;
    private List<List<String>> purchaseListData;
    List<List<String>> planningListData;
    List<List<String>> debtData;
    List<String> groupUserNames;;
    public JButton addItem = new JButton("Add Item");
    public JButton settleDebt = new JButton("Clear Debt");
    public JButton toHomepage = new JButton("Return to Groups");
    private final AddToPlanningController controllerAddPlanning;
    JPanel main;

    /**
     * Builds the gui for the group summery page and initializes controller.
     */
    public GroupSummaryView(String groupname, String groupid, String username,
                            List<List<String>> purchaseListData, List<List<String>> planningListData,
                            List<List<String>> debtData, List<String> groupUserNames, HomePageView homePageView) {

        this.groupid = groupid;
        this.groupname = groupname;
        this.planningListData = planningListData;
        this.purchaseListData = purchaseListData;
        this.debtData = debtData;
        this.groupUserNames = groupUserNames;
        this.homePageView = homePageView;

        main = new JPanel();

        ItemDataInterface itemData;
        GroupDataInterface groupData;
        try {
            itemData = new ItemDataAccess();
            groupData = new GroupDataAccess();
        } catch (IOException | ParseException e1) {
            throw new RuntimeException(e1);
        }

        AddToPlanningPresenter presenter = new AddToPlanningPresenter();

        AddToPlanningBoundaryIn useCase = new AddToPlanningList(presenter, groupData, itemData);

        this.controllerAddPlanning = new AddToPlanningController(useCase);

        // SetUp JFrame
        setSize(1500, 820);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // SetUp JPanel
        main.setSize(1500, 820);
        main.setLayout(new BorderLayout());

        // Defining and positioning JComponents
        JTabbedPane t = new JTabbedPane();
        JComponent p1 = new JPanel();
        JComponent p2 = new JPanel();
        JComponent p3 = new JPanel();

        // Title
        JTextArea temp = new JTextArea(groupname);
        temp.setEditable(false);
        JPanel temporary_panel = new JPanel();
        temporary_panel.add(temp);

        // Group Information
        JTextArea RHS = new JTextArea("This is the group information. \n" +
                "Group Name: " + this.groupname + "\n" +
                "Group Code: " + this.groupid + "\n");
        RHS.setEditable(false);

        // Buttons
        JPanel btn_group = new JPanel();
        btn_group.add(addItem);
        btn_group.add(settleDebt);
        btn_group.add(toHomepage);

        JPanel text_group = new JPanel();
        text_group.add(RHS);

        JPanel right_hand_side = new JPanel();
        right_hand_side.setLayout(new BoxLayout(right_hand_side, BoxLayout.PAGE_AXIS));
        right_hand_side.add(text_group);
        right_hand_side.add(btn_group);

        PlanningListView c = new PlanningListView(planningListData, username, groupid, groupUserNames);
        PurchaseListView p = new PurchaseListView(purchaseListData);
        BalanceView b = new BalanceView(debtData, username, groupUserNames);
        p2.add(p);
        p1.add(c);

        JTextArea group_members = new JTextArea("Members: " + this.groupUserNames.toString());
        group_members.setEditable(false);
        JPanel bottoms_up = new JPanel();
        bottoms_up.add(group_members);

        t.addTab("Planning", p1);
        t.setMnemonicAt(0, KeyEvent.VK_1);
        t.addTab("Purchases", p2);
        t.setMnemonicAt(1, KeyEvent.VK_2);
        t.addTab("Balances", p3);
        t.setMnemonicAt(2, KeyEvent.VK_3);

        p3.add(b);
        main.add(t, BorderLayout.CENTER);
        main.add(temporary_panel, BorderLayout.NORTH);
        main.add(right_hand_side, BorderLayout.LINE_START);
        main.add(bottoms_up, BorderLayout.SOUTH);

        setContentPane(main);

        addItem.addActionListener(this);
        settleDebt.addActionListener(this);
        toHomepage.addActionListener(this);
    }

    /**.
     * @param evt the event to be processed
     * React to a certain button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Add Item")){
            String item = JOptionPane.showInputDialog("Please enter in Item Name:");
            UpdatedLists planningList = this.controllerAddPlanning.performPlanningAdd(item, this.groupid);

        }

        if (evt.getActionCommand().equals("Clear Debt")){
            ClearDebtView clearDebtView = new ClearDebtView();
        }

        if (evt.getActionCommand().equals("Return to Groups")) {
            this.dispose();
            MainWindowView mainWindowView = new MainWindowView();
            mainWindowView.setContentPane(homePageView);
            mainWindowView.setVisible(true);
        }

        if (evt.getActionCommand().equals("Purchase")){

        }
    }

}


