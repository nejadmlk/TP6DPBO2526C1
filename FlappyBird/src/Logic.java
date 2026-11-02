import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Logic extends KeyAdapter implements ActionListener {
    int frameWidth = 360;
    int frameHeight = 600;

    int playerWidth = 34;
    int playerHeight = 24;

    int pipeWidth = 64;
    int pipeHeight = 512;

    int openingSpace = 150;

    // Kecepatan awal permainan
    int pipeVelocityX = -2;

    // Pengaturan gravitasi dan lompatan
    double gravity = 0.7;
    double jumpStrength = -8.0;

    double baseGravity = 0.7;
    double baseJumpStrength = -10.0;

    // Pengali kecepatan permainan
    double speedMultiplier = 1.0;
    int speedLevel = 0;

    // Batas gerakan vertikal pipa
    int minPipeY = 90;
    int maxPipeY = 350;
    int pipeVerticalSpeed = 2;

    Player player;

    ArrayList<Pipe> pipes;

    Image lowerPipeImage;
    Image upperPipeImage;

    Timer gameLoop;
    Timer pipesCooldown;

    Random random = new Random();

    boolean gameOver = false;

    int score = 0;

    public Logic() {
        player = new Player(80, 300, playerWidth, playerHeight);
        pipes = new ArrayList<>();

        ImageIcon lowerIcon = new ImageIcon(
                getClass().getResource("/assets/lowerPipe.png")
        );

        ImageIcon upperIcon = new ImageIcon(
                getClass().getResource("/assets/upperPipe.png")
        );

        lowerPipeImage = lowerIcon.getImage();
        upperPipeImage = upperIcon.getImage();

        gameLoop = new Timer(1000 / 60, this);

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
    }

    public void startGame() {
        gameOver = false;

        gameLoop.start();
        pipesCooldown.start();

        AudioPlayer.playBgm();
    }

    public void placePipes() {
        int randomPipeY = -pipeHeight / 4
                - random.nextInt(pipeHeight / 2);

        int openingY = randomPipeY + pipeHeight + openingSpace;

        Pipe upperPipe = new Pipe(
                frameWidth,
                randomPipeY,
                pipeWidth,
                pipeHeight,
                upperPipeImage
        );

        Pipe lowerPipe = new Pipe(
                frameWidth,
                openingY,
                pipeWidth,
                pipeHeight,
                lowerPipeImage
        );

        // Arah gerakan awal pipa
        lowerPipe.verticalVelocity = random.nextBoolean()
                ? pipeVerticalSpeed
                : -pipeVerticalSpeed;

        pipes.add(upperPipe);
        pipes.add(lowerPipe);
    }

    public void move() {
        if (gameOver) {
            return;
        }

        // Gerakan pemain dengan gravitasi
        player.velocityY += gravity;
        player.y += (int) player.velocityY;

        // Gerakan horizontal pipa
        for (Pipe pipe : pipes) {
            pipe.x += pipeVelocityX;
        }

        // Hapus pipa yang sudah keluar layar
        pipes.removeIf(pipe -> pipe.x + pipe.width < 0);

        // Gerakan vertikal pipa setelah skor lebih dari 50
        if (score > 50) {
            movePipesVertically();
        }

        // Periksa tabrakan dengan pipa
        for (Pipe pipe : pipes) {
            if (checkCollision(pipe)) {
                endGame();
                return;
            }
        }

        // Periksa apakah pemain jatuh ke bawah
        if (player.y + player.height >= frameHeight) {
            endGame();
            return;
        }

        checkScore();
    }

    public void movePipesVertically() {
        // Setiap dua pipa merupakan satu pasangan
        for (int i = 0; i < pipes.size(); i += 2) {
            if (i + 1 >= pipes.size()) {
                break;
            }

            Pipe upperPipe = pipes.get(i);
            Pipe lowerPipe = pipes.get(i + 1);

            // Balik arah ketika mencapai batas
            if (lowerPipe.y <= minPipeY) {
                lowerPipe.verticalVelocity = pipeVerticalSpeed;
            }

            if (lowerPipe.y >= maxPipeY) {
                lowerPipe.verticalVelocity = -pipeVerticalSpeed;
            }

            // Kedua pipa bergerak bersamaan
            upperPipe.y += lowerPipe.verticalVelocity;
            lowerPipe.y += lowerPipe.verticalVelocity;
        }
    }

    public boolean checkCollision(Pipe pipe) {
        Rectangle playerRect = new Rectangle(
                player.x,
                player.y,
                player.width,
                player.height
        );

        Rectangle pipeRect = new Rectangle(
                pipe.x,
                pipe.y,
                pipe.width,
                pipe.height
        );

        return playerRect.intersects(pipeRect);
    }

    public void checkScore() {
        for (Pipe pipe : pipes) {
            if (pipe.image == lowerPipeImage && !pipe.passed) {
                if (player.x > pipe.x + pipe.width) {
                    score++;
                    pipe.passed = true;

                    updateGameSpeed();
                }
            }
        }
    }

    public void updateGameSpeed() {
        // Setiap 20 skor, kecepatan meningkat
        int newSpeedLevel = score / 20;

        if (newSpeedLevel > speedLevel) {
            speedLevel = newSpeedLevel;

            // Pengali kecepatan bertambah 1,5 kali
            speedMultiplier = Math.pow(1.5, speedLevel);

            // Kecepatan horizontal pipa
            pipeVelocityX = -(int) Math.round(
                    2 * speedMultiplier
            );

            // Gravitasi mengikuti pace permainan
            gravity = baseGravity * speedMultiplier;

            // Kekuatan lompatan mengikuti pace permainan
            jumpStrength = baseJumpStrength * speedMultiplier;

            // Percepat kemunculan pipa
            int newDelay = (int) Math.max(
                    350,
                    1500 / speedMultiplier
            );

            pipesCooldown.setDelay(newDelay);
            pipesCooldown.setInitialDelay(newDelay);
        }
    }

    public void endGame() {
        if (gameOver) {
            return;
        }

        gameOver = true;

        gameLoop.stop();
        pipesCooldown.stop();

        AudioPlayer.lowerVolume();
    }

    public void restartGame() {
        player.y = 300;
        player.velocityY = 0;

        pipes.clear();

        score = 0;

        speedLevel = 0;
        speedMultiplier = 1.0;

        pipeVelocityX = -2;

        // Reset gravitasi dan lompatan
        gravity = baseGravity;
        jumpStrength = baseJumpStrength;

        // Reset waktu kemunculan pipa
        pipesCooldown.setDelay(1500);
        pipesCooldown.setInitialDelay(1500);

        gameOver = false;

        AudioPlayer.restartBgm();

        gameLoop.start();
        pipesCooldown.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameOver) {
                player.velocityY = jumpStrength;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (gameOver) {
                restartGame();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
    }
}