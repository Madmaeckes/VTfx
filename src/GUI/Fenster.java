/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javafx.scene.paint.Color;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.animation.Animation;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 *
 * @author Manuel
 */
public class Fenster extends TabPane {

    private final Tab momentaneGeschwTab;
    private final Tab graphTab;
    //private final Button btn;
    private SaulenDiagramm saulenDiagramm;
    private Ball trigger;
    private Ball ball;
    private Ball ball2;
    
    private Text text;

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

        trigger = new Ball(0, 0);
        ball = new Ball(1, 100);
        ball2 = new Ball(2, 200);
        text = new Text("Menü");
        text.setFill(Color.WHITE);
        text.setBoundsType(TextBoundsType.VISUAL); 
        StackPane stack = new StackPane();
        stack.getChildren().add(trigger);
        stack.getChildren().add(text);
        trigger.setOnMouseEntered((MouseEvent me) -> {
            
            if (ball.timeline.getStatus() != Animation.Status.RUNNING) {
                ball2.setVisible(true);
                ball2.timeline.play();
                ball.setVisible(true);
                ball.timeline.play();
            } else {
                ball2.timeline.pause();
                ball.timeline.pause();
            }
        });
        trigger.setOnMouseExited((MouseEvent me) -> {
            if (ball.timeline.getStatus() != Animation.Status.RUNNING) {

                ball2.setVisible(false);
                ball2.reset();
                ball.setVisible(false);
                ball.reset();
                //ball.timeline.play();
            } else {
                ball2.setVisible(false);
                ball2.reset();
                ball.setVisible(false);
                ball.reset();
                //ball.timeline.pause();
            }
        });
        saulenDiagramm = new SaulenDiagramm();

        StackPane root = new StackPane();
        root.getChildren().add(ball);
        root.getChildren().add(ball2);
        root.getChildren().add(stack);
      //  root.getChildren().add(trigger);
        BorderPane border2 = new BorderPane();
        border2.setTop(root);
        BorderPane border = new BorderPane();
        border.setRight(border2);
       // root.setRight(ball);
       // root.setRight(trigger);
    //    momentaneGeschwTab.setContent(root);
        momentaneGeschwTab.setContent(border);
        graphTab.setContent(saulenDiagramm);
        getTabs().addAll(momentaneGeschwTab, graphTab);

    }
    
   
}
