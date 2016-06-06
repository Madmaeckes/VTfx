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
    private int red;
    private int green;
    private int blue;

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
        red = registry.getInt("Red", 100);
        green = registry.getInt("Green", 0);
        blue = registry.getInt("Blue", 0);
        tachofarbe = Color.rgb(red, green, blue);
    }

    public void setMassstab(double massstab) {
        this.massstab = massstab;
        registry.putDouble("Massstab", massstab);
    }

    public void setBetriebsart(int betriebsart) {
        this.betriebsart = betriebsart;
        registry.putInt("Betriebsart", betriebsart);
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

    public Color getTachofarbe() {
        return tachofarbe;
    }

    public void setGleisexploration(boolean gleisexploration) {
        this.gleisexploration = gleisexploration;
        registry.putBoolean("Gleisexploration", gleisexploration);
    }

    public void setTachofarbe(Color tachofarbe) {
        this.tachofarbe = tachofarbe;
        registry.putInt("Red", (int) tachofarbe.getRed());
        registry.putInt("Green", (int) tachofarbe.getGreen());
        registry.putInt("Blue", (int) tachofarbe.getBlue());

    }
}
