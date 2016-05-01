/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.geschwindigkeitsanzeige.Digit;
import GUI.geschwindigkeitsanzeige.Geschwindigkeitsanzeige;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Manuel Eble
 */
public class Reiterleiste extends TabPane {

    private final Tab momentaneGeschwTab;
    private final Tab graphTab;
    private SaulenDiagramm saulenDiagramm;

    private Geschwindigkeitsanzeige geschwindigkeitsanzeige;
    private Button fahrstufeButton;

    private HBox digitAnzeige;

    public Reiterleiste() {
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
        
        geschwindigkeitsanzeige = new Geschwindigkeitsanzeige(Color.GREENYELLOW, Color.rgb(255, 255, 255));

        Button button1 = new Button();
        button1.setOnAction((ActionEvent e) -> {
            geschwindigkeitsanzeige.setMomentaneGeschw(123.66789);

        });

        BorderPane b3 = new BorderPane();
        b3.setCenter(geschwindigkeitsanzeige);
        b3.setTop(button1);
        momentaneGeschwTab.setContent(b3);
        BorderPane b2 = new BorderPane();
        b2.setRight(fahrstufeButton);
        b2.setCenter(saulenDiagramm);
        graphTab.setContent(b2);

        getTabs().addAll(momentaneGeschwTab, graphTab);

    }

}
