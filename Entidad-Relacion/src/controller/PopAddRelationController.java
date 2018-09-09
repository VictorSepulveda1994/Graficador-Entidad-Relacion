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
public class PopAddRelationController implements Initializable {
    
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al ingresar nombre");
            alert.setHeaderText("Se encontro un error en el nombre de la relacion,"
                    + " es vacio o tiene mas de 20 caracteres. Debe ingresar el nombre nuevamente.");
            alert.showAndWait();
        }
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
