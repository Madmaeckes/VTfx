/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Manuel Eble
 */
public class Fenster {

    private Scene scene;
    private final Header header;
    private final Footer footer;

    public Fenster(Stage primaryStage) {

        header = new Header();
        footer = new Footer();
        Reiterleiste reiterleiste = new Reiterleiste();

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(reiterleiste);
        root.setBottom(footer);
        scene = new Scene(root, 800, 600);
        primaryStage.setTitle("VisTrain");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public Header getHeader() {
        return header;
    }

    public Footer getFooter() {
        return footer;
    }
}
