/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane; 
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Manuel Eble
 */
public class Einstellungsfenster extends Application {
    
    
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
        @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
        public Einstellungsfenster() {
                    //Editable combobox. Use the default item display length
        ComboBox<String> editableComboBox = new ComboBox<String>();
        editableComboBox.setId("second-editable");
        editableComboBox.setPromptText("Edit or Choose...");
        editableComboBox.setItems(strings);
        editableComboBox.setEditable(true);
        
                //Non-editable combobox. Created with a builder
//        ComboBox uneditableComboBox = ComboBoxBuilder.create()
//                .id("uneditable-combobox")
//                .promptText("Make a choice...")
//                .items(FXCollections.observableArrayList(strings.subList(0, 8))).build();
            
         // create label to show result of selected toggle button
//        final Label label = new Label();
//        label.setStyle("-fx-font-size: 2em;");
        // create 3 toggle buttons and a toogle group for them
        final ToggleButton tb1 = new ToggleButton("Pendelverkehr");
        final ToggleButton tb2 = new ToggleButton("Kreisverkehr");
        ToggleGroup group = new ToggleGroup();
        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);
//        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//            @Override public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle selectedToggle) {
//                if(selectedToggle!=null) {
//                    label.setText(((ToggleButton) selectedToggle).getText());
//                }
//                else {
//                    label.setText("...");
//                }
//            }
//        });
        // select the first button to start with
        group.selectToggle(tb1);
        // add buttons and label to grid and set their positions
        GridPane.setConstraints(tb1,0,0);
        GridPane.setConstraints(tb2,1,0);
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
//        text.setFill(Color.DODGERBLUE);
//        text.setEffect(new Lighting());
        text.setFont(Font.font(Font.getDefault().getFamily()));
        
        Text text1 = new Text(0, 500, "Betriebsart");
//        text1.setFill(Color.DODGERBLUE);
//        text1.setEffect(new Lighting());
        text1.setFont(Font.font(Font.getDefault().getFamily()));
        
        //add text to the main root group
//        rootGroup.getChildren().add(text);
        
        //Rasterlayout
        TilePane tilePane = new TilePane(Orientation.VERTICAL, 0, 20);
        tilePane.setPrefRows(2);
        tilePane.setAlignment(Pos.CENTER);
        
        
//        Text text1 = new Text(20, 20, "1");
//        Text text2 = new Text(20, 20, "2");
//        Text text3 = new Text(20, 20, "3");
//        
        tilePane.getChildren().add(text);
        tilePane.getChildren().add(text1);
        tilePane.getChildren().add(editableComboBox);
        tilePane.getChildren().add(grid);
        border.setCenter(tilePane);
        
        
        
        rootGroup.getChildren().add(border);
        
    }
}
