/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Collection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Manuel
 */
public class SaulenDiagramm extends Pane {

    final BarChart<String, Number> bc;
    XYChart.Series series1;
    XYChart.Series series2;
    XYChart.Series series3;
    XYChart.Series series4;
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";
    
    public Button fahrstufeButton;

    public SaulenDiagramm() {

        bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Fahrstufendiagramm");
        xAxis.setLabel("Fahrstufe");
        yAxis.setLabel("Value");

        series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data(austria, 25601.34));
        series1.getData().add(new XYChart.Data(brazil, 20148.82));
        series1.getData().add(new XYChart.Data(france, 10000));
        series1.getData().add(new XYChart.Data(italy, 35407.15));
        series1.getData().add(new XYChart.Data(usa, 12000));

        series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data(austria, 57401.85));
        series2.getData().add(new XYChart.Data(brazil, 41941.19));
        series2.getData().add(new XYChart.Data(france, 45263.37));
        series2.getData().add(new XYChart.Data(italy, 117320.16));
        series2.getData().add(new XYChart.Data(usa, 14845.27));

        series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data(austria, 45000.65));
        series3.getData().add(new XYChart.Data(brazil, 44835.76));
        series3.getData().add(new XYChart.Data(france, 18722.18));
        series3.getData().add(new XYChart.Data(italy, 17557.31));
        series3.getData().add(new XYChart.Data(usa, 92633.68));

        this.getChildren().add(bc);

//        Timeline tl = new Timeline();
//        tl.getKeyFrames().add(new KeyFrame(Duration.millis(5000),
//                new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                for (XYChart.Series<String, Number> series : bc.getData()) {
//                    for (XYChart.Data<String, Number> data : series.getData()) {
//                        data.setYValue(Math.random() * 100);
//                    }
//                }
//            }
//        }));
//        tl.setCycleCount(Animation.INDEFINITE);
//        tl.play();
        
        bc.getData().addAll(series1, series2, series3);

    }

    /**
     * Setzt einen Balken in Höhe der übergebenen Geschwindigkeit für die
     * übergebene Fahrstufe
     * 
     * @param fahrstufe Die Fahrstufe in der die Lok fehrt
     * @param geschwindigkeit Die gemessene Geschwindigkeit der Lok
     */
    public void setGeschwFuerFahrstufe(String fahrstufe, double geschwindigkeit) {
        series4 = new XYChart.Series();
        series4.setName(fahrstufe);
        series4.getData().add(new XYChart.Data(fahrstufe, geschwindigkeit));
        bc.getData().add(series4);
    }
}
