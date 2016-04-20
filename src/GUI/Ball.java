/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.Constants.*;
import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author Manuel
 */
public class Ball extends Circle {

    private static final Duration DURATION = Duration.millis(300);
    private static double FLOOR = HEIGHT / 2 - HEIGHT_CORRECTION - 10;
    public Timeline timeline;

    public Ball(final int index, double floor) {
        initBall(index, floor);
    }

    private void initBall(final int index, double floor) {
        this.FLOOR = floor;
        setRadius(BALL_RADIUS);
        setCenterX((index + 1) * 2 * BALL_RADIUS + index * SPACE_X);
        setCenterY(INFOPANEL_HEIGHT + BALL_RADIUS);
        setCache(true);
        setFill(BALL_GRADIENT);
        createTimeline();
//        setOnMouseEntered((MouseEvent me) -> {
//            if (timeline.getStatus() != Status.RUNNING) {
//                timeline.play();
//            } else {
//                timeline.pause();
//            }
//        });
//        setOnMouseExited((MouseEvent me) -> {
//            if (timeline.getStatus() != Status.RUNNING) {
//                timeline.play();
//            } else {
//                timeline.pause();
//            }
//        });

    }

    private void createTimeline() {
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);

        final KeyValue kv = new KeyValue(
                translateYProperty(),
                FLOOR,
                Interpolator.SPLINE(0.1f, 0, 0.1f, 0));
        final KeyFrame kf = new KeyFrame(DURATION, kv);
        timeline.getKeyFrames().setAll(kf);
    }

    public void reset() {
        timeline.stop();
        setTranslateY(0);
    }
}
