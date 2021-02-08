public class ViewForms {
    private final MainFrame mainFrame;
    private final MainForm formThreeFields;
    private final MainForm formOneFields;

    public ViewForms() {
        this.mainFrame = MainFrame.getInstance();
        this.formOneFields = new FormOneFields();
        this.formThreeFields = new FormThreeFields(formOneFields);
        formOneFields.setMainForm(formThreeFields);

    }


    public void run() {
        mainFrame.initialization();
        viewFormThreeFields();
    }

    private void viewFormOneFields() {
        mainFrame.setContent(formOneFields);
    }

    private void viewFormThreeFields() {
        mainFrame.setContent(formThreeFields);
    }
}
