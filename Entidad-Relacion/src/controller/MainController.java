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
import static controller.PopSaveImageController.exist;
import static controller.PopSaveImageController.namePhoto;
import static controller.PopSaveImageController.nameURL;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import model.Entity;
import static model.Main.diagram;
import model.Relation;

/**
 *
 * @author Equipo Rocket
 */
public class MainController implements Initializable {
    
    /**
     *Drawing panel
     */
    @FXML
    public Pane pizarra;

    /**
     *Add entity
     */
    @FXML
    public Button botonAgregarNodo;

    /**
     *Add relation
     */
    @FXML
    public Button botonAgregarRelacion;
    
    /**
     *
     */
    public static int x_i=20;

    /**
     *
     */
    public static int y_i=20;

    /**
     *
     */
    public static int largo=100;

    /**
     *
     */
    public static int ancho=50;
    
    double x, y;
    
    /**
     *Handles close window
     */
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    /**
     *Manage to minimize window
     */
    @FXML
    private void min(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
    /**
     *
     */
    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }
    
    /**
     *
     */
    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /**
     *
     * Add an entity 
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
     *
     * Add an relacion
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
     *
     * Save the image of the blackboard
     */
    public void popSaveImage()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Guardar imagen");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopSaveImage.fxml"));
        
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
     * Save an entity
     */
    public void showEntity() throws IOException{
        popAddEntity();
        while(nameOfEntity.isEmpty() || nameOfEntity.length()>21){
            popAddEntity();
        }
        String name=PopAddEntityController.nameOfEntity;
        Entity entity= new Entity(name);
        diagram.addEntity(entity);
       
    }
    
    /**
     *
     * Save an relation
     */
    public void showRelation() throws IOException{
        popAddRelation();
        while(nameOfRelation.isEmpty() || nameOfRelation.length()>21){
            popAddRelation();
        }
        String name= PopAddRelationController.nameOfRelation;
        Relation relation = new Relation(name);
        diagram.addRelation(relation);
        
    }
    
    /**
     *
     * Clean the window
     */
    public void cleanScreen() throws IOException{
        diagram.clearAll();
       
    }
    
    /**
     *
     * Save a Image in .png
     */
    public void saveImage() throws IOException{
        popSaveImage(); 
        while(namePhoto.isEmpty() || namePhoto.length()>21 || exist==false){
            popSaveImage();
        }
        WritableImage wim = pizarra.snapshot(new SnapshotParameters(), null);
        System.out.println("Nombre: "+namePhoto);
        System.out.println("Direccion: "+nameURL);
        System.out.println(nameURL+"\\"+namePhoto+".png");
        File file = new File(nameURL+"\\"+namePhoto+".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
        } catch (Exception s) {
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
}
