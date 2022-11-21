package View;

import Entities.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BalanceView extends JPanel{

    private JTable table;
    private JScrollPane scrollPane;
    Object[][] rows;
    String[] columns = new String[]{"Member", "You Owe", "They Owe", "Debt"};

    public BalanceView() {
        setBalances();
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        table = new JTable(model);
        table.setEnabled(false);
        scrollPane = new JScrollPane(table);

        add(scrollPane);


        setSize(1000, 600);
        setVisible(true);

    }

    public void setBalances(){
        rows =  new Object[][]{{new JButton("button"), "Amit", " ", " "},
                {"102", "Jai", " ", " "},
                {"101", "Sachin", " ", " "},
                {"1", "2", " ", " "}};
    }

}





