/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.PopAgregarNodoController.nombreDelNodo;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alejandra
 */
public class PopAgregarRelacionController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    public TextField nombreRelacion;

    //private IntegerSpinnerValueFactory numeros= new IntegerSpinnerValueFactory(0, 7, 0);
    /**
     * Initializes the controller class.
     */
    public static String nombreDeLaRelacion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    public void continuar(){
        nombreDeLaRelacion=nombreRelacion.textProperty().get();
        if(nombreDelNodo.isEmpty() || nombreDelNodo.length()>21){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al ingresar nombre");
            alert.setHeaderText("Se encontro un error en el nombre de la relacion,"
                    + " es vacio o tiene mas de 20 caracteres. Debe ingresar el nombre nuevamente.");
            alert.showAndWait();
        }
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
