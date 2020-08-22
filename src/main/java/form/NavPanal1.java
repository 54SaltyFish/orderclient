package form;

import jlistenner.ResetDataPanelListener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavPanal1 extends JPanel{
    private JPanel nav1;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;

    public NavPanal1(ResetDataPanelListener listener) {
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyDialog dialog = new MyDialog();
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        b4.addMouseListener(listener);
    }

    private void createUIComponents() {
        nav1 = this;
    }
}
