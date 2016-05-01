/* ....Show License.... */
package GUI.geschwindigkeitsanzeige;

import java.util.Calendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Geschwindigkeitsanzeige extends Parent {

    private Calendar calendar = Calendar.getInstance();
    private Digit[] digits;
    private Timeline delayTimeline, secondTimeline;

    private Circle circle;
    private char[] charArray;

    public Geschwindigkeitsanzeige(Color onColor, Color offColor) {
        // create effect for on LEDs
        //Glow onEffect = new Glow(1.7f);
        Glow onEffect = new Glow(0.5);
        onEffect.setInput(new InnerShadow());
        // create effect for on dot LEDs
        Glow onDotEffect = new Glow(1.7f);
        onDotEffect.setInput(new InnerShadow(5, Color.BLACK));
        // create effect for off LEDs
        //InnerShadow offEffect = new InnerShadow();
        InnerShadow offEffect = new InnerShadow(1, Color.WHITESMOKE);
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

//        // create dots
//        Group dots = new Group(
//                new Circle(80 + 54 + 20, 44, 6, onColor),
//                new Circle(80 + 54 + 17, 64, 6, onColor),
//                new Circle((80 * 3) + 54 + 20, 44, 6, onColor),
//                new Circle((80 * 3) + 54 + 17, 64, 6, onColor));
//        dots.setEffect(onDotEffect);
//        getChildren().add(dots);
//        // update digits to current time and start timer to update every second
//        refreshClocks();
    }

    private void refreshClocks() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        digits[0].showNumber(hours / 10);
        digits[1].showNumber(hours % 10);
        digits[2].showNumber(minutes / 10);
        digits[3].showNumber(minutes % 10);
        digits[4].showNumber(seconds / 10);
        digits[5].showNumber(seconds % 10);
    }

    public void setMomentaneGeschw(double geschw) {
        //Auf zwei Nachkommastellen runden
        String s = Double.toString(Math.round(geschw * 100) / 100.0);
        //Ziffern des double geschw in String[] umspeichern
        String[] digits2 = s.split("(?<=.)");
                //Vorangehende Ziffern bei kleineren Geschwindigkeiten ausschalten
                if (geschw <= 10) {
                    digits[0].showNumber(10);
                    digits[1].showNumber(10);
                    digits[2].showNumber(Integer.parseInt(digits2[0]));
                    digits[3].showNumber(Integer.parseInt(digits2[2]));
                    digits[4].showNumber(Integer.parseInt(digits2[3]));
                } else if (geschw <= 100) {
                    digits[0].showNumber(10);
                    digits[1].showNumber(Integer.parseInt(digits2[0]));
                    digits[2].showNumber(Integer.parseInt(digits2[1]));
                    digits[3].showNumber(Integer.parseInt(digits2[3]));
                    digits[4].showNumber(Integer.parseInt(digits2[4]));
                } else {
                    digits[0].showNumber(Integer.parseInt(digits2[0]));
                    digits[1].showNumber(Integer.parseInt(digits2[1]));
                    digits[2].showNumber(Integer.parseInt(digits2[2]));
                    digits[3].showNumber(Integer.parseInt(digits2[4]));
                    digits[4].showNumber(Integer.parseInt(digits2[5]));
                }
    }

    public void play() {
        // wait till start of next second then start a timeline to call refreshClocks() every second
        delayTimeline = new Timeline();
        delayTimeline.getKeyFrames().add(
                new KeyFrame(new Duration(1000 - (System.currentTimeMillis() % 1000)), (ActionEvent event) -> {
                    if (secondTimeline != null) {
                        secondTimeline.stop();
                    }
                    secondTimeline = new Timeline();
                    secondTimeline.setCycleCount(Timeline.INDEFINITE);
                    secondTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), (ActionEvent event1) -> {
                        refreshClocks();
                    }));
                    secondTimeline.play();
                }));
        delayTimeline.play();
    }

    public void stop() {
        delayTimeline.stop();
        if (secondTimeline != null) {
            secondTimeline.stop();
        }
    }
}
