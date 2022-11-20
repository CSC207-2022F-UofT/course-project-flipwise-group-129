package View;

// should this inherit from ItemListView

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseListView extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    Object[][] rows = new Object[][]{{new JButton("button"), "Amit", "670000"},
            {"102", "Jai", "780000"},
            {"101", "Sachin", "700000"},
            {"1", "2", "3"}};
    String[] columns = new String[]{"Item Name", "Cost", "Who brought it"};
    public PurchaseListView() {

        TableCellRenderer tableRenderer;
        table = new JTable(new JTableButtonModel(rows, columns));
        tableRenderer = table.getDefaultRenderer(JButton.class);
        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
        scrollPane = new JScrollPane(table);

        add(scrollPane);

        setVisible(true);
        setSize(1000, 600);

    }

}

