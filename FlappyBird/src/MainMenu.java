
import java.awt.*;
import javax.swing.*;

public class MainMenu extends JPanel {

    Image backgroundImage;

    public MainMenu(Runnable startAction, Runnable exitAction) {

        // Mengambil background yang sama dengan permainan
        ImageIcon backgroundIcon = new ImageIcon(
                getClass().getResource("/assets/background.png")
        );

        backgroundImage = backgroundIcon.getImage();

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Judul permainan
        JLabel title = new JLabel("FLAPPY BIRD");

        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setForeground(Color.WHITE);

        gbc.gridy = 0;
        add(title, gbc);

        // Tombol Start
        JButton startButton = new JButton("START");

        startButton.setFont(new Font("Arial", Font.BOLD, 18));

        gbc.gridy = 1;
        add(startButton, gbc);

        // Tombol Exit
        JButton exitButton = new JButton("EXIT");

        exitButton.setFont(new Font("Arial", Font.BOLD, 18));

        gbc.gridy = 2;
        add(exitButton, gbc);

        // Menjalankan game ketika Start ditekan
        startButton.addActionListener(e -> startAction.run());

        // Keluar dari game ketika Exit ditekan
        exitButton.addActionListener(e -> exitAction.run());
    }

    // Menggambar background menu
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(
                backgroundImage,
                0,
                0,
                getWidth(),
                getHeight(),
                this
        );
    }
}
