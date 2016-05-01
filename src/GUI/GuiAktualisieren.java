/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.geschwindigkeitsanzeige.Geschwindigkeitsanzeige;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Manuel Eble
 */
public class GuiAktualisieren {

    // Konstanten des Verbindungsstaus
    public static final int VERBINDET = 1;
    public static final int VERBUNDEN = 2;
    public static final int TRENNT = 3;
    public static final int GETRENNT = 4;

    private static final Circle roterKreis = new Circle(5, 5, 6, Color.RED);

    public static void setMomentaneGeschw(double geschw) {
        Geschwindigkeitsanzeige geschwindigkeitsanzeige = vtfx.VTfx.getFenster().getReiterleiste().getGeschwindigkeitsanzeige();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                geschwindigkeitsanzeige.setMomentaneGeschw(geschw);
            }
        });
    }

    public static void setVerbindungsstatus(final int verbindungsstatus) {

        Scene scene = vtfx.VTfx.getFenster().getScene();
        Button verbindenButton = vtfx.VTfx.getFenster().getHeader().getVerbindenButton();
        Button messungStartenButton = vtfx.VTfx.getFenster().getHeader().getMessungStartenButton();
        Footer footer = vtfx.VTfx.getFenster().getFooter();

        switch (verbindungsstatus) {
            case VERBINDET:
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        scene.setCursor(Cursor.WAIT);
                        verbindenButton.setDisable(true);
                        footer.setVerbindungsstatus("Verbindet... ");
                        footer.setVerbindungsstatusFarbe(Color.BLACK);
                    }
                });
                break;
            case VERBUNDEN:
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        scene.setCursor(Cursor.DEFAULT);
                        messungStartenButton.setDisable(false);
                        verbindenButton.setStyle(null);
                        verbindenButton.setGraphic(roterKreis);
                        verbindenButton.setText("Trennen");
                        verbindenButton.setDisable(false);
                        footer.setVerbindungsstatus("Verbunden ");
                        footer.setVerbindungsstatusFarbe(Color.GREEN);
                    }
                });
                break;
            case TRENNT:
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        scene.setCursor(Cursor.WAIT);
                        verbindenButton.setDisable(true);
                        footer.setVerbindungsstatus("Trennt... ");
                        footer.setVerbindungsstatusFarbe(Color.BLACK);
                    }
                });
                break;
            case GETRENNT:
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        scene.setCursor(Cursor.DEFAULT);
                        messungStartenButton.setDisable(true);
                        verbindenButton.setGraphic(null);
                        verbindenButton.setStyle("-fx-base: green;");
                        verbindenButton.setText("Verbinden");
                        verbindenButton.setDisable(false);
                        footer.setVerbindungsstatus("Getrennt ");
                        footer.setVerbindungsstatusFarbe(Color.RED);
                    }
                });
                break;
        };
    }
}
