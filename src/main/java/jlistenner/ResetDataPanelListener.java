package jlistenner;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResetDataPanelListener extends MouseAdapter {
    private JPanel dataPanel;
    private JPanel tablePanel;

    public ResetDataPanelListener(JPanel dataPanel, JPanel tablePanel) {
        this.dataPanel = dataPanel;
        this.tablePanel = tablePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("click " + tablePanel.getName());
        if (dataPanel.getComponents()[0] == tablePanel) {
            return;
        }
        dataPanel.removeAll();
        dataPanel.repaint();
        dataPanel.add(tablePanel);
        dataPanel.revalidate();
    }

}
