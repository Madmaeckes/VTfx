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
        Gleisabschnitt g1 = new Gleisabschnitt(0,2);
        Gleisabschnitt g2 = new Gleisabschnitt(0,3);
        Gleisabschnitt g3 = new Gleisabschnitt(0,4);
        Gleisabschnitt g4 = new Gleisabschnitt(0,5);
        Gleisabschnitt g5 = new Gleisabschnitt(1,1);
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
