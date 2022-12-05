package View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class AddPurchaseView extends JOptionPane{
    final JLabel item_request;
    final JTextField item_price;
    final JTextArea text_contributing_members;
    final JCheckBox[] contributing_members;
    public AddPurchaseView(List<String> groupUserNames) {


        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setVisible(true);

        // fetch item cost
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

        contributing_members = createCheckboxes(groupUserNames);

        //fetch contributing members
        JPanel check_box_contributing_members = new JPanel();
        for (JCheckBox contributing_member : contributing_members) {
            check_box_contributing_members.add(contributing_member);
        }

        p.add(pricing);
        p.add(contributing_members_text);
        p.add(check_box_contributing_members);

        showOptionDialog(null, p,
                "Purchase Item", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

    }

    /**
     *
     * @param Current_Members list of current members
     * @return  returns a list of JCheckboxes with all the group's current members
     */
    public JCheckBox[] createCheckboxes(List<String> Current_Members) {
        JCheckBox[] output = new JCheckBox[Current_Members.size()];
        for (int i = 0; i < Current_Members.size(); i++) {
            JCheckBox checkbox_member = new JCheckBox(Current_Members.get(i));
            output[i] = checkbox_member;
        }
        return output;
    }

    /**
     *
     * @return gets the price of the item
     */
    public String getItemPrice(){ return item_price.getText();}

    /**
     *
     * @return returns a list of selected members for the purchase made
     */
    public List<String> getSelectedMembers(){
        List<String> selectedMembers = new ArrayList<>();
        for (JCheckBox contributing_member : contributing_members) {
            if (contributing_member.isSelected()) {
                selectedMembers.add(contributing_member.getText());
            }
        }
        return selectedMembers;
    }

}
