import javax.swing.*;

public class FormThreeFields implements MainForm {
    private JPanel PanelThreeFields;
    private JTextField firstNameField;
    private JTextField surNameField;
    private JTextField midNameField;
    private JButton collapseButton;

    public FormThreeFields() {
    }

    @Override
    public JPanel getContent() {
        return PanelThreeFields;
    }

    @Override
    public JButton getButton() {
        return collapseButton;
    }

    @Override
    public JTextField[] getTextFields() {
        return new JTextField[]{firstNameField, surNameField, midNameField};
    }
}
