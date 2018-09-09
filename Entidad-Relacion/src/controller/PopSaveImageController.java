/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
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
public class PopSaveImageController implements Initializable {
    @FXML
    public AnchorPane root;
    @FXML
    public TextField nameImage;
    @FXML
    public TextField nameDestination;
    /**
     * Initializes the controller class.
     */
    public static String namePhoto = "";
    public static String nameURL = "";
    public static boolean exist = true;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void addImage() throws IOException{
        namePhoto= nameImage.textProperty().get();
        if(namePhoto.isEmpty() || namePhoto.length()>21){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al ingresar nombre");
            alert.setHeaderText("Se encontro un error en el nombre de la imagen,"
                    + " es vacio o tiene mas de 20 caracteres. Debe ingresar el nombre nuevamente.");
            alert.showAndWait();
        }
        nameURL= nameDestination.textProperty().get();
        File file = new File(nameURL);
        if (!file.exists()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al ingresar direccion");
            alert.setHeaderText("Se encontro un error en la direccion donde deseas guardar la imagen"
                    + ".Debe ingresar la direccion nuevamente.");
            alert.showAndWait();
            exist=false;           
        }
        else{            
            exist=true;
        }
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
