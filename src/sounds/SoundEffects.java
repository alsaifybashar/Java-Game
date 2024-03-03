package sounds;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Handles sound effects and music
 */
public class SoundEffects {
    public static Media hit;
    public static Media fire1;
    public static Media gameover;
    public static Media enemeydead;

    /**
     * Loads all the files needed
     */
    public static void init() {
        hit = load(hit,"hit.wav");
        fire1 = load(fire1, "fire1.mp3");
        gameover = load(gameover, "gameover.wav");
        enemeydead = load(enemeydead, "enemydead.wav");
    }

    /**
     * Loads the file in our specified path
     * @param media
     * @param file
     * @return
     */
    public static Media load(Media media, String file){
       return new Media(new File(("src/sounds/"+file)).toURI().toString());
    }

    /**
     * @return enemydead sound
     */
    public static Media getEnemeydead() {
        return enemeydead;
    }

    /**
     * @return fire sound
     */
    public static Media getFire1() {
        return fire1;
    }

    /**
     * @return gameover sound
     */
    public static Media getGameover() {
        return gameover;
    }

    /**
     * @return player hit sound
     */
    public static Media getHit() {
        return hit;
    }

    /**
     * Access media via getter and then play it
     * @param media
     */
    public static void play(Media media) {
        new MediaPlayer(media).play();
    }

}
