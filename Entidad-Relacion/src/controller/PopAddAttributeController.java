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
import static model.Diagram.count;

/**
 * FXML Controller class
 * Esta clase se encarga de agregar un atributo.
 * @author Equipo Rocket
 */
public class PopAddAttributeController extends CallPop implements Initializable {
    
    /**
     * Panel donde se realizaran las acciones.
     */
    @FXML
    private AnchorPane root;
    
    /**
     * Entrada por donde se recibe el nombre del atributo a crear.
     */
    @FXML
    private TextField nameNewAttribute;
    
    /**
     * Lista de opciones, para que el usuario elija el tipo de atributo a crear.
     */
    @FXML
    ComboBox<String> options= new ComboBox<String>(); 
    
    /**
     * Inicialización del nombre del atributo.
     */
    public static String nameAttribute="";
    
    /**
     * Donde se guarda el tipo de atributo escogido por el usuario.
     */
    public static AttributeType attributeType;
    
    /**
     * Se verifica si el atributo es "Compound" para que usuario pueda agregarle mas atributos.
     */
    public static boolean onlyCompound;
    
    
    /**
     * Incializacion de la clase controler.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        attributeType=AttributeType.GENERIC;
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
    
    /**
     * Metodo que guarda el nombre del atributo y el tipo para poder crearlo.
     */
    public void addToScreen(){
        nameAttribute=nameNewAttribute.textProperty().get();
        if( nameAttribute.isEmpty()){
            nameAttribute="a"+count;
            count++;
            if(attributeType==null){
                attributeType=AttributeType.GENERIC;
            }
            ((Stage)root.getScene().getWindow()).close();
        }
        else if(nameAttribute.length()>12 || MainController.diagram.thisNameExists(nameAttribute) ){
            alertName();
        }
        else{
            ((Stage)root.getScene().getWindow()).close();
        }
    }
 
    /**
     * Metodo que cancela la operación.
     */
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
    
    /**
     * Metodo que le permite al asuario cambiar el tipo de atributo.
     */
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
