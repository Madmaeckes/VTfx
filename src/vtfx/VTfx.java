/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vtfx;

import GUI.Fenster;
import javafx.application.Application;
import javafx.stage.Stage;

import rmx.RMXconnector;

/**
 *
 * @author Manuel Eble
 */
public class VTfx extends Application {

    /**
     * Programmfenster
     */
    public static Fenster fenster;
    /**
     * Verwaltet die Verbindung zur RMX-PC-Zentrale (Singleton-Objekt)
     */
    public static final RMXconnector rmxVerbindung = RMXconnector.getRMXconnector();

    @Override
    public void start(Stage primaryStage) {
        fenster = new Fenster(primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static Fenster getFenster() {
        return fenster;
    }
}
