/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.einstellungen.Gleisabschnittstabelle;
import GUI.geschwindigkeitsanzeige.Geschwindigkeitsanzeige;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    private final SaulenDiagramm saulenDiagramm;

    private final Geschwindigkeitsanzeige geschwindigkeitsanzeige;
    private final Button fahrstufeButton;
    protected final Gleisabschnittstabelle messabschnittstabelle;

    private final Label testLabel;

    public Reiterleiste() {        
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        momentaneGeschwTab = new Tab("Momentane Geschw.");
        graphTab = new Tab("Säulendiagramm");

        saulenDiagramm = new SaulenDiagramm();

        fahrstufeButton = new Button("Neue Fahrstufe");
        fahrstufeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GuiAktualisieren.setGeschwFuerFahrstufe("F1", 40000);
                GuiAktualisieren.setGeschwFuerFahrstufe("F2", 55000);
            }
        });

        geschwindigkeitsanzeige = new Geschwindigkeitsanzeige();

        Button button1 = new Button();
        button1.setOnAction((ActionEvent e) -> {
            GuiAktualisieren.setFarbeFuerDigitalanzeige(Color.CORAL);
            GuiAktualisieren.setMomentaneGeschw(823.66980);

        });

        testLabel = new Label("km/h");
        
        BorderPane b3 = new BorderPane();
        messabschnittstabelle = new Gleisabschnittstabelle();
        b3.setTop(button1);
        b3.setCenter(geschwindigkeitsanzeige);
        b3.setBottom(messabschnittstabelle);
        momentaneGeschwTab.setContent(b3);
        BorderPane b2 = new BorderPane();
        b2.setRight(fahrstufeButton);
        b2.setCenter(saulenDiagramm);
        graphTab.setContent(b2);

        getTabs().addAll(momentaneGeschwTab, graphTab);

    }

    public Geschwindigkeitsanzeige getGeschwindigkeitsanzeige() {
        return geschwindigkeitsanzeige;
    }

    public SaulenDiagramm getSaulenDiagramm() {
        return saulenDiagramm;
    }

}
