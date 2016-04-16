/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Rectangle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

import javafx.animation.Animation;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Manuel
 */
public class Fenster extends TabPane {

    private final Tab momentaneGeschwTab;
    private final Tab graphTab;
    //private final Button btn;
    private SaulenDiagramm saulenDiagramm;
    private Ball ball;
    private Ball trigger;

    public Fenster() {

        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        momentaneGeschwTab = new Tab("Momentane Geschw.");
        graphTab = new Tab("Säulendiagramm");
      
//        btn = new Button("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });

        ball = new Ball(0);
        trigger = new Ball(1);
        trigger.setOnMouseEntered((MouseEvent me) -> {
            if (ball.timeline.getStatus() != Animation.Status.RUNNING) {
                ball.timeline.play();
            } else {
                ball.timeline.pause();
            }
        });
        trigger.setOnMouseExited((MouseEvent me) -> {
            if (ball.timeline.getStatus() != Animation.Status.RUNNING) {
                ball.timeline.play();
            } else {
                ball.timeline.pause();
            }
        });
        saulenDiagramm = new SaulenDiagramm();

        StackPane root = new StackPane();
        root.getChildren().add(ball);
        root.getChildren().add(trigger);
        momentaneGeschwTab.setContent(root);
        graphTab.setContent(saulenDiagramm);
        getTabs().addAll(momentaneGeschwTab, graphTab);

    }
    
   
}
