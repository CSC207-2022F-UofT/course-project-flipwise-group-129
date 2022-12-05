import View.MainWindowView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindowView::new);
    }
}
