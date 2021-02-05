import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollapseListener implements ActionListener {

    MainFrame mainFrame;
    MainForm formOneFields;
    MainForm formThreeFields;

    public CollapseListener(MainFrame mainFrame, MainForm formOneFields, MainForm formThreeFields) {
        this.mainFrame = mainFrame;
        this.formOneFields = formOneFields;
        this.formThreeFields = formThreeFields;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!formThreeFields.getTextFields()[0].getText().isEmpty() && !formThreeFields.getTextFields()[1].getText().isEmpty() &&
                isName()) {
            joinText();
        } else {
            showMessageWrongInput();
        }
    }

    private boolean isName() {
        return (formThreeFields.getTextFields()[0].getText() + formThreeFields.getTextFields()[1].getText() +
                formThreeFields.getTextFields()[2].getText()).matches("^[\\p{L}\\p{Blank}.’\\-]+");
    }

    private void showMessageWrongInput() {
        JOptionPane.showMessageDialog(
                formOneFields.getContent(),
                "Введите ФИО через пробел, только буквы и - ",
                "Некорректный ввод",
                JOptionPane.WARNING_MESSAGE
        );
    }

    void joinText() {
        String first = formThreeFields.getTextFields()[0].getText();
        String sur = formThreeFields.getTextFields()[1].getText();
        String mid = formThreeFields.getTextFields()[2].getText();
        formOneFields.getTextFields()[0].setText(sur + " " + first + (mid.isEmpty() ? "" : (" " + mid)));
        mainFrame.setContent(formOneFields);
    }
}
