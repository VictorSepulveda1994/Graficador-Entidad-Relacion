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
 * @author Equipo Rocket
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
     *Guarda el nombre
     */
    public static String enteredName="";
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
        enteredName=newName.textProperty().get();
        if(enteredName.isEmpty() || enteredName.length()>12){
            alertName();
            enteredName = "";
        }
        else{
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

    
