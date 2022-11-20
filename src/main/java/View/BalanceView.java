package View;

import Entities.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class BalanceView extends JPanel{

    private JTable table;
    private JScrollPane scrollPane;
    Object[][] rows = new Object[][]{{new JButton("button"), "Amit", " ", " "},
            {"102", "Jai", " ", " "},
            {"101", "Sachin", " ", " "},
            {"1", "2", " ", " "}};
    String[] columns = new String[]{"Member", "You Owe", "They Owe", "Debt"};

    public BalanceView() {

        TableCellRenderer tableRenderer;
        table = new JTable(new JTableButtonModel(rows, columns));
        tableRenderer = table.getDefaultRenderer(JButton.class);
        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
        scrollPane = new JScrollPane(table);

        add(scrollPane);


        setSize(1000, 600);
        setVisible(true);

    }

}





