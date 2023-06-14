import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.standard.Sides;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Menu {
    
    private WelcomeComponent welcome;

    private Image bg, startbuttonimg, settingsbuttonimg;

    private ArrayList<Button> buttons;

    private boolean add_events = false;

    private String wakingtime, bedtime, schooltime, emailaddress;

    public Menu() throws IOException {

        Util.loadFonts();

        bg = new ImageIcon("assets\\Poppins\\bg.jpg").getImage().getScaledInstance(1280, 720, Image.SCALE_FAST);

        startbuttonimg = new ImageIcon("assets\\start.png").getImage().getScaledInstance(1280, 720, Image.SCALE_FAST);

        settingsbuttonimg = new ImageIcon("assets\\settings.png").getImage().getScaledInstance(1280, 720, Image.SCALE_FAST);

        Button start = new Button("start", 50, 150, 256, 70, null, startbuttonimg.getScaledInstance(256, 70, Image.SCALE_FAST));

        Button settings = new Button("settings", 50, 250, 256, 70, null, settingsbuttonimg.getScaledInstance(256, 70, Image.SCALE_FAST));

        buttons = new ArrayList<Button>();

        buttons.add(start);
        buttons.add(settings);

        refreshTimes();

        welcome = new WelcomeComponent();

    }

    public void refreshTimes() {
        try {
            Scanner f = new Scanner(new BufferedReader(new FileReader("assets\\wakingtime.txt")));
            if (f.hasNextLine()) {
                wakingtime = f.nextLine();
            }
            f.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            Scanner f = new Scanner(new BufferedReader(new FileReader("assets\\bedtime.txt")));
            if (f.hasNextLine()) {
                bedtime = f.nextLine();
            }
            f.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            Scanner f = new Scanner(new BufferedReader(new FileReader("assets\\schooltime.txt")));
            if (f.hasNextLine()) {
                schooltime = f.nextLine();
            }
            f.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            Scanner f = new Scanner(new BufferedReader(new FileReader("assets\\emailaddress.txt")));
            if (f.hasNextLine()) {
                emailaddress = f.nextLine();
            }
            f.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void draw(Graphics g, int mx, int my, boolean clicked, MenuPanel menu, int WIDTH, int HEIGHT, boolean[] keys) throws IOException {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.drawImage(bg, 0, 0, null);

        welcome.draw(g, keys);

        for (Button b : buttons) {
            b.draw(g);
            if (b.clicked(mx, my, clicked)) {
                if (b.getName().equals("start")) {
                    add_events = true;
                    JTextArea xField = new JTextArea(5, 20);

                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("All Events:"));
                    myPanel.add(xField);

                    UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                       "List Events Seperated by Newline (E.g., Talk to Bob - 5 minutes, Homework - 60 minutes)", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        // DONE
                        try {
                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("assets\\events.txt", false)));
                            out.println(xField.getText());
                            out.close();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        System.out.println(xField.getText());

                        try {
                            DOALLTHECOOLSTUFF();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                } else if (b.getName().equals("settings")) {
                    JTextField EmailAddress = new JTextField(40);
                    EmailAddress.setText(emailaddress);

                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("Email: "));
                    myPanel.add(EmailAddress);

                    UIManager.put("OptionPane.minimumSize",new Dimension(600,200)); 

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                       "Default Settings", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {

                        try {
                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("assets\\emailaddress.txt", false)));
                            out.println(EmailAddress.getText());
                            out.close();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }

                    refreshTimes();
                }
            }
        }
    }

    public void DOALLTHECOOLSTUFF() throws IOException, InterruptedException {
        String Script_Path0 = "tz.py";
        ProcessBuilder Process_Builder0 = new
                ProcessBuilder("python",Script_Path0)
                .inheritIO();

        Process Demo_Process0 = Process_Builder0.start();
        Demo_Process0.waitFor();

        String Script_Path1 = "schedule_gen.py";
        ProcessBuilder Process_Builder1 = new
                ProcessBuilder("python",Script_Path1)
                .inheritIO();

        Process Demo_Process1 = Process_Builder1.start();
        Demo_Process1.waitFor();

        String Script_Path2 = "main.py";
        ProcessBuilder Process_Builder2 = new
                ProcessBuilder("python",Script_Path2)
                .inheritIO();

        Process Demo_Process2 = Process_Builder2.start();
        Demo_Process2.waitFor();
    }
}

class WelcomeComponent {
    public WelcomeComponent() {
    }

    public void draw(Graphics g, boolean[] keys) throws IOException {
        g.setFont(Util.fontTextSemiBold);
        g.drawString("Welcome to ", 50, 80);
        g.setFont(Util.fontTextBold);
        g.drawString("SideSchedule", 305, 80);
    }

}