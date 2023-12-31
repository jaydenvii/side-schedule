import java.awt.*;

public class Button {

    private String name;

    private int x, y, width, height;

    private Image img;

    private Rectangle rect;

    private Color color;

    public Button(String name, int x, int y, int width, int height, Color col, Image img) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = col;
        this.rect = new Rectangle(x, y, width, height);
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public boolean clicked(int mx, int my, boolean clicked) {
        if (rect.contains(new Point(mx, my))) {
            if (clicked) {
                MenuPanel.setClicked(false);
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        if (img != null) {
            g.drawImage(img, x, y, null);
        }
    }
}
