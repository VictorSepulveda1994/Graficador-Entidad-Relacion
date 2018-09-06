/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.PopAgregarNodoController.nombreDelNodo;
import static controller.PopAgregarRelacionController.nombreDeLaRelacion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Victor Sepulveda
 */
public class MainController implements Initializable {
    
    @FXML
    public Pane pizarra;
    @FXML
    public Button botonAgregarNodo;
    @FXML
    public Button botonAgregarRelacion;
    
    public static int x_i=20;
    public static int y_i=20;
    public static int largo=100;
    public static int ancho=50;
    
    double x, y;
    
    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }
    
    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void min(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void popAgregarEntidad()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Agregar entidad");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAgregarNodo.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();    
        
    }
    
    public void popAgregarRelacion()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Agregar relacion");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAgregarRelacion.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();    
        
    }
    
    public void mostrarEntidad() throws IOException{
        popAgregarEntidad();
        while(nombreDelNodo.isEmpty() || nombreDelNodo.length()>21){
            popAgregarEntidad();
        }
        String nombre=PopAgregarNodoController.nombreDelNodo;
        System.out.println("Nombre: "+nombre);
    }
    public void mostrarRelacion() throws IOException{
        popAgregarRelacion();
        while(nombreDeLaRelacion.isEmpty() || nombreDeLaRelacion.length()>21){
            popAgregarEntidad();
        }
        String nombre= PopAgregarRelacionController.nombreDeLaRelacion;
        System.out.println("Nombre: "+nombre);
    }
}
