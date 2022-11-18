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
    private JTextPane temp;

    public GroupSummaryView() {
        super("Group view");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t = new JTabbedPane();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        temp = new JTextPane();

        PurchaseListView p = new PurchaseListView();
        BalanceView b = new BalanceView();
        p2.add(p);


        t.addTab("Planning", p1);
        t.setMnemonicAt(0, KeyEvent.VK_1);
        t.addTab("Purchases", p2);
        t.setMnemonicAt(1, KeyEvent.VK_2);
        t.addTab("Balances", p3);
        t.setMnemonicAt(2, KeyEvent.VK_3);

        p2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Purchases", TitledBorder.CENTER, TitledBorder.TOP));
        p3.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Balances", TitledBorder.CENTER, TitledBorder.TOP));
        p3.add(b);
        add(t);

        setSize(1000,600);
        setVisible(true);
    }

    public static void main(String[] args) {
            GroupSummaryView group = new GroupSummaryView();
        }

    }


