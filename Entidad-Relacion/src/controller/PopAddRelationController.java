package controller;

import static controller.MainController.entitiesSelect;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Attribute;
import model.Entity;
import model.FigureType;
import model.Relation;
import static model.Diagram.count;
import model.Relation.Cardinality;

/**
 * FXML Controller class
 * Esta clase se encarga de agregar una relación.
 * @author Equipo Rocket
 */
public class PopAddRelationController extends CallPop implements Initializable {
    
    /**
     * Panel donde se realizaran las acciones.
     */
    @FXML
    private AnchorPane root;

    /**
     * Entrada por donde se recibe el nombre de la relación a crear.
     */
    @FXML
    public TextField nameRelation;

    @FXML
    public ChoiceBox optionsCardinality;
    
    ObservableList <String> typesOfCardinality = FXCollections.observableArrayList("Uno a uno","Uno a muchos","Muchos a muchos","Muchos a uno");
    /**
    * Inicialización del nombre de la relación.
    */
    public static String nameOfRelation = "";
    
    public static Cardinality typeCardinality;
    
    /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        optionsCardinality.setItems(typesOfCardinality);
        if(entitiesSelect.size()==2){
            optionsCardinality.setValue(typesOfCardinality.get(0));
        }
        else{
            optionsCardinality.setDisable(true);
            optionsCardinality.setValue("");
        }
        
    }
    
    /**
     * Obtiene el nombre ingresado, verifica el tamaño correcto o si esta vacío y
     * crea un objeto "relation" y es agregado a "diagram".
     */
    public void addToScreen(){
        nameOfRelation=nameRelation.textProperty().get();
        if(nameOfRelation.isEmpty()){
            nameOfRelation="r"+count;
            count++;
            if(optionsCardinality.getValue().equals("Uno a uno")){  
                typeCardinality=Cardinality.ONE_TO_ONE;
            }
            else if(optionsCardinality.getValue().equals("Uno a muchos")){
                typeCardinality=Cardinality.ONE_TO_MANY;
            }
            else if(optionsCardinality.getValue().equals("Muchos a muchos")){
                typeCardinality=Cardinality.MANY_TO_MANY;
            }
            else if(optionsCardinality.getValue().equals("Muchos a uno")){
                typeCardinality=Cardinality.MANY_TO_ONE;
            }
            FigureType type = FigureType.STRONG;
            ArrayList<Entity> entities= (ArrayList<Entity>) entitiesSelect.clone();
            ArrayList<Attribute> attributes= new ArrayList<>();
            Relation relation = new Relation(nameOfRelation, MainController.diagram.numberOfEntitiesSelect(), (int)MainController.event.getX(), (int)MainController.event.getY(), false, entities, attributes, type,typeCardinality);
            entitiesSelect.clear();
            MainController.diagram.addRelation(relation);
            MainController.diagram.deselectAllEntities();
            ((Stage)root.getScene().getWindow()).close();
        }    
        else if(nameOfRelation.length()>12 || MainController.diagram.thisNameExists(nameOfRelation)){
            alertName();
        }
        else{
            if(optionsCardinality.getValue().equals("Uno a uno")){  
                typeCardinality=Cardinality.ONE_TO_ONE;
            }
            else if(optionsCardinality.getValue().equals("Uno a muchos")){
                typeCardinality=Cardinality.ONE_TO_MANY;
            }
            else if(optionsCardinality.getValue().equals("Muchos a muchos")){
                typeCardinality=Cardinality.MANY_TO_MANY;
            }
            else if(optionsCardinality.getValue().equals("Muchos a uno")){
                typeCardinality=Cardinality.MANY_TO_ONE;
            }
            FigureType type = FigureType.STRONG;
            ArrayList<Entity> entities= (ArrayList<Entity>) entitiesSelect.clone();
            ArrayList<Attribute> attributes= new ArrayList<>();
            Relation relation = new Relation(nameOfRelation, MainController.diagram.numberOfEntitiesSelect(), (int)MainController.event.getX(), (int)MainController.event.getY(), false, entities, attributes, type,typeCardinality);
            entitiesSelect.clear();
            MainController.diagram.addRelation(relation);
            MainController.diagram.deselectAllEntities();
            ((Stage)root.getScene().getWindow()).close();
        }
    }
    
    /**
     * Cancela la operación
     */
    public void cancel(){
        entitiesSelect.clear();
        MainController.diagram.deselectAllEntities();
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
