/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alejandra
 */
public class PopAgregarNodoController implements Initializable {
    @FXML
    public AnchorPane root;
    @FXML
    public TextField nombreNodo;
    /**
     * Initializes the controller class.
     */
    public static String nombreDelNodo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void agregarALaPantalla(){
        nombreDelNodo=nombreNodo.textProperty().get();
        ((Stage)root.getScene().getWindow()).close();
    }
}
