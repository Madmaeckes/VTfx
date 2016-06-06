/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import javafx.scene.Scene;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 *
 * @author Manuel Eble
 */
public class Fenster {
    
    /**
     * Pseudosingletoninstanz
     * (Zuletzt erzeugtes Fenster. Fenster sollte nur einmal bei Programmstart erzeugt werden.)
     */
    private static Fenster fenster;
    
    private final Scene scene;
    private final Stage stage;
    private final Header header;
    private final Footer footer;
    private final Reiterleiste reiterleiste;

    public Fenster(Stage primaryStage) {

        header = new Header();
        footer = new Footer();
        reiterleiste = new Reiterleiste();

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(reiterleiste);
        root.setBottom(footer);
        scene = new Scene(root, 800, 600);
        primaryStage.setTitle("VisTrain");
        primaryStage.setScene(scene);
        primaryStage.show();
        this.stage = primaryStage;
        this.fenster = this;
    }
    
    public static Fenster getFenster() {
        return fenster;
    }
    
    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public Header getHeader() {
        return header;
    }

    public Reiterleiste getReiterleiste() {
        return reiterleiste;
    }

    public Footer getFooter() {
        return footer;
    }
}
