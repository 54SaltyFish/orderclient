package form;

import lombok.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Data
public class OrderEdit extends JPanel{
    public static OrderEdit INSTANCE = new OrderEdit();
    private JPanel rootPanel;
    private JLabel l11;
    private JLabel l12;
    private JLabel l13;
    private JLabel l14;
    private JLabel l15;
    private JLabel l16;
    private JLabel l18;
    private JLabel l21;
    private JLabel l22;
    private JLabel l23;
    private JLabel l24;
    private JLabel l25;
    private JTextField taskNameText;
    private JLabel l27;
    private JLabel l17;
    private JPanel dataEditTable;

    private void createUIComponents() {
        taskNameText = new JTextField();
        dataEditTable = EditTable.createJPanel();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("OrderEdit");
        frame.setContentPane(new OrderEdit().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
