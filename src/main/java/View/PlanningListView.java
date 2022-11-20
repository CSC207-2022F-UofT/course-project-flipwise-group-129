package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlanningListView extends JPanel implements ActionListener {

    JButton purchase = new JButton("Purchase");
    private JTable table;
    private JScrollPane scrollPane;
    Object[][] rows = new Object[][]{{new JButton("button"), "Amit"},
            {"102", "Jai"},
            {"101", "Sachin"},
            {"1", "2"}};
    String[] columns = new String[]{"", "Item name"};


    public PlanningListView() {

        TableCellRenderer tableRenderer;
        table = new JTable(new JTableButtonModel(rows, columns));
        tableRenderer = table.getDefaultRenderer(JButton.class);
        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
        scrollPane = new JScrollPane(table);

        add(scrollPane);

        setVisible(true);
        setSize(1000, 600);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Purchase")){

        }
    }
}
