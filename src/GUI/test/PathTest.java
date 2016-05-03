/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.test;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import static javafx.util.Duration.seconds;

/**
 *
 * @author Manuel
 */
public class PathTest extends Pane {

    private PathTransition pathTransition;

    public PathTest() {
        
        StackPane stack = new StackPane();
        Rectangle rect = new Rectangle(0, 0, 40, 40);
        
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setFill(Color.ORANGE);
        //this.getChildren().add(rect);
        stack.getChildren().add(rect);
//        Ball ball = new Ball(3, 0);
//        this.getChildren().add(ball);
        //   new MoveTo(20, 20),
//                        new CubicCurveTo(380, 0, 220, 120, 120, 80), 
//                new CubicCurveTo(0, 40, 0, 240, 220, 120)
        Path path = new Path(new MoveTo(0, 0), new CubicCurveTo(0, 0, 0, 0, -200, 200));
        path.setStroke(Color.DODGERBLUE);
        path.getStrokeDashArray().setAll(5d, 5d);
        //this.getChildren().add(path);
        stack.getChildren().add(path);
        this.getChildren().add(stack);
        
        pathTransition = new PathTransition(seconds(1), path, rect);
//      pathTransition = new PathTransition(seconds(0.5), path, ball);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(true);

    }

    public void play() {
        pathTransition.play();
    }

    public void stop() {
        pathTransition.stop();
    }
   
    public void reset() {
        pathTransition.jumpTo(Duration.ZERO);
        pathTransition.stop();
    }
}
