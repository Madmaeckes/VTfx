package GUI.geschwindigkeitsanzeige;

import java.text.DecimalFormat;
import java.util.Calendar;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Creates a speedometer out of five digits.
 * 
 * @author Manuel Eble
 */
public class Geschwindigkeitsanzeige extends Parent {

    private final Digit[] digits;

    private final Circle circle;

    private final Color onColor;
    private final Color offColor;
    private final Glow onEffect;
    private final Glow offEffect;
    
    private Label kmhLabel;

    public Geschwindigkeitsanzeige() {
        
        onColor = Color.GREENYELLOW;
        // create effect for on LEDs
        //Glow onEffect = new Glow(1.7f);
        onEffect = new Glow(0.1);
        onEffect.setInput(new InnerShadow(5, Color.BLACK));
       // this.setEffect(onEffect);
        // create effect for on dot LEDs
        Glow onDotEffect = new Glow(0.1);
        onDotEffect.setInput(new InnerShadow(5, Color.BLACK));
        
        offEffect = new Glow();
        offEffect.setInput(new InnerShadow(5, Color.LIGHTGREY));
        
        offColor = Color.ALICEBLUE;
        // create effect for off LEDs
        //InnerShadow offEffect = new InnerShadow();
  //      InnerShadow offEffect = new InnerShadow(0, Color.WHITE);
        // create digits
        digits = new Digit[5];
        for (int i = 0; i < 5; i++) {
            Digit digit = new Digit(onColor, offColor, onEffect, offEffect);
            //digit.setLayoutX(i * 80 + ((i + 1) % 2) * 20);
            digit.setLayoutX(i * 80);
            digits[i] = digit;
            getChildren().add(digit);
        }

        circle = new Circle(80 * 3 - 23, 100, 7, onColor);
        circle.setEffect(onDotEffect);
        getChildren().add(circle);
        
      //  kmhLabel = new Label("parent");
      //  kmhLabel.setLayoutX(5 * 80 - 20);
      //  kmhLabel.setLayoutY(95);
      //  getChildren().add(kmhLabel);
    }

    /**
     * Gibt die uebergebene Geschwindigkeit auf der digitalen 
     * Geschwindigkeitsanzeige aus.
     * Zulaessiger Wertebereich: [0, 999.99]
     * Rundet hierfür auf 2 Nachkommastellen, 
     * gibt Zahlen >999.99 als exakt 999.99
     * und negative Zahlen als exakt 0 aus!
     * 
     * @param geschw 
     */
    public void setMomentaneGeschw(double geschw) {
        //Auf zwei Nachkommastellen runden
        DecimalFormat f = new DecimalFormat("#0.00");
        String s = f.format(geschw);
        //Ziffern din String[] umspeichern
        String[] digits2 = s.split("(?<=.)");

        //Zu kleine Zahlen abfangen
        if (geschw < 0) {
            setMomentaneGeschw(0);
            return;
        }
        //Vorangehende Ziffern bei kleineren Geschwindigkeiten ausschalten
        if (geschw < 10) {
            digits[0].showNumber(10);
            digits[1].showNumber(10);
            digits[2].showNumber(Integer.parseInt(digits2[0]));
            digits[3].showNumber(Integer.parseInt(digits2[2]));
            digits[4].showNumber(Integer.parseInt(digits2[3]));
        } else if (geschw < 100) {
            digits[0].showNumber(10);
            digits[1].showNumber(Integer.parseInt(digits2[0]));
            digits[2].showNumber(Integer.parseInt(digits2[1]));
            digits[3].showNumber(Integer.parseInt(digits2[3]));
            digits[4].showNumber(Integer.parseInt(digits2[4]));
        } else if (geschw < 999.99) {
            digits[0].showNumber(Integer.parseInt(digits2[0]));
            digits[1].showNumber(Integer.parseInt(digits2[1]));
            digits[2].showNumber(Integer.parseInt(digits2[2]));
            digits[3].showNumber(Integer.parseInt(digits2[4]));
            digits[4].showNumber(Integer.parseInt(digits2[5]));
        } else { //Zu große Zahlen abfangen
            setMomentaneGeschw(999.99);
        }
    }

    public void setFarbe(Color farbe) {
        for (int i = 0; i < 5; i++) {
            digits[i].setFarbe(farbe);
        }
        circle.setFill(farbe);
    }
}
