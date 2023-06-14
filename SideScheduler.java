import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class SideScheduler extends JFrame { // frame
    CardLayout card;

    // all screens
    private MenuPanel menuPanel;

    private String currPanel;

    // game dimensions
    private static final int WIDTH = 1280, HEIGHT = 720;

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public SideScheduler() throws IOException, FontFormatException {
        super("SideScheduler");

        // TODO: get last panel from close
        currPanel = "menu";

        // adding panels to card layout
        card = new CardLayout();
        setLayout(card);

        menuPanel = new MenuPanel(this);
        add("menu", menuPanel);

        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // set X to exit
        setVisible(true); // make panel visible
    }

    public void setCurrPanel(String panel) {
        currPanel = panel;
    }

    public String getCurrPanel() {
        return currPanel;
    }

    public void start() {
        // display current panel
        card.show(getContentPane(), currPanel);
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new SideScheduler();
    }
}

