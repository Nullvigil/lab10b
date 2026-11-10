package com.mycompany.lab10b;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OrderController {

    @FXML
    private Button closeBtn;
 
    @FXML
    private Label drinkSelection;
 
    @FXML
    private Label entrySelection;
 
    @FXML
    private Label specialText;
 
    @FXML
    private Label totalOrder;
 
    @FXML
    private Label waterBool;
 
    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
 
    // Fills every label from the Order model.
    public void load(Order order) {
        entrySelection.setText(String.format("%s    $%.2f",
                order.getEntree(), order.getEntreePrice()));
        drinkSelection.setText(String.format("%s    $%.2f",
                order.getDrink(), order.getDrinkPrice()));
        waterBool.setText(order.isWater() ? "Yes" : "No");
 
        String special = order.getSpecialRequest().trim();
        specialText.setText(special.isEmpty() ? "None" : special);
 
        totalOrder.setText(String.format("$%.2f", order.getTotal()));
    }
 
}
