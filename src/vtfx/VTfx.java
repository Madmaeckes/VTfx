/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vtfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import GUI.Fenster;
import GUI.Footer;
import GUI.Header;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.HBoxBuilder;
import rmx.RMXconnector;

/**
 *
 * @author Manuel
 */
public class VTfx extends Application {
    
    /**
     * Verwaltet die Verbindung zur RMX-PC-Zentrale (Singleton-Objekt)
     */
    public static final RMXconnector rmxVerbindung = RMXconnector.getRMXconnector();
    
    @Override
    public void start(Stage primaryStage) {

        
        Fenster fenster = new Fenster();
//        Scene scene = new Scene(fenster, 800, 600);
        

        BorderPane root = new BorderPane();
        root.setTop(new Header());
        root.setCenter(fenster);
        root.setBottom(new Footer());
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("VisTrain");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
