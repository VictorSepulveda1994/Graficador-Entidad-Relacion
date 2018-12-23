package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static model.Diagram.selectedElement;
import model.Entity;
import model.FigureType;
import model.Relation;

/**
 * FXML Controller 
 * Esta clase permite editar una entidad.
 * @author Equipo Rocket
 */
public class PopEditEntityController extends CallPop implements Initializable{
    
    /**
     * Panel donde se realizaran las acciones.
     */
    @FXML
    private AnchorPane root;
    
    /**
     * Opcion para cambiar el tipo de entidad (Debil/Fuerte).
     */
    @FXML
    CheckBox option;
    
    /**
     * Recibe el nombre de la entidad.
     */
    @FXML
    public TextField newName;

    /**
     * Guarda el nombre editado de la entidad.
     */
    public static String enteredName;
    
    /**
     * Guarda el tipo editado de la entidad.
     */
    public static FigureType type;
    
    /**
     * Guarda la nueva entidad con los cambios de edición.
     */
    public static Entity newEntity;
    
    /***
     *  Texto que le advierte al usuario que no puede poner la entidad debil sino cumple con el requisito necesario.
     */
    @FXML
    public Text alertEntity;
    
    /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        newEntity= new Entity((Entity) selectedElement);
        type=newEntity.getType();
        newName.setText(newEntity.getName());
        if(type.equals(FigureType.WEAK)){
            option.setSelected(true);
        }
        else{
            option.setSelected(false);
        }
        if(!theEntityCanBeWeak()){
            option.setDisable(true);
            alertEntity.setVisible(true);
        }

    }
    
    /**
     * Metodo que se encarga de guardar todos los cambios realizados a la entidad.
     * También muestra mensaje de error en caso de realizar una accion incorrecta.
     */
    public void addToScreen(){
        enteredName=newName.textProperty().get();
        if(enteredName.isEmpty() || enteredName.length()>12){
            alertName();
            enteredName=newEntity.getName();
        }
        else{
            if(option.isSelected()){
                type=FigureType.WEAK;  
                ((Stage)root.getScene().getWindow()).close();                      
            }
            else{
                type=FigureType.STRONG;
            }
            newEntity.setName(enteredName);
            newEntity.setType(type);
            ((Stage)root.getScene().getWindow()).close();
            
        }
    }
    
    public boolean theEntityCanBeWeak(){
        MainController.diagram.actualizar();
        int numberOfStrongEntities=0;
        for (int i = 0; i <MainController.diagram.getRelations().size(); i++) {
            Relation relation = MainController.diagram.getRelations().get(i);
            if(relation.hasThisEntity(newEntity)){
                for (int j = 0; j <relation.getEntities().size(); j++) {
                    if(relation.getEntities().get(j).getType().equals(FigureType.STRONG) && !relation.getEntities().get(j).getName().equals(newEntity.getName())){
                        numberOfStrongEntities+=1;
                    }
                }
            }
        }
        return numberOfStrongEntities>=1;
    }
    
    /**
     * Cancela la operación
     */
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
    
}

    
