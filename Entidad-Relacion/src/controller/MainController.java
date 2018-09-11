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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import model.Entity;
import static model.Main.diagram;
import model.*;

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
    @FXML
    public Canvas canvas;
    
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
    
    @FXML
    public void addEntity (MouseEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        x = event.getSceneX()-120;
        y = event.getSceneY()-50;
        Entity entity = new Entity ("Test");
        entity.setPointsPolygon(x, y);
        entity.getPolygon().drawRectangle(entity.getName(), gc);

        /////////////DIAMOND/////////////////////
        /*
        Relation relation = new Relation("Test");
        relation.setHeight(100);
        relation.setWidth(50);
        Polygon diamond = new Polygon ();
        relation.setPolygon(diamond);
        
        relation.getPolygon().addPoint(new Point(x,y-(relation.getHeight()/2))); //Point1
        relation.getPolygon().addPoint(new Point(x+(relation.getWidth()/2),y));  //Point2
        relation.getPolygon().addPoint(new Point(x,y+(relation.getHeight()/2))); //Point3
        relation.getPolygon().addPoint(new Point(x-(relation.getWidth()/2),y));  //Point4
        
        relation.getPolygon().drawDiamond(relation.getName(), gc);
        */
        //////////////////////////////////////////
        
        /////////////TRIANGLE/////////////////////
        /*
        Relation relation = new Relation("Test"); 
        relation.setHeight(70);
        relation.setWidth(65);
        Polygon triangle = new Polygon ();
        relation.setPolygon(triangle);
        
        relation.getPolygon().addPoint(new Point(x,y-(relation.getHeight()/2)));                          //Point1
        relation.getPolygon().addPoint(new Point(x+(relation.getWidth()/2),y+(relation.getHeight()/2)));  //Point2
        relation.getPolygon().addPoint(new Point(x-(relation.getWidth()/2),y+(relation.getHeight()/2)));  //Point3
        
        relation.getPolygon().drawTriangle(relation.getName(), gc);
        */
        //////////////////////////////////////////
        
        /////////////PENTAGON/////////////////////
        /*
        Relation relation = new Relation("Test"); 
        relation.setHeight(85);
        relation.setWidth(85);
        Polygon pentagon = new Polygon ();
        relation.setPolygon(pentagon);
        
        relation.getPolygon().addPoint(new Point(x,y-(relation.getHeight()/2)));                          //Point1
        relation.getPolygon().addPoint(new Point(x+(relation.getWidth()/2),y-(relation.getHeight()/8)));  //Point2
        relation.getPolygon().addPoint(new Point(x+(relation.getWidth()/3),y+(relation.getHeight()/2)));  //Point3
        relation.getPolygon().addPoint(new Point(x-(relation.getWidth()/3),y+(relation.getHeight()/2)));  //Point4
        relation.getPolygon().addPoint(new Point(x-(relation.getWidth()/2),y-(relation.getHeight()/8)));  //Point5
        
        relation.getPolygon().drawPentagon(relation.getName(), gc);
        */
        //////////////////////////////////////////
        
        /////////////HEXAGON/////////////////////
        /*
        Relation relation = new Relation("Test"); 
        relation.setHeight(80);
        relation.setWidth(90);
        Polygon hexagon = new Polygon ();
        relation.setPolygon(hexagon);
        
        relation.getPolygon().addPoint(new Point(x-(relation.getWidth()/4),y-(relation.getHeight()/2)));    //Point1
        relation.getPolygon().addPoint(new Point(x+(relation.getWidth()/4),y-(relation.getHeight()/2)));    //Point2    
        relation.getPolygon().addPoint(new Point(x+(relation.getWidth()/2),y));                             //Point3
        relation.getPolygon().addPoint(new Point(x+(relation.getWidth()/4),y+(relation.getHeight()/2)));    //Point4 
        relation.getPolygon().addPoint(new Point(x-(relation.getWidth()/4),y+(relation.getHeight()/2)));    //Point5
        relation.getPolygon().addPoint(new Point(x-(relation.getWidth()/2),y));                             //Point6
        
        relation.getPolygon().drawHexagon(relation.getName(), gc);
        */
        //////////////////////////////////////////
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
        diagram.clearAll(canvas);
        
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
        WritableImage wim = canvas.snapshot(new SnapshotParameters(), null);
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
