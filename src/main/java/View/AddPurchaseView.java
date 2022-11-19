package View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddPurchaseView extends JPanel{

    String[] members = new String[]{}; //NEEDS TO BE REFINED
    JButton confirm;
    JButton reject;
    JLabel item_request;
    JTextField item_price;
    JTextArea text_contributing_members;
    JCheckBox[] contributing_members = createCheckboxes(members);
    JTextArea confirmation;
    JTextArea price_information;
    public AddPurchaseView() {
        //set window
        setSize(500, 250);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);

        //set text asking for cost
        price_information = new JTextArea("Add the cost of the <item>");
        price_information.setEditable(false);
        JPanel price_request_text = new JPanel();
        price_request_text.add(price_information);

        //fetch item cost
        item_request = new JLabel("Item cost:");
        item_price = new JTextField(20);
        JPanel pricing = new JPanel();
        pricing.add(item_request);
        pricing.add(item_price);

        //set text asking for who cost is being split amongst
        text_contributing_members = new JTextArea("Who else is pitching in?");
        text_contributing_members.setEditable(false);
        JPanel contributing_members_text = new JPanel();
        contributing_members_text.add(text_contributing_members);

        //fetch contributing members
        JPanel check_box_contributing_members = new JPanel();
        for (int i = 0; i < contributing_members.length; i++) {
            check_box_contributing_members.add(contributing_members[i]);
        }
        JCheckBox sample = new JCheckBox("Saleh");
        check_box_contributing_members.add(sample);

        //set text asking for confirmation
        confirmation = new JTextArea("Are you sure you want to purchase this item");
        confirmation.setEditable(false);
        JPanel confirm_text = new JPanel();
        confirm_text.add(confirmation);

        //fetch confirmation
        confirm = new JButton("Yes");
        reject = new JButton("No");
        JPanel buttons = new JPanel();
        buttons.add(confirm);
        buttons.add(reject);

        add(price_request_text);
        add(pricing);
        add(contributing_members_text);
        add(check_box_contributing_members);
        add(confirm_text);
        add(buttons);

    }

    public JCheckBox[] createCheckboxes(String[] Current_Members) {
        JCheckBox[] output = new JCheckBox[Current_Members.length];
        for (int i = 0; i < Current_Members.length; i++) {
            JCheckBox checkbox_member = new JCheckBox(Current_Members[i]);
            output[i] = checkbox_member;
        }
        return output;
    }

}
