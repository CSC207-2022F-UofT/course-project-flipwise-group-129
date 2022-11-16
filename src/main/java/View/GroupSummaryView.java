package View;

import Entities.PurchaseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GroupSummaryView extends JFrame{

    private JTabbedPane t;
    private JComponent p1, p2, p3;

    public GroupSummaryView() {
        super("Group view");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t = new JTabbedPane();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        t.addTab("Planning", p1);
        t.setMnemonicAt(0, KeyEvent.VK_1);
        t.addTab("Purchases", p2);
        t.setMnemonicAt(1, KeyEvent.VK_2);
        t.addTab("Balances", p3);
        t.setMnemonicAt(2, KeyEvent.VK_3);

        p3.setPreferredSize(new Dimension(410, 50));

        add(t);

        setSize(1000,600);
        setVisible(true);
        }

        public static void main(String[] args) {
            GroupSummaryView group = new GroupSummaryView();
        }

    }


