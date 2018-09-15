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
public class PopSaveImageController extends CallPop implements Initializable {

    /**
     *Panel where I perform actions
     */
    @FXML
    public AnchorPane root;

    /**
     *Receive the name of the image
     */
    @FXML
    public TextField nameImage;

    /**
     *Receive the name of the destination
     */
    @FXML
    public TextField nameDestination;
    
    public static String namePhoto = "";

    public static String nameURL = "";
 
    public static boolean exist = true;
    
    public static boolean cancelAction = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     *
     * Get the name of the image and the address where you want to save it, the
     * name of the image is checked the length and if it is empty, and it is verified
     * if the address exists inside the computer
     * @throws java.io.IOException
     */
    public void addImage() throws IOException{
        namePhoto= nameImage.textProperty().get();
        if(namePhoto.isEmpty() || namePhoto.length()>21){
            alertName();
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
        cancelAction = false;
        ((Stage)root.getScene().getWindow()).close();
    }
    
    public void cancel(){
        cancelAction = true;
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
