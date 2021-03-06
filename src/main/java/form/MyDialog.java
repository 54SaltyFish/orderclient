package form;

import jcomponent.DateChooserPanel;

import javax.swing.*;
import java.awt.event.*;

public class MyDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label1;
    private JLabel label2;
    private JTextField taskNameText;

    public MyDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String taskName = taskNameText.getText();

        OrderEdit orderEdit = new OrderEdit();
        orderEdit.getTaskNameText().setText(taskName);

        MainUI.UI.resetDataPanel(orderEdit.getRootPanel());

        dispose();
    }

    private void onCancel() {
        //TODO add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        MyDialog dialog = new MyDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {

        DateChooserPanel ser1 = DateChooserPanel.getInstance("yyyy-MM-dd");
        label1 = new JLabel("aaa");
        label1.setBounds(10, 50, 200, 30);
        ser1.register(label1);

        DateChooserPanel ser2 = DateChooserPanel.getInstance("yyyy-MM-dd");
        label2 = new JLabel("bbb");
        label2.setBounds(10, 50, 200, 30);
        ser2.register(label2);

        taskNameText = new JTextField();
    }
}
