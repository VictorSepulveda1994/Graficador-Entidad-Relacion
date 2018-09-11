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
public class PopAddEntityController implements Initializable {

    /**
     *Panel where I perform actions
     */
    @FXML
    public AnchorPane root;

    /**
     *Receive the name of the entity
     */
    @FXML
    public TextField nameEntity;
    
    
    public static String nameOfEntity = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     *Get the name of an entity and verify the length and if it is empty
     */
    public void addToScreen(){
        nameOfEntity=nameEntity.textProperty().get();
        if(nameOfEntity.isEmpty() || nameOfEntity.length()>21){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al ingresar nombre");
            alert.setHeaderText("Se encontro un error en el nombre de la entidad,"
                    + " es vacio o tiene mas de 20 caracteres. Debe ingresar el nombre nuevamente.");
            alert.showAndWait();
        }
        ((Stage)root.getScene().getWindow()).close();
    }
}
