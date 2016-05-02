/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenaufnahme;

import GUI.GuiAktualisieren;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Manuel Weber
 */
public class Messung {

    // Singelton-Pattern --------------------------------------------
    /**
     * Singelton-Objekt
     */
    private static Messung messungsInstanz;

    /**
     * Privater Konstruktor verhindert die Instanziierung von aussen.
     */
    private Messung() {
        messungsInstanz = new Messung();
        messungsInstanz.setGleisbild(new ArrayList<>());
        messreihe = new double[2][999][2];
    }

    /**
     * Gibt das Messungs-Objekt zurueck.
     *
     * @return Messungs-Objekt (Singleton-Objekt)
     */
    public static synchronized Messung getMessung() {
        if (messungsInstanz == null) {
            messungsInstanz = new Messung();
        }
        return messungsInstanz;
    }

    /**
     * Unterbindet das Klonen des Objekts (Singleton)
     */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    // Ende Singleton-Pattern ---------------------------------------
    private List<Gleisabschnitt> gleisbild;

    /**
     * Wahrheitswert, ob momentan gemessen werden soll
     */
    private boolean messungAktiv;

    /**
     * momentan befahrener Gleisabschnitt (evtl. mit aktiver Messung)
     */
    private Gleisabschnitt gleisabschnitt;

    /**
     * Zeitpunkt zu dem die Lok in den momentanen Gleisabschnitt eingefahren
     * ist.
     */
    private long startzeit;

    /**
     * Fahrstufe auf der der Gleisabschnitt durchfahren wird (null, wenn sich
     * die Fahrstufe zwischendurch aendert)
     */
    private int fahrstufe;

    /**
     * true bei Aenderung der Fahrstufe/Fahrtrichtung innerhalb einer
     * Messstrecke
     */
    private boolean error;

    /**
     * Sammlung von gemessener Gesamtstrecke und Gesamtzeit je
     * Fahrstufe/Fahrtrichtung: [fahrtrichtung][fahrstufe][s bzw. t]
     * ([2][999][2], 0 = vorwaerts bzw. s, 1 = rueckwaerts bzw. t)
     */
    private double[][][] messreihe;

    public void start() {
        this.messungAktiv = true;
    }

    public void stop() {
        this.messungAktiv = false;
    }

    public void updateGleis(Gleisabschnitt g) {
        if (messungAktiv
                && gleisabschnitt.isMessstrecke()
                && gleisabschnitt.getNext().equals(g)) {
            /* wenn Messungen aktiviert sind, der verlassene Abschnitt
            eine Messstrecke ist und in die nachfolgende Strecke 
            eingefahren wurde */
            geschwindigkeitBerechnen();
        }
        if (g.isMessstrecke()) {
            startzeit = System.currentTimeMillis();
            error = false;
        }
    }

    /**
     * MUSS bei jeder Aenderung von Fahrstufe / Fahrtrichtung aufgerufen werden.
     * Findet diese beim Durchfahren einer Messstrecke statt, kann diese
     * Durchfahrt nicht ausgewertet werden!
     */
    public void updatedFahrtparameter() {
        if (gleisabschnitt.isMessstrecke()) {
            this.error = true;
        }
    }

    private void geschwindigkeitBerechnen() {
        long t = System.currentTimeMillis() - startzeit;
        double s = gleisabschnitt.getLaenge();
        double v = s / t;
        GuiAktualisieren.setMomentaneGeschw(v);
        if (!error) {
            // Zu den Messwerten hinzufügen wenn ohne Fahrdatenaenderung
            int fstat = 0;
            if (Fahrtstatus.getFahrtstatus().isReverse()) {
                fstat = 1;
            }
            int fs = Fahrtstatus.getFahrtstatus().getFahrstufe();
            messreihe[fstat][fs][0] += s;
            messreihe[fstat][fs][1] += t;
            // Gui Diagramm aktualisieren
            gleisabschnitt.setLetzteGemesseneGeschwindigkeit(v);
        } else {
            // auf fehlerhafte Messung hinweisen
            gleisabschnitt.setLetzteGemesseneGeschwindigkeit(-1);
        }
    }

    /* Getter & Setter */
    public List<Gleisabschnitt> getGleisbild() {
        return gleisbild;
    }

    public void setGleisbild(List<Gleisabschnitt> gleisbild) {
        this.gleisbild = gleisbild;
    }

    public int getFahrstufe() {
        return fahrstufe;
    }
}
