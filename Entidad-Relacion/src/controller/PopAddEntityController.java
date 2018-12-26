package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Attribute;
import model.Entity;
import model.FigureType;
import static model.Diagram.count;


/**
 * FXML Controller class
 * Esta clase se encarga de agregar una entidad.
 * @author Equipo Rocket
 */
public class PopAddEntityController extends CallPop implements Initializable {

    /**
     * Panel donde se realizaran las acciones.
     */
    @FXML
    private AnchorPane root;

    /**
     * Entrada por donde se recibe el nombre de la entidad a crear.
     */
    @FXML
    private TextField nameEntity;
    
    /**
    * Inicialización del nombre de la entidad.
    */
    public static String nameOfEntity = "";
    
    /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Obtiene el nombre ingresado, verifica el tamaño correcto o si esta vacío
     * Crea un objeto "entity" y es agregado a "diagram".
     */
    public void addToScreen(){
        nameOfEntity=nameEntity.textProperty().get();
        if( nameOfEntity.isEmpty()){
            nameOfEntity="e"+count;
            count++;
            FigureType type = FigureType.STRONG;
            ArrayList<Attribute> attributes= new ArrayList<>();
            MainController.diagram.addEntity(new Entity(nameOfEntity, (int)MainController.event.getX(), (int)MainController.event.getY(), false,type,attributes));
            ((Stage)root.getScene().getWindow()).close();
        }
        else if(nameOfEntity.length()>12 || MainController.diagram.thisNameExists(nameOfEntity)){
            alertName();
        }
        else{
            FigureType type = FigureType.STRONG;
            ArrayList<Attribute> attributes= new ArrayList<>();
            MainController.diagram.addEntity(new Entity(nameOfEntity, (int)MainController.event.getX(), (int)MainController.event.getY(), false,type,attributes));
            ((Stage)root.getScene().getWindow()).close();
        }
    }
    
    /**
     * Metodo que se encarga de cancelar la operación
     */
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
}
