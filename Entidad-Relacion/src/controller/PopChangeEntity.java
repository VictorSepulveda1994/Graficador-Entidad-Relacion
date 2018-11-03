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
import model.Entity;
import model.EntityType;
/**
 *
 * @author Equipo Rocket
 */
public class PopChangeEntity extends CallPop implements Initializable{
/**
     * Panel donde se realizaran las acciones
     */
    @FXML
    private AnchorPane root;
    @FXML
    CheckBox opcion1;
    @FXML
    CheckBox opcion2;
    /**
     * Recibe el nombre de la entidad
     */
    @FXML
    public TextField newName;

    /**
     *Guarda el nombre
     */
    public static String enteredName;
    public static EntityType type;
    public static Entity newentity= new Entity((Entity) selectedElement);
    /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type=newentity.getType();
        newName.setText(newentity.getName());
        if(type.equals(EntityType.WEAK)){
            opcion1.setSelected(true);
        }
        else{
            opcion2.setSelected(true);
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
            enteredName=newentity.getName();
        }
        else{
            if((opcion1.isSelected() && opcion2.isSelected()) || (!opcion1.isSelected() && !opcion2.isSelected())){
                alertTypeEnity();
            }
            else{
                if(opcion1.isSelected()){
                    type=EntityType.WEAK;
                }
                else{
                    type=EntityType.STRONG;
                }
                newentity.setName(enteredName);
                newentity.setType(type);
                ((Stage)root.getScene().getWindow()).close();
            }
        }
    }
    
    
    /**
     * Cancela la operación
     */
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
    
}

    
