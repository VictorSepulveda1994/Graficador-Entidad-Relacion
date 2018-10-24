/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.EntityType;

/**
 *
 * @author Equipo Rocket
 */

public class PopQuestionEntityTypeController extends CallPop implements Initializable {

    /**
     * Panel donde se realizaran las acciones
     */
    @FXML
    private AnchorPane root;
    
    /**
     * Casilla de selección para entidad fuerte
     */
    @FXML
    private CheckBox strongEntity;
    
    /**
     * Casilla de selección para entidad débil
     */
    @FXML
    private CheckBox weakEntity;
    
    public static EntityType typeChoosed=null;
    
    /**
     * Inicio de la clase controladora
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeChoosed=null;
    }
    
 
    /**
     * Cancela la operación
     */
    public void nextWindow() throws IOException{   
        if((strongEntity.isSelected()==false && weakEntity.isSelected()==false) || (strongEntity.isSelected()==true && weakEntity.isSelected()==true)){
            alertTypeEnity();
            typeChoosed=null;
        }
        else{
            if(strongEntity.isSelected()){
                typeChoosed=EntityType.STRONG;
            }
            else{
                typeChoosed=EntityType.WEAK;
            }             
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
