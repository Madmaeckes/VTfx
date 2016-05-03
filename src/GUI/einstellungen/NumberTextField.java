package GUI.einstellungen;

import javafx.scene.control.TextField;

/**
 * TextFeld welches als Eingabe ausschließlich Zahlen und eine maximal 5 Zeichen
 * erlaubt.
 *
 * @author Manuel Eble
 */
public class NumberTextField extends TextField {

    @Override
    public void replaceText(int start, int end, String text) {
        if ((text.matches("[0-9]") && getText().length() < 5) || text == "") {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if ((text.matches("[0-9]") && getText().length() < 5) || text == "") {
            super.replaceSelection(text);
        }
    }

}
