package View;

import Entities.Group;

import javax.swing.*;

public class BalanceView extends JPanel{

    private String[][] data;
    private String[] col;
    private JTable j;

    public BalanceView() {

        data = new String[][]{{"101", "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"101", "Sachin", "700000"},
                {"1", "2", "3", "4"}};
        col = new String[]{"NAME", "YOU OWE", "THEY OWE", "DEBT"};
        j = new JTable(data, col);
        JScrollPane s = new JScrollPane(j);
        this.add(s);
    }


}





