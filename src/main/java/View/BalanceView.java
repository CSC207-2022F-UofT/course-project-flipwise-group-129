package View;

import Entities.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BalanceView extends JPanel{

    private JTable table;
    private JScrollPane scrollPane;
    Object[][] rows;
    String[] columns = new String[]{"Member", "You Owe", "They Owe", "Debt"};

    public BalanceView(List<List<String>> debtData, String username, List<String> groupUsernames) {
        setRows(debtData, username, groupUsernames);
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        table = new JTable(model);
        table.setEnabled(false);
        scrollPane = new JScrollPane(table);

        add(scrollPane);


        setSize(1000, 600);
        setVisible(true);

    }

//    public void setBalances(){
//        rows =  new Object[][]{{new JButton("button"), "Amit", " ", " "},
//                {"102", "Jai", " ", " "},
//                {"101", "Sachin", " ", " "},
//                {"1", "2", " ", " "}};
//    }

    public void setRows(List<List<String>> debtData, String username, List<String> groupUsernames) {
        this.rows = new Object[groupUsernames.size()][];
        int j = 0;
        for (int i = 0; i < debtData.size(); i++) {
            List<String> currentDebt = debtData.get(i);
            if (currentDebt.get(1) == username) {
                this.rows[j][0] = currentDebt.get(0);
                this.rows[j][1] = currentDebt.get(3);
                j++;
            }
        }
    }
}





