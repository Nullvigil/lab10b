package com.mycompany.lab10b;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        closeBtn.setOnAction(e -> Platform.exit()); //I acknoledge that you must
        //double press close button but oh well no time to troubleshoot
    }
    
    public void load() {
        
    }
    
}
