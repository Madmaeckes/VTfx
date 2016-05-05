/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

/**
 *
 * @author Manuel Eble
 */
public class Einstellungsfenster extends Application {

    private final ToggleButton tb1 = new ToggleButton("Pendelverkehr");
    private final ToggleButton tb2 = new ToggleButton("Kreisverkehr");
    private final ColorPicker colorPicker = new ColorPicker(Color.GRAY);
    private ToolBar standardToolbar = ToolBarBuilder.create().items(colorPicker).build();
    private ComboBox<String> editableComboBox = new ComboBox<String>();
    private final ObservableList strings = FXCollections.observableArrayList(
            "H0", "H1", "0",
            "1", "2", "TT",
            "N", "Z");

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
        //Editable combobox. Use the default item display length

        editableComboBox.setId("second-editable");
        editableComboBox.setPromptText("Edit or Choose...");
        editableComboBox.setItems(strings);
        editableComboBox.setEditable(true);


        // create 3 toggle buttons and a toogle group for them
        ToggleGroup group = new ToggleGroup();
        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);
                colorPicker.setOnAction(new EventHandler() {
 
            public void handle(Event t) {
                Color newColor = colorPicker.getValue();
;
            }
        });
 
//        VBox coloredObjectsVBox = VBoxBuilder.create().alignment(Pos.CENTER).spacing(20).children(coloredText, coloredButton).build();        
//        VBox outerVBox = VBoxBuilder.create().alignment(Pos.CENTER).spacing(150).padding(new Insets(0, 0, 120, 0)).children(standardToolbar, coloredObjectsVBox).build();
        // select the first button to start with
        group.selectToggle(tb1);
        // add buttons and label to grid and set their positions
        GridPane.setConstraints(tb1, 0, 0);
        GridPane.setConstraints(tb2, 1, 0);
//        GridPane.setConstraints(label,0,1,3,1);
        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setHgap(10);
//        root.getChildren().add(grid);
        grid.getChildren().addAll(tb1, tb2);

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
        
        border.setCenter(tilePane);

        rootGroup.getChildren().add(border);

    }


    public double massstabAuslesen(ComboBox c) {
        String s = c.getSelectionModel().toString();
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
            massstab = s.indexOf(2, l);
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
        return betriebsart;
    }

}
