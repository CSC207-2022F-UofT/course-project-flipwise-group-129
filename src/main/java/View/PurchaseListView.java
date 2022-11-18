package View;

// should this inherit from ItemListView

import javax.swing.*;

public class PurchaseListView extends JPanel{

    private String[][] data;
    private String[] header;
    private JTable j;
    private JScrollPane s;
    public PurchaseListView() {

        data = new String[][]{{"101", "Amit", "670000", "2"},
                {"102", "Jai", "780000", "2"},
                {"101", "Sachin", "700000", "3"},
                {"1", "2", "3", "4"}};
        header = new String[]{"NAME", "YOU OWE", "THEY OWE", "DEBT"};
        j = new JTable(data, header);
        s = new JScrollPane();
        j.setSize(400, 402);
        j.setAlignmentY(700);

        this.add(j);

        setVisible(true);
        setSize(1000, 1000);

    }

}

