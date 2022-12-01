package View;

import Entities.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BalanceView extends JPanel{

    private JTable table;
    Object[][] rows;
    String[] columns = new String[]{"Member", "You Owe", "They Owe", "Debt"};

    public BalanceView(List<List<String>> debtData, String username, List<String> groupUsernames) {

        setSize(1000, 600);
        setVisible(true);
        setRows(debtData, username, groupUsernames);
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        table = new JTable(model);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

    }

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





