package form;

import lombok.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Data
public class OrderEdit extends JPanel{
    public static OrderEdit INSTANCE = new OrderEdit();
    private JPanel data;
    private JTable table1;
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
    private JLabel l26;
    private JLabel l27;
    private JLabel l17;
    private JPanel dataEditTable;

    private void createUIComponents() {

        table1 = new JTable();
        table1.setModel(table());
        dataEditTable = EditTable.createJPanel();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("OrderEdit");
        frame.setContentPane(new OrderEdit().data);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Object[] heads;
    Object[][] datas;
    DefaultTableModel table() {
        heads = new String[] {
                "序号", "操作单位", "项号", "操作命令内容", "类型", "状态", "注意事项"
        };

        datas = new String[5][7];
        for (int i = 0; i < 5; i++) {
            datas[i][0] = i + 1 + "";
            datas[i][2] = "" + i;
            datas[i][3] = "第" + (i+1) + "布";
            datas[i][4] = "普通命令";
            datas[i][5] = "待下令";
        }

        DefaultTableModel tableModel=new DefaultTableModel(datas, heads){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        return tableModel;
    }
}
