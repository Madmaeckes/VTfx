package GUI.einstellungen;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * TextFeld welches als Eingabe ausschließlich Zahlen mit eine leange von
 * maximal 5 Zeichen erlaubt.
 *
 * @author Manuel Eble
 */
public class NumberTextField extends TextField {

    public NumberTextField(Boolean decimal) {

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
        
        if ((getText().length() < 5) || text == "") {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if ((getText().length() < 5) || text == "") {
            super.replaceSelection(text);
        }
    }

}
