package View;

// should this inherit from ItemListView

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class PurchaseListView extends JPanel {

    Object[][] rows;
    String[] columns = new String[]{"Item Name", "Cost", "Who bought it"};

    /**
     * constructor that makes a purchase list view
     */
    public PurchaseListView(List<List<String>> purchasedListView) {

        setVisible(true);
        setSize(1000, 600);

        setRows(purchasedListView);
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        JTable table = new JTable(model);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

    }

    /**
     * helper function that sets rows in an appropriate manner from the available data
     * @param purchasedListData the purchased list data to be shown
     */
    public void setRows(List<List<String>> purchasedListData) {

            List<String> data = new ArrayList<>();
            this.rows = new Object[purchasedListData.size()][3];
            for (int i = 0; i < purchasedListData.size(); i++) {
                data.add(purchasedListData.get(i).get(1));
                data.add(purchasedListData.get(i).get(2));
                data.add(purchasedListData.get(i).get(3));
                this.rows[i] = data.toArray();
                data = new ArrayList<>();
            }
        }

}

