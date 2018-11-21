package controller;

import com.itextpdf.text.DocumentException;
import java.awt.Dimension;
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
import javafx.scene.layout.GridPane;
import model.Diagram;
import model.Element;
import model.Entity;
import javafx.scene.input.ScrollEvent;

/**
 * FXML Controller class
 * Esta clase se encarga de generar la interracción con el usuario.
 * @author Equipo Rocket
 */
public class MainController extends CallPop implements Initializable{
    
    /**
     * "ToggleButtons" y "Buttons" creados para seleccionar la accion que se desea hacer.
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
    @FXML
    private ToggleButton attributeToggleButton;
    @FXML    
    private ToggleButton heritageToggleButton;
    @FXML
    private Button cleanButton;
    @FXML
    private Button exportButton;
    
    /**
     * Contenedor principal de todos los nodos del programa.
     */
    @FXML
    private GridPane root;

    /**
     * Lugar donde se dibujaran las figuras.
     */
    @FXML
    private Canvas canvas;

    /**
     * Clase static para guardar las coordenadas del mouse.
     */
    public static MouseEvent event;
    
    /**
     * Clase static para guardar y gestionar los elementos "Entities", 
     * "Relations" y "Connectors" que se crearan por el usuario.
     */
    public static Diagram diagram;
    
    /**
     * Variable que gestionará si se muestran los puntos en el "canvas".
     */
    private boolean showPoints;
    
    /**
     * Variables que establecen el ancho mínimo y el alto mínimo del "canvas"
     */
    private int minWidth;
    private int minHeight;
    
    /**
     * Lista que guarda las entidades seleccionadas por el usuario.
     */
    public static ArrayList<Entity> entitiesSelect;
    public ArrayList<Diagram> diagramsUndo;
    public ArrayList<Diagram> diagramsRedo;
    public Diagram copy;
    
    /**
     * Accion para cerrar la ventana.
     */
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    /**
     * Accion para Minimizar Ventana.
     */
    @FXML
    private void min(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
    /**
     * Accion para abrir la ventana de ayuda.
     */
    @FXML
    private void helpMe(MouseEvent event) throws IOException {
        popShowHelp();
    }
    
    /**
     * Si el "entityToggleButton" es presionado, se activará y desactivará los demás.
     * Permite crear "entities" en el canvas.
     */
    @FXML
    private void buttonEntityClicked(ActionEvent event){
        entitiesSelect.clear();
        attributeToggleButton.setSelected(false);
        entityToggleButton.setSelected(true);
        relationToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
        deleteToggleButton.setSelected(false);
        heritageToggleButton.setSelected(false);
        heritageToggleButton.setScaleX(1);
        heritageToggleButton.setScaleY(1);
        //Cambios de tamaño de botones
        entityToggleButton.setScaleX(1.15);
        entityToggleButton.setScaleY(1.15);
        attributeToggleButton.setScaleX(1);
        attributeToggleButton.setScaleY(1);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        deleteToggleButton.setScaleX(1);
        deleteToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.CROSSHAIR);
        //Deselecciono las entidades, para que al apretar "Relacion" no se tomen en cuenta las anteriores
        diagram.deselectAllEntities();
        diagram.paint(canvas,showPoints);
    }
    
