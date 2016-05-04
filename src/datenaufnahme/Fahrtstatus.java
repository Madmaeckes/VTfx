package datenaufnahme;

import java.util.Observable;

/**
 * Momentane Position, Fahrtrichtung und Fahrstufe der Lok.
 *
 * @author Manuel Weber
 */
public class Fahrtstatus extends Observable {

    // Singelton-Pattern --------------------------------------------
    /**
     * Singelton-Objekt
     */
    private static Fahrtstatus fahrtstatusInstanz;

    /**
     * Privater Konstruktor verhindert die Instanziierung von aussen.
     */
    private Fahrtstatus() {
        gleisabschnitt = new Gleisabschnitt(0, 0);
        vorherigerGleisabschnitt = gleisabschnitt;
    }

    /**
     * Gibt das Fahrtstatus-Objekt zurueck. Erzeugt (wenn noch nicht vorhanden
     * das Singleton-Objekt und das dazugehoerige Messungs-Objekt.
     *
     * @return Fahrtstatus-Objekt (Singleton-Objekt)
     */
    public static synchronized Fahrtstatus getFahrtstatus() {
        if (fahrtstatusInstanz == null) {
            fahrtstatusInstanz = new Fahrtstatus();
            fahrtstatusInstanz.addObserver(new Messung());
        }
        return fahrtstatusInstanz;
    }

    /**
     * Unterbindet das Klonen des Objekts (Singleton)
     */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    // Ende Singleton-Pattern ---------------------------------------
    private int fahrstufe;

    private boolean reverse;

    public Gleisabschnitt gleisabschnitt;

    private Gleisabschnitt vorherigerGleisabschnitt;

    /**
     * Aktualisiert den aktiven Gleisabschnitt, falls ein zulaessiger Wechsel
     * stattfand.
     *
     * @param adrRMX
     * @param bit
     */
    public void updateGleisabschnitt(int adrRMX, int bit) {
        Gleisabschnitt g;
        try {
            g = Gleisbild.getGleisbild().getGleisabschnitt(adrRMX, bit);
        } catch (NullPointerException e) {
            //unbekannten Gleisabschnitt gefunden
            g = new Gleisabschnitt(adrRMX, bit);
            System.out.println("new Gleis");
            Gleisbild.getGleisbild().add(g);
            //ggf. Gui updaten
        }

        /* Besetzmeldungsoptimierung (Einfahren in momentan besetzten
        oder vorherigen Gleisabschnitt ist logisch ausgeschlossen) */
        if (g.equals(this.gleisabschnitt)) {
            return;
        }
        if (g.equals(this.vorherigerGleisabschnitt)) {
            return;
        }

        //aktuellen und letzten Gleisabschnitt merken
        this.vorherigerGleisabschnitt = new Gleisabschnitt(
                this.gleisabschnitt.getAdrRMX(),
                this.gleisabschnitt.getBit());
        this.gleisabschnitt = g;
        setChanged();
        notifyObservers();
        System.out.print("Gleisabshcnitt: " + adrRMX + "-" + bit);
        System.out.println("  [" + this.gleisabschnitt.getAdrRMX()
                + "-" + this.gleisabschnitt.getBit() + "  "
                + this.vorherigerGleisabschnitt.getAdrRMX()
                + "-" + this.vorherigerGleisabschnitt.getBit() + "]");
    }

    /* Getter & Setter */
    public int getFahrstufe() {
        return fahrstufe;
    }

    public void setFahrstufe(int fahrstufe) {
        this.fahrstufe = fahrstufe;
        System.out.println("Fahrstufe: " + fahrstufe);
        setChanged();
        notifyObservers();
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
        if (reverse) {
            System.out.println("rückwärts");
        } else {
            System.out.println("vorwärts");
        }
        setChanged();
        notifyObservers();
    }
}
