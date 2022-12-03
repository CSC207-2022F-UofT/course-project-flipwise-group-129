package View;

import Entities.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BalanceView extends JPanel{

    private final JTable table;
    Object[][] rows;
    String[] columns = new String[]{"Member", "Debt owed", "Debt deserved"};

    public BalanceView(List<List<Object>> debtData, String username, List<String> groupUsernames) {

        setSize(1000, 600);
        setVisible(true);

        setRows(debtData, username, groupUsernames);
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        table = new JTable(model);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        System.out.println("debtdata " + debtData);
                System.out.println("members " + groupUsernames);

    }

    public void setRows(List<List<Object>> debtData, String username, List<String> groupUsernames) {
            this.rows = new Object[groupUsernames.size()][3];
            int j = 0;
            for (int i = 0; i < debtData.size(); i++) {
                List<Object> currentDebt = debtData.get(i);
                if (currentDebt.get(0) == username) {
                    this.rows[j][0] = currentDebt.get(1);
                    this.rows[j][1] = currentDebt.get(3);
                    j++;
                }
            }
            j = 0;
            for (int i = 0; i < debtData.size(); i++) {
                List<Object> currentDebt = debtData.get(i);
                if (this.rows[j][0] == currentDebt.get(0) && currentDebt.get(1) == username && j < groupUsernames.size()) {
                    this.rows[j][2] = currentDebt.get(3);
                    i = 0;
                    j++;
                }
            }
    }





}





