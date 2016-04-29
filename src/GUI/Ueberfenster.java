/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Manuel Eble
 */
public class Ueberfenster {
    
    public Ueberfenster() {
        final Stage stage = new Stage();

        //create root node of scene, i.e. group
        Group rootGroup = new Group();

        //create scene with set width, height and color
        Scene scene = new Scene(rootGroup, 300, 400, Color.WHITESMOKE);

        //set scene to stage
        stage.setScene(scene);

        //set title to stage
        stage.setTitle("Über VisTrain");

        //center stage on screen
        stage.centerOnScreen();

        //show the stage
        stage.show();

        //add some node to scene
        Text text = new Text(20, 110, "VisTrain");
        text.setFill(Color.DODGERBLUE);
        text.setEffect(new Lighting());
        text.setFont(Font.font(Font.getDefault().getFamily(), 50));

        //add text to the main root group
        rootGroup.getChildren().add(text);
    }
}
