package form;

import jlistenner.ResetDataPanelListener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavPanal extends JPanel{
    private JPanel rootPanel;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;

    public NavPanal(JPanel dataPanel) {
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyDialog dialog = new MyDialog();
                dialog.pack();
                dialog.setVisible(true);
            }
        });

        b2.addMouseListener(new ResetDataPanelListener(dataPanel, OrderEdit.INSTANCE.getRootPanel()));

        //TODO
        b3.addMouseListener(new ResetDataPanelListener(dataPanel, OrderAudit.createJPanel()));
        b3.addMouseListener(new ResetDataPanelListener(dataPanel, new OrderAudit().getRootPanel()));

        b4.addMouseListener(new ResetDataPanelListener(dataPanel, OrderCatalog.INSTANCE.getRootPanel()));
    }

    private void createUIComponents() {
        rootPanel = this;
    }
}
