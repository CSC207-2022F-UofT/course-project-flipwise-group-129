package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class PlanningListView extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    Object[][] rows;
    String[] columns = new String[]{"", "Item name"};

    public PlanningListView() {

//        TableCellRenderer tableRenderer;
//        table = new JTable(new JTableButtonModel(rows, columns));
//        tableRenderer = table.getDefaultRenderer(JButton.class);
//        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
        setPlanningList();
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        table = new JTable(model);

        Action delete = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );

                AddPurchaseView addPurchaseView = new AddPurchaseView();
                if (addPurchaseView.getOption() == JOptionPane.YES_OPTION) {
                    ((DefaultTableModel)table.getModel()).removeRow(modelRow);
                    JOptionPane.showMessageDialog(null, "HELLO");
                }

                else {
                    JOptionPane.showMessageDialog(null, "GOODBYE");
                }

            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(table, delete, 0);

        scrollPane = new JScrollPane(table);

        add(scrollPane);

        setVisible(true);
        setSize(1000, 600);

    }

    public void setPlanningList(){
        // filter through codis data (codis data in parameter)
        // get updated planning list and reload page
        // testing
        rows = new Object[][]{{"Purchase", "the"},
                {"Purchase", "the"},
                {"Purchase", "Sandwich"},
                {"Purchase", "8"}};



    }
}
