import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MenuPanel extends JPanel implements KeyListener, ActionListener, MouseListener {
    Timer timer;
    private SideScheduler sideScheduler;
    private static boolean[] keys;

    private int WIDTH, HEIGHT;
    private int tarX, tarY;

    //variables
    private static boolean clicked;

    //menu
    private Menu menu;

    public MenuPanel(SideScheduler a) throws IOException, FontFormatException {
        sideScheduler = a;
        keys = new boolean[KeyEvent.KEY_LAST+1];
        setPreferredSize(new Dimension(sideScheduler.getWIDTH(), sideScheduler.getHEIGHT()));
        setFocusable(true);
        requestFocus();
        // adding listener for events
        addMouseListener(this);
        addKeyListener(this);

        WIDTH = sideScheduler.getWIDTH();
        HEIGHT = sideScheduler.getHEIGHT();

        menu = new Menu();

        timer = new Timer(25, this); // manages frames
        timer.start();
    }

    public static void setKey(int key, boolean pressed) {
        keys[key] = pressed;
    }

    public SideScheduler getSideScheduler() {
        return sideScheduler;
    }

    // MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {;}
    @Override
    public void mouseExited(MouseEvent e) {;}
    @Override
    public void mousePressed(MouseEvent e) {
        clicked = true;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        clicked = false;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keys[key] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keys[key] = false;
    }

    // ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {

        if (sideScheduler.getCurrPanel().equals("menu")) {
            Point mouse = MouseInfo.getPointerInfo().getLocation(); // loc of mouse on screen
            Point offset = new Point(0,0);
            try {
                offset = getLocationOnScreen(); // loc of panel
            } catch (IllegalComponentStateException ex) {
                System.out.println(ex + " hmm");
            }
            tarX = mouse.x - offset.x;
            tarY = mouse.y - offset.y;
        }

        requestFocus();
        sideScheduler.start();
        repaint();
    }

    public static void setClicked(boolean b) {
        clicked = b;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH, HEIGHT);
        try {
            menu.draw(g, tarX, tarY, clicked, this, WIDTH, HEIGHT, keys);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
