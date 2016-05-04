
package datenaufnahme;

/**
 * Verkettete Liste aller definierten Gleisabschnitte.
 *
 * @author Manuel Weber
 */
public class Gleisbild {

    // Singelton-Pattern --------------------------------------------
    /**
     * Singelton-Objekt
     */
    private static Gleisbild gleisbildInstanz;

    /**
     * Privater Konstruktor verhindert die Instanziierung von aussen.
     */
    private Gleisbild() {
        size = 0;
        Gleisabschnitt g1 = new Gleisabschnitt(0, 2);
        Gleisabschnitt g2 = new Gleisabschnitt(0, 3);
        Gleisabschnitt g3 = new Gleisabschnitt(0, 4);
        Gleisabschnitt g4 = new Gleisabschnitt(0, 5);
        Gleisabschnitt g5 = new Gleisabschnitt(1, 1);
        g1.setLaenge(10);
        g2.setLaenge(10);
        g3.setLaenge(10);
        g4.setLaenge(10);
        g5.setLaenge(10);
        g1.setMessstrecke(false);
        g2.setMessstrecke(true);
        g3.setMessstrecke(true);
        g4.setMessstrecke(true);
        g5.setMessstrecke(false);
        add(g1);
        add(g2);
        add(g3);
        add(g4);
        add(g5);
    }

    /**
     * Gibt das Gleisbild-Objekt zurueck.
     *
     * @return Gleisbild-Objekt (Singleton-Objekt)
     */
    public static synchronized Gleisbild getGleisbild() {
        if (gleisbildInstanz == null) {
            gleisbildInstanz = new Gleisbild();
        }
        return gleisbildInstanz;
    }

    /**
     * Unterbindet das Klonen des Objekts (Singleton)
     */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    // Ende Singleton-Pattern ---------------------------------------

    private Gleisabschnitt[][] g = new Gleisabschnitt[112][10];

    private int size;

    /**
     * Fuegt den uebergebenen Gleisabschnitt am Ende der vorhandenen Gleise
     * hinzu.
     *
     * @param gleisabschnitt
     */
    public void add(Gleisabschnitt gleisabschnitt) {
        g[gleisabschnitt.getAdrRMX()][gleisabschnitt.getBit()] 
                = gleisabschnitt;
        size++;
    }

    public void del(Gleisabschnitt gleisabschnitt) {
        g[gleisabschnitt.getAdrRMX()][gleisabschnitt.getBit()] 
                = null;
        size--;
    }
    
    protected Gleisabschnitt getGleisabschnitt(int adr, int bit) {
        return g[adr][bit];
    }

    public int size() {
        return size();
    }
}
