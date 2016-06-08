package vtfx;

import java.util.Locale;
import java.util.Observable;
import java.util.prefs.Preferences;
import javafx.scene.paint.Color;

/**
 * Kapselt die Einstellungen des Nutzers und haelt alle Aenderungen an diesen in
 * der Registry fest.
 *
 * @author Steffen Hirner, Manuel Weber
 */
public class Einstellungen extends Observable {

//----    Singleton-Pattern  ---------
    private static Einstellungen singletonInstanz;

    public static Einstellungen getEinstellungen() {

        if (singletonInstanz == null) {
            singletonInstanz = new Einstellungen();
        }
        return singletonInstanz;
    }

    private Einstellungen() {
        laden();
    }
//---------------------------------------               

    /**
     * Knoten unter dem in der Registy die Einstellungen gespeichert oder
     * ausgelesen werden.
     */
    private final Preferences registry = Preferences.userRoot()
            .node("SOFTWARE").node("VisTrain");

    /*
         Einstellungsattribute
     */
    private double massstab;

    private int betriebsart;
    private Color tachofarbe;
    private double tachored;
    private double tachogreen;
    private double tachoblue;
    private Color diagrammfarbe;
    private double diagrammred;
    private double diagrammgreen;
    private double diagrammblue;

    /**
     * Wahrheitswert ob neu befahrene, unbekannte Gleisabschnitte automatisch im
     * Gleisbild ergaenzt werden sollen.
     */
    private boolean gleisexploration;

    /**
     * Laedt die Einstellungen aus der Registry.
     */
    private void laden() {
        massstab = registry.getDouble("Massstab", 50);
        betriebsart = registry.getInt("Betriebsart", 0);
        gleisexploration = registry.
                getBoolean("Gleisexploration", false);
        tachored = registry.getDouble("Tachored", 0);
        tachogreen = registry.getDouble("Tachogreen", 0);
        tachoblue = registry.getDouble("Tachoblue", 0);
        diagrammred = registry.getDouble("Diagrammred", 0);
        diagrammgreen = registry.getDouble("Diagrammgreen", 0);
        diagrammblue = registry.getDouble("Diagrammblue", 0);
//        tachofarbe = Color.color(red, green, blue);
    }

    public double getMassstab() {
        return massstab;
    }

    public int getBetriebsart() {
        return betriebsart;
    }

    public boolean isGleisexploration() {
        return gleisexploration;
    }

    public double getDiagrammred() {
        return tachored;
    }

    public double getDiagrammgreen() {
        return diagrammgreen;
    }

    public double getDiagrammblue() {
        return diagrammblue;
    }

    public Color getTachofarbe() {
        return tachofarbe;
    }

    public double getTachored() {
        return tachored;
    }

    public double getTachogreen() {
        return tachogreen;
    }

    public double getTachoblue() {
        return tachoblue;
    }

    public Color getDiagrammfarbe() {
        return diagrammfarbe;
    }

    public void setMassstab(double massstab) {
        this.massstab = massstab;
        registry.putDouble("Massstab", massstab);
    }

    public void setBetriebsart(int betriebsart) {
        this.betriebsart = betriebsart;
        registry.putInt("Betriebsart", betriebsart);
    }

    public void setGleisexploration(boolean gleisexploration) {
        this.gleisexploration = gleisexploration;
        registry.putBoolean("Gleisexploration", gleisexploration);
    }

//    public void setTachofarbe(Color tachofarbe) {
//        this.tachofarbe = tachofarbe;
//        System.out.println(tachofarbe.getBlue());
//        registry.putInt("Red", (int) tachofarbe.getRed());
//        registry.putInt("Green", (int) tachofarbe.getGreen());
//        registry.putInt("Blue", (int) tachofarbe.getBlue());
//
//    }
    public void setTachored(double tachored) {
        this.tachored = tachored;
        registry.putDouble("Tachored", tachored);
    }

    public void setTachogreen(double tachogreen) {
        this.tachogreen = tachogreen;
        registry.putDouble("Green", tachogreen);
    }

    public void setTachoblue(double tachoblue) {
        this.tachoblue = tachoblue;
        registry.putDouble("Tachoblue", tachoblue);
    }

    public void setDiagrammred(double diagrammred) {
        this.diagrammred = diagrammred;
        registry.putDouble("Diagrammred", diagrammred);
    }

    public void setDiagrammgreen(double diagrammgreen) {
        this.diagrammgreen = diagrammgreen;
        registry.putDouble("Diagrammgreen", diagrammgreen);
    }

    public void setDiagrammblue(double diagrammblue) {
        this.diagrammblue = diagrammblue;
        registry.putDouble("Diagrammblue", diagrammblue);
    }
}
