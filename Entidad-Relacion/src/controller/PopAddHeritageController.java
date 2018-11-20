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
import model.FigureType;
import model.Heritage;
import model.HeritageType;

/**
 * FXML Controller class
 * Esta clase se encarga de agregar una herencia.
 * @author Equipo Rocket
 */
public class PopAddHeritageController extends CallPop implements Initializable {
    
    /**
     * Panel donde se realizaran las acciones.
     */
    @FXML
    private AnchorPane root;
    
    /**
     * Casilla para escoger el tipo de la herencia (Disyunción).
     */
    @FXML
    private CheckBox option1;
    
    /**
     * Casilla para escoger el tipo de la herencia (Solapamiento).
     */
    @FXML
    private CheckBox option2;
    
    /**
     * Donde se guarda el tipo de la herencia.
     */
    public static HeritageType type;
    
    /**
     * Iniacializa la clase controler.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Metodo que se encarga de guardar todos los cambios realizados en la herencia.
     * También muestra mensaje de error en caso de realizar una accion incorrecta. 
     */
    public void options(){
        if((option1.isSelected()==false && option2.isSelected()==false) || (option1.isSelected()==true && option2.isSelected()==true)){
            alertTypeHeritageIncorrect();
            type=null;
        }
        else{
            if(option2.isSelected()){
                type=HeritageType.DISJUNCTION;
            }
            else{
                type=HeritageType.OVERLAP;
            }
            if(validFather() && validBrother() && validEntities()){
                ArrayList<Entity> entities= (ArrayList<Entity>) entitiesSelect.clone();
                ArrayList<Attribute> attributes= new ArrayList<>();
                Heritage heritage = new Heritage(null, (int)MainController.event.getX(), (int)MainController.event.getY(),false, attributes, entities, type);
                entitiesSelect.clear();
                MainController.diagram.addHeritage(heritage);
                MainController.diagram.deselectAllEntities();
                ((Stage)root.getScene().getWindow()).close();
            }
            else{
                alertInvalid();
                entitiesSelect.clear();
                MainController.diagram.deselectAllEntities();
                ((Stage)root.getScene().getWindow()).close();
            }
        }      
    }
    
    /**
     * Metodo que se encarga de validar si el padre escogido para la herencia.
     * @return verdadero si el padre es valido, falso en caso contrario.
     */
    public boolean validFather(){
        boolean valid1=true;
        boolean valid2=true;
        for(int i=0; i< MainController.diagram.getHeritages().size();i++){
            for(int a=0;a<MainController.diagram.getHeritages().get(i).getDaughtersEntities().size();a++){
                if(entitiesSelect.get(0).equals(MainController.diagram.getHeritages().get(i).getDaughtersEntities().get(a))){
                    valid1=false;
                }
                if(valid1==false){
                    for(int j=1;j<entitiesSelect.size();j++){
                        if(MainController.diagram.getHeritages().get(i).getParentEntity().equals(entitiesSelect.get(j))){
                            valid2=false;
                        }
                    }
                }
            }      
        }
        if(!valid1 && !valid2){
            return false;
        }
        else{
            return true;
        }
    }
    
    /**
     * Metodo que se encarga de validar si el padre no es hermano.
     * @return verdadero si el hermano es valido, falso en caso contrario.
     */
    public boolean validBrother(){
        boolean valid1=true;
        for(int i=0; i< MainController.diagram.getHeritages().size();i++){
            for(int a=0;a<MainController.diagram.getHeritages().get(i).getDaughtersEntities().size();a++){
                if(entitiesSelect.get(0).equals(MainController.diagram.getHeritages().get(i).getDaughtersEntities().get(a))){
                    valid1=false;
                }
            }          
        }
        return valid1;
    }
    
    /**
     * Metodo que se encarga de validar los hijos de la herencia.
     * @return Verdadero si los hijos son validos, falso en caso contrario.
     */
    public boolean validEntities (){
        ArrayList<Entity> entities= (ArrayList<Entity>) entitiesSelect.clone();
        for (Entity entitie : entities) {
            if(entitie.type.equals(FigureType.WEAK)){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metodo que cancela la operación.
     */
    public void cancel(){
        entitiesSelect.clear();
        MainController.diagram.deselectAllEntities();
        ((Stage)root.getScene().getWindow()).close();
    } 
}
