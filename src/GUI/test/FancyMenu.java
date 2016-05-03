/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.test;

import GUI.test.Ball;
import javafx.animation.Animation;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 *
 * @author Manuel
 */
public class FancyMenu extends StackPane{
    
    //private final Button btn;
    private Ball trigger;
    private Ball ball;
    private Ball ball2;

    private Text text;
    
    public FancyMenu(){
        
        PathTest pathTest = new PathTest();
        
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
                pathTest.setVisible(true);
                pathTest.play();
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
                pathTest.setVisible(false);
                pathTest.reset();
                //ball.timeline.play();
            } else {
                ball2.setVisible(false);
                ball2.reset();
                ball.setVisible(false);
                ball.reset();
                //ball.timeline.pause();
            }
        });
        this.getChildren().add(pathTest);
        this.getChildren().add(ball);
        this.getChildren().add(ball2);
        this.getChildren().add(stack);
    }
}
