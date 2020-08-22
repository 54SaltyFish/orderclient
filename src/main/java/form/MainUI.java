package form;

import jcomponent.Navigation;
import jcomponent.NavigationBar;
import jcomponent.TablePopupMenu;
import jlistenner.ResetDataPanelListener;
import lombok.Data;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Data
public class MainUI {
    public static final JFrame frame = new JFrame("MyUI");
    public static final MainUI UI = new MainUI();
    private JPanel rootPanel;
    private JToolBar toolBar;
    private JButton file;
    private JButton edit;
    private JButton help;
    private JPanel dataPanel;
    private JToolBar navBar;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;

    public static void main(String[] args) {
//        JFrame frame = new JFrame("MyUI");
        frame.setContentPane(UI.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(createMenuBar());// 将菜单栏对象添加到窗体的菜单栏中

        frame.pack();
        frame.setVisible(true);
    }

    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();// 创建菜单栏对象

        JMenu menu = new JMenu("文件");// 创建菜单对象
        menuBar.add(menu);// 将菜单对象添加到菜单栏对象中
        JMenuItem menuItem = new JMenuItem("功能1");// 创建菜单项对象
//        menuItem.addActionListener(new ItemListener());// 为菜单项添加事件监听器
        menu.add(menuItem);// 将菜单项对象添加到菜单对象中
        JMenu sonMenu = new JMenu("功能2");// 创建菜单的子菜单对象
        menu.add(sonMenu);// 将子菜单对象添加到上级菜单对象中
        JMenuItem sonMenuItem = new JMenuItem("功能3");// 创建子菜单的菜单项对象
//        sonMenuItem.addActionListener(new ItemListener());// 为菜单项添加事件监听器
        sonMenu.add(sonMenuItem);// 将子菜单的菜单项对象添加到子菜单对象中

        return menuBar;
    }

    private void createUIComponents() {
        NavigationBar bar = new NavigationBar("zzz");
//        Navigation nav2 = new Navigation("bbb", new JPanel(new GridLayout(10, 1)));
//        nav2.addToPanel(new JButton("b1"));
//        nav2.addToPanel(new JButton("b2"));
//        Navigation nav3 = new Navigation("ccc", new JPanel(new GridLayout(10, 1)));
//        nav3.addToPanel(new JButton("c1"));
//        nav3.addToPanel(new JButton("c2"));

        dataPanel = new JPanel();
        Navigation nav1 = new Navigation();
        nav1.addNavMouseListener(bar);
        nav1.setPanel(new NavPanal1(new ResetDataPanelListener(dataPanel, OrderCatalog.INSTANCE.getRootPanel())));

        Navigation nav2 = new Navigation();
        nav2.addNavMouseListener(bar);
        nav2.setPanel(new NavPanal1(new ResetDataPanelListener(dataPanel, OrderAudit.createJPanel())));
        Navigation nav3 = new Navigation();
        nav3.addNavMouseListener(bar);

        this.navBar = bar;
        this.label1 = nav1;
        this.label2 = nav2;
        this.label3 = nav3;

//        dataPanel.add(Table3.DATA3.getRootPanel());
        dataPanel.add(OrderCatalog.INSTANCE.getRootPanel());
        OrderCatalog.INSTANCE.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    new TablePopupMenu().showPopupMenu(OrderCatalog.INSTANCE.getTable(), e.getX(), e.getY());
            }
        });
//        dataPanel.add(BorderLayout.CENTER, new Tabel1().getRootPanel());
//        dataPanel = Tabel1.INSTANCE.getRootPanel();
    }

    public void resetDataPanel(JPanel tablePanel) {
        if (dataPanel.getComponents()[0] == tablePanel) {
            return;
        }
        dataPanel.removeAll();
        dataPanel.repaint();
        dataPanel.add(tablePanel);
        dataPanel.revalidate();
    }

//    /**
//     * 显示一个自定义的对话框
//     *
//     * @param owner 对话框的拥有者
//     * @param parentComponent 对话框的父级组件
//     */
//    public static void showCustomDialog(Frame owner, Component parentComponent) {
//        // 创建一个模态对话框
//        final JDialog dialog = new JDialog(owner, "提示", true);
//        // 设置对话框的宽高
//        dialog.setSize(250, 150);
//        // 设置对话框大小不可改变
//        dialog.setResizable(false);
//        // 设置对话框相对显示的位置
//        dialog.setLocationRelativeTo(parentComponent);
//
//        // 创建一个标签显示消息内容
//        JLabel messageLabel = new JLabel("对话框消息内容");
//
//        // 创建一个按钮用于关闭对话框
//        JButton okBtn = new JButton("确定");
//        okBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // 关闭对话框
//                dialog.dispose();
//            }
//        });
//
//        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
//        JPanel panel = new JPanel();
//
//        // 添加组件到面板
//        panel.add(messageLabel);
//        panel.add(okBtn);
//
//        // 设置对话框的内容面板
//        dialog.setContentPane(panel);
//        // 显示对话框
//        dialog.setVisible(true);
//    }

}
