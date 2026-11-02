import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
    int x;
    int y;
    int width;
    int height;

    double velocityY = 0;

    Image image;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        ImageIcon icon = new ImageIcon(
                getClass().getResource("/assets/bird.png")
        );

        image = icon.getImage();
    }
}