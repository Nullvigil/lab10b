package com.mycompany.lab10b;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Button clearBtn;

    @FXML
    private MenuItem clearMenu;

    @FXML
    private RadioButton coffeeRad;

    @FXML
    private MenuItem drinkClearMenu;

    @FXML
    private ToggleGroup drinksToggle;

    @FXML
    private MenuItem entryClearMenu;

    @FXML
    private ListView<String> entryList;

    @FXML
    private MenuItem exitMenu;

    @FXML
    private RadioButton juiceRad;

    @FXML
    private Button placeBtn;

    @FXML
    private MenuItem placeMenu;

    @FXML
    private RadioButton sodaRad;

    @FXML
    private MenuItem specialClearMenu;

    @FXML
    private TextField specialField;

    @FXML
    private RadioButton teaRad;

    @FXML
    private Label totalLabel;

    @FXML
    private CheckBox waterCheck;

    @FXML
    void clearOrderMenu(ActionEvent event) {
        clearOrder(event); //recall the btn action
    }

    @FXML
    void drinkClear(ActionEvent event) {
        drinksToggle.selectToggle(null);
    }

    @FXML
    void entryClear(ActionEvent event) {
        entryList.getSelectionModel().clearSelection();

    }

    @FXML
    void exit(ActionEvent event) {
         exitMenu.setOnAction(e -> Platform.exit());   
    }

    @FXML
    void placeOrderMenu(ActionEvent event) throws IOException {
        Object secondary = FXMLLoader.load(getClass().getResource("summary.fxml"));
        Stage stage = new Stage(); 
        stage.setScene(new Scene((Parent) secondary)); //I just followed the
        // alt-enter corrector, no idea what the parent is but it works now :)
        stage.show();
    }

    @FXML
    void specialClear(ActionEvent event) {
        specialField.setText("");
    }

     @FXML
    void clearOrder(ActionEvent event) {
        totalLabel.setText("Total: $0.00");
        entryList.getSelectionModel().clearSelection();
        drinksToggle.selectToggle(null);
        specialField.setText("");
        waterCheck.setSelected(false);
    }

    @FXML
    void placeOrder(ActionEvent event) throws IOException {
        Object secondary = FXMLLoader.load(getClass().getResource("summary.fxml"));
        Stage stage = new Stage(); 
        stage.setScene(new Scene((Parent) secondary)); //I just followed the
        // alt-enter corrector, no idea what the parent is but it works now :)
        stage.show();
    }
    
    public void initialize() {
        entryList.getItems().addAll("Chicken Salad","Turkey SandWhich",
                "Vegetable Wrap", "Grilled Cheese");
        coffeeRad.setToggleGroup(drinksToggle);
        teaRad.setToggleGroup(drinksToggle);
        sodaRad.setToggleGroup(drinksToggle);
        juiceRad.setToggleGroup(drinksToggle);
        clearBtn.setOnAction(e -> clearOrder(e)); //may be redundent who knows?
        entryList.getItems().addListener((obv, old, newVal) -> {
            if (newVal != null) {
                double newPrice = calcPrice(newVal);
            }
        }
    });
    
    private double calcPrice(String str) {
        switch (str) {
            case "Chicken salad" : return 8.50;
            case "Turkey Sandwhich" : return 7.99;
            case "Vegetable Wrap" : return 6.50;
            case "Grilled Cheese" : return 5.00;
            default: return 0.00;
        }
    }



}
