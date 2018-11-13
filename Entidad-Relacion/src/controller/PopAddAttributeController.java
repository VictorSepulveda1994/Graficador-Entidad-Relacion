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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AttributeType;

/**
 * FXML Controller class
 *
 * @author Equipo Rocket
 */
public class PopAddAttributeController extends CallPop implements Initializable {
    
    @FXML
    private AnchorPane root;
    @FXML
    private TextField nameNewAttribute;
    @FXML
    ComboBox<String> options= new ComboBox<String>(); 
    
    public static String nameAttribute="";
    
    public static AttributeType attributeType;
    public static boolean onlyCompound;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(onlyCompound==false){
            options.getItems().addAll("Atributo Clave","Atributo Clave Parcial","Atributo Generico","Atributo Multivaluado"
                    ,"Atributo Compuesto","Atributo Derivado");
            options.setValue("Elige una opción: ");
            options.getItems();
        }
        else{
            options.getItems().add("Atributo Generico");
            options.setValue("Elige una opción: ");
            options.getItems();
        }
    } 
    //cierra la ventana y agrega el nombre que este correcto
    public void addToScreen(){
        nameAttribute=nameNewAttribute.textProperty().get();
        if( nameAttribute.isEmpty() || nameAttribute.length()>12 || MainController.diagram.thisNameExists(nameAttribute) ){
            alertName();
        }
        else{
            ((Stage)root.getScene().getWindow()).close();
        }
    }
 
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
    //cambia la opcion del tipo de atributo
    @FXML
    public void changeOfOption(){
        if("Atributo Clave".equals(options.getValue())){
            attributeType=AttributeType.KEY;
        }
        if("Atributo Generico".equals(options.getValue())){
            attributeType=AttributeType.GENERIC;
        }
        if("Atributo Multivaluado".equals(options.getValue())){
            attributeType=AttributeType.MULTIVALED;
        }
        if("Atributo Compuesto".equals(options.getValue())){
            attributeType=AttributeType.COMPOUND;
        }
        if("Atributo Derivado".equals(options.getValue())){
            attributeType=AttributeType.DERIVATIVE;
        }
        if("Atributo Clave Parcial".equals(options.getValue())){
            attributeType=AttributeType.PARTIALKEY;
        }
    }
}
