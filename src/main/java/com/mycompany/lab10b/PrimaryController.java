package com.mycompany.lab10b;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

private final Order order = new Order();
 
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
 
    // ===================== File menu =====================
 
    @FXML
    void clearOrderMenu(ActionEvent event) {
        clearOrder(event); //recall the btn action
    }
 
    @FXML
    void placeOrderMenu(ActionEvent event) throws IOException {
        placeOrder(event);
    }
 
    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
    }
 
    // MENUBAR
    // Each one only clears its own control. The listeners in initialize()
    // notice the change and update the model + total
 
    @FXML
    void entryClear(ActionEvent event) {
        entryList.getSelectionModel().clearSelection();
    }
 
    @FXML
    void drinkClear(ActionEvent event) {
        drinksToggle.selectToggle(null);
    }
 
    @FXML
    void specialClear(ActionEvent event) {
        specialField.setText("");
    }
  
    @FXML
    void showAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Restaurant Ordering Application");
        alert.setContentText("Version 1.0\nDeveloper: <Matthew>");
        alert.showAndWait();
    }
 
    // ===================== Buttons =====================
 
    @FXML
    void clearOrder(ActionEvent event) {
        entryList.getSelectionModel().clearSelection();
        drinksToggle.selectToggle(null);
        specialField.setText("");
        waterCheck.setSelected(false);
        order.clear();
        updateTotal(); // In case nothing changed
    }
 
    @FXML
    void placeOrder(ActionEvent event) throws IOException {
        // Need one entree and one drink
        if (!order.isComplete()) {
            Alert warn = new Alert(Alert.AlertType.WARNING);
            warn.setTitle("Incomplete Order");
            warn.setHeaderText(null);
            warn.setContentText("Please select one entree and one drink before placing your order.");
            warn.showAndWait();
            return; // cancels opening window
        }
 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("summary.fxml"));
        Parent root = loader.load(); // I used stackOverflow for this sorry teacher
        // but I was stuck and I couldn't find it in the class notes
 
        OrderController summary = loader.getController();
        summary.load(order);
 
        Stage stage = new Stage();
        stage.setTitle("Order Summary");
        stage.setScene(new Scene(root));
        stage.show();
    }
 
    /**
     * loads the required stuff for the GUI
     */
    public void initialize() {
        for (String name : Order.getEntreeNames()) {
            entryList.getItems().add(name);
        }
 
        clearBtn.setOnAction(e -> clearOrder(e)); //may be redundent who knows? 
 
        entryList.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    order.setEntree(newVal); // null when cleared
                    updateTotal();
                });
 
        // Radio buttons: the toggle is null when cleared
        drinksToggle.selectedToggleProperty().addListener((obs, oldT, newT) -> {
            if (newT == null) {
                order.setDrink(null);
            } else {
                order.setDrink(((RadioButton) newT).getText());
            }
            updateTotal();
        });
 
        waterCheck.selectedProperty().addListener((obs, oldVal, newVal) -> {
            order.setWater(newVal);
        });
 
        specialField.textProperty().addListener((obs, oldVal, newVal) -> {
            order.setSpecialRequest(newVal);
        });
 
        updateTotal();
    }
    
    //update label of total (self explanitory)
    private void updateTotal() {
        totalLabel.setText(String.format("Total: $%.2f", order.getTotal()));
    }
 
}

//Little note: I really struggled with this code, not sure why. I also missed 
// the clear instructions to make an order class so it took me longer, hurray
//I skipped my second class of the day to make sure I coud finish it by today