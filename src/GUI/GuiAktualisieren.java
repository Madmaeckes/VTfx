/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.einstellungen.Gleisabschnittstabelle;
import GUI.geschwindigkeitsanzeige.Geschwindigkeitsanzeige;
import datenaufnahme.Gleisabschnitt;
import java.text.DecimalFormat;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

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

    private static final Polygon gruenerPfeil = new Polygon(new double[]{
        0, 0,
        10, 5,
        0, 10,});

    private static Gleisabschnittstabelle gleisabschnittstabelle
            = Fenster.getFenster().getReiterleiste().messabschnittstabelle;

    /**
     * Setzt die momentane Geschwindigkeit, sodass diese von der Klasse
     * Geschwindigkeitsanzeige angezeigt wird.
     *
     * @param geschw momentane Geschwindigkeit zwischen 0 und 999.99
     */
    public static void setMomentaneGeschw(double geschw) {
        Geschwindigkeitsanzeige geschwindigkeitsanzeige = Fenster.getFenster().getReiterleiste().getGeschwindigkeitsanzeige();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                geschwindigkeitsanzeige.setMomentaneGeschw(geschw);
            }
        });
    }

    /**
     * Setzt die Farbe der digitalen Geschwindigkeitsanzeige
     *
     * @param onColor Farbe der aktiven Digitalen Ziffern
     */
    public static void setFarbeFuerDigitalanzeige(Color onColor) {
        Geschwindigkeitsanzeige geschwindigkeitsanzeige = Fenster.getFenster().getReiterleiste().getGeschwindigkeitsanzeige();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                geschwindigkeitsanzeige.setFarbe(onColor);
            }
        });
    }

    /**
     * Setzt eine neue Säule im Fahrstufendiagramm, mit übergebener Fahrstufe als
     * Beschriftung und übergebener Geschwindigkeit als Säulenhöhe
     *
     * @param fahrstufe Bezeichnug der momentanen Fahrstufe als String
     * @param geschw Geschwindigkeit zur übergebenen Fahrstufe als double
     */
    public static void setGeschwFuerFahrstufe(int fahrstufe, double geschw) {
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Fahrstufendiagramm diagramm = Fenster.getFenster().getReiterleiste().getFahrstufendiagramm();
                diagramm.setGeschwFuerFahrstufe(fahrstufe, geschw);
            }
        });
    }
    
    /**
     * Leert den Inhalt des momentan angezeigten Fahrstufendiagramms.
     */
    public static void clearFahrstufendiagramm () {
                 Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Fahrstufendiagramm diagramm = Fenster.getFenster().getReiterleiste().getFahrstufendiagramm();
                diagramm.clear();
            }
        });
    }
    

    /**
     * Traegt einen uebergebenen Gleisabschnitt in die Gleisabschnittstabelle
     * ein.
     *
     * @param gleisabschnitt
     */
    public static void addGleisabschnitt(Gleisabschnitt gleisabschnitt) {
        gleisabschnittstabelle.addTabelleneintrag(Integer.toString(gleisabschnitt.getAdrRMX()),
                Integer.toString(gleisabschnitt.getBit()),
                Double.toString(gleisabschnitt.getLaenge()),
                gleisabschnitt.isMessstrecke());
    }

    /**
     * Traegt einen neuen letzten Messwert in die entsprechende Zeile der
     * Gleisabschnittstabelle ein.
     *
     * @param g Gleisabschnitt auf dem gemessen wurde
     * @param v gemessene Geschnidigkeit
     */
    public static void updateMesswet(Gleisabschnitt g, double v) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                DecimalFormat f = new DecimalFormat("#0.00");
                gleisabschnittstabelle.updateMesswert(g, String.valueOf(f.format(v)) + " cm/s");
            }
        });
    }

    /**
     * Markiert die Zeile in der Gleisabschnittstabelle, die den uebergebenen
     * Gleisabschnitt g enthaelt.
     *
     * @param g
     */
    public static void markiereTabellenZeile(Gleisabschnitt g) {
        gleisabschnittstabelle.markiereZeile(g);
    }

    /**
     * Aktualisiert die Statusleiste und den Verbindenbutton.
     *
     * @param verbindungsstatus 1 Verbindet, 2 Verbunden, 3 Trennt, 4 Getrennt
     */
    public static void setVerbindungsstatus(final int verbindungsstatus) {

        Scene scene = Fenster.getFenster().getScene();
        Button verbindenButton = Fenster.getFenster().getHeader().getVerbindenButton();
        Button messungStartenButton = Fenster.getFenster().getHeader().getMessungStartenButton();
        Footer footer = Fenster.getFenster().getFooter();

        switch (verbindungsstatus) {
            case VERBINDET:
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        scene.setCursor(Cursor.WAIT);
                        verbindenButton.setDisable(true);
                        messungStartenButton.setDisable(true);
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
                        verbindenButton.setStyle(null);
                        verbindenButton.setGraphic(getRedDot());
                        verbindenButton.setText("Trennen");
                        verbindenButton.setDisable(false);
                        messungStartenButton.setDisable(false);
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
                        messungStartenButton.setDisable(true);
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
                        verbindenButton.setGraphic(null);
                        verbindenButton.setStyle("-fx-base: green;");
                        verbindenButton.setText("Verbinden");
                        verbindenButton.setDisable(false);
                        messungStartenButton.setDisable(true);
                        footer.setVerbindungsstatus("Getrennt ");
                        footer.setVerbindungsstatusFarbe(Color.RED);
                    }
                });
                break;
        };
    }

    public static void setMessungsstatus(final String messungsstatus) {

        Footer footer = Fenster.getFenster().getFooter();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch (messungsstatus) {
                    case "MESSUNG_GESTARTET":
                        footer.setMessungsstatus("Messung läuft...");
                        footer.setMessungsstatusFarbe(Color.GREEN);
                        disableMessbutton();
                        break;
                    case "MESSUNG_GESTOPPT":
                        footer.setMessungsstatus("Messung gestoppt");
                        footer.setMessungsstatusFarbe(Color.RED);
                        enableMessbutton();
                        break;
                    case "MESSUNG_ABGEBROCHEN":
                        footer.setMessungsstatus("Messung abgebrochen "
                                + "- Verbindungsfehler!");
                        footer.setMessungsstatusFarbe(Color.RED);
                        enableMessbutton();
                        break;
                }
        }});
    }
    
    private static void disableMessbutton() {
        Button messungStartenButton = Fenster.getFenster().getHeader()
                .getMessungStartenButton();
        messungStartenButton.setText("Messung abbrechen");
        messungStartenButton.setGraphic(getRedDot());
    }
    
    private static void enableMessbutton() {
        Button messungStartenButton = Fenster.getFenster().getHeader()
               .getMessungStartenButton();
        messungStartenButton.setText("Messung starten");
        gruenerPfeil.setFill(Color.GREEN);
        messungStartenButton.setGraphic(gruenerPfeil);
    }
    
    /**
     * Stellt den roten Punkt für die Buttons (trennen/beenden) zur Verfügung.
     */
    private static Circle getRedDot() {
        return new Circle(5, 5, 6, Color.RED);
    }
}
