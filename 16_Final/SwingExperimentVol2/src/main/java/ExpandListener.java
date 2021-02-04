import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpandListener implements ActionListener {

    MainForm formOneFields = ViewForms.getFormOneFields();
    MainForm formThreeFields = ViewForms.getFormThreeFields();
    MainFrame mainFrame = ViewForms.getMainFrame();

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = formOneFields.getTextFields()[0].getText();
        String[] splitName = name.split(" ");
        if (splitName.length >= 2 && splitName.length <= 3 &&
                name.matches("^[\\p{L}\\s.’\\-]+$")) {
            splitText();
            mainFrame.setContent(formThreeFields);
        } else {
            JOptionPane.showMessageDialog(
                    formOneFields.getContent(),
                    "Введите ФИО через пробел, только буквы и - ",
                    "Некорректный ввод",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void splitText() {
        String[] name = formOneFields.getTextFields()[0].getText().split(" ");
        formThreeFields.getTextFields()[0].setText(name[0]);
        formThreeFields.getTextFields()[1].setText(name[1]);
        formThreeFields.getTextFields()[2].setText(name.length == 3 ? name[2] : "");
    }
}
