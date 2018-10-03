package model;

import controller.CallPop;
import static controller.PopChangeName.enteredName;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Equipo Rocket
 */
public class Diagram extends CallPop {
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
        connectors.clear();
        paintConnector(canvas);
        for (Connector connector : connectors) {
            connector.paint(canvas,showPoints);
        }
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
    public void selectElement(MouseEvent event, Canvas canvas, boolean showPoints){
        int iE = 0;
        for (Entity entity : entities) {
            if(entity.isInFigure(event)){
                selectedElement = entity;
                selectedElement.setSelected(true);
                iElement = iE;
                paint(canvas, showPoints);
                break;
            }
            iE++;
        }
        iE = 0;
        for (Relation relation : relations) {
            if(relation.isInFigure(event)){
                selectedElement = relation;
                selectedElement.setSelected(true);
                iElement = iE;
                paint(canvas, showPoints);
                break;
            }
            iE++;
        }
    }
    
    /**
     * Método que permite mover el objeto almacendo en "selectedElement"
     * @param event
     * @param canvas
     * @param showPoints
     * @param minWidth
     * @param minHeight
     */
    public void moveElement(MouseEvent event, Canvas canvas, boolean showPoints, int minWidth, int minHeight){
        if( selectedElement != null ){
            String type = selectedElement.getClass().getName().substring(6);
            if( "Entity".equals(type) ){
                entities.set(iElement, new Entity(selectedElement.name, (int)event.getX(), (int) event.getY(), selectedElement.selected));
               
            }
            if( "Relation".equals(type) ){
                ArrayList<Entity> entitiesCopy= new ArrayList<>();
                entitiesCopy=(ArrayList<Entity>) relations.get(iElement).getEntities().clone();
                relations.set(iElement, new Relation(selectedElement.name, selectedElement.figure.getSides(), (int)event.getX(), (int) event.getY(), selectedElement.selected,entitiesCopy));
            }
            for (int i=0; i<relations.size();i++) {
                for (int a=0; a<relations.get(i).getEntities().size();a++) {
                    int nElement=search(relations.get(i).getEntities().get(a));
                    if(nElement!=-1){
                        relations.get(i).getEntities().set(a, entities.get(nElement));
                    }
                }
            }           
            adjustScreen(canvas, minWidth, minHeight);
            paint(canvas, showPoints);
        }
    }
    
