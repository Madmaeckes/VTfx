/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.geometry.Pos;
import static javafx.geometry.Pos.TOP_LEFT;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 *
 * @author Manuel
 */
public class Footer extends HBox{
    
    public Label label1;
    public Label label2;
    public Region region1;
    
    public Footer() {
        label1 = new Label("This is the footer!");
        label2 = new Label("The footer is awesome.");
        region1 = new Region();
        region1.setPrefWidth(200);
        label1.setAlignment(Pos.BASELINE_LEFT);
        label2.setAlignment(Pos.BASELINE_RIGHT); 
        getChildren().addAll(label1, region1, label2);
        setHgrow(region1, Priority.ALWAYS);
    }


}
