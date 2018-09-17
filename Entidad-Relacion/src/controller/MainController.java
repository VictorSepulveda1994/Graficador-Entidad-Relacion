package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;
import model.Diagram;

/**
 *
 * @author Equipo Rocket
 */
public class MainController extends CallPop implements Initializable {

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
     * Variable que gestionará si se muestran los puntos en el "canvas"
     */
    private boolean showPoints;
    
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
    
    /**
     * Limpia la pantalla y el interior del objeto "diagram"
     */
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
            popAddRelation();
        }
        diagram.paint(canvas,showPoints);
    }
    
    /**
     *
     * Metodo para guardar una imagen como "imagen.png"
     */
    public void saveImage() {
        saveDiagramPng(canvas);
    }
    
    /**
     *
     * Metodo para cuando se deseen mostrar/ocultar los puntos de control
     */
    public void showPoints() {
        showPoints = !showPoints;
        diagram.paint(canvas, showPoints);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diagram = new Diagram();
        showPoints = false;
    }
    
    
}
