import javax.swing.*;
import java.awt.event.ActionListener;

public class FormThreeFields implements MainForm {
    private JPanel panelThreeFields;
    private JTextField firstNameField;
    private JTextField surNameField;
    private JTextField midNameField;
    private JButton collapseButton;

    public FormThreeFields() {
    }

    @Override
    public JPanel getContent() {
        return panelThreeFields;
    }

    @Override
    public JButton getButton() {
        return collapseButton;
    }

    @Override
    public JTextField[] getTextFields() {
        return new JTextField[]{firstNameField, surNameField, midNameField};
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
        collapseButton.addActionListener(listener);
    }
}
