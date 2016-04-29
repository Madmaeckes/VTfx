/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Manuel
 */
public class Einstellungsfenster {
    
    public Einstellungsfenster() {
        final Stage stage = new Stage();

        //create root node of scene, i.e. group
        Group rootGroup = new Group();

        //create scene with set width, height and color
        Scene scene = new Scene(rootGroup, 300, 400, Color.WHITESMOKE);

        //set scene to stage
        stage.setScene(scene);

        //set title to stage
        stage.setTitle("Einstellungen");

        //center stage on screen
        stage.centerOnScreen();

        //show the stage
        stage.show();

        //add some node to scene
        Text text = new Text(20, 110, "JavaFX");
        text.setFill(Color.DODGERBLUE);
        text.setEffect(new Lighting());
        text.setFont(Font.font(Font.getDefault().getFamily(), 50));

        //add text to the main root group
        rootGroup.getChildren().add(text);
        
        //Rasterlayout
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(2);
        tilePane.setAlignment(Pos.CENTER);
        
        Button button1 = new Button("Hallo");
        Text text1 = new Text(20, 20, "1");
        Text text2 = new Text(20, 20, "2");
        Text text3 = new Text(20, 20, "3");
        
        tilePane.getChildren().addAll(text1, text2, text3);
        tilePane.getChildren().add(button1);
        
        rootGroup.getChildren().add(tilePane);
        
    }
}
