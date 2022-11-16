package View;

import Entities.Item;

import javax.swing.*;

public class AddPurchaseView extends JFrame {

    JButton confirm;
    JButton reject;
    JLabel item_request;
    JTextField item_price;
    JTextArea confirmation;
    JTextArea price_information;
    public AddPurchaseView() {
        setTitle("Item Cost");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 250);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);

        price_information = new JTextArea("Add the cost of the <item>");
        price_information.setEditable(false);
        JPanel price_request_text = new JPanel();
        price_request_text.add(price_information);

        item_request = new JLabel("Item cost:");
        item_price = new JTextField(20);
        JPanel pricing = new JPanel();
        pricing.add(item_request);
        pricing.add(item_price);

        confirmation = new JTextArea("Are you sure you want to purchase this item");
        confirmation.setEditable(false);
        JPanel confirm_text = new JPanel();
        confirm_text.add(confirmation);

        confirm = new JButton("Yes");
        reject = new JButton("No");
        JPanel buttons = new JPanel();
        buttons.add(confirm);
        buttons.add(reject);

        add(price_request_text);
        add(pricing);
        add(confirm_text);
        add(buttons);

    }

}
