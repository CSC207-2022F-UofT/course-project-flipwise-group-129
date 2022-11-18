package View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddPurchaseView extends JFrame {

    List<String> members = new ArrayList<String>(); //NEEDS TO BE REFINED
    JButton confirm;
    JButton reject;
    JLabel item_request;
    JTextField item_price;
    JTextArea text_contributing_members;
    List<JCheckBox> contributing_members = createCheckboxes(members);
    JTextArea confirmation;
    JTextArea price_information;
    public AddPurchaseView() {
        //set window
        setTitle("Item Cost");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 250);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
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
        for (int i = 0; i < contributing_members.size(); i++) {
            check_box_contributing_members.add(contributing_members.get(i));
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

    public List<JCheckBox> createCheckboxes(List<String> Current_Members) {
        List<JCheckBox> output = new ArrayList<JCheckBox>();
        for (String member : Current_Members) {
            JCheckBox checkbox_member = new JCheckBox(member);
            output.add(checkbox_member);
        }
        return output;
    }

}
