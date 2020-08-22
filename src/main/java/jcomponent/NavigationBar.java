package jcomponent;

import javax.swing.*;

public class NavigationBar extends JToolBar {
    private JPanel panel;

    public NavigationBar() {
        this("");
    }

    public NavigationBar(String name) {
        super(SwingConstants.VERTICAL);
        this.setFloatable(false);
    }

    public void resetNavPanel(Navigation navigation) {
        if (panel == navigation.getPanel()) {
            return;
        }
        if (panel != null)
            this.remove(panel);
        int index = this.getComponentIndex(navigation);
        panel = navigation.getPanel();
        this.add(panel, index + 1);
    }

}
