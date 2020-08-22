package jcomponent;

import javax.swing.*;

public class TablePopupMenu extends JPopupMenu{
    private String[] menuItemNames = {
            "添加", "删除", "置为空白行", "上移一行", "下移一行", "复制", "粘贴"
    };

    public TablePopupMenu() {
        for (int i = 0; i < menuItemNames.length; i++) {
            JMenuItem menuItem = new JMenuItem(menuItemNames[i]);
            this.add(menuItem);
        }
        this.addSeparator();       // 添加一条分隔符

        // 在指定位置显示弹出菜单
//        popupMenu.show(invoker, x, y);
    }

    public void showPopupMenu(JComponent invoker, int x, int y) {
        this.show(invoker, x, y);
    }

}
