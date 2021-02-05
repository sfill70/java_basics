import javax.swing.*;
import java.awt.event.ActionListener;

public class FormOneFields implements MainForm {
    private JPanel panelFormFiled;
    private JTextField fullNameField;
    private JButton expandButton;

    public FormOneFields() {
    }

    @Override
    public JPanel getContent() {
        return panelFormFiled;
    }

    @Override
    public JButton getButton() {
        return expandButton;
    }

    @Override
    public JTextField[] getTextFields() {
        return new JTextField[] {fullNameField};
    }

    @Override
    public void setTextField(String[] names) {
        if (names.length > getTextFields().length) {
            return;
        }
        for (int i = 0; i < names.length; i++) {
            getTextFields()[i].setText(names[i]);
        }
    }

    @Override
    public void setListener(ActionListener listener) {
        expandButton.addActionListener(listener);
    }
}
