
package datenaufnahme;

/**
 * Kapselt RMX-Kanal und Bit von Gleisabschnitten.
 * 
 * @author Manuel Weber
 */
public class Gleisabschnitt {

    /* RMX-Kanal und Bit-Zahl */
    private int adrRMX;
    private int bit;
    
    /***
     * Laenge des Gleisabschnitts in cm.
     */
    private int laenge;
    
    /***
     * Wahrheitswert, ob es sich bei dem Abschnitt um eine Messstrecke handelt
     * (ansonsten wird nicht gemessen).
     */
    private boolean messstrecke;
    

    public Gleisabschnitt(int adrRMX, int bit) {
        this.adrRMX = adrRMX;
        this.bit = bit;
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

    public void setAdrRMX(int adrRMX) {
        this.adrRMX = adrRMX;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }

    public int getLaenge() {
        return laenge;
    }

    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    public boolean isMessstrecke() {
        return messstrecke;
    }

    public void setMessstrecke(boolean messstrecke) {
        this.messstrecke = messstrecke;
    }
    
}