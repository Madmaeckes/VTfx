/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vtfx;

import java.util.Locale;
import java.util.Observable;
import java.util.prefs.Preferences;

/**
 *
 * @author 612st
 */
public class Einstellungen extends Observable {
    
//    Singleton-Pattern
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
        
        	/**
	 * Knoten unter dem in der Registy die Einstellungen gespeichert oder
	 * ausgelesen werden.
	 */
	private final Preferences registry = Preferences.userRoot()
			.node("SOFTWARE").node("VisTrain");
        
        private double massstab;
        private int betriebsart;
        
        /**
	 * Laedt die Einstellungen aus der Registry.
	 */
	private void laden() {
		massstab = registry.getDouble("Massstab", 50);
		betriebsart = registry.getInt("Betriebsart", 0);
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

}
