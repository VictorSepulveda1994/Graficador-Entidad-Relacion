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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Equipo Rocket
 */
public class PopAddRelationController extends CallPop implements Initializable {
    
    /**
     *Panel where I perform actions
     */
    @FXML
    private AnchorPane root;

    /**
     *Receive the name of the relation
     */
    @FXML
    public TextField nameRelation;

    
    public static String nameOfRelation = "";
    
    public static boolean cancelActionRelation = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO   
    }
    
    /**
     *Get the name of an relation and verify the length and if it is empty
     */
    public void addToScreen(){
        nameOfRelation=nameRelation.textProperty().get();
        if(nameOfRelation.isEmpty() || nameOfRelation.length()>21){
            alertName();
        }
        cancelActionRelation = false;
        ((Stage)root.getScene().getWindow()).close();
    }
    
    public void cancel(){
        cancelActionRelation = true;
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
