package View;

// should this inherit from ItemListView

import javax.swing.*;

public class PurchaseListView extends JPanel{

    private String[][] data;
    private String[] header;
    private JTable j;
    private JScrollPane s;
    public PurchaseListView() {

        data = new String[][]{{"101", "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"101", "Sachin", "700000"},
                {"1", "2", "3"}};
        header = new String[]{"Item Name", "Cost", "Who bought it"};
        j = new JTable(data, header);
        s = new JScrollPane(j);

        this.add(s);

        setVisible(true);
        setSize(1000, 1000);

    }

}

