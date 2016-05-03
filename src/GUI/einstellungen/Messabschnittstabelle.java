/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.einstellungen;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
/**
 *
 * @author Manuel Eble
 */
public class Messabschnittstabelle extends VBox {

    private TableView<TabellenModell> table = new TableView<TabellenModell>();
    private final ObservableList<TabellenModell> data
            = FXCollections.observableArrayList(
                    new TabellenModell("0", "02", "10", true),
                    new TabellenModell("0", "01", "35", true),
                    new TabellenModell("1", "02", "28", false),
                    new TabellenModell("0", "03", "42", true),
                    new TabellenModell("1", "01", "21", false)
            );
    private final HBox hbox = new HBox();
    
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    public Messabschnittstabelle() {

        table.setEditable(true);
        table.setPrefHeight(300);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ///////////////////////
        ///Tabellenspalten////
        /////////////////////
        TableColumn adressenSpalte = new TableColumn("Adresse");
        //Werte einlesen
        adressenSpalte.setCellValueFactory(
                new PropertyValueFactory<>("adresse"));
        //Textfelder als Tabellenfelder setzen
        adressenSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
        //Bearbeitungsevent
        adressenSpalte.setOnEditCommit(
                new EventHandler<CellEditEvent<TabellenModell, String>>() {
            @Override
            public void handle(CellEditEvent<TabellenModell, String> t) {
                ((TabellenModell) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setAdresse(t.getNewValue());
            }
        }
        );
        TableColumn bitSpalte = new TableColumn("Bit");
        bitSpalte.setCellValueFactory(
                new PropertyValueFactory<>("bit"));
        bitSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
        bitSpalte.setOnEditCommit(new EventHandler<CellEditEvent<TabellenModell, String>>() {
            @Override
            public void handle(CellEditEvent<TabellenModell, String> t) {
                ((TabellenModell) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setBit(t.getNewValue());
            }
        });
        TableColumn laengenSpalte = new TableColumn("Länge");
        laengenSpalte.setCellValueFactory(
                new PropertyValueFactory<>("laenge"));
        laengenSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
        laengenSpalte.setOnEditCommit(new EventHandler<CellEditEvent<TabellenModell, String>>() {
            @Override
            public void handle(CellEditEvent<TabellenModell, String> t) {
                System.out.println("handle laenge");
                ((TabellenModell) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setLaenge(t.getNewValue());
            }
        });

//        Callback<TableColumn<TabellenModell, Boolean>, TableCell<TabellenModell, Boolean>> booleanCellFactory = 
//            new Callback<TableColumn<TabellenModell, Boolean>, TableCell<TabellenModell, Boolean>>() {
//            @Override
//                public TableCell<TabellenModell, Boolean> call(TableColumn<TabellenModell, Boolean> p) {
//                    return new BooleanCell();
//            }
//        };
        TableColumn<TabellenModell, Boolean> istMessstreckeSpalte = new TableColumn<>("Ist Messstrecke");
        istMessstreckeSpalte.setCellValueFactory(
                new PropertyValueFactory<TabellenModell, Boolean>("istMessstrecke"));
        //CheckBox als Tabellenfelder setzen
        istMessstreckeSpalte.setCellFactory(CheckBoxTableCell.forTableColumn(istMessstreckeSpalte));
        istMessstreckeSpalte.setOnEditCommit(new EventHandler<CellEditEvent<TabellenModell, Boolean>>() {
            @Override
            public void handle(CellEditEvent<TabellenModell, Boolean> t) {
                System.out.println("handle ist messstrecke");
                ((TabellenModell) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setIstMessstrecke(t.getNewValue());
            }
        });

        table.setRowFactory(tv -> {
            TableRow<TabellenModell> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (!row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != ((Integer) db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    TabellenModell draggedPerson = table.getItems().remove(draggedIndex);

                    int dropIndex;

                    if (row.isEmpty()) {
                        dropIndex = table.getItems().size();
                    } else {
                        dropIndex = row.getIndex();
                    }

                    table.getItems().add(dropIndex, draggedPerson);

                    event.setDropCompleted(true);
                    table.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row;
        });

        final NumberTextField addAdresse = new NumberTextField();
        addAdresse.setPromptText("Adresse");
        addAdresse.setMaxWidth(adressenSpalte.getPrefWidth());
        final NumberTextField addBit = new NumberTextField();
        addBit.setMaxWidth(bitSpalte.getPrefWidth());
        addBit.setPromptText("Bit");
        final NumberTextField addLaenge = new NumberTextField();
        addLaenge.setMaxWidth(laengenSpalte.getPrefWidth());
        addLaenge.setPromptText("Länge");
        final CheckBox addIstMessstrecke = new CheckBox("Messstrecke");
        addIstMessstrecke.setMaxWidth(istMessstreckeSpalte.getPrefWidth());
        addIstMessstrecke.setSelected(true);

        final Button addButton = new Button("Hinzufügen");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new TabellenModell(
                        addAdresse.getText(),
                        addBit.getText(),
                        addLaenge.getText(),
                        addIstMessstrecke.isSelected()));
                addAdresse.clear();
                addBit.clear();
                addLaenge.clear();
                addIstMessstrecke.setSelected(true);
            }
        });

        hbox.getChildren().addAll(addAdresse, addBit, addLaenge, addIstMessstrecke, addButton);
        hbox.setHgrow(addIstMessstrecke, Priority.ALWAYS);
        addIstMessstrecke.setMaxWidth(addIstMessstrecke.getPrefWidth());
        hbox.setSpacing(3);
        table.setItems(data);
        table.getColumns().addAll(adressenSpalte, bitSpalte, laengenSpalte, istMessstreckeSpalte);
        getChildren().addAll(table, hbox);
        setSpacing(3);
    }

    public static class TabellenModell {

        private final SimpleStringProperty adresse;
        private final SimpleStringProperty bit;
        private final SimpleStringProperty laenge;
        private final SimpleBooleanProperty istMessstrecke;

        private TabellenModell(String adresse, String bit, String laenge, Boolean istMessstrecke) {
            this.adresse = new SimpleStringProperty(adresse);
            this.bit = new SimpleStringProperty(bit);
            this.laenge = new SimpleStringProperty(laenge);
            this.istMessstrecke = new SimpleBooleanProperty(istMessstrecke);
        }

        public String getAdresse() {
            return adresse.get();
        }

        public void setAdresse(String adresse) {
            this.adresse.set(adresse);
        }

        public String getBit() {
            return bit.get();
        }

        public void setBit(String bit) {
            this.bit.set(bit);
        }

        public String getLaenge() {
            return laenge.get();
        }

        public void setLaenge(String laenge) {
            this.laenge.set(laenge);
        }

        public Boolean istMessstrecke() {
            return istMessstrecke.get();
        }

        public void setIstMessstrecke(Boolean istMessstrecke) {
            System.out.println("SET IST MESSSTRECKE");
            System.out.println(istMessstrecke);
            this.istMessstrecke.set(istMessstrecke);
        }
    }
}
