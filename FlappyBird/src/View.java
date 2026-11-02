import java.awt.*;
import javax.swing.*;

public class View extends JPanel {
    Logic logic;

    Image backgroundImage;

    public View(Logic logic) {
        this.logic = logic;

        setPreferredSize(new Dimension(360, 600));

        ImageIcon backgroundIcon = new ImageIcon(
                getClass().getResource("/assets/background.png")
        );

        backgroundImage = backgroundIcon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.drawImage(
                backgroundImage,
                0, 0, 360, 600,
                null
        );

        // Pemain
        g.drawImage(
                logic.player.image,
                logic.player.x,
                logic.player.y,
                logic.player.width,
                logic.player.height,
                null
        );

        // Pipa
        for (Pipe pipe : logic.pipes) {
            g.drawImage(
                    pipe.image,
                    pipe.x,
                    pipe.y,
                    pipe.width,
                    pipe.height,
                    null
            );
        }

        // Skor di bagian atas background
        g.setFont(new Font("Arial", Font.BOLD, 40));

        String scoreText = String.valueOf(logic.score);

        FontMetrics metrics = g.getFontMetrics();

        int scoreX = (360 - metrics.stringWidth(scoreText)) / 2;

        // Bayangan skor
        g.setColor(Color.BLACK);
        g.drawString(scoreText, scoreX + 2, 62);

        // Teks skor
        g.setColor(Color.WHITE);
        g.drawString(scoreText, scoreX, 60);

        // Tampilan Game Over
        if (logic.gameOver) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, 360, 600);

            g.setColor(Color.WHITE);

            g.setFont(new Font("Arial", Font.BOLD, 35));
            g.drawString("GAME OVER", 75, 270);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + logic.score, 135, 320);

            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Press R to Restart", 105, 370);
        }
    }
}