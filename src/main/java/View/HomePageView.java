package View;
import javax.swing.*;
import java.awt.*;

public class HomePageView extends JPanel{
        JButton btn_create;
        JButton btn_join;
        JLabel homePage;


        public HomePageView() {
            setSize(600, 500);
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
        }

}
