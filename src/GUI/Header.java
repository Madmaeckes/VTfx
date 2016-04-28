/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import vtfx.Funktionen;

/**
 *
 * @author Manuel
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
    public Button trennenButton;
    public Button messungStartenButton;

    private Circle circle1 = new Circle(5, 5, 6, Color.RED);
    private Polygon polygon1 = new Polygon(new double[]{
        0, 0,
        10, 5,
        0, 10,});

    public Header() {

        //Menü
        menuBar = new MenuBar();
        file = new Menu("Datei");
        edit = new Menu("Über");
        newFile = new MenuItem("New File");
        save = new MenuItem("Save");
        saveAs = new MenuItem("Save As...");
        settings = new MenuItem("Einstellungen");
        undo = new MenuItem("Impressum");
        redo = new MenuItem("Hilfe");

        file.getItems().addAll(newFile, save, saveAs, settings);
        edit.getItems().addAll(undo, redo);
        menuBar.getMenus().addAll(file, edit);
        menuBar.setPrefWidth(800);

        settings.setOnAction((ActionEvent t) -> {
            Einstellungsfenster einstellungsfenster = new Einstellungsfenster();
        });

        //Toolbar
        toolBar = new ToolBar();
        verbindenButton = new Button("Verbinden");
        verbindenButton.setStyle("-fx-base: green;");
        trennenButton = new Button("Trennen", circle1);
        polygon1.setFill(Color.GREEN);
        messungStartenButton = new Button("Messung starten", polygon1);
        toolBar.getItems().addAll(verbindenButton, trennenButton, new Separator(), messungStartenButton);

        verbindenButton.setOnAction((ActionEvent e) -> {
            try {
                Funktionen.verbinden();
            } catch (Exception ex) {
                Logger.getLogger(Header.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        trennenButton.setOnAction((ActionEvent e) -> {
           Funktionen.trennen();
        });

        add(menuBar, 0, 0);
        add(toolBar, 0, 1);
        //    getChildren().add(menuBar);
        //    getChildren().add(toolBar);

    }
}
