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
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static model.Diagram.selectedElement;
import model.Heritage;
import model.HeritageType;

/**
 * FXML Controller class
 *
 * @author Reinalda Contreras
 */
public class PopChangeHeritageController extends CallPop implements Initializable {
    /**
    * Panel donde se realizaran las acciones
    */
    @FXML
    private AnchorPane root;
    
    /**
    * Opcion para escoger herencia tipo disyunción
    */
    @FXML
    CheckBox option1;
    
    /**
    * Opcion para escoger herencia tipo solapamiento
    */
    @FXML
    CheckBox option2;
    

    public static HeritageType type;
    public static Heritage newHeritage;
    /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        newHeritage= (Heritage) selectedElement;
        type=newHeritage.getHeritageType();
        if(type.equals(HeritageType.DISJUNCTION)){
            option1.setSelected(true);
        }
        else{
            option2.setSelected(true);
        }
    }
    
    /**
     * Obtiene el nombre ingresado, verifica el tamaño correcto o si esta vacío y
     * crea un objeto "relation" y es agregado a "diagram"
     */
    public void addToScreen(){
        if((option1.isSelected()==false && option2.isSelected()==false) || (option1.isSelected()==true && option2.isSelected()==true)){
            alertTypeHeritageIncorrect();
            type=null;
        }
        else{
            if(option1.isSelected()){
                type=HeritageType.DISJUNCTION;

            }
            else{
                type=HeritageType.OVERLAP;
            }
            newHeritage.setHeritageType(type);
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