    /**
     * Si el "attributeToggleButton" es presionado, se activará y desactivará los demás.
     * Permite crear "Atritbutes" en el canvas.
     */
    @FXML
    private void buttonAttributeClicked(ActionEvent event){
        entitiesSelect.clear();
        attributeToggleButton.setSelected(true);
        entityToggleButton.setSelected(false);
        relationToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
        deleteToggleButton.setSelected(false);
        heritageToggleButton.setSelected(false);
        heritageToggleButton.setScaleX(1);
        heritageToggleButton.setScaleY(1);
        attributeToggleButton.setScaleX(1.15);
        attributeToggleButton.setScaleY(1.15);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        deleteToggleButton.setScaleX(1);
        deleteToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.CROSSHAIR);
        diagram.deselectAllEntities();
        diagram.paint(canvas,showPoints);
    }
    
    /**
     * Si el "heritageToggleButton" es presionado, se activará y desactivará los demás.
     * Permite crear "heritages" entre entidades dentro del canvas.
     */
    @FXML
    private void buttonHeritageClicked(ActionEvent event){
        entitiesSelect.clear();
        attributeToggleButton.setSelected(false);
        entityToggleButton.setSelected(false);
        relationToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
        deleteToggleButton.setSelected(false);
        heritageToggleButton.setSelected(true);
        heritageToggleButton.setScaleX(1.15);
        heritageToggleButton.setScaleY(1.15);
        attributeToggleButton.setScaleX(1);
        attributeToggleButton.setScaleY(1);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        deleteToggleButton.setScaleX(1);
        deleteToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.CROSSHAIR);
        diagram.deselectAllEntities();
        diagram.paint(canvas,showPoints);
    }
    
    /**
     * Si el "relationToggleButton" es presionado, se activará y desactivará los demás.
     * Permite crear "relations" en el canvas
     */
    @FXML
    private void buttonRelationClicked(ActionEvent event){
        entitiesSelect.clear();
        attributeToggleButton.setSelected(false);
        relationToggleButton.setSelected(true);
        entityToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
        deleteToggleButton.setSelected(false);
        heritageToggleButton.setSelected(false);
        heritageToggleButton.setScaleX(1);
        heritageToggleButton.setScaleY(1);
        //Cambios de tamaño de botones
        relationToggleButton.setScaleX(1.15);
        relationToggleButton.setScaleY(1.15);
        attributeToggleButton.setScaleX(1);
        attributeToggleButton.setScaleY(1);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        deleteToggleButton.setScaleX(1);
        deleteToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.HAND);
        //Deselecciono las entidades, para que al apretar "Relacion" no se tomen en cuenta las anteriores
        diagram.deselectAllEntities();
        diagram.paint(canvas,showPoints);
    }
    
    /**
     * Si el "moveToggleButton" es presionado, se activará y desactivará los demás.
     * Permite mover las figuras en el canvas
     */
    @FXML
    private void buttonMoveClicked(ActionEvent event){
        entitiesSelect.clear();
        attributeToggleButton.setSelected(false);
        moveToggleButton.setSelected(true);
        relationToggleButton.setSelected(false);
        entityToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
        deleteToggleButton.setSelected(false);
        heritageToggleButton.setSelected(false);
        heritageToggleButton.setScaleX(1);
        heritageToggleButton.setScaleY(1);
        //Cambios de tamaño de botones
        moveToggleButton.setScaleX(1.15);
        moveToggleButton.setScaleY(1.15);
        attributeToggleButton.setScaleX(1);
        attributeToggleButton.setScaleY(1);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        deleteToggleButton.setScaleX(1);
        deleteToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.MOVE);
        //Deselecciono las entidades, para que al apretar "Relacion" no se tomen en cuenta las anteriores
        diagram.deselectAllEntities();
        diagram.paint(canvas,showPoints);
        
    }
    
    /**
     * Método para cuando se deseen mostrar/ocultar los puntos de control.
     */
    @FXML
    private void buttonShowPointsClicked(ActionEvent event) {
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
        diagram.deselectAllEntities();
        diagram.paint(canvas,showPoints);
    }
    
    /**
     * Botón que al ser presionado permite eliminar figuras en el "canvas".
     */
    @FXML
    private void buttonDeleteFigureClicked(ActionEvent event){
        entitiesSelect.clear();
        attributeToggleButton.setSelected(false);
        deleteToggleButton.setSelected(true);
        relationToggleButton.setSelected(false);
        entityToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        pointsToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
        heritageToggleButton.setSelected(false);
        heritageToggleButton.setScaleX(1);
        heritageToggleButton.setScaleY(1);
        entityToggleButton.setScaleX(1);
        attributeToggleButton.setScaleX(1);
        attributeToggleButton.setScaleY(1);
        entityToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        deleteToggleButton.setScaleX(1.15);
        deleteToggleButton.setScaleY(1.15);
        canvas.setCursor(Cursor.OPEN_HAND);
    }
    
    /**
     * Botón que al ser presionado permite editar figuras en el "canvas".
     */
    @FXML
    private void buttonEditClicked(ActionEvent event) throws IOException{  
        attributeToggleButton.setSelected(false);
        relationToggleButton.setSelected(false);
        entityToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        editToggleButton.setSelected(true);
        heritageToggleButton.setSelected(false);
        heritageToggleButton.setScaleX(1);
        heritageToggleButton.setScaleY(1);
        deleteToggleButton.setSelected(false);
        attributeToggleButton.setScaleX(1);
        attributeToggleButton.setScaleY(1);
        entityToggleButton.setScaleX(1);
        entityToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.TEXT);
    }
    
    /**
     * Limpia la pantalla y el interior del objeto "diagram".
     */
    @FXML
    private void buttonCleanScreenClicked(ActionEvent event) {
        diagram.clearAll(canvas, minWidth, minHeight);
        //Se restablecen los botones
        attributeToggleButton.setSelected(false);
        relationToggleButton.setSelected(false);
        entityToggleButton.setSelected(false);
        moveToggleButton.setSelected(false);
        editToggleButton.setSelected(false);
        deleteToggleButton.setSelected(false);
        heritageToggleButton.setSelected(false);
        heritageToggleButton.setScaleX(1);
        heritageToggleButton.setScaleY(1);
        entityToggleButton.setScaleX(1);
        attributeToggleButton.setScaleX(1);
        attributeToggleButton.setScaleY(1);
        entityToggleButton.setScaleY(1);
        moveToggleButton.setScaleX(1);
        moveToggleButton.setScaleY(1);
        relationToggleButton.setScaleX(1);
        relationToggleButton.setScaleY(1);
        editToggleButton.setScaleX(1);
        editToggleButton.setScaleY(1);
        canvas.setCursor(Cursor.DEFAULT);
    }
    
    /**
     * Metodo para guardar dibujo hecho en canvas como "imagen.png" o "archivo.pdf".
     */
    @FXML
    private void buttonExportClicked(ActionEvent event) throws DocumentException {
        export(canvas);
    }
    
    /**
     * Método para administrar las acciones cuando el canvas sea presionado.
     */
    @FXML
    private void canvasClicked(MouseEvent event) throws IOException {
        MainController.event = event;
        if(entityToggleButton.isSelected() && event.getX()-75 > 0  && event.getY()-45 > 0){
            popAddEntity(); 
            copiar();
        }
        else if(relationToggleButton.isSelected() && diagram.getEntities().size() > 0 && event.getX()-75 > 0  && event.getY()-45 > 0){
            diagram.selectElement(event, canvas, showPoints);
            if (diagram.getSelectedElement()!=null){
                Element element = diagram.getSelectedElement();
                String type = element.getClass().getName().substring(6);
                if("Relation".equals(type) || "Attribute".equals(type)){
                    diagram.deselectElement(event);
                    diagram.deselectAllEntities();
                }
                if (!element.isInFigure(event)){
                    entitiesSelect=diagram.entitiesSelect();
                    if(entitiesSelect.size()>6){
                        alertEntities();
                        diagram.deselectAllEntities();
                        entitiesSelect.clear();
                    }
                    else{
                        popAddRelation();
                        copiar();
                    }
                }   
            }
        }
        else if(heritageToggleButton.isSelected() && diagram.getEntities().size() > 0 && event.getX()-75 > 0  && event.getY()-45 > 0){
            diagram.selectElement(event, canvas, showPoints);
            if (diagram.getSelectedElement()!=null){
                Element element = diagram.getSelectedElement();
                String type = element.getClass().getName().substring(6);
                if("Relation".equals(type) || "Attribute".equals(type)){
                    diagram.deselectElement(event);
                    diagram.deselectAllEntities();
                }
                if("Entity".equals(type) && !searchEntity((Entity) element)){
                    entitiesSelect.add((Entity) element);
                }
                if (!element.isInFigure(event) && entitiesSelect.size()>1){
                    popAddHeritage();
                    copiar();
                }   
            }
        }
        else if(attributeToggleButton.isSelected()){
            diagram.addAttribute(event, canvas, showPoints);
            copiar();
        }
        else if(editToggleButton.isSelected()){
            diagram.selectElementEdit(event, canvas, showPoints);
            diagram.paint(canvas,showPoints);        
            copiar();
        }
        else if(deleteToggleButton.isSelected()){
            if(!diagram.getEntities().isEmpty() || !diagram.getRelations().isEmpty()){
                diagram.delete(event, canvas, showPoints);
                copiar();
            }
        }
        
        //Una vez realizada la acción correspondiente, actualizamos el canvas
        if(diagram.getEntities().size() > 0 || diagram.getRelations().size() > 0 ){
            diagram.adjustScreen(canvas, minWidth, minHeight);
            diagram.paint(canvas,showPoints);
        }
        else{
            canvas.setWidth(minWidth);
            canvas.setHeight(minHeight);
        }
    }
    
    /**
     * Método que guarda el "MouseEvent" cuando se presione el mouse en el "canvas"
     * y selecciona el elemento que haya en esa posición.
     */
    @FXML
    private void mousePressed(MouseEvent event){
        if(moveToggleButton.isSelected() && diagram.getSelectedElement() == null){
            diagram.selectElement(event, canvas, showPoints);
        }
    }
    
    /**
     * Método que mueve el elemento que haya sido seleccionado en el "canvas" 
     * cuando el mouse se mueva.
     */
    @FXML
    private void mouseDragged(MouseEvent event){
        if(moveToggleButton.isSelected()){
            diagram.moveElement(event, canvas, showPoints, minWidth, minHeight);
        }
    }
    
    /**
     * Método que deselecciona el elemento que haya sido seleccionado en el "canvas"
     * cuando se suelte el botón del mouse.
     */
    @FXML
    private void mouseReleased(MouseEvent event){
        if(moveToggleButton.isSelected()){
            diagram.deselectElement(event);
            diagram.deselectAllEntities();
            diagram.paint(canvas, showPoints);
        }
    }
    
    /**
     * Método que resalta la figura sobre la cual se posicione el mouse.
     * @param event
     */
    @FXML
    public void handle(MouseEvent event) {
        if(deleteToggleButton.isSelected()){
            //Pintar Entidad           
            for (int i = 0; i <diagram.getEntities().size(); i++) {
                if(diagram.getEntities().get(i).isInFigure(event)){
                    diagram.getEntities().get(i).setSelected(true);
                    break;
                }
                else{
                    diagram.getEntities().get(i).setSelected(false);
                }
                
            }
            //Pintar Relación
            for (int i = 0; i <diagram.getRelations().size(); i++) {
                if(diagram.getRelations().get(i).isInFigure(event)){
                    diagram.getRelations().get(i).setSelected(true);
                    break;
                }
                else{
                    diagram.getRelations().get(i).setSelected(false);
                }
            }
            //Pintar Attribute
            for (int i = 0; i <diagram.getAttributes().size(); i++) {
                if(diagram.getAttributes().get(i).isInFigure(event)){
                    diagram.getAttributes().get(i).setSelected(true);
                    break;
                }
                else{
                    diagram.getAttributes().get(i).setSelected(false);
                }
            }
            //Pintar Herencia
            for (int i = 0; i <diagram.getHeritages().size(); i++) {
                if(diagram.getHeritages().get(i).isInFigure(event)){
                    diagram.getHeritages().get(i).setSelected(true);
                    break;
                }
                else{
                    diagram.getHeritages().get(i).setSelected(false);
                }
            }
            diagram.paint(canvas, showPoints);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adjustNodes();
        diagram = new Diagram();
        entitiesSelect = new ArrayList<>();
        diagramsUndo= new ArrayList<>();
        diagramsRedo= new ArrayList<>();
        copiar();
        copy= new Diagram();
        showPoints = false;
        canvas.setCursor(Cursor.DEFAULT);
    }
    
    /**
     * Método que se encarga de ajustar todos los componenetes "Main.fxml" al tamaño de la pantalla.
     */
    private void adjustNodes(){
        //Obtenemos ancho y largo de la pantalla que se utilice para ejecutar el programa
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        //Ingresamos el ancho y largo deseado de la ventana del programa
        root.setMinWidth(screenSize.width);
        root.setMinHeight((screenSize.height*93)/100);
        //Cálculo e ingreso de ancho y alto de "canvas"
        minWidth = (int) (85*root.getMinWidth())/100;
        minHeight = (int) (83.5*root.getMinHeight())/100;
        canvas.setWidth(minWidth);
        canvas.setHeight(minHeight);
        //Creación de "ArrayList" con "buttons" y "togglebuttons"
        ArrayList<ToggleButton> toggleButtons = new ArrayList<>();
        ArrayList<Button> buttons = new ArrayList<>();
        toggleButtons.add(entityToggleButton);
        toggleButtons.add(relationToggleButton);
        toggleButtons.add(moveToggleButton);
        toggleButtons.add(pointsToggleButton);
        toggleButtons.add(editToggleButton);
        toggleButtons.add(deleteToggleButton);
        toggleButtons.add(attributeToggleButton);
        toggleButtons.add(heritageToggleButton);
        buttons.add(cleanButton);
        buttons.add(exportButton);
        //Ajuste del tamaño de todos los botones
        for (ToggleButton toggleButton : toggleButtons) {
            int widthPercent = (int) ((toggleButton.getPrefWidth()*100)/900);
            toggleButton.setPrefWidth( (root.getMinWidth()*widthPercent)/100 );
            int heightPercent = (int) ((toggleButton.getPrefHeight()*100)/550);
            toggleButton.setPrefHeight( (root.getMinHeight()*heightPercent)/100 );
            if("relationToggleButton".equals(toggleButton.getId())){
                toggleButton.setPrefWidth( (root.getMinHeight()*heightPercent)/100 );
            }
        }
        for (Button button : buttons) {
            int widthPercent = (int) ((button.getPrefWidth()*100)/900);
            button.setPrefWidth( (root.getMinWidth()*widthPercent)/100 );
            int heightPercent = (int) ((button.getPrefHeight()*100)/550);
            button.setPrefHeight((root.getMinHeight()*heightPercent)/100 );
        }
    }
    
    /**
     * Método que realiza el zoom al canvas por medio del ScrollEvent.
     * @param event
     */
    @FXML
    private void zoom(ScrollEvent event) {
        double zoomFactor = 1.05;
        double deltaY = event.getDeltaY();
        if (deltaY < 0){
          zoomFactor = 2.0 - zoomFactor;
        }
        canvas.setScaleX(canvas.getScaleX() * zoomFactor);
        canvas.setScaleY(canvas.getScaleY() * zoomFactor);
        event.consume();
    }
    
    /**
     * Método que se encarga de buscar una entidad dentro de las entidades seleccionadas en el canvas.
     * @param entity2
     */
    private boolean searchEntity(Entity entity2){
        for (Entity entity : entitiesSelect) {
            if(entity.getName().equals(entity2.getName())){
                return true;
            }
        }
        return false;
    }
    
    @FXML
    public void undo(){
        if(!diagramsUndo.isEmpty()){
            diagram=diagramsUndo.get(diagramsUndo.size()-1).getClone(); 
            copy=diagram.getClone();
            diagramsRedo.add(copy);
            diagramsUndo.remove(diagramsUndo.size()-1);
        }
        diagram.paint(canvas, showPoints);
        
    }
    
    @FXML
    public void redo(){
        if(!diagramsRedo.isEmpty()){
            diagram=diagramsRedo.get(diagramsRedo.size()-1).getClone();      
            diagramsRedo.remove(diagramsRedo.size()-1);
        }
        diagram.paint(canvas, showPoints);
        
    }
    
    
    public void copiar(){
        copy = diagram.getClone();
        diagramsUndo.add(copy);
    }
}
