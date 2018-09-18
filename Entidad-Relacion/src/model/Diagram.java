package model;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Equipo Rocket
 */
public class Diagram {
    private ArrayList <Entity> entities;
    private ArrayList <Relation> relations;
    private ArrayList <Connector> connectors;
    private Element selectedElement;
    private int iElement;

    public Diagram() {
        entities = new ArrayList <>();
        relations = new ArrayList <>();
        connectors = new ArrayList <>();
    }
    
    /**
     *
     * @param entity
     */
    public void addEntity (Entity entity){
        this.entities.add(entity);
    }
      
    /**
     *
     * @param relation
     */
    public void addRelation (Relation relation){
        this.relations.add(relation);
    }
        
    /**
     *
     * @param connector
     */
    public void addConnector (Connector connector){
        this.connectors.add(connector);
    }
    
    /**
     * Método que recorre "entities","relations","connectors" y dibuja dichos objetos en el "canvas"
     * @param canvas
     */
    public void paint(Canvas canvas, boolean showPoints){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Entity entity : entities) {
            entity.paint(canvas,showPoints);
        }
        for (Relation relation : relations) {
            relation.paint(canvas,showPoints);
        }
        /*
        for (Connector connector : connectors) {
            connector.paint(canvas.getGraphicsContext2D());
        }*/
    }
    
    /**
     * Método que retorna el punto mínimo presente en "diagram"
     */
    public Point minPoint(){
        int minX,minY;
        if(entities.size() > 0){
            minX = entities.get(0).maxPoint().getX();
            minY = entities.get(0).maxPoint().getY();
        }
        else {
            minX = relations.get(0).maxPoint().getX();
            minY = relations.get(0).maxPoint().getY();
        }
        for (Entity entity : entities) {
            if(minX > entity.minPoint().getX()){
                minX = entity.minPoint().getX();
            }
            if(minY > entity.minPoint().getY()){
                minY = entity.minPoint().getY();
            }
        }
        for (Relation relation : relations) {
            if(minX > relation.minPoint().getX()){
                minX = relation.minPoint().getX();
            }
            if(minY > relation.minPoint().getY()){
                minY = relation.minPoint().getY();
            }
        }
        return (new Point(minX, minY));
    }
    
    /**
     * Método que retorna el punto máximo presente en "diagram"
     */
    public Point maxPoint(){
        int maxX,maxY;
        if(entities.size() > 0){
            maxX = entities.get(0).maxPoint().getX();
            maxY = entities.get(0).maxPoint().getY();
        }
        else {
            maxX = relations.get(0).maxPoint().getX();
            maxY = relations.get(0).maxPoint().getY();
        }
        for (Entity entity : entities) {
            if(maxX < entity.maxPoint().getX()){
                maxX = entity.maxPoint().getX();
            }
            if(maxY < entity.maxPoint().getY()){
                maxY = entity.maxPoint().getY();
            }
        }
        for (Relation relation : relations) {
            if(maxX < relation.maxPoint().getX()){
                maxX = relation.maxPoint().getX();
            }
            if(maxY < relation.maxPoint().getY()){
                maxY = relation.maxPoint().getY();
            }
        }
        return (new Point(maxX, maxY));
    }
    
    /**
     * Método que guarda en "selectedElement" el objeto que contiene al punto de "event"
     * Además, guarda el indice de dicho elemento en "iElement"
     */
    public void selectElement(MouseEvent event){
        int iE = 0;
        for (Entity entity : entities) {
            if(entity.isInFigure(event)){
                selectedElement = entity;
                iElement = iE;
                break;
            }
            iE++;
        }
        iE = 0;
        for (Relation relation : relations) {
            if(relation.isInFigure(event)){
                selectedElement = relation;
                iElement = iE;
                break;
            }
            iE++;
        }
    }
    
    /**
     * Método que permite mover el objeto almacendo en "selectedElement"
     */
    public void moveElement(MouseEvent event, Canvas canvas, boolean showPoints, int minWidth, int minHeight){
        if( selectedElement != null ){
            String type = selectedElement.getClass().getName().substring(6);
            if( "Entity".equals(type) ){
                entities.set(iElement, new Entity(selectedElement.name, (int)event.getX(), (int) event.getY()));
            }
            if( "Relation".equals(type) ){
                relations.set(iElement, new Relation(selectedElement.name, selectedElement.figure.getSides(), (int)event.getX(), (int) event.getY()));
            }
            adjustScreen(canvas, minWidth, minHeight);
            paint(canvas, showPoints);
        }
    }
    
    /**
     * Método que limpia "selectedElement" y el índice "iElement"
     */
    public void deselectElement(MouseEvent event){
        selectedElement = null;
        iElement = -1;
    }
    
    /**
     * Método para ajustar el canvas según figuras en el interior
     */
    public void adjustScreen(Canvas canvas, int minWidth, int minHeight){
        Point maxPoint = maxPoint();
        if( maxPoint.getX() <= minWidth){
            canvas.setWidth(minWidth);
        }
        else{
            if( maxPoint.getX() > canvas.getWidth() || maxPoint.getX() < canvas.getWidth() ){
                canvas.setWidth(maxPoint.getX() + 5);
            }
        }
        if( maxPoint.getY() <= minHeight){
            canvas.setHeight(minHeight);
        }
        else{
            if( maxPoint.getY() > canvas.getHeight() || maxPoint.getY() < canvas.getHeight() ){
                canvas.setHeight(maxPoint.getY() + 5);
            }
        }
    }
    
    /**
     * Elimina todo el contenido en el diagrama y en regresa el canvas al estado original
     */
    public void clearAll(Canvas canvas, int minWidth, int minHeight){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double width=canvas.getWidth();
        double height=canvas.getHeight();
        gc.clearRect(0,0 , width, height);
        this.entities.clear();
        this.relations.clear();
        this.connectors.clear();
        canvas.setWidth(minWidth);
        canvas.setHeight(minHeight);
    }
    
    /**
     *
     * @return entities
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     *
     * @return relations
     */
    public ArrayList<Relation> getRelations() {
        return relations;
    }

    /**
     *
     * @return connectors
     */
    public ArrayList<Connector> getConnectors() {
        return connectors;
    }
    
}
