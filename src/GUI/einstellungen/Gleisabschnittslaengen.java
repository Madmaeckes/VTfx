/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.einstellungen;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Manuel Eble
 */
public class Gleisabschnittslaengen extends GridPane {

    public Label label;
    public NumberTextField textField;
    public Button button;

    public Gleisabschnittslaengen() {
        this.setHgap(10);
        setVgap(10);
        
        label = new Label("Anzahl Sensoren:");
        textField = new NumberTextField();
        button = new Button("Enter");
        button.setOnAction((ActionEvent e) -> {
            setAnzahlSensoren(Integer.parseInt(textField.getText()));
        });

        RowConstraints rowinfo3 = new RowConstraints();
        rowinfo3.setPercentHeight(50);
        setPadding(new Insets(8, 8, 8, 8));

        getRowConstraints().add(rowinfo3);
        GridPane.setConstraints(textField, 1, 0);
        GridPane.setConstraints(button, 2, 0);
        getChildren().addAll(label, textField, button);
    }
    
    public void setAnzahlSensoren(Integer anzahl) {
        for (int i = 0; i < anzahl; i++) {
            System.out.println(i);
        }
    }
}
