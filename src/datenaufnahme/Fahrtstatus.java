/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenaufnahme;

/**
 * Momentane Position, Fahrtrichtung und Fahrstufe der Lok.
 * 
 * @author Manuel Weber
 */
public class Fahrtstatus {

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
     * Gibt das Fahrtstatus-Objekt zurueck.
     *
     * @return Fahrtstatus-Objekt (Singleton-Objekt)
     */
    public static synchronized Fahrtstatus getFahrtstatus() {
        if (fahrtstatusInstanz == null) {
            fahrtstatusInstanz = new Fahrtstatus();
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
     * Aktualisiert den aktiven Gleisabschnitt, 
     * falls ein zulaessiger Wechsel stattfand.
     * @param adrRMX
     * @param bit 
     */
    public void updateGleisabschnitt(int adrRMX, int bit) {
        Gleisabschnitt g = new Gleisabschnitt(adrRMX, bit);
          
        // nur nachfolgenden Gleisabschnitt zulassen
        if (g.equals(this.gleisabschnitt)) 
            return;
        if (g.equals(this.vorherigerGleisabschnitt))
            return;
           
        //aktuellen und letzten GLeisabschnitt merken
        this.vorherigerGleisabschnitt = new Gleisabschnitt(
                this.gleisabschnitt.getAdrRMX(),
                this.gleisabschnitt.getBit());
        this.gleisabschnitt = new Gleisabschnitt(adrRMX, bit);
        System.out.println("Gleisabshcnitt: " + adrRMX + "-" + bit);
        System.out.println("[" +this.gleisabschnitt.getAdrRMX() 
                        + "-" + this.gleisabschnitt.getBit() + "  "
                       +this.vorherigerGleisabschnitt.getAdrRMX() 
                        + "-" + this.vorherigerGleisabschnitt.getBit() + "]");
    }

    /* Getter & Setter */
    public int getFahrstufe() {
        return fahrstufe;
    }

    public void setFahrstufe(int fahrstufe) {
        this.fahrstufe = fahrstufe;
        System.out.println("Fahrstufe: " + fahrstufe);
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
    }
}
