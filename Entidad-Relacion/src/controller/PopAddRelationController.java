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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Equipo Rocket
 */
public class PopAddRelationController extends CallPop implements Initializable {
    
    /**
     *Muestra la ventana
     */
    @FXML
    private AnchorPane root;

    /**
     *Recibe el nombre de la relacion
     */
    @FXML
    public TextField nameRelation;

    /**
     *Guarda el nombre de la relacion
     */
    public static String nameOfRelation = "";
    
    /**
     *Cancela la accion
     */
    public static boolean cancelActionRelation = false;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO   
    }
    
    /**
     *Guarda el nombre de la relacion y verifica que no este vacia y que sea menor que
     * 20 la cantidad de letras
     */
    public void addToScreen(){
        nameOfRelation=nameRelation.textProperty().get();
        if(nameOfRelation.isEmpty() || nameOfRelation.length()>21){
            alertName();
        }
        cancelActionRelation = false;
        ((Stage)root.getScene().getWindow()).close();
    }
    
    /**
     *Cancela la accion y cierra la ventana
     */
    public void cancel(){
        cancelActionRelation = true;
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
