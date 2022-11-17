package View;

import javax.swing.*;
import java.awt.*;

public class GroupCreationView extends JFrame {
        JLabel groupName = new JLabel();
        JTextField groupNameInfo = new JTextField();
        JButton btn_create = new JButton();
        JButton btn_leave = new JButton();
        JLabel label_title = new JLabel();
        JTextArea filler;

        public GroupCreationView() {
            super("Group Creation");

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 500);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            groupName = new JLabel("Group Name:");
            groupNameInfo = new JTextField(20);
            JPanel groupNames = new JPanel();
            groupNames.add(groupName);
            groupNames.add(groupNameInfo);
            groupNames.setAlignmentX(Component.CENTER_ALIGNMENT);

            btn_create = new JButton("Create Group");
            JPanel createGroup = new JPanel();
            createGroup.add(btn_create);

            filler = new JTextArea("Welcome to the group creation menu. Enter your group's name, then click the button c" +
                    "reate it", 2, 30);
            filler.setEditable(false);
            JPanel fillerText = new JPanel();
            fillerText.add(filler);

            label_title = new JLabel("Group Creation Menu");
            btn_leave = new JButton("<-");
            JPanel titleLabel = new JPanel();
            titleLabel.add(btn_leave);
            titleLabel.add(label_title);

            add(titleLabel);
            add(fillerText);
            add(groupNames);
            add(createGroup);

            setVisible(true);
        }
}
