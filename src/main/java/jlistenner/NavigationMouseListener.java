package jlistenner;

import jcomponent.Navigation;
import jcomponent.NavigationBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavigationMouseListener extends MouseAdapter {
    private NavigationBar navBar;
    private Navigation nav;

    public NavigationMouseListener(NavigationBar navBar, Navigation nav) {
        this.navBar = navBar;
        this.nav = nav;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        navBar.resetNavPanel(nav);
        navBar.repaint();
        navBar.revalidate();
    }
}
