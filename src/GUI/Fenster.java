/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


/**
 *
 * @author Manuel
 */
public class Fenster extends TabPane {

    private final Tab momentaneGeschwTab;
    private final Tab graphTab;
    private SaulenDiagramm saulenDiagramm;
    
    private Button fahrstufeButton;

    
    public Fenster() {

        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        momentaneGeschwTab = new Tab("Momentane Geschw.");
        graphTab = new Tab("Säulendiagramm");

        saulenDiagramm = new SaulenDiagramm();
        
        fahrstufeButton = new Button("Neue Fahrstufe");
        fahrstufeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saulenDiagramm.setGeschwFuerFahrstufe("F1", 40000);
            }
        });

        BorderPane b2 = new BorderPane();
        b2.setRight(fahrstufeButton);
        b2.setCenter(saulenDiagramm);
        graphTab.setContent(b2);
        
        getTabs().addAll(momentaneGeschwTab, graphTab);

    }

}
