import javax.swing.*;

public class ViewForms {
    private static MainFrame mainFrame;
    private static MainForm formThreeFields = new FormThreeFields();
    private static MainForm formOneFields = new FormOneFields();

    public ViewForms() {
        mainFrame = MainFrame.getInstance();
        formThreeFields = new FormThreeFields();
        formOneFields = new FormOneFields();
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    public static MainForm getFormThreeFields() {
        return formThreeFields;
    }

    public static MainForm getFormOneFields() {
        return formOneFields;
    }

    public void run() {
        mainFrame.initialization();
        formThreeFields.getButton().addActionListener(new CollapseListener());
        formOneFields.getButton().addActionListener(new ExpandListener());
        viewFormThreeFields();
    }

    private void viewFormOneFields() {
        mainFrame.setContent(formOneFields);
    }

    private void viewFormThreeFields() {
        mainFrame.setContent(formThreeFields);
    }
}
