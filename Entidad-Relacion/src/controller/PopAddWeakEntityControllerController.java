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
import model.Entity;

/**
 * FXML Controller class
 *
 * @author Reinalda Contreras
 */
public class PopAddWeakEntityControllerController extends CallPop implements Initializable {

    /**
     * Panel donde se realizaran las acciones
     */
    @FXML
    private AnchorPane root;

    /**
     * Recibe el nombre de la entidad
     */
    @FXML
    private TextField nameEntity;
    
    
    public static String nameOfEntity = "";
    /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Obtiene el nombre ingresado, verifica el tamaño correcto o si esta vacío
     * Crea un objeto "entity" y es agregado a "diagram"
     */
    public void addToScreen(){
        nameOfEntity=nameEntity.textProperty().get();
        if( nameOfEntity.isEmpty() || nameOfEntity.length()>12 || MainController.diagram.thisNameExists(nameOfEntity) ){
            alertName();
        }
        else{
            Entity entity = new Entity(nameOfEntity, (int)MainController.event.getX(), (int)MainController.event.getY(), false);
            
            MainController.diagram.addEntity(entity);
            ((Stage)root.getScene().getWindow()).close();
        }
    }
    
    /**
     * Cancela la operación
     */
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
}
