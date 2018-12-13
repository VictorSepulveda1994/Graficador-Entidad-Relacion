/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Connector;
import static model.Diagram.selectedElement;
import model.Element;


/**
 * FXML Controller class
 *
 * @author EquipoRocket
 */
public class PopEditConnectorController extends CallPop implements Initializable  {

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

    public static Connector newConnector;
    public static boolean isDoubleConnector;
     /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        newConnector = (Connector)selectedElement;      
        if(newConnector.isDoble()){
            option.setSelected(true);
        }
        else{
            option.setSelected(false);
        }
    }
    
    /**
     * Metodo que se encarga de guardar todos los cambios realizados a la entidad.
     * También muestra mensaje de error en caso de realizar una accion incorrecta.
     */
    public void addToScreen(){
        if(option.isSelected()){
            isDoubleConnector=true;
            /*
            Element element1= newConnector.getElement1();
            element1.setDoubleConnector(true);
            newConnector.setElement1(element1);
            Element element2= newConnector.getElement2();
            element2.setDoubleConnector(true);
            newConnector.setElement2(element2);
            */
        }
        else{
            isDoubleConnector=false;/*
            Element element1= newConnector.getElement1();
            element1.setDoubleConnector(false);
            newConnector.setElement1(element1);
            Element element2= newConnector.getElement2();
            element2.setDoubleConnector(false);
            newConnector.setElement2(element2);
            */
        }
        ((Stage)root.getScene().getWindow()).close();

        
    }
    
    public boolean connectorIsDouble(){
        return newConnector.getElement1().doubleConnector==true && newConnector.getElement2().doubleConnector==true;
    }
      
    /**
     * Cancela la operación
     */
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
}
