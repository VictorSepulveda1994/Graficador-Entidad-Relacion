/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 *
 * @author Milagod
 */
public class PopChangeName extends CallPop implements Initializable{
/**
     * Panel donde se realizaran las acciones
     */
    @FXML
    private AnchorPane root;

    /**
     * Recibe el nombre de la entidad
     */
    @FXML
    public TextField newName;

    
    /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO   
    }
    
    /**
     * Obtiene el nombre ingresado, verifica el tamaño correcto o si esta vacío y
     * crea un objeto "relation" y es agregado a "diagram"
     */
    public void addToScreen(){
        alertName();
    }
    
    
    /**
     * Cancela la operación
     */
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
    
}

    
