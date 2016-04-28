package vtfx;

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
        //messung abbrechen
        if (VTfx.rmxVerbindung.getVerbindungsStatus() == RMXconnector.VERBUNDEN) {
            VTfx.rmxVerbindung.trennen();
        }
    }

}
