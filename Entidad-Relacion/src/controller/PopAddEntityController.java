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
 * @author Equipo Rocket
 */
public class PopAddEntityController extends CallPop implements Initializable {

    /**
<<<<<<< HEAD
<<<<<<< HEAD
     *Panel donde se realizan las acciones
=======
     * Panel donde se realizaran las acciones
>>>>>>> origin/victor
=======
     * Panel donde se realizaran las acciones
>>>>>>> origin/victor
     */
    @FXML
    private AnchorPane root;

    /**
<<<<<<< HEAD
<<<<<<< HEAD
     *Recibe el nombre de la entidad
=======
     * Recibe el nombre de la entidad
>>>>>>> origin/victor
     */
    @FXML
    private TextField nameEntity;
=======
     * Recibe el nombre de la entidad
     */
    @FXML
    private TextField nameEntity;
    
>>>>>>> origin/victor
    
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
<<<<<<< HEAD
<<<<<<< HEAD
     *Añade un nombre y luego verifica si este esta vacio o su tamaño es mayor a 20
=======
     * Obtiene el nombre ingresado, verifica el tamaño correcto o si esta vacío
     * Crea un objeto "entity" y es agregado a "diagram"
>>>>>>> origin/victor
=======
     * Obtiene el nombre ingresado, verifica el tamaño correcto o si esta vacío
     * Crea un objeto "entity" y es agregado a "diagram"
>>>>>>> origin/victor
     */
    public void addToScreen(){
        nameOfEntity=nameEntity.textProperty().get();
        if(nameOfEntity.isEmpty() || nameOfEntity.length()>21){
            alertName();
        }
<<<<<<< HEAD
        cancelActionEntity = false;
        ((Stage)root.getScene().getWindow()).close();
    }
    
    /**
     *Cancela la accion y cierra la ventana
     */
    public void cancel(){
        cancelActionEntity = true;
=======
        else{
            MainController.diagram.addEntity(new Entity(nameOfEntity, (int)MainController.event.getX(), (int)MainController.event.getY() ) );
        }
<<<<<<< HEAD
>>>>>>> origin/victor
=======
        else{
            MainController.diagram.addEntity(new Entity(nameOfEntity, (int)MainController.event.getX(), (int)MainController.event.getY() ) );
        }
>>>>>>> origin/victor
        ((Stage)root.getScene().getWindow()).close();
    }
}
