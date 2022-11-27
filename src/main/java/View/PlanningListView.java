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

public class PlanningListView extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    Object[][] rows;
    String[] columns = new String[]{"", "Item name"};
    GroupSummaryView groupSummaryView;

    public PlanningListView(List<List<String>> planningListData, String username, String groupID,
                            List<String> groupUserName) {

        setRows(planningListData);
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        table = new JTable(model);

        Action delete = new AbstractAction()
        {
            private AddPurchaseController controllerAddPurchase;

            public void actionPerformed(ActionEvent evt)
            {
                JTable table = (JTable)evt.getSource();
                int modelRow = Integer.valueOf( evt.getActionCommand() );

                AddPurchaseView addPurchaseView = new AddPurchaseView(username, groupID, "PLACEHOLDER", groupUserName);
                if (addPurchaseView.getReply() == JOptionPane.YES_OPTION) {


                    ((DefaultTableModel)table.getModel()).removeRow(modelRow);
                    JOptionPane.showMessageDialog(null, "Purchased item.");

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

                    this.controllerAddPurchase.controlAddPurchaseUseCase("Item#23", addPurchaseView.getSelectedMembers(),
                            "Saleh", Float.parseFloat(addPurchaseView.item_price.getText().toString()),
                            "Group#24");
                }

                else {
                    JOptionPane.showMessageDialog(null, "Did not purchase item.");
                }

            }
        };

//        ButtonColumn buttonColumn = new ButtonColumn(table, delete, 0);


        scrollPane = new JScrollPane(table);

        add(scrollPane);

        setVisible(true);
        setSize(1000, 600);

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

    public void setRows(List<List<String>> planningListData) {
        this.rows = new Object[planningListData.size()][2];
        for (int i = 0; i < planningListData.size(); i++) {
            this.rows[i] = planningListData.get(i).toArray();
            //CONFIRM WITH CODI WHAT IS IN THIS 2D ARRAY
        }
    }
}
