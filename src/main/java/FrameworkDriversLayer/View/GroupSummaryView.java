package FrameworkDriversLayer.View;

import InterfaceAdapters.Controllers.AddPurchaseController;
import InterfaceAdapters.Controllers.AddToPlanningController;
import InterfaceAdapters.Controllers.SettlementController;
import InterfaceAdapters.Controllers.UpdatePaymentBalanceController;
import FrameworkDriversLayer.DataAccess.GroupDataAccess;
import FrameworkDriversLayer.DataAccess.ItemDataAccess;
import FrameworkDriversLayer.DataAccess.UserDataAccess;
import UseCaseLayer.DataAccessInterface.GroupDataInterface;
import UseCaseLayer.DataAccessInterface.ItemDataInterface;
import UseCaseLayer.DataAccessInterface.UserDataInterface;
import UseCaseLayer.DataStructures.UpdatedDebts;
import UseCaseLayer.DataStructures.UpdatedLists;
import UseCaseLayer.InputBoundary.AddPurchaseBoundaryIn;
import UseCaseLayer.InputBoundary.AddToPlanningBoundaryIn;
import UseCaseLayer.InputBoundary.SettlementBoundaryIn;
import UseCaseLayer.InputBoundary.UpdatePaymentBalanceBoundaryIn;
import UseCaseLayer.OutputBoundary.AddPurchaseBoundaryOut;
import InterfaceAdapters.Presenters.AddPurchasePresenter;
import InterfaceAdapters.Presenters.AddToPlanningPresenter;
import InterfaceAdapters.Presenters.SettlementPresenter;
import InterfaceAdapters.Presenters.UpdatePaymentBalancePresenter;
import UseCaseLayer.UseCases.AddPurchase;
import UseCaseLayer.UseCases.AddToPlanningList;
import UseCaseLayer.UseCases.SettlementPayment;
import UseCaseLayer.UseCases.UpdatePaymentBalance;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupSummaryView extends JPanel implements ActionListener {
    private final String username;
    private final String group_name;
    private final String groupID;
    private final AddPurchaseController controllerAddPurchase;
    private final List<List<String>> purchaseListData;
    private final MainWindowView mainWindowView;
    final List<List<String>> planningListData;
    final List<List<Object>> debtData;
    final List<String> groupUserNames;
    PlanningListView planningListView;
    public final JButton addItem = new JButton("Add Item");
    public final JButton settleDebt = new JButton("Settle Debt");
    public final JButton toHomepage = new JButton("Return to Groups");
    public final JButton purchaseItem = new JButton("Purchase Item");
    private final AddToPlanningController controllerAddPlanning;
    private final SettlementController controllerSettlementPayment;
    private final UpdatePaymentBalanceController updatePaymentBalanceController;


    /**
     * Builds the gui for the group summery page and initializes controller.
     */
    public GroupSummaryView(String groupName, String groupID, String username,
                            List<List<String>> purchaseListData, List<List<String>> planningListData,
                            List<List<Object>> debtData, List<String> groupUserNames,
                            MainWindowView mainWindowView) {

        this.groupID = groupID;
        this.group_name = groupName;
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

        SettlementPresenter settlementPresenter = new SettlementPresenter();
        SettlementBoundaryIn settleUseCase = new SettlementPayment(settlementPresenter, groupData);

        this.controllerSettlementPayment = new SettlementController(settleUseCase);

        UpdatePaymentBalancePresenter updatePaymentBalancePresenter = new UpdatePaymentBalancePresenter();
        UpdatePaymentBalanceBoundaryIn updatePaymentUseCase = new UpdatePaymentBalance(groupData, itemData,
                updatePaymentBalancePresenter);

        this.updatePaymentBalanceController = new UpdatePaymentBalanceController(updatePaymentUseCase);

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
        JTextArea temp = new JTextArea(groupName);
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

        JPanel toHomepagePanel = new JPanel();
        toHomepagePanel.add(toHomepage);

        JPanel text_group = new JPanel();
        text_group.add(RHS);

        JPanel right_hand_side = new JPanel();
        right_hand_side.setLayout(new BoxLayout(right_hand_side, BoxLayout.PAGE_AXIS));
        right_hand_side.add(text_group);
        right_hand_side.add(btn_group);
        right_hand_side.add(toHomepagePanel);

        this.planningListView = new PlanningListView(planningListData);
        PurchaseListView p = new PurchaseListView(purchaseListData);
        BalanceView b = new BalanceView(debtData, username, getMembers(this.groupUserNames, this.username));
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

            if (item == null || !(isAlpha(item))) {
                showMessage("Error with input!");
            }
            else {
                UpdatedLists updatedLists = this.controllerAddPlanning.performPlanningAdd(item, this.groupID);

                if (updatedLists.getResultMessage().equals("Success") && isAlpha(item)) {
                    resetGroupSummary(this.group_name, this.groupID, this.username, this.purchaseListData,
                            updatedLists.getNewPlanningList(), this.debtData, this.groupUserNames, this.mainWindowView);
                    updateUserGroups(mainWindowView.getUserGroups(), this.groupID, updatedLists.getNewPlanningList(),
                            this.purchaseListData, this.debtData);
                } else {
                    showMessage("Not Successful :(");
                }

            }
        }

        if (evt.getActionCommand().equals("Settle Debt")){
            ClearDebtView clearDebtView = new ClearDebtView(
                    getMembers(this.groupUserNames, this.username));

            if (clearDebtView.getSelectedMember() == null) {
                showMessage("Error with input.");
            } else {
                UpdatedDebts updatedDebts = this.controllerSettlementPayment.settleDebt(clearDebtView.getSelectedMember(),
                        this.username, this.groupID);
                if (updatedDebts.getOutcomeMessage().equals("Success")){
                    resetGroupSummary(this.group_name, this.groupID, this.username, this.purchaseListData,
                            this.planningListData, updatedDebts.getUpdatedBalances(), this.groupUserNames,
                            this.mainWindowView);
                    updateUserGroups(mainWindowView.getUserGroups(), this.groupID, this.planningListData,
                            this.purchaseListData, updatedDebts.getUpdatedBalances());
                }else { showMessage(updatedDebts.getOutcomeMessage());}
            }
        }

        if (evt.getActionCommand().equals("Purchase Item")){
            JTable table = planningListView.getTable();
            if (table.getSelectedRowCount() == 1) {
                String item = (String) table.getValueAt(table.getSelectedRow(), 0);

                String itemID = getItemID(planningListData, item);
                AddPurchaseView addPurchaseView = new AddPurchaseView(
                        this.groupUserNames);

                if ((addPurchaseView.getItemPrice().matches("[0-9]+")) && (addPurchaseView.getSelectedMembers().size() > 0)) {
                    float item_price = Float.parseFloat(addPurchaseView.getItemPrice());
                    UpdatedLists updatedList = controllerAddPurchase.controlAddPurchaseUseCase(itemID,
                            addPurchaseView.getSelectedMembers(), this.username, item_price, this.groupID);
                    UpdatedDebts updatedDebts = this.updatePaymentBalanceController.create(this.groupID,
                            this.username, item_price, itemID, addPurchaseView.getSelectedMembers());
                        System.out.println("updated debts lists " + updatedDebts.getUpdatedBalances());
                    if (updatedList.getResultMessage().equals("Success") && updatedDebts.getOutcomeMessage().equals("Success")) {
                        System.out.println("Resetting group summary from purchase item");
                        resetGroupSummary(this.group_name, this.groupID, this.username, updatedList.getNewPurchasedList(),
                                updatedList.getNewPlanningList(), updatedDebts.getUpdatedBalances(), this.groupUserNames,
                                this.mainWindowView);
                        updateUserGroups(mainWindowView.getUserGroups(), this.groupID, updatedList.getNewPlanningList(),
                                updatedList.getNewPurchasedList(), updatedDebts.getUpdatedBalances());
                    } else {
                        showMessage(updatedList.getResultMessage());
                    }
                }else { showMessage("Error with inputs!");}
            }
            else {
                if (table.getRowCount() == 0) { showMessage("The table is empty! " +
                        "There are no items to purchase."); }
                else { showMessage("Please select a single item to purchase."); }
            }
        }
    }

    /**
     * this helper function acts to "update" the currently shown GroupSummaryView page by overwriting the current
     * instance of a GroupSummaryView
     * @param groupName the group name for the group being shown
     * @param groupID   specific group ID
     * @param username  username of the user accessing the group
     * @param purchaseListData  purchase list data to be shown
     * @param planningListData  planning list data to be shown
     * @param debtData          debt data to be shown
     * @param groupUserNames    all the users in the group
     * @param mainWindowView    cautious main window view
     */
    public void resetGroupSummary(String groupName, String groupID, String username,
                                  List<List<String>> purchaseListData, List<List<String>> planningListData,
                                  List<List<Object>> debtData, List<String> groupUserNames, MainWindowView mainWindowView){
        GroupSummaryView groupSummaryView = new GroupSummaryView(groupName, groupID, username,
                purchaseListData,  planningListData, debtData, groupUserNames, mainWindowView);
        mainWindowView.setContentPane(groupSummaryView);
        groupSummaryView.getToHomepage().addActionListener(mainWindowView);
        setVisible(true);
    }

    /**
     * a helper function to show a message as a popup
     * @param message   intended message to be shown
     */
    public void showMessage(String message) { JOptionPane.showMessageDialog(this, message); }

    /**
     * Button that leads to homepage
     * @return  returns the button in question
     */
    public JButton getToHomepage() {return toHomepage; }

    /**
     * return the ID of an item that is being purchased from the given data
     * @param planningListData  the planning list data
     * @param item_name         the item being purchased
     * @return                  the ID of the item being purchased
     */
    public String getItemID(List<List<String>> planningListData, String item_name){
        String itemID = "";
        for (List<String> values : planningListData){
            if (values.get(1).equals(item_name)){
                itemID = values.get(0);
            }
        }
        return itemID;
    }

    /**
     * takes in a string and tests whether it is an alpha
     * @param name  string being used
     * @return      whether the string is compatible with the back-end implementation
     */
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z0-9]+");
    }

    /**
     * updates the list of user groups the user is a part of
     * @param userGroups    the current list of groups the user is a part of
     * @param groupID       the new group's ID
     * @param newPlanningList   the planning list of the group
     * @param newPurchaseList   the purchased list of the group
     * @param newDebtData       the debt data of the group
     */
    public void updateUserGroups(List<List<Object>> userGroups, String groupID,
                                 List<List<String>> newPlanningList, List<List<String>>
                                 newPurchaseList, List<List<Object>> newDebtData){
        for (List<Object> group : userGroups){
            if (group.get(0).equals(groupID)){
                group.set(2, newPlanningList);
                group.set(3, newPurchaseList);
                group.set(5, newDebtData);

            }
        }
    }

    /**
     * @param groupUsernames    the current list of groups the user is a part of
     * @param username       the new group's ID
     * @return the list of users in the group without the user that is logged in.
     */
    public List<String> getMembers (List < String > groupUsernames, String username){
        List<String> members = new ArrayList<>();
        for (String groupUsername : groupUsernames) {
            if (!(groupUsername.equals(username))) {
                members.add(groupUsername);
            }
        }
        return members;
    }
}


