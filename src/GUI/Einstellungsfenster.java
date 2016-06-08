/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ToolBarBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import vtfx.Einstellungen;

/**
 *
 * @author Manuel Eble
 */
public class Einstellungsfenster extends Application {

    private final ToggleButton tb1 = new ToggleButton("Vorwärts");
    private final ToggleButton tb2 = new ToggleButton("Rückwärts");
    private final ToggleButton tb3 = new ToggleButton("Beides");
    private final ColorPicker colorPicker = new ColorPicker();
    private final ColorPicker colorPicker2 = new ColorPicker();
    private ToolBar standardToolbar = ToolBarBuilder.create().items(colorPicker).build();
    private ToolBar standardToolbar2 = ToolBarBuilder.create().items(colorPicker2).build();
    private ComboBox<String> editableComboBox = new ComboBox<String>();
    private final ObservableList strings = FXCollections.observableArrayList(
            "H0", "H1", "0",
            "1", "2", "TT",
            "N", "Z");
    private final Button buttonBestaetigung = new Button("\u00dc" + "bernehmen");
    private Color tachofarbe;
    private Color diagrammfarbe;

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        HBox hbox = HBoxBuilder.create().alignment(Pos.CENTER).spacing(15).build();

        // hbox.getChildren().addAll(uneditableComboBox, editableComboBox);
        root.getChildren().add(hbox);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    public Einstellungsfenster() {

        editableComboBox.setId("second-editable");
        editableComboBox.setPromptText("Edit or Choose...");
        editableComboBox.setItems(strings);
        editableComboBox.setEditable(true);

        // create 3 toggle buttons and a toogle group for them
        ToggleGroup group = new ToggleGroup();
        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);
        tb3.setToggleGroup(group);
        colorPicker.setOnAction(new EventHandler() {

            public void handle(Event t) {
                tachofarbe = colorPicker.getValue();
            }
        });
        colorPicker2.setOnAction(new EventHandler() {

            public void handle(Event t) {
                diagrammfarbe = colorPicker2.getValue();
            }
        });

//        VBox coloredObjectsVBox = VBoxBuilder.create().alignment(Pos.CENTER).spacing(20).children(coloredText, coloredButton).build();        
//        VBox outerVBox = VBoxBuilder.create().alignment(Pos.CENTER).spacing(150).padding(new Insets(0, 0, 120, 0)).children(standardToolbar, coloredObjectsVBox).build();
        // select the first button to start with
        group.selectToggle(tb1);
        // add buttons and label to grid and set their positions
        GridPane.setConstraints(tb1, 0, 0);
        GridPane.setConstraints(tb2, 1, 0);
        GridPane.setConstraints(tb3, 2, 0);
//        GridPane.setConstraints(label,0,1,3,1);
        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setHgap(10);
//        root.getChildren().add(grid);
        grid.getChildren().addAll(tb1, tb2, tb3);

        final Stage stage = new Stage();
        stage.setWidth(500);
        //create root node of scene, i.e. group
        Group rootGroup = new Group();

        //create scene with set width, height and color
        Scene scene = new Scene(rootGroup, 300, 400, Color.WHITESMOKE);

        //set scene to stage
        stage.setScene(scene);

        //set title to stage
        stage.setTitle("Einstellungen");

        //center stage on screen
        stage.centerOnScreen();

        //show the stage
        stage.show();
        BorderPane border = new BorderPane();
        //add some node to scene
        Text text = new Text(0, 150, "Maßstab");
        text.setFont(Font.font(Font.getDefault().getFamily()));

        Text text1 = new Text(0, 500, "Betriebsart");
        text1.setFont(Font.font(Font.getDefault().getFamily()));

        Text text2 = new Text(0, 500, "Tachofarbe");
        text2.setFont(Font.font(Font.getDefault().getFamily()));

        Text text3 = new Text(0, 500, "Diagrammfarbe");
        text2.setFont(Font.font(Font.getDefault().getFamily()));
        //add text to the main root group
        rootGroup.getChildren().add(text);
        //Rasterlayout
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(2);
        tilePane.setAlignment(Pos.CENTER);

