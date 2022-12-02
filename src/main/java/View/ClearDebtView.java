package View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClearDebtView extends JOptionPane {

    private String username;
    private String groupID;
    private List<String> members;
    private  List<JRadioButton> list_of_members;
    private ButtonGroup radio_btn_group;

    public ClearDebtView(String username, String groupID, List<String> members) {

        this.username = username;
        this.groupID = groupID;
        this.members = members;
        this.list_of_members = createRadioButtons(this.members);

        // fetch debt request
        JPanel radio_box_contributing_members = new JPanel();
        this.radio_btn_group = new ButtonGroup();

        for (int i = 0; i < list_of_members.size(); i++) {
            radio_box_contributing_members.add(list_of_members.get(i));
            radio_btn_group.add(list_of_members.get(i));
        }

        showOptionDialog(this, radio_box_contributing_members,
                "Who do you want to clear your debt with?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
    }

    public List<JRadioButton> createRadioButtons(List<String> Current_Members) {
        List<JRadioButton> output = new ArrayList<JRadioButton>();
        for (String member : Current_Members) {
            JRadioButton checkbox_member = new JRadioButton(member);
            output.add(checkbox_member);
        }
        return output;
    }

    public List<String> getSelectedMembers(){
        List<String> selectedMembers = new ArrayList<String>();
        Enumeration elements = this.radio_btn_group.getElements();
        while (elements.hasMoreElements()) {
            AbstractButton button = (AbstractButton)elements.nextElement();
            if (button.isSelected()) {
                selectedMembers.add(button.getText());
            }
        }
        return selectedMembers;
    }


}
