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
//import UseCases.AddPurchase;
import UseCases.AddPurchase;
import UseCases.AddToPlanningList;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlanningListView extends JPanel {
    private JTable table;
    DefaultTableModel model;
    private String username;
    private String groupID;
    private List<String> groupUserName;
    private JScrollPane scrollPane;
    Object[][] rows;
    String[] columns = new String[]{"Item"};
    GroupSummaryView groupSummaryView;
    JButton purchase;
//    private ButtonTable buttonTable;
    private AddPurchaseController controllerAddPurchase;

    public PlanningListView(List<List<String>> planningListData, String username, String groupID,
                            List<String> groupUserName) {

        setSize(1000, 600);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setVisible(true);

        setRows(planningListData);
        this.model = new DefaultTableModel(rows, columns);
        table = new JTable(model);

//        table.setEnabled(false);

        scrollPane = new JScrollPane(table);
        add(scrollPane);


    }
        public void setRows (List <List<String>> planningListData) {
            if (planningListData != null) {
                List<String> itemnames = new ArrayList<>();
                this.rows = new Object[planningListData.size()][1];
                for (int i = 0; i < planningListData.size(); i++) {
                    itemnames.add(planningListData.get(i).get(1));
                    this.rows[i] = itemnames.toArray();
                    itemnames = new ArrayList<>();
                }
            }
        }

        public JTable getTable(){ return this.table; }
    }


