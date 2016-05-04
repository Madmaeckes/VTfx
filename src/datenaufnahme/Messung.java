package datenaufnahme;

import GUI.GuiAktualisieren;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Zentrale Klasse zur Aufnahme von Messwerten. Das Messungs-Objekt beobachtet
 * (Observer) permanent den Fahrtstatus, bei dessen Erzeugung es erstellt wird.
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
        // Null-Pointer beim Startgleis verhindern
        gleisabschnitt = new Gleisabschnitt(-1, -1);
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

    /**
     * Andert das momentan befahrene Gleis. Nimmt bei Verlassen einer
     * Messstrecke einen Messwert auf (wenn Messungen aktiviert sind). Startet
     * bei Einfahren in eine Messstrecke die Uhr.
     *
     * @param g jetzt besetzter Gleisabschnitt
     */
    private void updateGleis(Gleisabschnitt g) {
        if (gleisabschnitt.getAdrRMX() != -1) { 
            //wenn nicht Startgleis
            if (messungAktiv && gleisabschnitt.isMessstrecke()) {
                geschwindigkeitBerechnen();
            }
            System.out.println(g.isMessstrecke());
            if (g.isMessstrecke()) {
                System.out.println("go!");
                startzeit = System.currentTimeMillis();
                error = false;
            }
        }
        gleisabschnitt = g;
    }

    /**
     * Berechnet die Geschwindigkeit die fuer den letzten Gleisabschnitt
     * gemessen wurde und gibt sie an die Gui weiter. Wurde der Gleisabschnitt
     * mit konstanter Fahrstufe (/Fahrtrichtung) durchfahren (kein error), wird
     * der wert zu den Fahrstufen-Messwerten hinzugefuegt.
     */
    private void geschwindigkeitBerechnen() {
        long t = System.currentTimeMillis() - startzeit;
        double s = gleisabschnitt.getLaenge();
        double v = s / t;
        if (!error) {
            // Zu den Messwerten hinzufügen wenn ohne Fahrdatenaenderung
            int fstat = 0;
            if (Fahrtstatus.getFahrtstatus().isReverse()) {
                fstat = 1;
            }
            int fs = Fahrtstatus.getFahrtstatus().getFahrstufe();
            messreihe[fstat][fs][0] += s;
            messreihe[fstat][fs][1] += t;
            // Gui aktualisieren
            GuiAktualisieren.setMomentaneGeschw(v);
            double fsv = messreihe[fstat][fs][0] / messreihe[fstat][fs][1];
            if (fstat == 1)
                fs = -fs;
            GuiAktualisieren.setGeschwFuerFahrstufe(String.valueOf(fs), fsv);
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

    /**
     * Beobachtet Aenderungen von Fahrstufe / Fahrtrichtung / Gleisabschnitt.
     * Findet beim Durchfahren einer Messstrecke eine Aenderung von Fahrstufe /
     * Fahrtrichtung statt, kann diese Durchfahrt nicht ausgewertet werden! Bei
     * Aenderung des Gleisabschnitts muss ggf. ein Messwert aufgenommen werden.
     */
    @Override
    public void update(Observable o, Object arg) {
        try {
            Gleisabschnitt g = Fahrtstatus.getFahrtstatus().gleisabschnitt;
            // Aenderung des Gleisabschnitt
            if (!g.equals(gleisabschnitt)) {
                updateGleis(g);
                return;
            }
            // Aenderung von Fahrstufe / Fahrtrichtung
            if (gleisabschnitt.isMessstrecke()) {
                System.out.println("error");
                this.error = true;
            }
        } catch (Exception e) {
            System.out.println("!!! " + e.getLocalizedMessage());
        }
    }
}
