import javax.swing.*;

public class FormOneFields {
    private JPanel PanelFormFiled;
    private JTextField fullNameField;
    private JButton expandButton;
    private JFrame frame;
    private FormThreeFields formThreeFields;

    public FormOneFields() {
    }

    public FormOneFields(JFrame frame) {
        this.frame = frame;
    }

    public FormOneFields(FormThreeFields formThreeFields, JFrame frame) {
        this.formThreeFields = formThreeFields;
        this.frame = frame;
    }

    public void setFormThreeFields(FormThreeFields formThreeFields) {
        this.formThreeFields = formThreeFields;
        expandButton.addActionListener(e -> btnPressed(this.formThreeFields));
    }

    public JPanel getPanelFormFiled() {
        return PanelFormFiled;
    }

    public JTextField getFullNameField() {
        return fullNameField;
    }

    public JButton getExpandButton() {
        return expandButton;
    }

    public void viewFormThreeFields() {
        frame.setContentPane(formThreeFields.getPanelThreeFields());
        frame.setSize(formThreeFields.getPanelThreeFields().getMinimumSize());
        frame.repaint();
    }

    private void btnPressed(FormThreeFields formThreeFields) {
        String name = fullNameField.getText();
        String[] splitName = name.split(" ");
        if (splitName.length >= 2 && splitName.length <= 3 &&
                name.matches("^[\\p{L}\\s.’\\-]+$")) {
            splitText(formThreeFields);
            viewFormThreeFields();
        } else {
            JOptionPane.showMessageDialog(
                    PanelFormFiled,
                    "Введите ФИО через пробел, только буквы и - ",
                    "Некорректный ввод",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void splitText(FormThreeFields formThreeFields) {
        String[] name = fullNameField.getText().split(" ");
        formThreeFields.getFirstNameField().setText(name[0]);
        formThreeFields.getSurNameField().setText(name[1]);
        formThreeFields.getMidNameField().setText(name.length == 3 ? name[2] : "");
    }
}
