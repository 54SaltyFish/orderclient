package form;

import lombok.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Data
public class OrderAudit {
    private JPanel data;
    private JTable table1;
    private JLabel label;

    public static void main(String[] args) {
        JFrame frame = new JFrame("OrderAudit");
        frame.setContentPane(new OrderAudit().data);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static JPanel createJPanel() {
        return new OrderAudit().data;
    }

    private void createUIComponents() {
        table1 = new JTable();
        table1.setModel(table());
    }

    Object[] heads;
    Object[][] datas;
    DefaultTableModel table() {
        heads = new String[] {
                "序号", "操作任务", "操作日期", "编制日期", "编制人"
        };

        datas = new String[5][5];
        for (int i = 0; i < 5; i++) {
            datas[i][0] = i + 1 + "";
            datas[i][1] = "恢复华英站256-" + (6 + i) + "隔离开关";
            datas[i][2] = "2020-5-" + (15 + i);
            datas[i][3] = "2020-5-" + (15 + i);
            datas[i][4] = "张三";
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
