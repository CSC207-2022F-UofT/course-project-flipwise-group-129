package View;

import javax.swing.*;

public class AddShoppingItemView extends JPanel {

    JTextArea confirmation;
    JTextArea item_request_text;
    JLabel item_name_text;
    JTextField item_name;
    JButton confirm;
    JButton reject;
    public AddShoppingItemView() {
        //set window
        setSize(500, 250);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);

        //set text asking for item name
        item_request_text = new JTextArea("Write down the name of the item you would like to add");
        item_request_text.setEditable(false);
        JPanel item_text_panel = new JPanel();
        item_text_panel.add(item_request_text);

        //fetch item name
        item_name_text = new JLabel("Item Name:");
        item_name = new JTextField(20);
        JPanel item_name_panel = new JPanel();
        item_name_panel.add(item_name_text);
        item_name_panel.add(item_name);

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

        add(item_text_panel);
        add(item_name_panel);
        add(confirm_text);
        add(buttons);

    }
}
