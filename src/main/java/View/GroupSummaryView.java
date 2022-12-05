package View;

import Controllers.AddPurchaseController;
import Controllers.AddToPlanningController;
import Controllers.SettlementController;
import Controllers.UpdatePaymentBalanceController;
import DataAccess.GroupDataAccess;
import DataAccess.ItemDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.PaymentInformation;
import DataStructures.UpdatedDebts;
import DataStructures.UpdatedLists;
import InputBoundary.AddPurchaseBoundaryIn;
import InputBoundary.AddToPlanningBoundaryIn;
import InputBoundary.SettlementBoundaryIn;
import InputBoundary.UpdatePaymentBalanceBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import Presenters.AddPurchasePresenter;
import Presenters.AddToPlanningPresenter;
import Presenters.SettlementPresenter;
import Presenters.UpdatePaymentBalancePresenter;
import UseCases.AddPurchase;
import UseCases.AddToPlanningList;
import UseCases.SettlementPayment;
import UseCases.UpdatePaymentBalance;
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
    List<List<Object>> debtData;
    List<String> groupUserNames;
    PlanningListView planningListView;
    public JButton addItem = new JButton("Add Item");
    public JButton settleDebt = new JButton("Settle Debt");
    public JButton toHomepage = new JButton("Return to Groups");
    public JButton purchaseItem = new JButton("Purchase Item");
    private final AddToPlanningController controllerAddPlanning;
    private final SettlementController controllerSettlementPayment;
    private final UpdatePaymentBalanceController updatePaymentBalanceController;


    /**
     * Builds the gui for the group summery page and initializes controller.
     */
    public GroupSummaryView(String groupname, String groupid, String username,
                            List<List<String>> purchaseListData, List<List<String>> planningListData,
                            List<List<Object>> debtData, List<String> groupUserNames,
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
            ClearDebtView clearDebtView = new ClearDebtView(this.username, this.groupID,
                    this.groupUserNames);

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

            DefaultTableModel model = (DefaultTableModel) table.getModel();

            if (table.getSelectedRowCount() == 1) {
                String item = (String) table.getValueAt(table.getSelectedRow(), 0);

                String itemID = getItemID(planningListData, item);
                AddPurchaseView addPurchaseView = new AddPurchaseView(itemID,
                        this.username, this.groupID, this.groupUserNames);

                if ((addPurchaseView.getItemPrice().matches("[0-9]+")) && (addPurchaseView.getSelectedMembers().size() > 0)) {
                    Float item_price = Float.parseFloat(addPurchaseView.getItemPrice());
                    UpdatedLists updatedList = controllerAddPurchase.controlAddPurchaseUseCase(itemID,
                            addPurchaseView.getSelectedMembers(), this.username, item_price, this.groupID);
                    UpdatedDebts updatedDebts = this.updatePaymentBalanceController.create(this.groupID,
                            this.username, item_price, itemID, addPurchaseView.getSelectedMembers());
                        System.out.println("updated debts lists " + updatedDebts.getUpdatedBalances());
                    if (updatedList.getResultMessage().equals("Success") && updatedDebts.getOutcomeMessage().equals("Success")) {
                        System.out.println("Reseting group summary from purchase item");
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

    public void resetGroupSummary(String groupname, String groupid, String username,
                                  List<List<String>> purchaseListData, List<List<String>> planningListData,
                                  List<List<Object>> debtData, List<String> groupUserNames, MainWindowView mainWindowView){
        GroupSummaryView groupSummaryView = new GroupSummaryView(groupname, groupid, username,
                purchaseListData,  planningListData, debtData, groupUserNames, mainWindowView);
        mainWindowView.setContentPane(groupSummaryView);
        groupSummaryView.getToHomepage().addActionListener(mainWindowView);
        setVisible(true);
        System.out.println("This is group " + groupname);
        System.out.println("This is groupID " + groupid);
        System.out.println("This is username " + username);
        System.out.println("This is planning " + planningListData);
        System.out.println("This is purchase " + purchaseListData);
        System.out.println("This is debt " + debtData);
        System.out.println("This is members " + groupUserNames);
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

        System.out.println("updated usergroups " + userGroups);
    }
}


