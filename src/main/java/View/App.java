package View;
import Entities.User;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new UserRegisterView());
                frame.setVisible(true);
            }

        });
    }
}
