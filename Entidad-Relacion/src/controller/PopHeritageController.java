package controller;

import static controller.MainController.entitiesSelect;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Attribute;
import model.Entity;
import model.Heritage;
import model.HeritageType;

/**
 * FXML Controller class
 *
 * @author Alejandra
 */
public class PopHeritageController extends CallPop implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private CheckBox opcion1;
    @FXML
    private CheckBox opcion2;

    public static HeritageType type;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void options(){
        if((opcion1.isSelected()==false && opcion2.isSelected()==false) || (opcion1.isSelected()==true && opcion2.isSelected()==true)){
            alertTypeHeritageIncorrect();
            type=null;
        }
        else{
            if(opcion2.isSelected()){
                type=HeritageType.DISJUNCTION;
            }
            else{
                type=HeritageType.OVERLAP;
            }
            ArrayList<Entity> entities= (ArrayList<Entity>) entitiesSelect.clone();
            ArrayList<Attribute> attributes= new ArrayList<>();
            Heritage heritage = new Heritage(null, (int)MainController.event.getX(), (int)MainController.event.getY(),false, attributes, entities, type);
            entitiesSelect.clear();
            MainController.diagram.addHeritage(heritage);
            MainController.diagram.deselectAllEntities();
            ((Stage)root.getScene().getWindow()).close();
        }      
    }
    
    public void cancel(){
        entitiesSelect.clear();
        MainController.diagram.deselectAllEntities();
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
