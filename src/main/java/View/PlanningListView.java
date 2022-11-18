package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class PlanningListView extends JPanel {

    Object[][] data;
    String[] header;
    JButton purchase = new JButton("Purchase");
    JTable initial_table;
    JScrollPane planning_table;
    private JTable table;
    private JScrollPane scrollPane;
    Object[][] rows = new Object[][]{{new JButton("button"), "Amit", "670000", "2"},
            {"102", "Jai", "780000", "2"},
            {"101", "Sachin", "700000", "3"},
            {"1", "2", "3", "4"}};
    String[] columns = new String[]{"", "Item name", "Person who purchased", "Date/Time"};


    public PlanningListView() {

        TableCellRenderer tableRenderer;
        table = new JTable(new JTableButtonModel(rows, columns));
        tableRenderer = table.getDefaultRenderer(JButton.class);
        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
        scrollPane = new JScrollPane(table);

        add(scrollPane);

    }
}
