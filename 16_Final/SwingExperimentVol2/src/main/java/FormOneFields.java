import javax.swing.*;

public class FormOneFields implements MainForm {
    private JPanel PanelFormFiled;
    private JTextField fullNameField;
    private JButton expandButton;
    public FormOneFields() {
    }

    @Override
    public JPanel getContent() {
        return PanelFormFiled;
    }

    public JTextField getFullNameField() {
        return fullNameField;
    }

    @Override
    public JButton getButton() {
        return expandButton;
    }

    @Override
    public JTextField[] getTextFields() {
        return new JTextField[] {fullNameField};
    }
}
