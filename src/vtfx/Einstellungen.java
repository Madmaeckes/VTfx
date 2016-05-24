
package vtfx;

import java.util.Locale;
import java.util.Observable;
import java.util.prefs.Preferences;

/**
 * Kapselt die Einstellungen des Nutzers und haelt alle Aenderungen an
 * diesen in der Registry fest.
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
        
        /**
         * Wahrheitswert ob neu befahrene, unbekannte Gleisabschnitte
         * automatisch im Gleisbild ergaenzt werden sollen. 
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

        public void setGleisexploration(boolean gleisexploration) {
            this.gleisexploration = gleisexploration;
            registry.putBoolean("Gleisexploration", gleisexploration);
        }       
}
