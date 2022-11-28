package View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class AddPurchaseView extends JOptionPane{

    String[] members;
    JLabel item_request;
    JTextField item_price;
    JTextArea text_contributing_members;
    JCheckBox[] contributing_members;
    List<String> checked_members;

    int reply;
    public AddPurchaseView(String username, String groupID, String itemID, List<String> groupUserNames) {

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

        contributing_members = createCheckboxes(members);

        //fetch contributing members
        JPanel check_box_contributing_members = new JPanel();
        for (int i = 0; i < contributing_members.length; i++) {
            check_box_contributing_members.add(contributing_members[i]);
        }

        p.add(pricing);
        p.add(contributing_members_text);
        p.add(check_box_contributing_members);

        reply = this.showOptionDialog(null, p,
                "Purchase Item", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);


    }
    public JCheckBox[] createCheckboxes(String[] Current_Members) {
        JCheckBox[] output = new JCheckBox[Current_Members.length];
        for (int i = 0; i < Current_Members.length; i++) {
            JCheckBox checkbox_member = new JCheckBox(Current_Members[i]);
            output[i] = checkbox_member;
            // update planning and purchase lists
        }
        return output;
    }

//    public void setMembers(){}

    public int getReply(){
        return this.reply;
    }
    public String getItemPrice(){ return this.item_price.getText(); }

    public void setMembers(String[] members){
        this.members = members;
    }

    public List<String> getSelectedMembers(){
        List<String> selectedMembers = new ArrayList<String>();
        for (int i = 0; i < contributing_members.length; i++) {
            if (contributing_members[i].isSelected()) {
                selectedMembers.add(contributing_members[i].getName());
            }
        }
        return selectedMembers;
    }

}
