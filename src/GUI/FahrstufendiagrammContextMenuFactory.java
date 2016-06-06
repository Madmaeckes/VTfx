/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 *Aufgerufen bei Rechtsklick auf das Saeulendiagmm (Fahrstufendiagramm).
 * 
 * @author Manuel Weber
 */
public class FahrstufendiagrammContextMenuFactory {
    
    private static final MenuItem resizeItem = new MenuItem("TODO");
   
    protected static ContextMenu getContextMenu() {
        
        resizeItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override 
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        return new ContextMenu(resizeItem);
    }
    
    
}
