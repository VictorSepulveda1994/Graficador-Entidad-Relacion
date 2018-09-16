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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static controller.PopAddEntityController.nameOfEntity;
import static controller.PopAddRelationController.nameOfRelation;
import static controller.PopSaveImageController.exist;
import static controller.PopSaveImageController.namePhoto;
import static controller.PopSaveImageController.nameURL;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import model.Diagram;
import model.Entity;
import model.Relation;

/**
 *
 * @author Equipo Rocket
 */
public class MainController implements Initializable {

    /**
     * ToggleButtons creados para seleccionar la accion que se desea hacer
     */
    @FXML
    private ToggleButton entityToggleButton;
    @FXML
    private ToggleButton relationToggleButton;
    
    /**
     * Lugar donde se dibujaran las figuras
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
     * Accion para cerrar la ventana
     */
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    /**
     * Accion para Minimizar Ventana
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
            //diagram.addEntity(new Entity( (int)event.getX(), (int)event.getY() ) );
        }
        if(relationToggleButton.isSelected()){
            diagram.addRelation(new Relation("Test",12, (int)event.getX(), (int)event.getY() ) );
        }
        
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        diagram.paint(canvas);
    }
    
    /**
     *
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
     * Guardar una imagen como "imagen.png"
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
        diagram = new Diagram();
    }
    
    
}
