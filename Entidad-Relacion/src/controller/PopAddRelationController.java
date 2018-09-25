package controller;

import static controller.MainController.entitiesSelect;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Entity;
import model.Relation;

/**
 * FXML Controller class
 *
 * @author Equipo Rocket
 */
public class PopAddRelationController extends CallPop implements Initializable {
    
    /**
     * Panel donde se realizaran las acciones
     */
    @FXML
    private AnchorPane root;

    /**
     * Recibe el nombre de la entidad
     */
    @FXML
    public TextField nameRelation;

    
    public static String nameOfRelation = "";
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
        nameOfRelation=nameRelation.textProperty().get();
        if(nameOfRelation.isEmpty() || nameOfRelation.length()>12){
            alertName();
        }
        else{
            ArrayList<Entity> entities= new ArrayList<>();
            entities=(ArrayList<Entity>) entitiesSelect.clone();
            Relation relation=new Relation(nameOfRelation, MainController.diagram.numberOfEntitiesSelect(), (int)MainController.event.getX(), (int)MainController.event.getY(), false,entities);
            entitiesSelect.clear();
            MainController.diagram.addRelation(relation);
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
