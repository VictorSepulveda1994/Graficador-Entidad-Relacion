package model;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
        connectors.clear();
        paintConnector(canvas);
    }
    
    /**
     * Método que retorna el punto mínimo presente en "diagram"
     */
    public Point minPoint(){
        int minX,minY;
        if(entities.size() > 0){
            minX = entities.get(0).maxPoint().getPosX();
            minY = entities.get(0).maxPoint().getPosY();
        }
        else {
            minX = relations.get(0).maxPoint().getPosX();
            minY = relations.get(0).maxPoint().getPosY();
        }
        for (Entity entity : entities) {
            if(minX > entity.minPoint().getPosX()){
                minX = entity.minPoint().getPosX();
            }
            if(minY > entity.minPoint().getPosY()){
                minY = entity.minPoint().getPosY();
            }
        }
        for (Relation relation : relations) {
            if(minX > relation.minPoint().getPosX()){
                minX = relation.minPoint().getPosX();
            }
            if(minY > relation.minPoint().getPosY()){
                minY = relation.minPoint().getPosY();
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
            maxX = entities.get(0).maxPoint().getPosX();
            maxY = entities.get(0).maxPoint().getPosY();
        }
        else {
            maxX = relations.get(0).maxPoint().getPosX();
            maxY = relations.get(0).maxPoint().getPosY();
        }
        for (Entity entity : entities) {
            if(maxX < entity.maxPoint().getPosX()){
                maxX = entity.maxPoint().getPosX();
            }
            if(maxY < entity.maxPoint().getPosY()){
                maxY = entity.maxPoint().getPosY();
            }
        }
        for (Relation relation : relations) {
            if(maxX < relation.maxPoint().getPosX()){
                maxX = relation.maxPoint().getPosX();
            }
            if(maxY < relation.maxPoint().getPosY()){
                maxY = relation.maxPoint().getPosY();
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
    //AYLINE MODIFICO AQUI el 20/09
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
                relations.set(iElement, new Relation(selectedElement.name, selectedElement.figure.getSides(), (int)event.getX(), (int) event.getY(), selectedElement.selected));
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
    //AYLINE MODIFICO AQUI el 20/09
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
        if( maxPoint.getPosX() <= minWidth){
            canvas.setWidth(minWidth);
        }
        else{
            if( maxPoint.getPosX() > canvas.getWidth() || maxPoint.getPosX() < canvas.getWidth() ){
                canvas.setWidth(maxPoint.getPosX() + 5);
            }
        }
        if( maxPoint.getPosY() <= minHeight){
            canvas.setHeight(minHeight);
        }
        else{
            if( maxPoint.getPosY() > canvas.getHeight() || maxPoint.getPosY() < canvas.getHeight() ){
                canvas.setHeight(maxPoint.getPosY() + 5);
            }
        }
    }
    
    /**
     * Elimina todo el contenido en el diagrama y en regresa el canvas al estado original
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
        this.selectedElement = null;
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
    //AYLINE MODIFICO AQUI
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
    
    //AYLINE MODIFICO AQUI el 20/09
    public void createConnectors(){
        int j=0;
        for(int i=0;i<relations.size();i++){
            for(int a=0;a<relations.get(i).getEntities().size();a++){
                if(relations.get(i).getEntities().size()==1){
                    Connector connector= new Connector(relations.get(i),relations.get(i).getEntities().get(a));
                    connector.setPointElement1(relations.get(i).getFigure().getPoints().get(1));
                    Point punto=middlePoint(relations.get(i).getEntities().get(a).getFigure().getPoints()
                        .get(i),relations.get(i).getEntities().get(a).getFigure().getPoints()
                        .get(i+1));
                    connector.setPointElement2(punto);
                    connectors.add(connector); 
                    
                    Connector connector2= new Connector(relations.get(i),relations.get(i).getEntities().get(a));
                    connector2.setPointElement1(relations.get(i).getFigure().getPoints().get(2));
                    Point punto2=middlePoint(relations.get(i).getEntities().get(a).getFigure().getPoints()
                        .get(i),relations.get(i).getEntities().get(a).getFigure().getPoints()
                        .get(i+3));
                    connector2.setPointElement2(punto2);
                    connectors.add(connector2);         
                }
                if(relations.get(i).getEntities().size()==2){
                    Connector connector= new Connector(relations.get(i),relations.get(i).getEntities().get(0));
                    connector.setPointElement1(relations.get(i).getFigure().getPoints().get(2));
                    Point punto=middlePoint(relations.get(i).getEntities().get(0).getFigure().getPoints()
                        .get(i),relations.get(i).getEntities().get(0).getFigure().getPoints()
                        .get(i+1));
                    connector.setPointElement2(punto);
                    connectors.add(connector);
                    
                    Connector connector2= new Connector(relations.get(i),relations.get(i).getEntities().get(1));
                    connector2.setPointElement1(relations.get(i).getFigure().getPoints().get(0));
                    Point punto2=middlePoint(relations.get(i).getEntities().get(1).getFigure().getPoints()
                        .get(i+2),relations.get(i).getEntities().get(1).getFigure().getPoints()
                        .get(i+3));
                    connector2.setPointElement2(punto2);
                    connectors.add(connector2);         
                }
                if(relations.get(i).getEntities().size()>2){
                    Connector connector= new Connector(relations.get(i),relations.get(i).getEntities().get(a));
                    connector.setPointElement1(relations.get(i).getFigure().getPoints().get(a));
                    Point punto= new Point(0,0);
                    if(a==0 || a==5){
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
                    connector.setPointElement2(punto);
                    connectors.add(connector);
                }
            }
        }
    }
    //AYLINE MODIFICO AQUI
    public Point middlePoint(Point point1, Point point2){
        int x= (point1.getPosX()+point2.getPosX())/2;
        int y= (point1.getPosY()+point2.getPosY())/2;
        return new Point(x,y);
    }
    //AYLINE MODIFICO AQUI el 20/09
    public void paintConnector(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.ROYALBLUE);
        gc.setLineWidth(4);
        createConnectors();
        for(int i=0;i<connectors.size();i++){
           gc.strokeLine(connectors.get(i).getPointElement1().getPosX(),connectors.get(i).getPointElement1().
                   getPosY(), connectors.get(i).getPointElement2().getPosX(), connectors.get(i).getPointElement2().getPosY());
        }
    }   
}
