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

    /**
     * Initializes the controller class.
     */
    public static HeritageType type;
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
            ((Stage)root.getScene().getWindow()).close();
        }      
    }
    
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
