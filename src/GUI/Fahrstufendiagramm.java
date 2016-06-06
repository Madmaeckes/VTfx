/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;

/**
 *
 * @author Manuel Eble, Manuel Weber
 */
public class Fahrstufendiagramm extends Pane {

    private final BarChart<String, Number> bc;
    
    private XYChart.Series series = new XYChart.Series<>();
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    
    ScrollPane scrollPane;

    /**
     * Erzeugt ein Säulendiagramm auf dem über die Methode
     * setGeschwFuerFahrstufe() neue Säulen gesetzt werden können
     */
    public Fahrstufendiagramm() {

        bc = new BarChart<>(xAxis, yAxis);
      //  scrollPane = new ScrollPane();
        
//        scrollPane.setPrefSize(500, 500);
//        scrollPane.setContent(bc);
//        scrollPane.setPannable(true);
        bc.setTitle("Fahrstufendiagramm");
        xAxis.setLabel("Fahrstufe");
        yAxis.setLabel("Geschwindigkeit");
        bc.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(MouseButton.SECONDARY.equals(event.getButton())) {
                    FahrstufendiagrammContextMenuFactory.getContextMenu()
                            .show(Fenster.getFenster().getStage(), event.getScreenX(), event.getScreenY());
                }
            }
        });
            
        this.getChildren().add(bc);
        this.clear();
  
        bc.getData().add(series);
        bc.getXAxis().setAnimated(false);
        bc.getXAxis().setAutoRanging(false);
        bc.getYAxis().setAnimated(true);
        bc.getYAxis().setAutoRanging(true);
        bc.setBarGap(-3);
        
        // Zoom mit Mausrad ---
        
        final double SCALE_DELTA = 1.1;
        
        bc.setOnScroll(new EventHandler<ScrollEvent>() {
            public void handle(ScrollEvent event) {
                event.consume();

                if (event.getDeltaY() == 0) {
                    return;
                }

                double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;

                bc.setScaleX(bc.getScaleX() * scaleFactor);
                bc.setScaleY(bc.getScaleY() * scaleFactor);
            }
        });

        bc.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    bc.setScaleX(1.0);
                    bc.setScaleY(1.0);
                }
            }
        });
        // ---
    }

    /**
     * Setzt einen Balken in Höhe der übergebenen Geschwindigkeit für die
     * übergebene Fahrstufe.
     *
     * @param fahrstufe Die Fahrstufe in der die Lok fehrt
     * @param geschwindigkeit Die gemessene Geschwindigkeit der Lok
     */
    public void setGeschwFuerFahrstufe(int fahrstufe, double geschwindigkeit) {

        XYChart.Data data =  new XYChart.Data(
                        String.valueOf(fahrstufe), geschwindigkeit);
        
        series.getData().add(data);
        
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Fahrstufe " + data.getXValue().toString()+ ": \r" +
            data.getYValue() + " cm/s");
        Tooltip.install(data.getNode(), tooltip);  
        
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
