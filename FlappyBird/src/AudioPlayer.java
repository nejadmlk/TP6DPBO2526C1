
import javax.sound.sampled.*;
import java.net.URL;

public class AudioPlayer {

    static Clip bgm;

    // Volume normal dan volume ketika Game Over
    static float normalVolume = 0.0f;
    static float deathVolume = -5.0f;

    // Memutar BGM dari awal
    public static void playBgm() {

        try {

            // Menghentikan dan menutup musik sebelumnya
            stopBgm();

            URL soundURL = AudioPlayer.class.getResource(
                    "/assets/Bad Piggies Theme.wav"
            );

            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(soundURL);

            bgm = AudioSystem.getClip();
            bgm.open(audioStream);

            // Mengatur volume normal
            setVolume(normalVolume);

            // Memulai musik dari detik pertama
            bgm.setFramePosition(0);
            bgm.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mengatur volume BGM
    public static void setVolume(float volume) {

        if (bgm != null && bgm.isControlSupported(
                FloatControl.Type.MASTER_GAIN
        )) {

            FloatControl gainControl =
                    (FloatControl) bgm.getControl(
                            FloatControl.Type.MASTER_GAIN
                    );

            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();

            gainControl.setValue(
                    Math.max(min, Math.min(max, volume))
            );
        }
    }

    // Mengecilkan volume ketika karakter mati
    public static void lowerVolume() {
        setVolume(deathVolume);
    }

    // Mengembalikan volume normal
    public static void normalVolume() {
        setVolume(normalVolume);
    }

    // Menghentikan dan menutup BGM
    public static void stopBgm() {

        if (bgm != null) {

            bgm.stop();
            bgm.close();

            bgm = null;
        }
    }

    // Restart musik dari awal
    public static void restartBgm() {

        stopBgm();
        playBgm();
    }
}
