package View;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new GroupSummaryView());
                frame.setVisible(true);
            }
        });
    }
}
