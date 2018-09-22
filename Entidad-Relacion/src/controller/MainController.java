package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;
import model.Diagram;
import model.Element;
import model.Entity;

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
    @FXML
    private ToggleButton moveToggleButton;
    @FXML
    private ToggleButton pointsToggleButton;
    
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
     * "Relations" y "Connectors" que se crearan por el usuario
     */
    public static Diagram diagram;
    
    /**
     * Variable que gestionará si se muestran los puntos en el "canvas"
     */
    private boolean showPoints;
    
    /**
     * Variables que establecen el ancho mínimo y el alto mínimo del "canvas"
     */
    private final int minWidth = 673;
    private final int minHeight = 515;
    public static ArrayList<Entity> entitiesSelect = new ArrayList<>();
    
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
        moveToggleButton.setSelected(false);
        //Cambios de tamaño de botones
        entityToggleButton.setScaleX(1.15);
        entityToggleButton.setScaleY(1.15);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
    }
    
    /**
     * Si el "relationToggleButton" es presionado, se activará y desactivará los demás.
     */
    @FXML
    private void buttonRelationClicked(ActionEvent event){
        relationToggleButton.setSelected(true);
        entityToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        //Cambios de tamaño de botones
        relationToggleButton.setScaleX(1.15);
        relationToggleButton.setScaleY(1.15);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
    }
    
    /**
     * Si el "moveToggleButton" es presionado, se activará y desactivará los demás.
     */
    @FXML
    private void buttonMoveClicked(ActionEvent event){
        moveToggleButton.setSelected(true);
        relationToggleButton.setSelected(false);
        entityToggleButton.setSelected(false);
        //Cambios de tamaño de botones
        moveToggleButton.setScaleX(1.15);
        moveToggleButton.setScaleY(1.15);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        
    }
    
    /**
     * Limpia la pantalla y el interior del objeto "diagram"
     */
    @FXML
    private void cleanScreen(MouseEvent event) {
        diagram.clearAll(canvas, minWidth, minHeight);
    }
    
    @FXML
    private void canvasClicked(MouseEvent event) throws IOException {
        MainController.event = event;
        if(entityToggleButton.isSelected()){
            popAddEntity();
        }
        if(relationToggleButton.isSelected() && diagram.getEntities().size() > 0){
            diagram.selectElement(event, canvas, showPoints);
                if (diagram.getSelectedElement()!=null){
                    Element element = diagram.getSelectedElement();
                    if (!element.isInFigure(event)){
                        entitiesSelect=diagram.entitiesSelect();
                        popAddRelation();
                    }   
            }
        }
        if(diagram.getEntities().size() > 0 || diagram.getRelations().size() > 0 ){
            diagram.adjustScreen(canvas, minWidth, minHeight);
            diagram.paint(canvas,showPoints);
        }
        
    }
    
    @FXML
    private void mousePressed(MouseEvent event){
        if(moveToggleButton.isSelected()){
            diagram.selectElement(event, canvas, showPoints);
        }
    }
    
    @FXML
    private void mouseDragged(MouseEvent event){
        if(moveToggleButton.isSelected()){
            diagram.moveElement(event, canvas, showPoints, minWidth, minHeight);
        }
    }
    
    @FXML
    private void mouseReleased(MouseEvent event){
        if(moveToggleButton.isSelected()){
            diagram.deselectElement(event);
        }
    }
    
    /**
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
        if (showPoints){
            pointsToggleButton.setScaleX(1.15);
            pointsToggleButton.setScaleY(1.15);
        }
        else{
            pointsToggleButton.setScaleX(1);
            pointsToggleButton.setScaleY(1);
        }
        diagram.paint(canvas, showPoints);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diagram = new Diagram();
        showPoints = false;
        canvas.setCursor(Cursor.DEFAULT);
    }
    
    
}
