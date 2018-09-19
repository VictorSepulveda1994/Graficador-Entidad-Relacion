package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
        if(nameOfRelation.isEmpty() || nameOfRelation.length()>21){
            alertName();
        }
        else{
            MainController.diagram.addRelation(new Relation(nameOfRelation, calculateSidesOfRelation (), (int)MainController.event.getX(), (int)MainController.event.getY(), false) );
            ((Stage)root.getScene().getWindow()).close();
        }
    }
    
    public int calculateSidesOfRelation (){
        int numberOfEntities = MainController.diagram.numberOfEntitiesSelect();
        if (numberOfEntities==1 || numberOfEntities==2 || numberOfEntities==4){
            return 4;
        }
        return numberOfEntities;
    }
    
    /**
     * Cancela la operación
     */
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
