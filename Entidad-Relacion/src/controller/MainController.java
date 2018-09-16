package controller;

import static controller.PopAddEntityController.cancelActionEntity;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
<<<<<<< HEAD
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static controller.PopAddEntityController.nameOfEntity;
import static controller.PopAddRelationController.cancelActionRelation;
import static controller.PopAddRelationController.nameOfRelation;
import static controller.PopEditElementController.cancelActionEdit;
import static controller.PopEditElementController.enteredName;
=======
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static controller.PopSaveImageController.exist;
import static controller.PopSaveImageController.namePhoto;
import static controller.PopSaveImageController.nameURL;
>>>>>>> origin/victor
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.WritableImage;
<<<<<<< HEAD
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
=======
>>>>>>> origin/victor
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import model.Diagram;
import model.Relation;

/**
 *
 * @author Equipo Rocket
 */
<<<<<<< HEAD
public class MainController extends CallPop implements Initializable {
    
    /**
     *
     */
    @FXML
    public Pane pizarra;

    /**
     *Añade una entidad
     */
    @FXML
    public Button botonAgregarNodo;

    /**
     *Añade una relacion
     */
=======
public class MainController implements Initializable {

    /**
     * ToggleButtons creados para seleccionar la accion que se desea hacer
     */
    @FXML
    private ToggleButton entityToggleButton;
>>>>>>> origin/victor
    @FXML
    private ToggleButton relationToggleButton;
    
    /**
<<<<<<< HEAD
     *Se puede dibujar aca
=======
     * Lugar donde se dibujaran las figuras
>>>>>>> origin/victor
     */
    @FXML
    private Canvas canvas;

    /**
     * Clase static para guardar las coordenadas del mouse
     */
    public static MouseEvent event;
    
    /**
     * Clase static para guardar y gestionar los elementos "Entities", 
     * "Relation" y "Connector" que se crearan por el usuario
     */
    public static Diagram diagram;
    
    /**
<<<<<<< HEAD
     *Cierra la ventana
=======
     * Accion para cerrar la ventana
>>>>>>> origin/victor
     */
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    /**
<<<<<<< HEAD
     *Minimiza la pantalla
=======
     * Accion para Minimizar Ventana
>>>>>>> origin/victor
     */
    @FXML
    private void min(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
    /**
     * Permite arrastrar la ventana
     */
    @FXML
    void dragWindow(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - MainController.event.getX());
        stage.setY(event.getScreenY() - MainController.event.getY());
    }
    
    /**
     * Detecta cuando la ventana es presionada y guarda el "MouseEvent"
     */
    @FXML
    void pressedWindow(MouseEvent event) {
        MainController.event = event;
    }
    
    /**
     * Si el "entityToggleButton" es presionado, se activará y desactivará los demás.
     */
    @FXML
    private void buttonEntityClicked(ActionEvent event){
        entityToggleButton.setSelected(true);
        relationToggleButton.setSelected(false);
    }
    
<<<<<<< HEAD
    
    /**
     *
     * @param event
     */
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
=======
    /**
     * Si el "relationToggleButton" es presionado, se activará y desactivará los demás.
     */
    @FXML
    private void buttonRelationClicked(ActionEvent event){
        relationToggleButton.setSelected(true);
        entityToggleButton.setSelected(false);
    }
    
    @FXML
    private void cleanScreen(MouseEvent event) {
        diagram.clearAll(canvas);
    }
    
    @FXML
    private void canvasClicked(MouseEvent event) throws IOException {
        MainController.event = event;
        if(entityToggleButton.isSelected()){
            popAddEntity();
        }
        if(relationToggleButton.isSelected()){
            diagram.addRelation(new Relation("Test",12, (int)event.getX(), (int)event.getY() ) );
        }
>>>>>>> origin/victor
        
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        diagram.paint(canvas);
    }
    
    /**
     *
<<<<<<< HEAD
     * Guarda una entidad
     * @throws java.io.IOException
     */
    public void showEntity() throws IOException{
        popAddEntity();
        if(cancelActionEntity==false){
            while(nameOfEntity.isEmpty() || nameOfEntity.length()>21){
                popAddEntity();
            }
            String name=PopAddEntityController.nameOfEntity;
            Entity entity= new Entity(name);
            diagram.addEntity(entity);
        }
       
=======
     * Abre ventana para agregar el nombre de la entidad y crear la entidad
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
     * Abre ventana para agregar el nombre de la relacion y crear la relacion
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
>>>>>>> origin/victor
    }
    
    /**
     *
<<<<<<< HEAD
     * Guarda una relacion
     * @throws java.io.IOException
     */
    public void showRelation() throws IOException{
        popAddRelation();
        if(cancelActionRelation==false){
            while(nameOfRelation.isEmpty() || nameOfRelation.length()>21){
                popAddRelation();
            }
            String name= PopAddRelationController.nameOfRelation;
            Relation relation = new Relation(name);
            diagram.addRelation(relation);
        }
        
    }
    
    /**
     *Realiza la edicion de un nombre
     * @param event
     * @throws IOException
     */
    public void showEdition(MouseEvent event) throws IOException{
        popEditElement();
        for(int i=0; i<diagram.getEntities().size();i++){
            
        }
        if(cancelActionEdit==false){
            while(enteredName.isEmpty() || enteredName.length()>21){
                popEditElement();
            }
            String name= enteredName;
            System.out.println("name edit: "+name);
        }
        
    }
    
    /**
     *
     * Limpia la ventana
     * @throws java.io.IOException
     */
    public void cleanScreen() throws IOException{
        diagram.clearAll(canvas);
        
    }
    
    /**
     *
     * Guarda el dibujo como .png
     * @throws java.io.IOException
     */
    public void saveImage() throws IOException{
        FileChooser fileChooser = new FileChooser();
                 
        //Set extension filter
=======
     * Guardar una imagen como "imagen.png"
     */
    public void saveImage() {
        FileChooser fileChooser = new FileChooser();
                 
        //Ingreso de filtro de extensión
>>>>>>> origin/victor
        FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

<<<<<<< HEAD
        //Show save file dialog
=======
        //Mostrar diálogo de guardar archivo
>>>>>>> origin/victor
        final Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);

        if(file != null){
            try {
                WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), null);
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diagram = new Diagram();
    }
    
    
}
