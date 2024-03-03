package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.Multiplayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SceneManager {
    GameCore game;
    Scene gameScene;
    Scene startScene;
    Scene modeScene;
    Scene selectPort;
    Scene selectHost;
    Scene playerScene;
    ResizableCanvas canvas;
    GraphicsContext gc;
    Stage stage;
    double minWidth = 1600;
    double minHeight = 800;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void setupCanvas() {
        canvas = new ResizableCanvas();
        // Canvas canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        // Bind canvas size to stack pane size.
        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());
        root.setMinHeight(minHeight);
        root.setMinWidth(minWidth);
        gameScene = new Scene(root);
    }

    public void setupMenu() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setMinWidth(minWidth);
        root.setMinHeight(minHeight);
        Button button = new Button("Start");
        Button button1 = new Button("Settings");
        Label bamk = new Label("BAMK");
        bamk.setTextFill(Color.WHITE);
        root.getChildren().addAll(bamk, button);
        startScene = new Scene(root);
        startScene.getStylesheets().add("/css/start.css");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showPlayer();
            }
        });
    }

    public void setupSelectPort() {
        VBox root = new VBox();
        HBox h = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setMinWidth(minWidth);
        root.setMinHeight(minHeight);
        h.setAlignment(Pos.CENTER);
        TextField textfield = new TextField("3200");
        textfield.setPrefWidth(350);
        Button button = new Button("Start");
        Button button1 = new Button("Settings");
        Label bamk = new Label("Port:");
        bamk.setTextFill(Color.WHITE);

        h.getChildren().addAll(bamk, textfield);
        root.getChildren().addAll(h, button);
        selectPort = new Scene(root);
        selectPort.getStylesheets().add("/css/start.css");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int port = Integer.parseInt(textfield.getText());
                Multiplayer host = Multiplayer.host;
                try {
                    System.out.println("TRYING TO START");
                    host.connect("", port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("I GOT HERE");
                if (host.isConnected()) {
                    System.out.println("LETS GOOO");
                    game = new Host(getGraphicsContext(), minWidth, minHeight, host);
                    showCanvas();
                }
            }
        });
    }

    public void setupSelectHost() {
        VBox root = new VBox();
        HBox h = new HBox();
        HBox h2 = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setMinWidth(minWidth);
        root.setMinHeight(minHeight);
        h.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER);
        TextField textfield = new TextField("3200");
        TextField textField2 = new TextField("90.231.235.28");
        textfield.setPrefWidth(800);
        textField2.setPrefWidth(800);
        Button button = new Button("Start");
        Button button1 = new Button("Settings");
        Label port = new Label("Port:");
        Label host = new Label("   IP:");
        port.setTextFill(Color.WHITE);
        host.setTextFill(Color.WHITE);

        h2.getChildren().addAll(host, textField2);
        h.getChildren().addAll(port, textfield);
        root.getChildren().addAll(h, h2, button);
        selectHost = new Scene(root);
        selectHost.getStylesheets().add("/css/start.css");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String ip = textField2.getText();
                int port = Integer.parseInt(textfield.getText());
                Multiplayer guest = Multiplayer.guest;
                try {
                    guest.connect(ip, port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int tries = 0;

                while (tries < 10 && !guest.isConnected()) {
                    try {
                        Thread.sleep(1000);
                        tries++;
                        if (guest.getConnection().isConnectionFailed()) {
                            try {
                                guest.connect(ip, port);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (guest.isConnected()) {
                    game = new Guest(getGraphicsContext(), minWidth, minHeight, guest);
                    showCanvas();
                } else {
                    System.out.println("Tried to connect " + tries + " and failed");
                }
            }
        });
    }

    public void selectMode() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setMinWidth(minWidth);
        root.setMinHeight(minHeight);
        Button button1 = new Button("The Hunger Games");
        Button button2 = new Button("The Invasion");
        Button button3 = new Button("Multiplayer | Host");
        Button button4 = new Button("Multiplayer | Guest");

        // button2.setOnAction(e -> { gameScene;

        // });

        Label SelectMode = new Label("Select game mode");
        root.getChildren().add(SelectMode);

        // Adding buttons horizontally
        HBox hbox = new HBox(button1, button2, button3, button4);
        hbox.setSpacing(30);

        root.getChildren().addAll(hbox);
        hbox.setAlignment(Pos.BOTTOM_CENTER);

        modeScene = new Scene(root);
        modeScene.getStylesheets().add("/css/start.css");

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game = new Dual(getGraphicsContext(), minWidth, minHeight);
                showCanvas();
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showPlayer();
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSelectPort();
            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSelectHost();
            }
        });

    }

    public void setupPlayerSelection() {

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setMinHeight(minHeight);
        root.setMinWidth(minWidth);

        Image imgHeavy = new Image("/image/Player1.jpg", 100, 100, false, false);
        Image imgSpeedo = new Image("/image/Player2.jpg", 100, 100, false, false);

        ImageView imgVHeavy = new ImageView(imgHeavy);
        ImageView imgVSpeedo = new ImageView(imgSpeedo);

        root.getChildren().addAll(imgVHeavy, imgVSpeedo);

        Button button1 = new Button("Heavy");
        Button button2 = new Button("Speedo");
//        Button button3 = new Button("Player 3");

        button1.setGraphic(imgVHeavy);
        button2.setGraphic(imgVSpeedo);
        //button3.setGraphic(imgVSpeedo);

        button1.setContentDisplay(ContentDisplay.TOP);
        button2.setContentDisplay(ContentDisplay.TOP);
        //button3.setContentDisplay(ContentDisplay.TOP);


        HBox hbox = new HBox(button1, button2);
        hbox.setSpacing(250);
        hbox.setAlignment(Pos.CENTER);
        root.getChildren().addAll(hbox);

        Label label = new Label("Select Player");
        label.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(label);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(label, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMinHeight(minHeight);
        vbox.setMinWidth(minWidth);

        playerScene = new Scene(vbox);
        playerScene.getStylesheets().add("/css/start.css");

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game = new Invasion(getGraphicsContext(), minWidth, minHeight);
                ((Invasion) game).setPlayer(new Heavy(100.0,100.0, Direction.RIGHT, KeySchema.WASD));
                showCanvas();
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game = new Invasion(getGraphicsContext(), minWidth, minHeight);
                ((Invasion) game).setPlayer(new Speedo(100.0,100.0, Direction.RIGHT, KeySchema.WASD));
                showCanvas();
            }
        });


    }


    public void showCanvas() {
        stage.setScene(gameScene);
    }

    public void showStart() {
        stage.setScene(startScene);
    }


    public void showMode() {
        stage.setScene(modeScene);
    }

    public void showPlayer(){stage.setScene(playerScene);}


    public void showSelectPort() {
        stage.setScene(selectPort);
    }

    public void showSelectHost() {
        stage.setScene(selectHost);
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GameCore getGame() {
        return game;
    }
}
