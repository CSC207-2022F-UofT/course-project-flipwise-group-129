package View;

import Controllers.AddPurchaseController;
import Controllers.AddToPlanningController;
import DataAccess.GroupDataAccess;
import DataAccess.ItemDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.UpdatedLists;
import InputBoundary.AddPurchaseBoundaryIn;
import InputBoundary.AddToPlanningBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import Presenters.AddPurchasePresenter;
import Presenters.AddToPlanningPresenter;
import UseCases.AddPurchase;
import UseCases.AddToPlanningList;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class GroupSummaryView extends JPanel implements ActionListener {
    private final String username;
    private final String group_name;
    private final String groupID;
    private final AddPurchaseController controllerAddPurchase;
    private List<List<String>> purchaseListData;
    private MainWindowView mainWindowView;
    List<List<String>> planningListData;
    List<List<String>> debtData;
    List<String> groupUserNames;
    PlanningListView planningListView;
    public JButton addItem = new JButton("Add Item");
    public JButton settleDebt = new JButton("Clear Debt");
    public JButton toHomepage = new JButton("Return to Groups");
    public JButton purchaseItem = new JButton("Purchase Item");
    private final AddToPlanningController controllerAddPlanning;

    /**
     * Builds the gui for the group summery page and initializes controller.
     */
    public GroupSummaryView(String groupname, String groupid, String username,
                            List<List<String>> purchaseListData, List<List<String>> planningListData,
                            List<List<String>> debtData, List<String> groupUserNames,
                            MainWindowView mainWindowView) {

        this.groupID = groupid;
        this.group_name = groupname;
        this.username = username;
        this.planningListData = planningListData;
        this.purchaseListData = purchaseListData;
        this.debtData = debtData;
        this.groupUserNames = groupUserNames;
        this.mainWindowView = mainWindowView;

        ItemDataInterface itemData;
        GroupDataInterface groupData;
        UserDataInterface userData;
        try {
            itemData = new ItemDataAccess();
            groupData = new GroupDataAccess();
            userData = new UserDataAccess();
        } catch (IOException | ParseException e1) {
            throw new RuntimeException(e1);
        }

        AddPurchaseBoundaryOut addPurchasePresenter = new AddPurchasePresenter();
        AddPurchaseBoundaryIn purchaseUseCase = new AddPurchase();

        this.controllerAddPurchase = new AddPurchaseController(addPurchasePresenter, purchaseUseCase, groupData,
                itemData, userData);

        AddToPlanningPresenter addToPlanningPresenter = new AddToPlanningPresenter();
        AddToPlanningBoundaryIn planningUseCase = new AddToPlanningList(addToPlanningPresenter, groupData, itemData);

        this.controllerAddPlanning = new AddToPlanningController(planningUseCase);

        // SetUp JFrame
        setSize(1500, 820);
        setLayout(null);
        setVisible(true);

        // SetUp JPanel
        setSize(1500, 820);
        setLayout(new BorderLayout());

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
                "Group Name: " + this.group_name + "\n" +
                "Group ID: " + this.groupID + "\n" +
                "Members: " + this.groupUserNames + "\n");
        RHS.setEditable(false);

        // Buttons
        JPanel btn_group = new JPanel();
        btn_group.add(addItem);
        btn_group.add(settleDebt);
        btn_group.add(purchaseItem);

        JPanel tohomepage = new JPanel();
        tohomepage.add(toHomepage);

        JPanel text_group = new JPanel();
        text_group.add(RHS);

        JPanel right_hand_side = new JPanel();
        right_hand_side.setLayout(new BoxLayout(right_hand_side, BoxLayout.PAGE_AXIS));
        right_hand_side.add(text_group);
        right_hand_side.add(btn_group);
        right_hand_side.add(tohomepage);

        this.planningListView = new PlanningListView(planningListData, username, groupid, groupUserNames);
        PurchaseListView p = new PurchaseListView(purchaseListData);
        BalanceView b = new BalanceView(debtData, username, groupUserNames);
        p2.add(p);
        p1.add(planningListView);

        t.addTab("Planning", p1);
        t.setMnemonicAt(0, KeyEvent.VK_1);
        t.addTab("Purchases", p2);
        t.setMnemonicAt(1, KeyEvent.VK_2);
        t.addTab("Balances", p3);
        t.setMnemonicAt(2, KeyEvent.VK_3);

        p3.add(b);
        add(t, BorderLayout.CENTER);
        add(temporary_panel, BorderLayout.NORTH);
        add(right_hand_side, BorderLayout.LINE_START);

        addItem.addActionListener(this);
        settleDebt.addActionListener(this);
        purchaseItem.addActionListener(this);
    }

    /**.
     * @param evt the event to be processed
     * React to a certain button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Add Item")) {
            String item = JOptionPane.showInputDialog("Please enter in Item Name:");

            if (isAlpha(item)) {
                UpdatedLists updatedLists = this.controllerAddPlanning.performPlanningAdd(item, this.groupID);
                System.out.println("Item is " + item);

                if (updatedLists.getResultMessage().equals("Success") && isAlpha(item)) {
                    resetGroupSummary(this.group_name, this.groupID, this.username, this.purchaseListData,
                            updatedLists.getNewPlanningList(), this.debtData, this.groupUserNames, this.mainWindowView);
                } else {
                    showMessage("Not Successful :(");
                }
            }
            else {
                showMessage("Error with input!");
            }
        }

        if (evt.getActionCommand().equals("Clear Debt")){
            ClearDebtView clearDebtView = new ClearDebtView();
        }

        if (evt.getActionCommand().equals("Purchase Item")){
            JTable table = planningListView.getTable();

            DefaultTableModel model = (DefaultTableModel) table.getModel();

            if (table.getSelectedRowCount() == 1){
                String item = (String) table.getValueAt(table.getSelectedRow(), 0);

                String itemID = getItemID(planningListData, item);
                AddPurchaseView addPurchaseView = new AddPurchaseView(itemID,
                        this.username, this.groupID, this.groupUserNames);
                UpdatedLists updatedList = controllerAddPurchase.controlAddPurchaseUseCase(itemID,
                        addPurchaseView.getSelectedMembers(), this.username,
                        addPurchaseView.getItemPrice(), this.groupID);

                if (updatedList.getResultMessage().equals("Success")) {
                    resetGroupSummary(this.group_name, this.groupID, this.username, updatedList.getNewPurchasedList(),
                            updatedList.getNewPlanningList(), this.debtData, this.groupUserNames,
                            this.mainWindowView);
                }

                else { showMessage(updatedList.getResultMessage()); }
            }
            else {
                if (table.getRowCount() == 0) { showMessage("The table is empty! " +
                        "There are no items to purchase."); }
                else { showMessage("Please select a single item to purchase."); }
            }
        }
    }

    public void resetGroupSummary(String groupname, String groupid, String username,
                                  List<List<String>> purchaseListData, List<List<String>> planningListData,
                                  List<List<String>> debtData, List<String> groupUserNames, MainWindowView mainWindowView){
        GroupSummaryView groupSummaryView = new GroupSummaryView(groupname, groupid, username,
                purchaseListData,  planningListData, debtData, groupUserNames, mainWindowView);
        mainWindowView.setContentPane(groupSummaryView);
        setVisible(true);
    }

    public void showMessage(String message) { JOptionPane.showMessageDialog(this, message); }

    public JButton getToHomepage() {return toHomepage; }

    public String getItemID(List<List<String>> planningListData, String item_name){
        String itemID = "";
        for (List<String> values : planningListData){
            if (values.get(1).equals(item_name)){
                itemID = values.get(0);
            }
        }
        return itemID;
    }

    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z0-9]+");
    }

}


