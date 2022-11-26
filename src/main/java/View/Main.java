package View;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame new_frame = new JFrame();
                new_frame.add(new UserRegisterView());
                new_frame.setVisible(true);
                new_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }
}
