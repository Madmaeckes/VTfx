/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Manuel
 */
public class Header extends HBox{

    public MenuBar menuBar;
    public Menu file;
    public Menu edit;
    public MenuItem newFile;
    public MenuItem save;
    public MenuItem saveAs;
    public MenuItem undo;
    public MenuItem redo;
    
    
    public Header() {
        
        menuBar = new MenuBar();
        file = new Menu("File");
        edit = new Menu("Edit");
        newFile = new MenuItem("New File");
        save = new MenuItem("Save");
        saveAs = new MenuItem("Save As...");
        undo = new MenuItem("Undo");
        redo = new MenuItem("Redo");
        
        file.getItems().addAll(newFile, save, saveAs);
        edit.getItems().addAll(undo, redo);
        menuBar.getMenus().addAll(file, edit);
        
        getChildren().add(menuBar);

    }
}
