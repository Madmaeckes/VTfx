/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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

    public static void setVerbindungsstatus(final int verbindungsstatus) {

        Button verbindenButton = vtfx.VTfx.getFenster().getHeader().getVerbindenButton();
        Footer footer = vtfx.VTfx.getFenster().getFooter();

        switch (verbindungsstatus) {
            case VERBINDET:
                verbindenButton.setDisable(true);
                footer.setVerbindungsstatus("Verbindet... ");
                footer.setVerbindungsstatusFarbe(Color.BLACK);
                break;
            case VERBUNDEN:
                verbindenButton.setStyle(null);
                verbindenButton.setGraphic(roterKreis);
                verbindenButton.setText("Trennen");
                verbindenButton.setDisable(false);
                footer.setVerbindungsstatus("Verbunden ");
                footer.setVerbindungsstatusFarbe(Color.GREEN);
                break;
            case TRENNT:
                verbindenButton.setDisable(true);
                footer.setVerbindungsstatus("Trennt... ");
                footer.setVerbindungsstatusFarbe(Color.BLACK);
                break;
            case GETRENNT:
                verbindenButton.setStyle("-fx-base: green;");
                verbindenButton.setText("Verbinden");
                verbindenButton.setDisable(false);
                footer.setVerbindungsstatus("Getrennt ");
                footer.setVerbindungsstatusFarbe(Color.RED);
                break;
        }
    }
}
