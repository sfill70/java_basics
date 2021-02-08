import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormThreeFields implements MainForm {
    private JPanel panelThreeFields;
    private JTextField firstNameField;
    private JTextField surNameField;
    private JTextField midNameField;
    private JButton collapseButton;
    MainFrame mainFrame;
    MainForm formOneFields;

    public FormThreeFields() {
    }

    public FormThreeFields(MainForm formOneFields) {
        this.formOneFields = formOneFields;
        getButton().addActionListener(e -> btnPressed());
    }

    public FormThreeFields(MainFrame mainFrame, MainForm formOneFields) {
        this.mainFrame = mainFrame;
        this.formOneFields = formOneFields;
        getButton().addActionListener(e -> btnPressed());
    }

    public void setMainForm(MainForm formThreeFields) {
        this.formOneFields = formOneFields;
        getButton().addActionListener(e -> btnPressed());
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

    public void btnPressed() {
        if (!firstNameField.getText().isEmpty() && !surNameField.getText().isEmpty() &&
                isName()) {
            joinText();
        } else {
            showMessageWrongInput();
        }
    }

    private boolean isName() {
        return (firstNameField.getText() + surNameField.getText() +
                midNameField.getText()).matches("^[\\p{L}\\p{Blank}.’\\-]+");
    }

    private void showMessageWrongInput() {
        JOptionPane.showMessageDialog(
                getContent(),
                "Введите ФИО через пробел, только буквы и - ",
                "Некорректный ввод",
                JOptionPane.WARNING_MESSAGE
        );
    }

    void joinText() {
        String first = firstNameField.getText();
        String sur = surNameField.getText();
        String mid = midNameField.getText();
        formOneFields.setTextField(new String[]{sur + " " + first + (mid.isEmpty() ? "" : (" " + mid))});
        MainFrame.getInstance().setContent(formOneFields);
    }
}
