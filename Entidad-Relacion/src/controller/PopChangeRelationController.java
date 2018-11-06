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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static model.Diagram.selectedElement;
import model.FigureType;
import model.Relation;

/**
 * FXML Controller class
 *
 * @author 
 */
public class PopChangeRelationController extends CallPop implements Initializable{
/**
     * Panel donde se realizaran las acciones
     */
    @FXML
    private AnchorPane root;
    @FXML
    CheckBox opcion;
    /**
     * Recibe el nombre de la entidad
     */
    @FXML
    public TextField newName;

    /**
     *Guarda el nombre
     */
    public static String enteredName;
    public static FigureType type;
    public static Relation newRelation= new Relation((Relation) selectedElement);
    /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type=newRelation.getType();
        newName.setText(newRelation.getName());
        if(type.equals(FigureType.WEAK)){
            opcion.setSelected(true);
        }
        else{
            opcion.setSelected(false);
        }
    }
    
    /**
     * Obtiene el nombre ingresado, verifica el tamaño correcto o si esta vacío y
     * crea un objeto "relation" y es agregado a "diagram"
     */
    public void addToScreen(){
        enteredName=newName.textProperty().get();
        if(enteredName.isEmpty() || enteredName.length()>12){
            alertName();
            enteredName=newRelation.getName();
        }
        else{
            if(opcion.isSelected()){
                type=FigureType.WEAK;
            }
            else{
                type=FigureType.STRONG;
            }
            newRelation.setName(enteredName);
            newRelation.setType(type);
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
