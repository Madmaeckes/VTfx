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
        anzahlMessstrecken = 0;

        //Test-Gleisbild
        Gleisabschnitt g1 = new Gleisabschnitt(0, 2);
        Gleisabschnitt g2 = new Gleisabschnitt(0, 3);
        Gleisabschnitt g3 = new Gleisabschnitt(0, 4);
        Gleisabschnitt g4 = new Gleisabschnitt(0, 5);
        Gleisabschnitt g5 = new Gleisabschnitt(1, 1);
        g1.setLaenge(56.2);
        g2.setLaenge(23);
        g3.setLaenge(23);
        g4.setLaenge(23);
        g5.setLaenge(23);
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
     *
     * @return
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    // Ende Singleton-Pattern ---------------------------------------
    private final Gleisabschnitt[][] gleise = new Gleisabschnitt[112][10];

    /**
     * Anzahl der definierten Gleisabschnitte
     */
    private int size;

    /**
     * Anzahl der definierten Messstrecken
     */
    private int anzahlMessstrecken;

    /**
     * Fuegt den uebergebenen Gleisabschnitt dem Gleisbild hinzu. Existiert
     * bereits ein Gleisabschnitt mit gleicher Adresse/Bit-Zahl, so wird dieser
     * ueberschrieben.
     *
     * @param gleisabschnitt
     */
    public void add(Gleisabschnitt gleisabschnitt) {
        int adr = gleisabschnitt.getAdrRMX();
        int bit = gleisabschnitt.getBit();
        System.out.println("added Gleis " + adr + "-" + bit + 
                " Messstrecke=" + gleisabschnitt.isMessstrecke());
        if (gleise[adr][bit] == null) {
            size++; //neu angelegt
        }
        if (gleisabschnitt.isMessstrecke()) {
            if (gleise[adr][bit] == null || !gleise[adr][bit].isMessstrecke()) {
                //Messstrecke neu angelegt oder zuvor keine Messstrecke
                anzahlMessstrecken++;
            }
        }
        gleise[adr][bit] = gleisabschnitt;
    }

    /**
     * Entfernt einen angelegten Gleisabschnitt aus dem Gleisbild.
     *
     * @param gleisabschnitt
     * @throws java.lang.NullPointerException wenn der uebergebene Geisabschnitt
     * nicht im Gleisbild enthalten ist.
     */
    public void del(Gleisabschnitt gleisabschnitt) throws NullPointerException {
        int adr = gleisabschnitt.getAdrRMX();
        int bit = gleisabschnitt.getBit();
        Gleisabschnitt g = getGleisabschnitt(adr, bit);
        size--;
        if (g.isMessstrecke()) {
            anzahlMessstrecken--; //entfernte Messstrecke
        }
        gleise[adr][bit] = null;
    }

    /**
     * Gibt das Gleisabschnitt-Objekt das von der uebergebenen Adress- und
     * Bit-Zahl identifiziert wird.
     *
     * Gibt null zurueck wenn der gesuchte Gleisabschnitt nicht im Gleisbild
     * enthalten ist.
     *
     * @param adr
     * @param bit
     * @return gleisabschnitt mit adr/bit
     * @throws java.lang.NullPointerException wenn der uebergebene Geisabschnitt
     * nicht im Gleisbild enthalten ist.
     */
    protected Gleisabschnitt getGleisabschnitt(int adr, int bit)
            throws NullPointerException {
        if (gleise[adr][bit] == null) {
            throw new NullPointerException("Gleisabschnitt nicht gefunden.");
        }
        return gleise[adr][bit];
    }

    /**
     * @return Anzahl aller angelegten Gleisabschnitte
     */
    public int size() {
        return size;
    }

    /**
     * @return Anzahl aller angelegten Messstrecken
     */
    public int getAnzahlMessstrecken() {
        return anzahlMessstrecken;
    }
}
