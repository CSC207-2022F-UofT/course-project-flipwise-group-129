package View;

import Entities.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BalanceView extends JPanel{

    private String[][] data;
    private String[] header;
    private JTable j;
    private JScrollPane s;

    public BalanceView() {

        data = new String[][]{{"101", "Amit", "670000", "2"},
                {"102", "Jai", "780000", "2"},
                {"101", "Sachin", "700000", "3"},
                {"1", "2", "3", "4"}};
        header = new String[]{"NAME", "YOU OWE", "THEY OWE", "DEBT"};
        j = new JTable(data, header);
        j.setSize(400, 400);

        DefaultTableModel tableModel = new DefaultTableModel(data, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        j.setModel(tableModel);

        s = new JScrollPane(j);

        setVisible(true);
        setSize(400, 400);
        add(s);

    }



}





