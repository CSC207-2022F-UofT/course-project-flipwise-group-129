package View;

// should this inherit from ItemListView

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseListView extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    Object[][] rows;
    String[] columns = new String[]{"Item Name", "Cost", "Who brought it"};
    public PurchaseListView() {
        setPurchaseList();
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        table = new JTable(model);
        table.setEnabled(false);
        scrollPane = new JScrollPane(table);

        add(scrollPane);

        setVisible(true);
        setSize(1000, 600);

    }

    public void setPurchaseList(){
        rows = new Object[][]{{new JButton("button"), "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"101", "Sachin", "700000"},
                {"1", "2", "3"}};
    }

}

