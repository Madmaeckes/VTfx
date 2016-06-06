package vtfx;

import datenaufnahme.Fahrtstatus;
import java.util.concurrent.TimeoutException;
import rmx.RMXconnector;

/**
 * Stellt die zentralen Programm-Funktionen zum Aufruf durch die GUI bereit.
 *
 * @author Manuel Weber
 */
public class Funktionen {

    /**
     * Stellt eine Verbindung zur RMX-PC-Zentrale her, sofern noch keine
     * besteht.
     *
     * @throws TimeoutException bei Zeitueberschreitung des Verbindungsaufbaus
     */
    public static void verbinden() throws Exception {

        VTfx.rmxVerbindung.verbinden();
        while (VTfx.rmxVerbindung.getVerbindungsStatus() == RMXconnector.VERBINDEN) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (VTfx.rmxVerbindung.getVerbindungsStatus() != RMXconnector.VERBUNDEN) {
            throw new TimeoutException(
                    "Verbindung zur RMX-PC-Zentrale fehlgeschlagen.");
        }

    }

    /**
     * Trennt die Verbindung zur RMX-PC-Zentrale, sofern vorhanden.
     */
    public static void trennen() {
        Fahrtstatus.getMessung().stop(); //falls Messung aktiv -> stoppen
        if (VTfx.rmxVerbindung.getVerbindungsStatus() 
                == RMXconnector.VERBUNDEN) {
            VTfx.rmxVerbindung.trennen();
        }
    }

    /**
     * Startet eine Messung 
     * @throws Exception wenn keine Verbindung zur RMX-PC-Zentrale besteht
     *                     oder das Gleisbild nichthinreichend definiert ist.
     */
    public static void messungStarten() throws Exception {
        if (VTfx.rmxVerbindung.getVerbindungsStatus() 
                == RMXconnector.VERBUNDEN) {
            Fahrtstatus.getFahrtstatus().getMessung().start();
        } else {
            throw new Exception ("Nur bei bestehender Verbindung "
                    + "mit der RMX-PC-Zentrale kann eine Messung "
                    + "gestartet werden!");
        }
    }
    
    /**
     * Stoppt eine laufende Messung
     */
    public static void messungStoppen() {
     Fahrtstatus.getFahrtstatus().getMessung().stop();
    }
}
