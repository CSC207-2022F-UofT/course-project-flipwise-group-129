package View;

import Entities.PurchaseList;
import View.BalanceView;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GroupSummaryView extends JPanel implements ActionListener {

    private JTabbedPane t;
    private JComponent p1, p2, p3;
    private JTextArea temp, RHS, group_members;

    private String groupname;
    private String groupid;
    JButton add_item = new JButton("Add Item");
    JButton clear_debt = new JButton("Clear Debt");
    JButton return_to_homepage = new JButton("Return to Groups");

    public GroupSummaryView(String groupname, String groupid) {

        this.groupid = groupid;
        this.groupname = groupname;

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
                "Group Name: " + this.groupname + "\n" +
                "Group Code: " + this.groupid + "\n");
        RHS.setEditable(false);

        JPanel btn_group = new JPanel();
        btn_group.add(add_item);
        btn_group.add(clear_debt);
        btn_group.add(return_to_homepage);

        JPanel text_group = new JPanel();
        text_group.add(RHS);


        JPanel right_hand_side = new JPanel();
        right_hand_side.setLayout(new BoxLayout(right_hand_side, BoxLayout.PAGE_AXIS));
        right_hand_side.add(text_group);
        right_hand_side.add(btn_group);

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
        add(t, BorderLayout.CENTER);
        add(temporary_panel, BorderLayout.NORTH);
        add(right_hand_side, BorderLayout.LINE_START);
        add(bottoms_up, BorderLayout.SOUTH);

        add_item.addActionListener(this);
        clear_debt.addActionListener(this);
        return_to_homepage.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Item")){
            String item = JOptionPane.showInputDialog("Please enter in Item Name:");
        }

        if (e.getActionCommand().equals("Clear Debt")){
            ClearDebtView clearDebtView = new ClearDebtView();
        }

        if (e.getActionCommand().equals("Return to Groups")){
            HomePageView homePageView = new HomePageView();
            homePageView.setVisible(true);
        }
    }


//    private void setHomepageView(){
//        HomePageView homePageView = new HomePageView();
////        this.setContentPane(homePageView);
//    }

}


