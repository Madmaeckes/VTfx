/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import vtfx.Funktionen;

/**
 * Contains the menu.
 *
 * @author Manuel Eble
 */
public class Header extends GridPane {

    public MenuBar menuBar;
    public Menu file;
    public Menu edit;
    public MenuItem newFile;
    public MenuItem save;
    public MenuItem saveAs;
    public MenuItem settings;
    public MenuItem undo;
    public MenuItem redo;

    public ToolBar toolBar;
    public Button verbindenButton;
    public Button messungStartenButton;
    public Label einstellungsLabel;
    public Label ueberLabel;
    public Label hilfeLabel;

    private final Polygon polygon1 = new Polygon(new double[]{
        0, 0,
        10, 5,
        0, 10,});

    private final Alert alert = new Alert(AlertType.ERROR);

    public Header() {

//        //Drop-Down-Menu//
//
//        menuBar = new MenuBar();
//        file = new Menu("Datei");
//        edit = new Menu("Über");
//        newFile = new MenuItem("New File");
//        save = new MenuItem("Save");
//        saveAs = new MenuItem("Save As...");
//        settings = new MenuItem("Einstellungen");
//        undo = new MenuItem("Impressum");
//        redo = new MenuItem("Hilfe");
//
//        file.getItems().addAll(newFile, save, saveAs, settings);
//        edit.getItems().addAll(undo, redo);
//        menuBar.getMenus().addAll(file, edit);
//        menuBar.setPrefWidth(800);
//
//        settings.setOnAction((ActionEvent t) -> {
//            Einstellungsfenster einstellungsfenster = new Einstellungsfenster();
//        });
        //  add(menuBar, 0, 0);
        //Toolbar//
        toolBar = new ToolBar();
        toolBar.setPrefWidth(800);
        verbindenButton = new Button("Verbinden");
        verbindenButton.setStyle("-fx-base: green;");
        polygon1.setFill(Color.GREEN);
        messungStartenButton = new Button("Messung starten", polygon1);
        messungStartenButton.setDisable(true);
        einstellungsLabel = new Label("Einstellungen");
        ueberLabel = new Label("Über");
        hilfeLabel = new Label("?");

        einstellungsLabel.setOnMouseEntered((MouseEvent e) -> {
            scaleLabel(einstellungsLabel, 1.04);
        });
        einstellungsLabel.setOnMouseExited((MouseEvent e) -> {
            scaleLabel(einstellungsLabel, 1);
        });
        einstellungsLabel.setOnMouseClicked((MouseEvent e) -> {
            Einstellungsfenster einstellungsfenster = new Einstellungsfenster();
        });
        ueberLabel.setOnMouseEntered((MouseEvent e) -> {
            scaleLabel(ueberLabel, 1.04);
        });
        ueberLabel.setOnMouseExited((MouseEvent e) -> {
            scaleLabel(ueberLabel, 1);
        });
        ueberLabel.setOnMouseClicked((MouseEvent e) -> {
            Ueberfenster ueberFenster = new Ueberfenster();
        });
        hilfeLabel.setOnMouseEntered((MouseEvent e) -> {
            scaleLabel(hilfeLabel, 1.5);
        });
        hilfeLabel.setOnMouseExited((MouseEvent e) -> {
            scaleLabel(hilfeLabel, 1);
        });
        hilfeLabel.setOnMouseClicked((MouseEvent e) -> {
            Faqfenster faqFenster = new Faqfenster();
        });

        verbindenButton.setOnAction((ActionEvent e) -> {
            verbindenButtonActionPerformed();
        });

        messungStartenButton.setOnAction((ActionEvent e) -> {
            messungStartenButtonActionPerformed();
        });

        //Add elements to toolBar
        toolBar.getItems().addAll(verbindenButton, new Separator(), messungStartenButton, new Separator(), einstellungsLabel, new Separator(), ueberLabel, new Separator(), hilfeLabel);

        //Add toolBar to header
        add(toolBar, 0, 0);
    }

    /**
     * Zeigt eine Fehlermeldung an.
     *
     * @param e Exception deren Message angezeigt werden soll.
     */
    private void popAlert(Exception e) {
        alert.setAlertType(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    /**
     * Initiiert die Verbindung und aktualisiert ggf. die Gui.
     *
     */
    public void verbindenButtonActionPerformed() {
        if (verbindenButton.getText().equals("Verbinden")) {
            try {
                GuiAktualisieren.setVerbindungsstatus(1);
                Funktionen.verbinden();
            } catch (Exception e) {
                popAlert(e);
                GuiAktualisieren
                        .setVerbindungsstatus(GuiAktualisieren.GETRENNT);
            }
        } else {
            try {
                GuiAktualisieren.setVerbindungsstatus(3);
                //falls beim trennen Messung aktiv:
                GuiAktualisieren.setMessungsstatus("MESSUNG_GESTOPPT");
                Funktionen.trennen();
            } catch (Exception e) {
                popAlert(e);
            }
        }
    }

    /**
     * Initiiert die Messung und aktualisiert ggf. die Gui.
     */
    public void messungStartenButtonActionPerformed() {
        if (messungStartenButton.getText().equals("Messung starten")) {
        try {
            GuiAktualisieren.setMessungsstatus("MESSUNG_GESTARTET");
            Funktionen.messungStarten();
        } catch (Exception e) {
            popAlert(e);
        }
        } else {
            try {
                GuiAktualisieren.setMessungsstatus("MESSUNG_GESTOPPT");
                Funktionen.messungStoppen();
            } catch (Exception e) {
                popAlert(e);
            }
        }
    }

    /**
     * Aendert die Größe eines übergebenen Labels auf die übergebene Größe scale
     *
     * @param l Label dessen Größe geändert werden soll
     * @param scale Größe, wobei 1 für Standardgröße steht
     */
    public void scaleLabel(Label l, double scale) {
        l.setScaleX(scale);
        l.setScaleY(scale);
    }

    public Button getVerbindenButton() {
        return verbindenButton;
    }

    public Button getMessungStartenButton() {
        return messungStartenButton;
    }
}
