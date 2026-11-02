import java.awt.Image;

public class Pipe {
    int x;
    int y;
    int width;
    int height;

    Image image;

    boolean passed = false;

    // Kecepatan gerakan vertikal pipa
    int verticalVelocity = 0;

    public Pipe(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }
}