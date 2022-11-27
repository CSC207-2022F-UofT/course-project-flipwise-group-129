package View;

import Controllers.AddPurchaseController;
import Controllers.AddToPlanningController;
import DataAccess.GroupDataAccess;
import DataAccess.ItemDataAccess;
import DataAccess.UserDataAccess;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class PlanningListView extends JPanel implements ActionListener{
    private JTable table;
    private String username;
    private String groupID;
    private List<String> groupUserName;
    private JScrollPane scrollPane;
    Object[][] rows;
    String[] columns = new String[]{" ", "Item name"};
    GroupSummaryView groupSummaryView;

    private ButtonTable buttonTable;
    private final AddPurchaseController controllerAddPurchase;

    public PlanningListView(List<List<String>> planningListData, String username, String groupID,
                            List<String> groupUserName) {

        this.username = username;
        this.groupID = groupID;
        this.groupUserName = groupUserName;

        ItemDataInterface itemData;
        GroupDataInterface groupData;
        UserDataInterface userData;
        try {
            itemData = new ItemDataAccess();
            groupData = new GroupDataAccess();
            userData = new UserDataAccess();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e); // Display popup
        }

        AddPurchaseBoundaryOut presenter = new AddPurchasePresenter();


        AddPurchaseBoundaryIn useCase = new AddPurchase();

        this.controllerAddPurchase = new AddPurchaseController(presenter, useCase, groupData, itemData, userData);

        setRows(planningListData);
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        table = new JTable(model);

        ButtonTable buttonTable = new ButtonTable(table);
        table.getColumn(" ").setCellEditor(buttonTable);
        table.getColumn(" ").setCellRenderer(buttonTable);

        buttonTable.getPurchase().addActionListener(this);
        scrollPane = new JScrollPane(table);

        add(scrollPane);

        setVisible(true);
        setSize(1000, 600);


    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Purchase")) {
            AddPurchaseView addPurchaseView = new AddPurchaseView(this.username, this.groupID,
                    "PLACEHOLDER", this.groupUserName);

            JOptionPane.showMessageDialog(null, "Purchased item.");

            this.controllerAddPurchase.controlAddPurchaseUseCase("Item#23", addPurchaseView.getSelectedMembers(),
                    "Saleh", Float.parseFloat(addPurchaseView.item_price.getText().toString()),
                    "Group#24");

        }
    }
//    public void setPlanningList(){
//        // filter through codis data (codis data in parameter)
//        // get updated planning list and reload page
//        // testing
//        rows = new Object[][]{{"Purchase", "the"},
//                {"Purchase", "the"},
//                {"Purchase", "Sandwich"},
//                {"Purchase", "8"}};
//    }

        public void setRows (List < List < String>> planningListData) {
            this.rows = new Object[planningListData.size()][2];
            for (int i = 0; i < planningListData.size(); i++) {
                this.rows[i] = planningListData.get(i).toArray();
                //CONFIRM WITH CODI WHAT IS IN THIS 2D ARRAY
            }
        }
    }


