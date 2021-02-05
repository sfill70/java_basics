import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpandListener implements ActionListener {

    MainFrame mainFrame;
    MainForm formOneFields;
    MainForm formThreeFields;

    public ExpandListener(MainFrame mainFrame, MainForm formOneFields, MainForm formThreeFields) {
        this.mainFrame = mainFrame;
        this.formOneFields = formOneFields;
        this.formThreeFields = formThreeFields;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = formOneFields.getTextFields()[0].getText();
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
        String[] name = formOneFields.getTextFields()[0].getText().split(" ");
        String tmp = name[0];
        name[0] = name[1];
        name[1] = tmp;
        name[2] = name.length == 3 ? name[2] : "";
        formThreeFields.setTextField(name);
        mainFrame.setContent(formThreeFields);
    }

    private void showMessageWrongInput() {
        JOptionPane.showMessageDialog(
                formOneFields.getContent(),
                "Введите ФИО через пробел, только буквы и - ",
                "Некорректный ввод",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
