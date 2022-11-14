package View;
import javax.swing.*;
import java.awt.*;

public class HomePageView extends JFrame{
        JButton btn_create = new JButton();
        JButton btn_join = new JButton();
        JLabel homePage = new JLabel();


        public HomePageView() {
            super("Home Page");

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 500);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            homePage = new JLabel("HomePage");
            homePage.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPanel homepageDetails = new JPanel();
            homepageDetails.add(homePage);


            btn_create = new JButton("Create Group");
            btn_join = new JButton("Join Group");
            JPanel groupButtons = new JPanel();
            groupButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
            groupButtons.add(btn_create);
            groupButtons.add(btn_join);


            add(homePage);
            add(Box.createRigidArea(new Dimension(0, 5)));
            add(groupButtons);


            setVisible(true);
        }

}
