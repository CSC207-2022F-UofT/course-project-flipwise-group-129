package FrameworkDriversLayer.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClearDebtView extends JOptionPane {

    private final ButtonGroup radio_btn_group;

    public ClearDebtView(List<String> members) {

        List<JRadioButton> list_of_members = createRadioButtons(members);

        // fetch debt request
        JPanel radio_box_contributing_members = new JPanel();
        this.radio_btn_group = new ButtonGroup();

        for (JRadioButton list_of_member : list_of_members) {
            radio_box_contributing_members.add(list_of_member);
            radio_btn_group.add(list_of_member);
        }

        showOptionDialog(this, radio_box_contributing_members,
                "Who do you want to clear your debt with?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
    }

    /**
     * Takes in a list of current members and returns a list of Radio Buttons with the same members
     * @param Current_Members List of members
     * @return  list of radio buttons
     */
    public List<JRadioButton> createRadioButtons(List<String> Current_Members) {
        List<JRadioButton> output = new ArrayList<>();
        for (String member : Current_Members) {
            JRadioButton checkbox_member = new JRadioButton(member);
            output.add(checkbox_member);
        }
        return output;
    }

    /**
     *  returns the selected member from the radio buttons chosen
     * @return  the selected member
     */
    public String getSelectedMember(){
        Enumeration<AbstractButton> elements = this.radio_btn_group.getElements();
        String member = "";
        while (elements.hasMoreElements()) {
            AbstractButton button = elements.nextElement();
            if (button.isSelected()) {
                member = button.getText();
            }
        }
        return member;

    }
}
