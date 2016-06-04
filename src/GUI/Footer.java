/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

/**
 * A footer which shows status information about the connection and the
 * measurement.
 *
 * @author Manuel Eble
 */
public class Footer extends HBox {

    public Label verbindungsstatusLabel;
    public Label messungsstatusLabel;
    public Region region1;

    public Footer() {

        setStyle("-fx-border-top-style: solid;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: grey;");

        messungsstatusLabel = new Label("");
        verbindungsstatusLabel = new Label("Getrennt ");
        verbindungsstatusLabel.setTextFill(Color.RED);
        region1 = new Region();
        region1.setPrefWidth(200);
        getChildren().addAll(messungsstatusLabel, region1, verbindungsstatusLabel);
        setHgrow(region1, Priority.ALWAYS);
    }

    // Setter-Methoden
    public void setMessungsstatus(String messungsstatus) {
        messungsstatusLabel.setText(messungsstatus);
    }

    public void setMessungsstatusFarbe(Paint farbe) {
        messungsstatusLabel.setTextFill(farbe);
    }

    public void setVerbindungsstatus(String verbindungsstatus) {
        verbindungsstatusLabel.setText(verbindungsstatus);
    }

    public void setVerbindungsstatusFarbe(Paint farbe) {
        verbindungsstatusLabel.setTextFill(farbe);
    }
}
