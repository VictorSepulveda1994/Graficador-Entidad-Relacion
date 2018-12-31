package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Aggregation;
import static model.Diagram.count;
import model.Element;

/**
 * @author Equipo Rocket
 */
public class PopAddAggregationController extends CallPop implements Initializable {

    /**
     * Panel donde se realizaran las acciones.
     */
    @FXML
    private AnchorPane root;

    /**
     * Entrada por donde se recibe el nombre de la entidad a crear.
     */
    @FXML
    private TextField nameAggregation;
    
    /**
    * Inicialización del nombre de la entidad.
    */
    public static String nameOfAggregation = "";
    
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
        nameOfAggregation=nameAggregation.textProperty().get();
        if( nameOfAggregation.isEmpty()){
            nameOfAggregation="ag"+count;
            count++;
            Aggregation aggregation = new Aggregation(false, nameOfAggregation, MainController.elementsSelect);
            MainController.diagram.addAggregation(aggregation);
            //MainController.elementsSelect.clear();
            MainController.diagram.deselectAll();
            ((Stage)root.getScene().getWindow()).close();
        }
        else if(nameOfAggregation.length()>12 || MainController.diagram.thisNameExists(nameOfAggregation)){
            alertName();
        }
        else{
            ArrayList<Element> e = new ArrayList<>();
            Aggregation aggregation = new Aggregation(false, nameOfAggregation, MainController.elementsSelect);
            MainController.diagram.addAggregation(aggregation);
            //MainController.elementsSelect.clear();
            MainController.diagram.deselectAll();
            ((Stage)root.getScene().getWindow()).close();
        }
    }
    
    /**
     * Metodo que se encarga de cancelar la operación
     */
    public void cancel(){
        //MainController.elementsSelect.clear();
        MainController.diagram.deselectAll();
        ((Stage)root.getScene().getWindow()).close();
    }
}
