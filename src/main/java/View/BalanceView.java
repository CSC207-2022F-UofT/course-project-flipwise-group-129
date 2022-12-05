package View;

import Entities.Group;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class BalanceView extends JPanel {

    private final JTable table;
    Object[][] rows;
    String[] columns = new String[]{"Member", "Debt Owed", "Debt Deserved"};

    public BalanceView(List<List<Object>> debtData, String username, List<String> groupUsernames) {

        setSize(1000, 600);
        setVisible(true);

        setRows(debtData, username, getMembers(groupUsernames, username));
        DefaultTableModel model = new DefaultTableModel(rows, columns);
        table = new JTable(model);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

    }

    /**
     * this is a helper function that puts the given debt data, username and parameters into a form of
     * "List<List<String>>" for the rows of the table to be printed
     * @param debtData  debt data taken from a controller
     * @param username  username of the user currently logged in
     * @param members   other members of the group
     */
    public void setRows(List<List<Object>> debtData, String username, List<String> members) {
        this.rows = new Object[members.size()][3];
        for (int i = 0; i < members.size(); i++) {
            this.rows[i][0] = members.get(i);
            for (List<Object> debt : debtData) {
                if (debt.get(0).equals(members.get(i)) || debt.get(1).equals(members.get(i))) {
                    if (debt.get(0).equals(username)) { this.rows[i][2] = debt.get(2); }
                    else { this.rows[i][1] = debt.get(2);}
                }
            }
        }
    }
        public List<String> getMembers (List < String > groupUsernames, String username){
            List<String> members = new ArrayList<>();
            for (int i = 0; i < groupUsernames.size(); i++) {
                if (!(groupUsernames.get(i).equals(username))) {
                    members.add(groupUsernames.get(i));
                }
            }
            return members;
        }
    }













