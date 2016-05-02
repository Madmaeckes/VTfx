/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenaufnahme;

import java.util.HashSet;
import java.util.Set;

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
        gleisabschnitte = new HashSet<>();
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

    private Set<Gleisabschnitt> gleisabschnitte;
    
    private Gleisabschnitt index;
    
    private Gleisabschnitt last;

    /**
     * Fuegt den uebergebenen Gleisabschnitt am Ende der vorhandenen Gleise
     * hinzu.
     *
     * @param gleisabschnitt
     */
    public void add(Gleisabschnitt gleisabschnitt) {
        if (gleisabschnitte.size() == 0) {
            index = gleisabschnitt;
            last = gleisabschnitt;
        } else {
            last.setNext(gleisabschnitt);
            last = gleisabschnitt;
        }
        gleisabschnitte.add(gleisabschnitt);
    }

    /**
     * Fuegt einen neuen Gleisabschnitt (gleisabschnitt) als Nachfolger eines
     * bestimmten Vorgaengergleisabschnitts (vorgaenger) ein.
     *
     * @param gleisabschnitt
     * @param vorgaenger
     */
    public void add(Gleisabschnitt gleisabschnitt, Gleisabschnitt vorgaenger) throws Exception{
        if (!gleisabschnitte.contains(vorgaenger)) {
            throw new Exception("Vorgaengergleis existiert nicht!");
        }
        gleisabschnitt.setNext(vorgaenger.getNext());
        vorgaenger.setNext(gleisabschnitt);
        gleisabschnitte.add(gleisabschnitt);
    }

    public int size() {
        return gleisabschnitte.size();
    }
}
