package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class PlanningListView extends JPanel {
    private final JTable table;
    final DefaultTableModel model;
    Object[][] rows;
    final String[] columns = new String[]{"Item"};

    /**
     * the constructor to create a planning list view
     */
    public PlanningListView(List<List<String>> planningListData) {

        setSize(1000, 600);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setVisible(true);

        setRows(planningListData);
        this.model = new DefaultTableModel(rows, columns);
        table = new JTable(model);


        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);


    }

    /**
     * helper method that sets the rows of all the data to be displayed
     * @param planningListData the provided planning list data
     */
    public void setRows (List <List<String>> planningListData) {
        if (planningListData != null) {
            List<String> itemNames = new ArrayList<>();
            this.rows = new Object[planningListData.size()][1];
            for (int i = 0; i < planningListData.size(); i++) {
                itemNames.add(planningListData.get(i).get(1));
                this.rows[i] = itemNames.toArray();
                itemNames = new ArrayList<>();
            }
        }
    }

    /**
     * @return returns the table
     */
    public JTable getTable(){ return this.table; }

    }