        tilePane.getChildren().add(text);
        tilePane.getChildren().add(editableComboBox);
        tilePane.getChildren().add(text1);
        tilePane.getChildren().add(grid);
        tilePane.getChildren().add(text2);
        tilePane.getChildren().add(colorPicker);
        tilePane.getChildren().add(text3);
        tilePane.getChildren().add(colorPicker2);
        tilePane.getChildren().add(buttonBestaetigung);
        buttonBestaetigung.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                einstellungenSichern();
                GuiAktualisieren.setFarbeFuerDigitalanzeige(tachofarbe);
            }
        });

        border.setCenter(tilePane);

        rootGroup.getChildren().add(border);
        einstellungenLaden();
    }

    public double massstabAuslesen(ComboBox c) {
        String s = c.getSelectionModel().getSelectedItem().toString();
        double massstab = 0;
        if (s == "2") {
            massstab = 22.5;
        } else if (s == "1") {
            massstab = 32;
        } else if (s == "0") {
            massstab = 45;
        } else if (s == "H1") {
            massstab = 64;
        } else if (s == "H0") {
            massstab = 87;
        } else if (s == "TT") {
            massstab = 120;
        } else if (s == "N") {
            massstab = 160;
        } else if (s == "Z") {
            massstab = 220;
        } else if (Pattern.matches("[1][:][0-9.]*", s)) {
            int l = s.length();
            massstab = Double.parseDouble(s.substring(2, l));
        }
        else {
            Alert alert = new Alert(AlertType.WARNING, "Falsches Eingabeformat des Maßstabs, entweder aus Dropdown-Liste auswählen oder im Format 1:X!", ButtonType.CANCEL);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    //do stuff
}
        }


        return massstab;
    }

    public int betriebsartAuslesen() {
        int betriebsart = 0;
        if (tb1.isSelected()) {
            betriebsart = 0;
        }
        if (tb2.isSelected()) {
            betriebsart = 1;
        }
        if (tb3.isSelected()) {
            betriebsart = 2;
        }
        return betriebsart;
    }

    /**
     * Liest die Einstellungen aus der Registry aus.
     */
    private void einstellungenLaden() {

        double massstab = Einstellungen.getEinstellungen().getMassstab();
        String s = String.valueOf(massstab);
        if (massstab == 22.5) {
            s = "2";
        } else if (massstab == 32) {
            s = "1";
        } else if (massstab == 45) {
            s = "0";
        } else if (massstab == 64) {
            s = "H1";
        } else if (massstab == 87) {
            s = "H0";
        } else if (massstab == 120) {
            s = "TT";
        } else if (massstab == 160) {
            s = "N";
        } else if (massstab == 220) {
            s = "Z";
        } else {

            s = "1:" + (int) massstab;
        }
        editableComboBox.setValue(s);

        int betriebsart = Einstellungen.getEinstellungen().getBetriebsart();
        if (betriebsart == 0) {
            tb1.setSelected(true);
        }
        if (betriebsart == 1) {
            tb2.setSelected(true);
        }
        if (betriebsart == 2) {
            tb3.setSelected(true);
        }
        double tachored = Einstellungen.getEinstellungen().getTachored();
        double tachogreen = Einstellungen.getEinstellungen().getTachogreen();
        double tachoblue = Einstellungen.getEinstellungen().getTachoblue();
        tachofarbe = Color.color(tachored, tachogreen, tachoblue);
        colorPicker.setValue(tachofarbe);

        double diagrammred = Einstellungen.getEinstellungen().getDiagrammred();
        double diagrammgreen = Einstellungen.getEinstellungen().getDiagrammgreen();
        double diagrammblue = Einstellungen.getEinstellungen().getDiagrammblue();
        diagrammfarbe = Color.color(diagrammred, diagrammgreen, diagrammblue);
        colorPicker2.setValue(diagrammfarbe);
    }

    private void einstellungenSichern() {
        Einstellungen.getEinstellungen().setMassstab(
                (double) (massstabAuslesen(editableComboBox)));

        Einstellungen.getEinstellungen().setBetriebsart(
                (int) (betriebsartAuslesen()));

        Einstellungen.getEinstellungen().setTachored(colorPicker.getValue().getRed());

        Einstellungen.getEinstellungen().setTachogreen(colorPicker.getValue().getGreen());

        Einstellungen.getEinstellungen().setTachoblue(colorPicker.getValue().getBlue());

        Einstellungen.getEinstellungen().setDiagrammred(colorPicker2.getValue().getRed());

        Einstellungen.getEinstellungen().setDiagrammgreen(colorPicker2.getValue().getGreen());

        Einstellungen.getEinstellungen().setDiagrammblue(colorPicker2.getValue().getBlue());

    }
}