    public int search(Entity entity){
        for(int i=0; i<entities.size();i++){
            if(entities.get(i).getName().equals(entity.getName())){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Método que limpia "selectedElement" y el índice "iElement"
     * @param event
     */
    public void deselectElement(MouseEvent event){
        if( selectedElement != null ){
            String type = selectedElement.getClass().getName().substring(6);
            if( "Entity".equals(type) ){
                entities.get(iElement).setSelected(false);
            }
            if( "Relation".equals(type) ){
                relations.get(iElement).setSelected(false);
            }
        }
        selectedElement = null;
        iElement = -1;
    }
    
    /**
     * Método para ajustar el canvas según figuras en el interior
     * @param canvas
     * @param minWidth
     * @param minHeight
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
     * Elimina todo el contenido en el diagrama y regresa el canvas al estado original
     * @param canvas
     * @param minWidth
     * @param minHeight
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
    
    public int numberOfEntitiesSelect (){
        int count = 0;
        for (Entity entitie : this.entities) {
            if(entitie.selected){
                count++;
            }
        }
        return count;
    }
    
    public ArrayList<Entity> entitiesSelect (){
        ArrayList<Entity> entities= new ArrayList<>();
        for (Entity entitie : this.entities) {
            if(entitie.selected){
                entities.add(entitie);
            }
        }
        return entities;
    }
    
    public void deselectAllEntities (){
        for (Entity entitie : this.entities) {
            entitie.setSelected(false);
        }
        selectedElement=null;
    }
    
    public Element getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(Element selectedElement) {
        this.selectedElement = selectedElement;
    }
    
    public boolean isSomethingSelect (){
        return this.selectedElement!=null;
    }
    
    public void createConnectors(){
        int j=0;
        for(int i=0;i<relations.size();i++){
            for(int a=0;a<relations.get(i).getEntities().size();a++){
                if(relations.get(i).getEntities().size()==1){                 
                    Point punto=middlePoint(relations.get(i).getEntities().get(a).getFigure().getPoints()
                        .get(j),relations.get(i).getEntities().get(a).getFigure().getPoints()
                        .get(j+1));
                    Connector connector= new Connector(relations.get(i),relations.get(i).getFigure().getPoints().get(1),relations.get(i).getEntities().get(a),punto,"",false);
                    connectors.add(connector); 
                    
                    Point punto2=middlePoint(relations.get(i).getEntities().get(a).getFigure().getPoints()
                        .get(j),relations.get(i).getEntities().get(a).getFigure().getPoints()
                        .get(j+3));
                    Connector connector2= new Connector(relations.get(i),relations.get(i).getFigure().getPoints().get(2),relations.get(i).getEntities().get(a),punto2," ",false);
                    connectors.add(connector2);         
                }
                if(relations.get(i).getEntities().size()==2){
                    Point punto=middlePoint(relations.get(i).getEntities().get(0).getFigure().getPoints()
                        .get(j),relations.get(i).getEntities().get(0).getFigure().getPoints()
                        .get(j+1));
                    Connector connector= new Connector(relations.get(i),relations.get(i).getFigure().getPoints().get(2),relations.get(i).getEntities().get(0),punto," ",false);
                    connectors.add(connector);
                    
                    Point punto2=middlePoint(relations.get(i).getEntities().get(1).getFigure().getPoints()
                        .get(j+2),relations.get(i).getEntities().get(1).getFigure().getPoints()
                        .get(j+3));
                    Connector connector2= new Connector(relations.get(i),relations.get(i).getFigure().getPoints().get(0),relations.get(i).getEntities().get(1),punto2," ",false);
                    connectors.add(connector2);         
                }
                if(relations.get(i).getEntities().size()>2){
                    Point punto= new Point(0,0);
                    if(a==0 || a==5 || a==6){
                        punto=middlePoint(relations.get(i).getEntities().get(a).getFigure().getPoints()
                            .get(j+2),relations.get(i).getEntities().get(a).getFigure().getPoints()
                            .get(j+3));
                    }
                    if(a==1){
                        punto=middlePoint(relations.get(i).getEntities().get(a).getFigure().getPoints()
                            .get(j+3),relations.get(i).getEntities().get(a).getFigure().getPoints()
                            .get(j));
                    }
                    if(a==2){
                        punto=middlePoint(relations.get(i).getEntities().get(a).getFigure().getPoints()
                            .get(j+1),relations.get(i).getEntities().get(a).getFigure().getPoints()
                            .get(j));
                    }
                    if(a==3 || a==4){
                        punto=middlePoint(relations.get(i).getEntities().get(a).getFigure().getPoints()
                            .get(j+1),relations.get(i).getEntities().get(a).getFigure().getPoints()
                            .get(j+2));
                    }
                    Connector connector= new Connector(relations.get(i),relations.get(i).getFigure().getPoints().get(a),relations.get(i).getEntities().get(a),punto," ",false);
                    connectors.add(connector);
                }
            }
        }
    }

    public Point middlePoint(Point point1, Point point2){
        int x= (point1.getX()+point2.getX())/2;
        int y= (point1.getY()+point2.getY())/2;
        return new Point(x,y);
    }

    public void paintConnector(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
        createConnectors();
        for(int i=0;i<connectors.size();i++){
           gc.strokeLine(connectors.get(i).getPointElement1().getX(),connectors.get(i).getPointElement1().
                   getY(), connectors.get(i).getPointElement2().getX(), connectors.get(i).getPointElement2().getY());
        }
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
    
    public void selectElementEdit(MouseEvent event, Canvas canvas, boolean showPoints) throws IOException{
        int iE = 0;
        for (Entity entity : entities) {
            if(entity.isInFigure(event)){
                popEditElement();
                if(enteredName.length()>0){
                    entity.setName(enteredName);
                    entity.figure.setName(enteredName);
                }
                break;
            }
            iE++;
        }
        iE = 0;
        for (Relation relation : relations) {
            if(relation.isInFigure(event)){
                popEditElement();
                if(enteredName.length()>0){
                    relation.setName(enteredName);
                    relation.figure.setName(enteredName);
                }
                break;
            }
            iE++;
        }       
        paint(canvas, showPoints);
    }
    
    public void delete(MouseEvent event, Canvas canvas, boolean showPoints) throws IOException{
        for (int i = 0; i < this.relations.size(); i++) {
            if(this.relations.get(i).isInFigure(event)){
                System.out.println(this.relations.get(i).figure.getName());
                this.relations.remove(i);
            }
        }
        paint(canvas, showPoints);
    }
}
