import javax.swing.*;

public class MainFrame {

    private static volatile JFrame instance;

    public static JFrame getInstance() {
        if (instance == null) {
            synchronized (JFrame.class) {
                if (instance == null) {
                    instance = new JFrame();
                }
            }
        }
        return instance;
    }
}
