import javax.swing.*;

public class ViewForms {
    private JFrame frame;
    private FormThreeFields formThreeFields;
    private FormOneFields formOneFields;

    public ViewForms() {
        this.frame = MainFrame.getInstance();
        this.formOneFields = new FormOneFields(frame);
        this.formThreeFields = new FormThreeFields(formOneFields, frame);
        formOneFields.setFormThreeFields(formThreeFields);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(formThreeFields.getPanelThreeFields());
        frame.setSize(formThreeFields.getPanelThreeFields().getMinimumSize());
        frame.repaint();
    }
}
