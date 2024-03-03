package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import sounds.SoundEffects;

import java.util.HashMap;


public class Main extends Application {
    public static double WIDTH = 1600;
    public static double HEIGHT = 800;
    public static final int FRAMES_PER_SECOND = 100;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
    SceneManager sceneManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("BAMK Game");
        sceneManager = new SceneManager(primaryStage);

        //Setups all the scenes
        sceneManager.setupPlayerSelection();
        sceneManager.selectMode();
        sceneManager.setupMenu();
        sceneManager.setupCanvas();
        sceneManager.setupSelectPort();
        sceneManager.setupSelectHost();
        sceneManager.showMode();


        //Ready
        primaryStage.show();
        SoundEffects.init();


        //timeline and fps
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> {
                    if (sceneManager.getGame() != null) sceneManager.getGame().update(SECOND_DELAY);
                });
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

        //Listeners
        keyListener();
        resizeListener();
    }

    public void keyListener() {
        sceneManager.getGameScene().setOnKeyPressed(event -> {
            String codeString = event.getCode().toString();
            if (!currentlyActiveKeys.containsKey(codeString)) {
                currentlyActiveKeys.put(codeString, true);
                if (sceneManager.getGame() != null) sceneManager.getGame().setCurrentlyActiveKeys(currentlyActiveKeys);
                System.out.println(currentlyActiveKeys);
            }
        });
        sceneManager.getGameScene().setOnKeyReleased(event -> {
            currentlyActiveKeys.remove(event.getCode().toString());
            if (sceneManager.getGame() != null) sceneManager.getGame().setCurrentlyActiveKeys(currentlyActiveKeys);
        });


    }

    public void resizeListener() {
        sceneManager.getCanvas().widthProperty().addListener((obs, oldVal, newVal) -> {
            WIDTH = (double) newVal;
            System.out.println("Width: " + WIDTH + " Height: " + HEIGHT);
            if (sceneManager.getGame() != null) sceneManager.getGame().setDimensions(WIDTH, HEIGHT);
        });
        sceneManager.getCanvas().heightProperty().addListener((obs, oldVal, newVal) -> {
            HEIGHT = (double) newVal;
            System.out.println("Width: " + WIDTH + " Height: " + HEIGHT);
            if (sceneManager.getGame() != null) sceneManager.getGame().setDimensions(WIDTH, HEIGHT);
        });
        WIDTH = sceneManager.getCanvas().getWidth();
        HEIGHT = sceneManager.getCanvas().getHeight();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
