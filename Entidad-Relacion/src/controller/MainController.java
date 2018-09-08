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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static controller.PopAddEntityController.nameOfEntity;
import static controller.PopAddRelationController.nameOfRelation;
import model.Entity;
import static model.Main.diagram;
import model.Relation;

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
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void min(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
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
    
    public void showEntity() throws IOException{
        popAddEntity();
        while(nameOfEntity.isEmpty() || nameOfEntity.length()>21){
            popAddEntity();
        }
        String name=PopAddEntityController.nameOfEntity;
        Entity entity= new Entity(name);
        diagram.addEntity(entity);
       
    }
    
    public void showRelation() throws IOException{
        popAddRelation();
        while(nameOfRelation.isEmpty() || nameOfRelation.length()>21){
            popAddRelation();
        }
        String name= PopAddRelationController.nameOfRelation;
        Relation relation = new Relation(name);
        diagram.addRelation(relation);
        
    }
    
    public void cleanScreen() throws IOException{
        diagram.clearAll();
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
}
