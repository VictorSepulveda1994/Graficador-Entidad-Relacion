package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Attribute;
import static model.Diagram.selectedElement;

/**
 * FXML Controller class
 * Esta clase permite editar un atrbuto.
 * @author Equipo Rocket
 */
public class PopEditAttributeController extends CallPop implements Initializable{
    
    /**
     * Panel donde se realizaran las acciones.
     */
    @FXML
    private AnchorPane root;

    /**
     * Entrada por donde se recibe el nombre de el atributo a crear.
     */
    @FXML
    public TextField newName;

    /**
     * Donde se guarda el nombre editado del atributo.
     */
    public static String enteredNameA;
    
    public static Attribute newAttribute;
    /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newAttribute = (Attribute)selectedElement;
        newName.setText(newAttribute.getName());
    }
    
    /**
     * Metodo que se encarga de guardar todos los cambios realizados en el atributo.
     * También muestra mensaje de error en caso de realizar una accion incorrecta.
     */
    public void addToScreen(){
        enteredNameA=newName.textProperty().get();
        if(enteredNameA.isEmpty() || enteredNameA.length()>12 || MainController.diagram.thisNameExists(enteredNameA)){
            alertName();
            enteredNameA=newAttribute.getName();
        }
        else{
            newAttribute.setName(enteredNameA);
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

    
