import javax.swing.*;

public class FormView {
    private final MainFrame mainFrame;
    private MainForm formThreeFields = new FormThreeFields();
    ;
    private MainForm formOneFields = new FormOneFields();

    public FormView() {
        this.mainFrame = MainFrame.getInstance();
        formThreeFields = new FormThreeFields();
        formOneFields = new FormOneFields();
    }

    public void run() {
        mainFrame.initialization();
        formThreeFields.getButton().addActionListener(e -> btnPressedThreeFields());
        formOneFields.getButton().addActionListener(e -> btnPressedOneFields());
        viewFormThreeFields();
    }

    private void viewFormOneFields() {
        mainFrame.setContent(formOneFields);
    }

    private void viewFormThreeFields() {
        mainFrame.setContent(formThreeFields);
    }

    private void btnPressedThreeFields() {
        if (!formThreeFields.getTextFields()[0].getText().isEmpty() && !formThreeFields.getTextFields()[1].getText().isEmpty() &&
                (formThreeFields.getTextFields()[0].getText() + formThreeFields.getTextFields()[1].getText() + formThreeFields.getTextFields()[2].getText()).matches("^[\\p{L}\\p{Blank}.’\\-]+")) {
            formOneFields.getTextFields()[0].setText(joinText());
            viewFormOneFields();
        } else {
            JOptionPane.showMessageDialog(
                    formThreeFields.getContent(),
                    "Заполните поля",
                    "Некорректный ввод,  только буквы и - ",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private String joinText() {
        String first = formThreeFields.getTextFields()[0].getText();
        String sur = formThreeFields.getTextFields()[1].getText();
        String mid = formThreeFields.getTextFields()[2].getText();
        return sur + " " + first + (mid.isEmpty() ? "" : (" " + mid));
    }

    private void btnPressedOneFields() {
        String name = formOneFields.getTextFields()[0].getText();
        String[] splitName = name.split(" ");
        if (splitName.length >= 2 && splitName.length <= 3 &&
                name.matches("^[\\p{L}\\s.’\\-]+$")) {
            splitText();
            viewFormThreeFields();
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
