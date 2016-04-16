/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
 
/**
 *
 * @author Manuel
 */
public final class Constants {
 
    private Constants() {
    }
    public static final String TITLE = "Bouncing Balls Demo";
    public static final int NR_OF_BALLS = 5;
    public static final float WIDTH = 700f;
    public static final float HEIGHT = 400f;
    public static final int INFOPANEL_HEIGHT = 50;
    // due to native OS stripe in stage
    public static final float HEIGHT_CORRECTION = 30f;
    public static final float BALL_RADIUS = 40f;
    public static final int SPACE_X = (int) ((WIDTH - NR_OF_BALLS * BALL_RADIUS * 2) / (NR_OF_BALLS + 1));
    private static final Stop[] STOPS = new Stop[]{new Stop(0, Color.WHITE), new Stop(1, Color.BLACK)};
    public static final RadialGradient BALL_GRADIENT = new RadialGradient(45, 0.35f, 0, 0, 1, true, CycleMethod.NO_CYCLE, STOPS);
}

