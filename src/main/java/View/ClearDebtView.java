package View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClearDebtView extends JOptionPane {
    JTextArea debt_information;
    List<String> members = new ArrayList<String>();
    List<JRadioButton> list_of_members = createRadioButtons(members);

    JButton confirm;
    JButton reject;
    JTextArea confirmation;

    public ClearDebtView() {

        // fetch debt request
        JPanel radio_box_contributing_members = new JPanel();
        ButtonGroup radio_btn_group = new ButtonGroup();
        for (int i = 0; i < list_of_members.size(); i++) {
            radio_box_contributing_members.add(list_of_members.get(i));
            radio_btn_group.add(list_of_members.get(i));
        }

        //DUMMY
        JRadioButton sample1 = new JRadioButton("Saleh");
        JRadioButton sample2 = new JRadioButton("Rachel");
        radio_box_contributing_members.add(sample1);
        radio_box_contributing_members.add(sample2);
        radio_btn_group.add(sample2);
        radio_btn_group.add(sample1);

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
}
