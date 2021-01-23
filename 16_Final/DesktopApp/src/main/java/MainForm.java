import javax.swing.*;

public class MainForm {
    private JPanel mainPanel;
    private JLabel firstNameLabel;
    private JTextField surNameField;
    private JTextField firstNameField;
    private JLabel surNameLabel;
    private JTextField midNameField;
    private JLabel midNameLabel;
    private JButton collapseButton;
    private JTextField fullNameField;
    private JLabel fullNameLabel;
    private boolean isCollapsed;

    public MainForm() {
        isCollapsed = false;
        collapseButton.addActionListener(e -> btnPressed());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void invertGUI() {
        firstNameLabel.setVisible(isCollapsed);
        firstNameField.setVisible(isCollapsed);

        surNameLabel.setVisible(isCollapsed);
        surNameField.setVisible(isCollapsed);

        midNameLabel.setVisible(isCollapsed);
        midNameField.setVisible(isCollapsed);

        isCollapsed = !isCollapsed;

        collapseButton.setText(isCollapsed ? "Expand" : "Collapse");
        fullNameLabel.setVisible(isCollapsed);
        fullNameField.setVisible(isCollapsed);

        if (isCollapsed) {
            joinText();
        } else {
            splitText();
        }

    }

    private void joinText() {
        String first = firstNameField.getText();
        String sur = surNameField.getText();
        String mid = midNameField.getText();

        fullNameField.setText(sur + " " + first + (mid.isEmpty() ? "" : (" " + mid)));
    }

    private void splitText() {
        String[] name = fullNameField.getText().split(" ");

        surNameField.setText(name[0]);
        firstNameField.setText(name[1]);
        midNameField.setText(name.length == 3 ? name[2]: "");
    }

    private void btnPressed() {
        if (!isCollapsed) {
            if (!firstNameField.getText().isEmpty() && !surNameField.getText().isEmpty() &&
                    (firstNameField.getText() + surNameField.getText() + midNameField.getText()).matches("^[\\p{L}\\p{Blank}.’\\-]+")) {
                invertGUI();
            } else {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Заполните поля",
                        "Некорректный ввод,  только буквы и - ",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } else {
            String name = fullNameField.getText();
            String[] splitName = name.split(" ");
            if (splitName.length >= 2 && splitName.length <= 3 &&
                    name.matches("^[\\p{L}\\s.’\\-]+$")) {
                invertGUI();
            } else {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "Введите ФИО через пробел, только буквы и - ",
                        "Некорректный ввод",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }
}
