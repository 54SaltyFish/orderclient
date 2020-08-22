package form;

import lombok.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

@Data
public class OrderCatalog {
    public static OrderCatalog INSTANCE = new OrderCatalog();
    private JPanel rootPanel;
    private JTable table;
    private JLabel label1;
    private JLabel label2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("OrderCatalog");
        frame.setContentPane(new OrderCatalog().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JLabel label3;

    public static JPanel createJPanel() {
        return new OrderCatalog().rootPanel;
    }

    private void createUIComponents() {
        table = new JTable();

        JPopupMenu tablePopupMenu = new JPopupMenu();

        //"添加", "删除", "置为空白行", "上移一行", "下移一行", "复制", "粘贴"
        tablePopupMenu.add(createMenuItem("创建", table -> {
            MyDialog dialog = new MyDialog();
            dialog.pack();
            dialog.setVisible(true);
        }));//创建
        tablePopupMenu.add(createMenuItem("逆向创建", table -> {}));//逆向创建
        tablePopupMenu.addSeparator();

        tablePopupMenu.add(createMenuItem("打印", table -> {}));//打印
        tablePopupMenu.add(createMenuItem("导出", table -> {}));//导出

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    tablePopupMenu.show(table, e.getX(), e.getY());
            }
        });

        table.setModel(table());

    }

    private JMenuItem createMenuItem(String name, Consumer<DefaultTableModel> consumer) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                // TODO
//                if (e.getButton() == MouseEvent.BUTTON1)
                consumer.accept(tableModel);
            }
        });
        return menuItem;
    }

    Object[] heads;
    Object[][] datas;
    DefaultTableModel table() {
        heads = new String[] {
                "序号", "计划操作日期", "操作任务"
        };

        datas = new String[5][3];
        for (int i = 0; i < 5; i++) {
            datas[i][0] = i + 1 + "";
            datas[i][1] = "2020-5-" + (15 + i);
            datas[i][2] = "恢复华英站256-" + (6 + i) + "隔离开关";
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
