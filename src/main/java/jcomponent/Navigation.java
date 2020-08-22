package jcomponent;

import jlistenner.NavigationMouseListener;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Navigation extends JLabel {
    private JPanel panel;

    public Navigation() {
        this("");
    }

    public Navigation(String name) {
        this(name, new JPanel(new GridLayout(10, 1)));
    }

    public Navigation(String name, JPanel panel) {
        super(name);
        this.panel = panel;
    }

    public void addNavMouseListener(NavigationBar navBar) {
        addMouseListener(new NavigationMouseListener(navBar, this));
    }

    public void addToPanel(JComponent jComponent) {
        panel.add(jComponent);
    }
}
