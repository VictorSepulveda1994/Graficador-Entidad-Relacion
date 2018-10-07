package controller;

import com.itextpdf.text.DocumentException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import model.Accion;
import model.Diagram;
import model.Element;
import model.Entity;
import model.Relation;
import model.TipoDeAccion;

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
    @FXML
    private ToggleButton editToggleButton;
    @FXML
    private ToggleButton deleteToggleButton;

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
    private final int minWidth = 700;
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
    
    @FXML
    private void helpMe(MouseEvent event) throws IOException {
        popShowHelp();
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
        editToggleButton.setSelected(false);
        //Cambios de tamaño de botones
        entityToggleButton.setScaleX(1.15);
        entityToggleButton.setScaleY(1.15);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        editToggleButton.setScaleX(1);
        editToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.CROSSHAIR);
        //Deselecciono las entidades, para que al apretar "Relacion" no se tomen en cuenta las anteriores
        diagram.deselectAllEntities();
        diagram.paint(canvas,showPoints);
    }
    
    /**
     * Si el "relationToggleButton" es presionado, se activará y desactivará los demás.
     */
    @FXML
    private void buttonRelationClicked(ActionEvent event){
        relationToggleButton.setSelected(true);
        entityToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
        //Cambios de tamaño de botones
        relationToggleButton.setScaleX(1.15);
        relationToggleButton.setScaleY(1.15);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        editToggleButton.setScaleX(1);
        editToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.HAND);
        //Deselecciono las entidades, para que al apretar "Relacion" no se tomen en cuenta las anteriores
        diagram.deselectAllEntities();
        diagram.paint(canvas,showPoints);
    }
    
    /**
     * Si el "moveToggleButton" es presionado, se activará y desactivará los demás.
     */
    @FXML
    private void buttonMoveClicked(ActionEvent event){
        moveToggleButton.setSelected(true);
        relationToggleButton.setSelected(false);
        entityToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
         //Cambios de tamaño de botones
        moveToggleButton.setScaleX(1.15);
        moveToggleButton.setScaleY(1.15);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        editToggleButton.setScaleX(1);
        editToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.MOVE);
        //Deselecciono las entidades, para que al apretar "Relacion" no se tomen en cuenta las anteriores
        diagram.deselectAllEntities();
        diagram.paint(canvas,showPoints);
        
    }
    
    /**
     * Limpia la pantalla y el interior del objeto "diagram"
     */
    @FXML
    private void cleanScreen(MouseEvent event) {
        Accion accion=new Accion(TipoDeAccion.LimpiarPantalla,null);
        Diagram diagrama1= new Diagram();
        ArrayList <Entity> entities= (ArrayList<Entity>) diagram.getEntities().clone();
        ArrayList <Relation> relations= (ArrayList<Relation>) diagram.getRelations().clone();
        diagrama1.setEntities(entities);
        diagrama1.setRelations(relations);
        accion.setDiagram(diagrama1);
        diagram.addAcciones(accion);
        diagram.clearAll(canvas, minWidth, minHeight);
        //Se restablecen los botones
        relationToggleButton.setSelected(false);
        entityToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
        pointsToggleButton.setSelected(false);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        pointsToggleButton.setScaleX(1);
        pointsToggleButton.setScaleY(1);
        editToggleButton.setScaleX(1);
        editToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.DEFAULT);
    }
    
    @FXML
    private void edit (MouseEvent event) throws IOException{       
        relationToggleButton.setSelected(false);
        entityToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        pointsToggleButton.setSelected(false);
        editToggleButton.setSelected(true);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        pointsToggleButton.setScaleX(1);
        pointsToggleButton.setScaleY(1);
        editToggleButton.setScaleX(1.15);
        editToggleButton.setScaleY(1.15);
        canvas.setCursor(Cursor.TEXT);
    }
    
    @FXML
    private void deshacerAccion(){       
        System.out.println("UltimaAccion: "+diagram.getAcciones().get(diagram.getAcciones().size()-1).getTipo());
        if(diagram.getAcciones().size()>0){
            if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getTipo()
                    .equals(TipoDeAccion.AgregoEntidad)){
                System.out.println("Se elimina la ultima entidad");
                diagram.getEntities().remove(diagram.getEntities().size()-1);
            }
            if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getTipo()
                    .equals(TipoDeAccion.AgregoRelacion)){
                System.out.println("Se elimina la ultima relacion");
                diagram.getRelations().remove(diagram.getRelations().size()-1);
            }
            if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getTipo()
                    .equals(TipoDeAccion.EditarNombreEntidad)){
                System.out.println("DeseditarNombre");
                for (Entity entitie : diagram.getEntities()) {
                    if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getElemento2().equals(entitie)){
                        entitie.setName(diagram.getAcciones().get(diagram.getAcciones().size()-1).getElemento().getName());
                        entitie.figure.setName(diagram.getAcciones().get(diagram.getAcciones().size()-1).getElemento().getName()); 
                    }
                }
            }
            if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getTipo()
                    .equals(TipoDeAccion.EditarNombreRelacion)){
                System.out.println("DeseditarNombre");
                for (Relation relation : diagram.getRelations()) {
                    if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getElemento2().equals(relation)){
                        relation.setName(diagram.getAcciones().get(diagram.getAcciones().size()-1).getElemento().getName());
                        relation.figure.setName(diagram.getAcciones().get(diagram.getAcciones().size()-1).getElemento().getName()); 
                    }
                }
            }
            if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getTipo()
                    .equals(TipoDeAccion.LimpiarPantalla)){
                System.out.println("Deshace limpieza");
                System.out.println("adentro: "+diagram.getAcciones().get(diagram.getAcciones().size()-1).getDiagram().getEntities().size());
                if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getDiagram().getEntities().size()>0){
                    System.out.println("diagramaGuardado: "+diagram.getAcciones().get(diagram.getAcciones().size()-1).getDiagram().getEntities().get(0).getName());
                }

                diagram.setEntities(diagram.getAcciones().get(diagram.getAcciones().size()-1).getDiagram().getEntities());
                diagram.setRelations(diagram.getAcciones().get(diagram.getAcciones().size()-1).getDiagram().getRelations());
            }
            /*
            if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getTipo()
                    .equals(TipoDeAccion.MoverElemento)){
                System.out.println("Mover elemento");
                for (Entity entitie : diagram.getEntities()) {
                    if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getElemento2().equals(entitie)){
                        System.out.println("desmover elemento");
                    }
                }
                for (Relation relation : diagram.getRelations()) {
                    if(diagram.getAcciones().get(diagram.getAcciones().size()-1).getElemento2().equals(relation)){
                        System.out.println("desmover elemento");
                    }
                }
            }
            */
            diagram.paint(canvas, showPoints);
            diagram.getAcciones().remove(diagram.getAcciones().size()-1);
        }
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
                String type = element.getClass().getName().substring(6);
                if("Relation".equals(type)){
                    diagram.deselectElement(event);
                    diagram.deselectAllEntities();
                }
                if (!element.isInFigure(event)){
                    entitiesSelect=diagram.entitiesSelect();
                    if(entitiesSelect.size()>7){
                        alertEntities();
                        diagram.deselectAllEntities();
                        entitiesSelect.clear();
                    }
                    else{
                        popAddRelation();
                    }
                }   
            }
        }
        
        if(diagram.getEntities().size() > 0 || diagram.getRelations().size() > 0 ){
            diagram.adjustScreen(canvas, minWidth, minHeight);
            diagram.paint(canvas,showPoints);
        }
        if(editToggleButton.isSelected()){
            diagram.selectElementEdit(event, canvas, showPoints);
        }     
        /*
        if(deleteToggleButton.isSelected()){
            diagram.delete(event, canvas, showPoints);
        }
        */
        
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
    public void exportImagePng() {
        saveDiagramPng(canvas);
    }
    
    public void exportImagePdf() throws DocumentException {
        saveDiagramPdf(canvas);
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
        //Cambios para desactivar los otros botones
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        editToggleButton.setScaleX(1);
        editToggleButton.setScaleY(1);
        relationToggleButton.setSelected(false);
        entityToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
        canvas.setCursor(Cursor.DEFAULT);
        diagram.deselectAllEntities();
        diagram.paint(canvas,showPoints);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diagram = new Diagram();
        showPoints = false;
        canvas.setCursor(Cursor.DEFAULT);
    }

}
