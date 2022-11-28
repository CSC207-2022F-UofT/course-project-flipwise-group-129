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
        //set window
//        setSize(500, 250);
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        setVisible(true);

        //set text asking for cost
//        debt_information = new JTextArea("Who do you want to clear your debt with?");
//        debt_information.setEditable(false);
//        JPanel debt_clear_text = new JPanel();
//        debt_clear_text.add(debt_information);

        // fetch debt request
        JPanel radio_box_contributing_members = new JPanel();
        ButtonGroup radio_btn_group = new ButtonGroup();
        for (int i = 0; i < list_of_members.size(); i++) {
            radio_box_contributing_members.add(list_of_members.get(i));
            radio_btn_group.add(list_of_members.get(i));
        }

        //testing purposes
        JRadioButton sample1 = new JRadioButton("Saleh");
        JRadioButton sample2 = new JRadioButton("Rachel");
        radio_box_contributing_members.add(sample1);
        radio_box_contributing_members.add(sample2);
        radio_btn_group.add(sample2);
        radio_btn_group.add(sample1);
        //remove block!

        this.showOptionDialog(null, radio_box_contributing_members,
                "Who do you want to clear your debt with?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        //set text asking for confirmation
//        confirmation = new JTextArea("Are you sure you want to clear your debt?");
//        confirmation.setEditable(false);
//        JPanel confirm_text = new JPanel();
//        confirm_text.add(confirmation);

        //fetch confirmation
//        confirm = new JButton("Yes");
//        reject = new JButton("No");
//        JPanel buttons = new JPanel();
//        buttons.add(confirm);
//        buttons.add(reject);

//        add(debt_clear_text);
//        add(radio_box_contributing_members);
//        add(confirm_text);
//        add(buttons);
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
