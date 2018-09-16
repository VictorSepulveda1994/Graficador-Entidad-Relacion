/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Equipo Rocket
 */
public class CallPop {
    
    /**
     *
     * Se añade el nombre
     * @throws java.io.IOException
     */
    public void popAddEntity()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Agregar entidad");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAddEntity.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();

    }
    
    /**
     *Edita el nombre de un elemento
     * @throws IOException
     */
    public void popEditElement()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Editar elemento");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopEditElement.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();

    }
    /**
     *
     * Se añade el nombre
     * @throws java.io.IOException
     */
    public void popAddRelation()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Agregar relacion");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAddRelation.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();    
        
    }
    
    /**
     *Muestra un mensaje de error al ingresar nombre invalido
     */
    public void alertName(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error al ingresar nombre");
        alert.setHeaderText("Se encontró un error en el nombre,"
                + " es vacío o tiene más de 20 caracteres. Debe ingresar el nombre nuevamente.");
        alert.showAndWait();
    }
}
