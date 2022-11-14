package View;

import javax.swing.*;
import java.awt.*;

public class GroupJoinView extends JFrame {
    JLabel label_ID = new JLabel();
    JTextField text_ID = new JTextField();
    JButton btn_create = new JButton();
    JButton btn_leave = new JButton();
    JLabel label_title = new JLabel();
    JTextArea filler = new JTextArea();

    public GroupJoinView() {
        super("Join Group");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        label_ID = new JLabel("Group ID:");
        text_ID = new JTextField(15);
        JPanel groupID = new JPanel();
        groupID.add(label_ID);
        groupID.add(text_ID);
        groupID.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn_create = new JButton("Join Group");
        JPanel createGroup = new JPanel();
        createGroup.add(btn_create);

        filler = new JTextArea("Enter the registered group ID to join that specific group", 2, 30);
        filler.setEditable(false);
        JPanel fillerText = new JPanel();
        fillerText.add(filler);

        label_title = new JLabel("Join Group");
        btn_leave = new JButton("<-");
        JPanel titleLabel = new JPanel();
        titleLabel.add(btn_leave);
        titleLabel.add(label_title);

        add(titleLabel);
        add(fillerText);
        add(groupID);
        add(createGroup);

        setVisible(true);
    }
}
