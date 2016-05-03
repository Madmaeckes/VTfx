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
import java.util.Observable;
import java.util.Set;

/**
 *
 * @author Manuel Weber
 */
public class Messung implements java.util.Observer {

    /**
     * Privater Konstruktor verhindert die Instanziierung von aussen. Es soll
     * ausschliesslich ein Messungs-Objekt verknuepft mit dem Fahrtstatus
     * (singleton) erzeugt werden.
     */
    protected Messung() {
        gleisbild = new ArrayList<>();
        messreihe = new double[2][999][2];
        System.out.println("new!");
    }

    private List<Gleisabschnitt> gleisbild;

    /**
     * Wahrheitswert, ob momentan gemessen werden soll
     */
    private boolean messungAktiv = true;

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

    private void updateGleis(Gleisabschnitt g) {
        System.out.println("|");
        if (messungAktiv
                && gleisabschnitt.isMessstrecke()
                && gleisabschnitt.getNext().equals(g)) {
            /* wenn Messungen aktiviert sind, der verlassene Abschnitt
            eine Messstrecke ist und in die nachfolgende Strecke 
            eingefahren wurde */
            geschwindigkeitBerechnen();
        }
        if (g.isMessstrecke()) {
            System.out.println("go!");
            startzeit = System.currentTimeMillis();
            error = false;
        }
    }

    /**
     * MUSS bei jeder Aenderung von Fahrstufe / Fahrtrichtung / Gleisabschnitt
     * aufgerufen werden. Findet beim Durchfahren einer Messstrecke eine
     * Aenderung von Fahrstufe / Fahrtrichtung statt, kann diese Durchfahrt
     * nicht ausgewertet werden!
     */
//    public void updatedFahrtstatus() {
//        System.out.println("$");
//        Gleisabschnitt g = Fahrtstatus.getFahrtstatus().gleisabschnitt;
//        // Aenderung des Gleisabschnitts
//        if (!g.equals(this.gleisabschnitt)) {
//            updateGleis(g);
//            return;
//        }
//        // Aenderung von Fahrstufe / Fahrtrichtung
//        if (gleisabschnitt.isMessstrecke()) {
//            this.error = true;
//        }
//    }
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
            System.out.println("> " + v);
        } else {
            // auf fehlerhafte Messung hinweisen
            gleisabschnitt.setLetzteGemesseneGeschwindigkeit(-1);
            System.out.println("> " + (-1));
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

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("^changed");
    }
}
