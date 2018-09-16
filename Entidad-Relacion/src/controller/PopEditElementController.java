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
 * FXML Controller class
 *
 * @author Alejandra
 */
public class PopEditElementController extends CallPop implements Initializable {

    /**
     *Panel donde se realizan las acciones
     */
    @FXML
    public AnchorPane root;
    
    /**
     *Recibe el nombre
     */
    @FXML
    public static TextField name;
    
    /**
     *Guarda el nombre
     */
    public static String enteredName="";

    /**
     *Verifica si se cancela la accion
     */
    public static boolean cancelActionEdit = false;
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
     *Guarda los cambios realizados
     */
    public void saveChange(){
        enteredName=name.textProperty().get();
        if(enteredName.isEmpty() || enteredName.length()>21){
            alertName();
        }
        cancelActionEdit = false;
        ((Stage)root.getScene().getWindow()).close();
    }
    
    /**
     *Cancela la accion
     */
    public void cancel(){
        cancelActionEdit = true;
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
