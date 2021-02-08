import javax.swing.*;
import java.awt.event.ActionListener;

public interface MainForm {

    JPanel getContent();
    JButton getButton();
    JTextField [] getTextFields();
    void setTextField(String[] names);
    void setListener(ActionListener listener);
    void setMainForm(MainForm form);
}
