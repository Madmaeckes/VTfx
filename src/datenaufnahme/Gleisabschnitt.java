package datenaufnahme;

/**
 * Kapselt RMX-Kanal und Bit von Gleisabschnitten.
 * Konstruktor: RMX-Kanal-Adresse, Bit
 * Setter fuer Laenge und Messstrecke(boolean)
 *
 * @author Manuel Weber
 */
public class Gleisabschnitt {

    /* RMX-Kanal und Bit-Zahl */
    private final int adrRMX;
    private final int bit;

    /**
     * Laenge des Gleisabschnitts in cm.
     */
    private double laenge;

    /**
     * Wahrheitswert, ob es sich bei dem Abschnitt um eine Messstrecke handelt
     * (ansonsten wird nicht gemessen).
     */
    private boolean messstrecke;

    /**
     * Falls Messreihe und bereits Durchfahrt stattgefunden: gemessene
     * Geschwindigkeit der letzten Durchfahrt
     * /!\ -1 wenn Fehler bei der letzten Messung
     */
    private double letzteGemesseneGeschwindigkeit;

    public Gleisabschnitt(int adrRMX, int bit) {
        this.adrRMX = adrRMX;
        this.bit = bit;
        this.laenge = 0;
        this.messstrecke = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (((Gleisabschnitt) obj).getAdrRMX() == this.adrRMX
                && ((Gleisabschnitt) obj).getBit() == this.bit) {
            return true;
        } else {
            return false;
        }
    }

    public int getAdrRMX() {
        return adrRMX;
    }

    public int getBit() {
        return bit;
    }

    public double getLaenge() {
        return laenge;
    }

    public void setLaenge(double laenge) {
        this.laenge = laenge;
    }

    public boolean isMessstrecke() {
        return messstrecke;
    }

    public void setMessstrecke(boolean messstrecke) {
        this.messstrecke = messstrecke;
    }

    public double getLetzteGemesseneGeschwindigkeit() {
        return letzteGemesseneGeschwindigkeit;
    }

    protected void setLetzteGemesseneGeschwindigkeit(double letzteGemesseneGeschwindigkeit) {
        this.letzteGemesseneGeschwindigkeit = letzteGemesseneGeschwindigkeit;
    }
}
