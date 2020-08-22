package form;

import utils.ClipboardTools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EditTable {
    private JPanel rootPanel;
    private JTable table;
    private JPopupMenu tablePopupMenu;

    public static JPanel createJPanel() {
        return new EditTable().rootPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DataTable");
        frame.setContentPane(new EditTable().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    createTablePopupMenu().show(table, e.getX(), e.getY());
            }
        });

        table.setModel(table());
    }

    private JPopupMenu createTablePopupMenu() {
        tablePopupMenu = new JPopupMenu();

        //"添加", "删除", "置为空白行", "上移一行", "下移一行", "复制", "粘贴"
        tablePopupMenu.add(createInsertMenuItem());//添加
        tablePopupMenu.add(createRemoveMenuItem());//删除
        tablePopupMenu.add(createMenuItem("置为空白行", tableModel -> {

        }));//置为空白行
        tablePopupMenu.addSeparator();

        tablePopupMenu.add(createMoveUpRowsMenuItem());//上移一行
        tablePopupMenu.add(createMoveDownRowsMenuItem());//下移一行
        tablePopupMenu.addSeparator();

        tablePopupMenu.add(createCopyRowsMenuItem());//复制
        tablePopupMenu.add(createInsertCopyRowsMenuItem());//插入复制行

        return tablePopupMenu;
    }

    private JMenuItem createInsertMenuItem() {
        return createMenuItem("插入", tableModel -> {
            int insertRow = table.getSelectedRow();
            if (insertRow == -1)
                insertRow = tableModel.getRowCount();
            tableModel.insertRow(insertRow, new Vector<Object>(tableModel
                    .getColumnCount()));
        });
    }

    private JMenuItem createRemoveMenuItem() {
        return createMenuItem("删除", tableModel -> {
            int selectedRow = table.getSelectedRow();// 获得选中行的索引
            if (selectedRow != -1) // 存在选中行
                tableModel.removeRow(selectedRow); // 删除行
        });
    }

    private JMenuItem createMoveUpRowsMenuItem() {
        return createMenuItem("上移一行", tableModel -> {
            int[] selectedRows = table.getSelectedRows();
            table.clearSelection();
            IntStream.of(selectedRows).sorted().distinct()
                    .reduce(0, (maxNotMovedRow, row) -> {
                        if (row <= maxNotMovedRow) {
                            table.addRowSelectionInterval(row, row);
                            return ++maxNotMovedRow;
                        } else {
                            int lastRow = row - 1;
                            tableModel.moveRow(row, row, lastRow);
                            table.addRowSelectionInterval(lastRow, lastRow);
                            return maxNotMovedRow;
                        }
                    });
        });
    }

    private JMenuItem createMoveDownRowsMenuItem() {
        return createMenuItem("下移一行", tableModel -> {
            int[] selectedRows = table.getSelectedRows();
            int rowCount = table.getRowCount();
            table.clearSelection();
            IntStream.of(selectedRows).distinct().boxed().sorted(Comparator.reverseOrder())
                    .reduce(rowCount - 1, (minNotMovedRow, row) -> {
                        System.out.println(minNotMovedRow + "" + row);
                        if (row >= minNotMovedRow) {
                            table.addRowSelectionInterval(row, row);
                            return --minNotMovedRow;
                        } else {
                            int nextRow = row + 1;
                            tableModel.moveRow(row, row, nextRow);
                            table.addRowSelectionInterval(nextRow, nextRow);
                            return minNotMovedRow;
                        }
                    });
        });
    }

    private JMenuItem createCopyRowsMenuItem() {
        return createMenuItem("复制", tableModel -> {
            int[] selectedRows = table.getSelectedRows();
            List<Vector> rowsData = IntStream.of(selectedRows)
                    .mapToObj(row -> (Vector)tableModel.getDataVector().get(row))
                    .map(rowData -> (Vector)rowData.clone())
                    .collect(Collectors.toList());
            ClipboardTools.copy(rowsData);
        });
    }

    private JMenuItem createInsertCopyRowsMenuItem() {
        return createMenuItem("插入复制行", tableModel -> {
            List<Vector> rowsData = ClipboardTools.paste();
            int selectedRow = table.getSelectedRow();
            Collections.reverse(rowsData);
            rowsData.stream()
                    .forEachOrdered(rowData -> tableModel.insertRow(selectedRow, rowData));
        });
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

    private int lastRow(int row) {
        return row - 1 < 0 ? 0 : row - 1;
    }

    private int nextRow(int row, int rowCount) {
        return row + 1 > rowCount ? rowCount : row + 1;
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
            datas[i][3] = "第" + (i+1) + "步";
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
