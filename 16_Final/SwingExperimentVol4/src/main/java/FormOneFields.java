import javax.swing.*;
import java.awt.event.ActionListener;

public class FormOneFields implements MainForm {
    private JPanel panelFormFiled;
    private JTextField fullNameField;
    private JButton expandButton;
    MainFrame mainFrame;
    MainForm formThreeFields;

    public FormOneFields() {
    }

    public FormOneFields(MainForm formThreeFields) {
        this.formThreeFields = formThreeFields;
    }

    public FormOneFields(MainFrame mainFrame, MainForm formThreeFields) {
        this.mainFrame = mainFrame;
        this.formThreeFields = formThreeFields;
    }

    public FormOneFields(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void setMainForm(MainForm formThreeFields) {
        this.formThreeFields = formThreeFields;
        getButton().addActionListener(e -> btnPressed());
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
        return new JTextField[]{fullNameField};
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

    public void btnPressed() {
        String name = fullNameField.getText();
        String[] splitName = name.split(" ");
        if (!(splitName.length >= 2 && splitName.length <= 3 &&
                isName(name))) {
            showMessageWrongInput();
            return;
        }
        splitText();
    }

    private boolean isName(String name) {
        return name.matches("^[\\p{L}\\s.’\\-]+$");
    }

    private void splitText() {
        String[] name = fullNameField.getText().split(" ");
        String tmp = name[0];
        name[0] = name[1];
        name[1] = tmp;
        name[2] = name.length == 3 ? name[2] : "";
        formThreeFields.setTextField(name);
        MainFrame.getInstance().setContent(formThreeFields);
    }

    private void showMessageWrongInput() {
        JOptionPane.showMessageDialog(
                getContent(),
                "Введите ФИО через пробел, только буквы и - ",
                "Некорректный ввод",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
