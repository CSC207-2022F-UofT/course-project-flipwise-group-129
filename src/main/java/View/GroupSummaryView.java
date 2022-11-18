package View;

import Entities.PurchaseList;
import View.BalanceView;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GroupSummaryView extends JFrame {

    private JTabbedPane t;
    private JComponent p1, p2, p3;
    private JTextArea temp, RHS, group_members;


    public GroupSummaryView() {
        super("Group view");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);
        setVisible(true);
        setLayout(new BorderLayout());

        t = new JTabbedPane();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        temp = new JTextArea("Group Summary");
        temp.setEditable(false);

        JPanel temporary_panel = new JPanel();
        temporary_panel.add(temp);

        RHS = new JTextArea("This is the group information. \n" +
                "Group Name: " + "\n" +
                "Group Code: " + "\n");
        RHS.setEditable(false);
        JPanel right_hand_side = new JPanel();
        right_hand_side.add(RHS);

        PlanningListView c = new PlanningListView();
        PurchaseListView p = new PurchaseListView();
        BalanceView b = new BalanceView();
        p2.add(p);
        p1.add(c);

        group_members = new JTextArea("Placeholder for the names");
        group_members.setEditable(false);
        JPanel bottoms_up = new JPanel();
        bottoms_up.add(group_members);


        t.addTab("Planning", p1);
        t.setMnemonicAt(0, KeyEvent.VK_1);
        t.addTab("Purchases", p2);
        t.setMnemonicAt(1, KeyEvent.VK_2);
        t.addTab("Balances", p3);
        t.setMnemonicAt(2, KeyEvent.VK_3);

        //p2.setBorder(BorderFactory.createTitledBorder(
                //BorderFactory.createEtchedBorder(), "Purchases", TitledBorder.CENTER, TitledBorder.TOP));
        //p3.setBorder(BorderFactory.createTitledBorder(
                //BorderFactory.createEtchedBorder(), "Balances", TitledBorder.CENTER, TitledBorder.TOP));
        p3.add(b);
        add(t, BorderLayout.LINE_START);
        add(temporary_panel, BorderLayout.NORTH);
        add(right_hand_side, BorderLayout.CENTER);
        add(bottoms_up, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
            GroupSummaryView group = new GroupSummaryView();
        }

    }


