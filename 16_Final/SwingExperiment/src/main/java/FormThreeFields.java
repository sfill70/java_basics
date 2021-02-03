import javax.swing.*;

public class FormThreeFields {
    private JPanel PanelThreeFields;
    private JTextField firstNameField;
    private JTextField surNameField;
    private JTextField midNameField;
    private JButton collapseButton;
    private FormOneFields formOneFields;
    private JFrame frame;

    public FormThreeFields() {
    }

    public FormThreeFields(FormOneFields formOneFields, JFrame frame) {
        this.formOneFields = formOneFields;
        this.frame = frame;
        collapseButton.addActionListener(e -> btnPressed(formOneFields));
    }

    public void setFormOneFields(FormOneFields formOneFields) {
        this.formOneFields = formOneFields;
    }

    public JPanel getPanelThreeFields() {
        return PanelThreeFields;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getSurNameField() {
        return surNameField;
    }

    public JTextField getMidNameField() {
        return midNameField;
    }

    public JButton getCollapseButton() {
        return collapseButton;
    }

    public void viewFormOneFields() {
        frame.setContentPane(formOneFields.getPanelFormFiled());
        frame.setSize(formOneFields.getPanelFormFiled().getMinimumSize());
        frame.repaint();
    }

    private void btnPressed(FormOneFields formOneFields) {
        if (!firstNameField.getText().isEmpty() && !surNameField.getText().isEmpty() &&
                (firstNameField.getText() + surNameField.getText() + midNameField.getText()).matches("^[\\p{L}\\p{Blank}.’\\-]+")) {
            formOneFields.getFullNameField().setText(joinText());
            viewFormOneFields();
        } else {
            JOptionPane.showMessageDialog(
                    PanelThreeFields,
                    "Заполните поля",
                    "Некорректный ввод,  только буквы и - ",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private String joinText() {
        String first = firstNameField.getText();
        String sur = surNameField.getText();
        String mid = midNameField.getText();
        return sur + " " + first + (mid.isEmpty() ? "" : (" " + mid));
    }
}
