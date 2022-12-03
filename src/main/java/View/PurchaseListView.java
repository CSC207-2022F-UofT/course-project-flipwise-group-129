package View;

// should this inherit from ItemListView

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PurchaseListView extends JPanel {

    private JTable table;
    Object[][] rows;
    String[] columns = new String[]{"Item Name", "Cost", "Who bought it"};
    public PurchaseListView(List<List<String>> purchasedListView) {

        setVisible(true);
        setSize(1000, 600);

        setRows(purchasedListView);
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        table = new JTable(model);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

    }

    public void setRows(List<List<String>> purchasedListData) {
        if (purchasedListData != null) {
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
}

