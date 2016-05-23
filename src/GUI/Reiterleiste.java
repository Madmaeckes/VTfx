/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.einstellungen.Gleisabschnittstabelle;
import GUI.einstellungen.Gleisabschnittslaengen;
import GUI.einstellungen.Gleisabschnittstabelle.TabellenModell;
import GUI.geschwindigkeitsanzeige.Geschwindigkeitsanzeige;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
    private SaulenDiagramm saulenDiagramm;

    private Geschwindigkeitsanzeige geschwindigkeitsanzeige;
    private Button fahrstufeButton;
    protected Gleisabschnittslaengen messabschnittslaengen;
    protected Gleisabschnittstabelle messabschnittstabelle;

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
                GuiAktualisieren.setGeschwFuerFahrstufe("F1", 40000);
                GuiAktualisieren.setGeschwFuerFahrstufe("F2", 55000);
            }
        });

        geschwindigkeitsanzeige = new Geschwindigkeitsanzeige(Color.rgb(255, 255, 255));

        Button button1 = new Button();
        button1.setOnAction((ActionEvent e) -> {
            GuiAktualisieren.setFarbeFuerDigitalanzeige(Color.CORAL);
            GuiAktualisieren.setMomentaneGeschw(3.66980);

        });

        BorderPane b3 = new BorderPane();
        messabschnittstabelle = new Gleisabschnittstabelle();
        b3.setTop(button1);
        b3.setCenter(geschwindigkeitsanzeige);
        b3.setBottom(messabschnittstabelle);
        momentaneGeschwTab.setContent(b3);
//        messabschnittslaengen = new Messabschnittslaengen();
//        momentaneGeschwTab.setContent(messabschnittslaengen);
       // momentaneGeschwTab.setContent(messabschnittstabelle);
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
