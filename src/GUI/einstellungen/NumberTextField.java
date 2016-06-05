package GUI.einstellungen;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * TextFeld welches mithilfe uebergebener Parameter verschiedene Eingaben
 * erlaubt.
 *
 * @author Manuel Eble
 */
public class NumberTextField extends TextField {

    //Maximale Zeichenlaenge der Eingabe
    private int laenge;
    //Pattern fuer die Eingabe
    private String pattern;

    /**
     * Verarbeitet uebergebene Parameter und erlaubt Dezimalzahl falls
     * decimal=true
     *
     * @param decimal Wird true uebergeben sind dezimalzahlen erlaubt
     * @param laenge Setzt maximallaenge an Zeichen
     * @param pattern Setzt das Zeichenpattern
     */
    public NumberTextField(Boolean decimal, int laenge, String pattern) {

        this.laenge = laenge;
        this.pattern = pattern;

        if (decimal) {

            DecimalFormat format = new DecimalFormat("#.0");

            this.setTextFormatter(new TextFormatter<>(c
                    -> {
                if (c.getControlNewText().isEmpty()) {
                    return c;
                }

                ParsePosition parsePosition = new ParsePosition(0);
                Object object = format.parse(c.getControlNewText(), parsePosition);

                if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
                    return null;
                } else {
                    return c;
                }
            }));
        }

    }

    @Override
    public void replaceText(int start, int end, String text) {

        if ((text.matches(this.pattern) && getText().length() < this.laenge) || text == "") {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if ((text.matches(this.pattern) && getText().length() < this.laenge) || text == "") {
            super.replaceSelection(text);
        }
    }

}
