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
 * @author Equipo Rocket
 */
public class PopAddEntityController extends CallPop implements Initializable {

    /**
     *Panel donde se realizan las acciones
     */
    @FXML
    public AnchorPane root;

    /**
     *Recibe el nombre de la entidad
     */
    @FXML
    public TextField nameEntity;
    
    /**
     *Guarda el nombre de la entidad 
     */
    public static String nameOfEntity = "";
    
    /**
     *cancela la accion
     */
    public static boolean cancelActionEntity = false;
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
     *Añade un nombre y luego verifica si este esta vacio o su tamaño es mayor a 20
     */
    public void addToScreen(){
        nameOfEntity=nameEntity.textProperty().get();
        if(nameOfEntity.isEmpty() || nameOfEntity.length()>21){
            alertName();
        }
        cancelActionEntity = false;
        ((Stage)root.getScene().getWindow()).close();
    }
    
    /**
     *Cancela la accion y cierra la ventana
     */
    public void cancel(){
        cancelActionEntity = true;
        ((Stage)root.getScene().getWindow()).close();
    }
}
