/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import vtfx.Funktionen;

/**
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

    private Polygon polygon1 = new Polygon(new double[]{
        0, 0,
        10, 5,
        0, 10,});

    private Alert alert = new Alert(AlertType.ERROR);

    public Header() {

//        //Menü
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
        //Toolbar
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

        toolBar.getItems().addAll(verbindenButton, new Separator(), messungStartenButton, new Separator(), einstellungsLabel, new Separator(), ueberLabel, new Separator(), hilfeLabel);

        verbindenButton.setOnAction((ActionEvent e) -> {
            verbindenButtonActionPerformed();
        });

        //  add(menuBar, 0, 0);
        add(toolBar, 0, 0);
    }

    public void verbindenButtonActionPerformed() {
        if (verbindenButton.getText().equals("Verbinden")) {
            try {
                Funktionen.verbinden();
            } catch (Exception e) {
                alert.setAlertType(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                GuiAktualisieren
                        .setVerbindungsstatus(GuiAktualisieren.GETRENNT);
            }
        } else {
            try {
                Funktionen.trennen();
            } catch (Exception e) {
                alert.setAlertType(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

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
