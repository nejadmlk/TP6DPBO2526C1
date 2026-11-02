import java.awt.*;
import javax.swing.*;

public class App {
    JFrame frame;

    CardLayout cardLayout;
    JPanel mainPanel;

    Logic logic;
    View view;

    Timer updateTimer;

    public App() {
        frame = new JFrame("Flappy Bird");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        MainMenu menu = new MainMenu(
                this::startGame,
                () -> System.exit(0)
        );

        menu.setPreferredSize(new Dimension(360, 600));

        mainPanel.add(menu, "menu");

        frame.add(mainPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        cardLayout.show(mainPanel, "menu");
    }

    public void startGame() {
        logic = new Logic();
        view = new View(logic);

        JPanel gamePanel = new JPanel(new BorderLayout());

        gamePanel.add(view, BorderLayout.CENTER);

        // Hapus panel permainan sebelumnya
        Component[] components = mainPanel.getComponents();

        for (Component component : components) {
            if ("game".equals(component.getName())) {
                mainPanel.remove(component);
            }
        }

        gamePanel.setName("game");

        mainPanel.add(gamePanel, "game");

        // Hentikan timer sebelumnya
        if (updateTimer != null) {
            updateTimer.stop();
        }

        // Timer untuk memperbarui tampilan
        updateTimer = new Timer(16, e -> {
            view.repaint();
        });

        updateTimer.start();

        view.setFocusable(true);
        view.addKeyListener(logic);

        cardLayout.show(mainPanel, "game");

        frame.pack();
        frame.setLocationRelativeTo(null);

        SwingUtilities.invokeLater(() -> {
            view.requestFocusInWindow();
        });

        logic.startGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}