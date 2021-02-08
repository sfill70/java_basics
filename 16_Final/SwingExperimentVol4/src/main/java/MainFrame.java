import javax.swing.*;

public class MainFrame {

    private static volatile MainFrame instance;
    private static JFrame frame;

    public static MainFrame getInstance() {
        if (instance == null) {
            synchronized (MainFrame.class) {
                if (instance == null) {
                    instance = new MainFrame();
                    frame = new JFrame();
                }
            }
        }
        return instance;
    }

    void initialization(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void setContent(MainForm form) {
        frame.setContentPane(form.getContent());
        frame.setSize(form.getContent().getMinimumSize());
        frame.repaint();
    }
}
