import javax.swing.*;

public class FormView {
    private final JFrame frame;
    private final FormThreeFields formThreeFields;
    private final FormOneFields formOneFields;

    public FormView() {
        this.frame = MainFrame.getInstance();
        this.formThreeFields = new FormThreeFields();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.formThreeFields.getCollapseButton().addActionListener(e -> btnPressedThreeFields());
        this.formOneFields = new FormOneFields();
        this.formOneFields.getExpandButton().addActionListener(e -> btnPressedOneFields());
        frame.setContentPane(formThreeFields.getPanelThreeFields());
        frame.setSize(formThreeFields.getPanelThreeFields().getMinimumSize());
    }

    private void viewFormOneFields() {
        frame.setContentPane(formOneFields.getPanelFormFiled());
        frame.setSize(formOneFields.getPanelFormFiled().getMinimumSize());
        frame.repaint();
    }

    private void viewFormThreeFields() {
        frame.setContentPane(formThreeFields.getPanelThreeFields());
        frame.setSize(formThreeFields.getPanelThreeFields().getMinimumSize());
        frame.repaint();
    }

    private void btnPressedThreeFields() {
        if (!formThreeFields.getFirstNameField().getText().isEmpty() && !formThreeFields.getSurNameField().getText().isEmpty() &&
                (formThreeFields.getFirstNameField().getText() + formThreeFields.getSurNameField().getText() + formThreeFields.getMidNameField().getText()).matches("^[\\p{L}\\p{Blank}.’\\-]+")) {
            formOneFields.getFullNameField().setText(joinText());
            viewFormOneFields();
        } else {
            JOptionPane.showMessageDialog(
                    formThreeFields.getPanelThreeFields(),
                    "Заполните поля",
                    "Некорректный ввод,  только буквы и - ",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private String joinText() {
        String first = formThreeFields.getFirstNameField().getText();
        String sur = formThreeFields.getSurNameField().getText();
        String mid = formThreeFields.getMidNameField().getText();
        return sur + " " + first + (mid.isEmpty() ? "" : (" " + mid));
    }

    private void btnPressedOneFields() {
        String name = formOneFields.getFullNameField().getText();
        String[] splitName = name.split(" ");
        if (splitName.length >= 2 && splitName.length <= 3 &&
                name.matches("^[\\p{L}\\s.’\\-]+$")) {
            splitText();
            viewFormThreeFields();
        } else {
            JOptionPane.showMessageDialog(
                    formOneFields.getPanelFormFiled(),
                    "Введите ФИО через пробел, только буквы и - ",
                    "Некорректный ввод",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void splitText() {
        String[] name = formOneFields.getFullNameField().getText().split(" ");
        formThreeFields.getSurNameField().setText(name[0]);
        formThreeFields.getSurNameField().setText(name[1]);
        formThreeFields.getMidNameField().setText(name.length == 3 ? name[2] : "");
    }
}
