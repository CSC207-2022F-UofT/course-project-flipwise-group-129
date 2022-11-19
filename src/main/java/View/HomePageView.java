package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView extends JPanel implements ActionListener {
        JButton btn_create;
        JButton btn_join;
        JLabel homePage;


        public HomePageView() {
            setSize(1000, 600);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            homePage = new JLabel("HomePage");
            homePage.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPanel homepageDetails = new JPanel();
            homepageDetails.add(homePage);

            JLabel test = new JLabel("This is a group's area");
            JPanel new_test = new JPanel();
            new_test.add(test);

            JLabel test2 = new JLabel("This is another group's area");
            JPanel new_test_2 = new JPanel();
            new_test_2.add(test2);


            btn_create = new JButton("Create Group");
            btn_join = new JButton("Join Group");
            JPanel groupButtons = new JPanel();
            groupButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
            groupButtons.add(btn_create);
            groupButtons.add(btn_join);


            add(homePage);
            add(Box.createRigidArea(new Dimension(0, 5)));
            add(groupButtons);
            add(new_test);
            add(new_test_2);


            setVisible(true);
            btn_create.addActionListener(this);
            btn_join.addActionListener(this);

            // create join items
        }

        public void actionPerformed(ActionEvent e){
            if (e.getActionCommand().equals("Create Group")){
                JOptionPane.showInputDialog("Please enter in Group Name:");
                //controller stuff
                // update homepage
            }

            if (e.getActionCommand().equals("Join Group")){
                JOptionPane.showInputDialog("Please enter in Group ID:");
                //controller stuff
                //update homepage
            }
        }



//        public void getNumberOfGroups(){ return }

}
