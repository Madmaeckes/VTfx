/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author Manuel Eble, Manuel Weber
 */
public class SaulenDiagramm extends Pane {

    final private BarChart<String, Number> bc;
    
    XYChart.Series series = new XYChart.Series<>();
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    public Button fahrstufeButton;

    /**
     * Erzeugt ein Säulendiagramm auf dem über die Methode
     * setGeschwFuerFahrstufe() neue Säulen gesetzt werden können
     */
    public SaulenDiagramm() {

        bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Fahrstufendiagramm");
        xAxis.setLabel("Fahrstufe");
        yAxis.setLabel("Geschwindigkeit");
            
        this.getChildren().add(bc);
        this.clear();
  
        bc.getData().add(series);
        bc.getXAxis().setAnimated(false);
        bc.getXAxis().setAutoRanging(false);
        bc.getYAxis().setAnimated(true);
        bc.getYAxis().setAutoRanging(true);
        bc.setBarGap(-3);
    }

    /**
     * Setzt einen Balken in Höhe der übergebenen Geschwindigkeit für die
     * übergebene Fahrstufe.
     *
     * @param fahrstufe Die Fahrstufe in der die Lok fehrt
     * @param geschwindigkeit Die gemessene Geschwindigkeit der Lok
     */
    public void setGeschwFuerFahrstufe(int fahrstufe, double geschwindigkeit) {

        series.getData().add(
                new XYChart.Data(
                        String.valueOf(fahrstufe), geschwindigkeit));
        
        //System.out.println(Arrays.toString(series.getData().toArray()));
    }
    
    /**
     * Leert das monentane Fahrstufendiagramm.
     */
    public void clear() {
        series = new XYChart.Series<>();
        series.setName("Fahrstufenmessung");
    }
}
