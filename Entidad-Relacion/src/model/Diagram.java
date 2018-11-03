package model;

import controller.CallPop;
import controller.MainController;
import static controller.PopAddAttributeController.attributeType;
import static controller.PopAddAttributeController.nameAttribute;
import static controller.PopChangeController.enteredNameR;
import static controller.PopChangeController.newrelation;
import controller.PopChangeEntity;
import static controller.PopChangeEntity.newentity;
import static controller.PopChangeName.enteredName;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 *
 * @author Equipo Rocket
 */
public class Diagram extends CallPop {
    private ArrayList <Entity> entities;
    private ArrayList <Relation> relations;
    private ArrayList <Connector> connectors;
    private ArrayList <Attribute> attributes;
    public static Element selectedElement;
    private Element auxElement;
    private int iElement;

    /**
     *Constructor del diagrama
     */
    public Diagram() {
        entities = new ArrayList <>();
        relations = new ArrayList <>();
        connectors = new ArrayList <>();
        attributes = new ArrayList<>();
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
     *
     * @param entities
     */
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    /**
     *
     * @param relations
     */
    public void setRelations(ArrayList<Relation> relations) {
        this.relations = relations;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute attribute) {
        this.attributes.add(attribute);
    }
    
    /**
     * Método que recorre "entities","relations","connectors" y dibuja dichos objetos en el "canvas"
     * @param canvas
     * @param showPoints
     */
    public void paint(Canvas canvas, boolean showPoints){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        connectors.clear();
        paintConnector(canvas);
        for (Connector connector : connectors) {
            connector.paint(canvas,showPoints);
        }
        for (Entity entity : entities) {
            entity.figure.fillEntity(canvas);
            entity.paint(canvas,showPoints);
        }
        for (Relation relation : relations) {
            relation.figure.fillPolygon(canvas);
            relation.paint(canvas,showPoints);
        }
        for(Attribute attribute : attributes){
            attribute.figure.fillPolygon(canvas);
            if(attribute.getTipo().equals(AttributeType.DERIVATIVE)){
                attribute.paintDerivateAttribute(canvas, showPoints);
            }
            else if(attribute.getTipo().equals(AttributeType.KEY)){
                attribute.paintKeyAttribute(canvas, showPoints);
            }
            else if(attribute.getTipo().equals(AttributeType.PARTIALKEY)){
                attribute.paintPartialKeyAttribute(canvas, showPoints);
            }
            else{
                attribute.paint(canvas, showPoints);
            } 
        }
        //Se vuelven a pintar los puntos de control los conectores para que se puedan visualizar.
        for (Connector connector : connectors) {
            if(showPoints){
                connector.figure.paintPoints(canvas);
            }
        }
        //paintAttributes(canvas);
    }
    
    /**
     * Método que retorna el punto mínimo presente en "diagram"
     * @return 
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
     * @return 
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
     * @param event
     * @param canvas
     * @param showPoints
     */
    public void selectElement(MouseEvent event, Canvas canvas, boolean showPoints){
        int iE = 0;
        boolean ready = false;
        for (Entity entity : entities) {
            if(entity.isInFigure(event) && ready == false){
                ready = true;
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
            if(relation.isInFigure(event) && ready == false){
                ready = true;
                selectedElement = relation;
                selectedElement.setSelected(true);
                iElement = iE;
                paint(canvas, showPoints);
                break;
            }
            iE++;
        }
        iE = 0;
        for (Attribute attribute : attributes) {
            if(attribute.isInFigure(event) && ready == false){
                ready = true;
                selectedElement = attribute;
                selectedElement.setSelected(true);
                iElement = iE;
                paint(canvas, showPoints);
                break;
            }
            iE++;
        }
        ready = false;
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
        if( selectedElement != null && event.getX()-70 > 0  && event.getY()-40 > 0){
            String type = selectedElement.getClass().getName().substring(6);
            if( "Entity".equals(type) ){
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                attributesCopy=(ArrayList<Attribute>) entities.get(iElement).getAttributes().clone();
                entities.set(iElement, new Entity(selectedElement.name, (int)event.getX(), (int) event.getY(), selectedElement.selected,((Entity)selectedElement).getType(),attributesCopy));
                selectedElement = entities.get(iElement);
            }
            else if( "Relation".equals(type) ){
                ArrayList<Entity> entitiesCopy= new ArrayList<>();
                entitiesCopy=(ArrayList<Entity>) relations.get(iElement).getEntities().clone();
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                attributesCopy=(ArrayList<Attribute>) relations.get(iElement).getAttributes().clone();
                relations.set(iElement, new Relation(selectedElement.name, selectedElement.figure.getSides(), (int)event.getX(), (int) event.getY(), selectedElement.selected,entitiesCopy,attributesCopy));
                selectedElement = relations.get(iElement);
            }
            else if( "Attribute".equals(type)){
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                attributesCopy=(ArrayList<Attribute>) attributes.get(iElement).getAttributes().clone();
                attributes.set(iElement, new Attribute(((Attribute)selectedElement).getTipo(),selectedElement.name,selectedElement.selected,(int)event.getX(), (int) event.getY(),attributesCopy));
                selectedElement = attributes.get(iElement);
            }
            for (int i=0; i<relations.size();i++) {
                for (int a=0; a<relations.get(i).getEntities().size();a++) {
                    int nElement=search(relations.get(i).getEntities().get(a));
                    if(nElement!=-1){
                        relations.get(i).getEntities().set(a, entities.get(nElement));
                    }
                }
            }
            for (int i=0; i<relations.size();i++) {
                for (int a=0; a<relations.get(i).getAttributes().size();a++) {
                    int nElement=searchAttribute(relations.get(i).getAttributes().get(a));
                    if(nElement!=-1){
                        relations.get(i).getAttributes().set(a, attributes.get(nElement));
                    }
                }
            }
            for (int i=0; i<entities.size();i++) {
                for (int a=0; a<entities.get(i).getAttributes().size();a++) {
                    int nElement=searchAttribute(entities.get(i).getAttributes().get(a));
                    if(nElement!=-1){
                        entities.get(i).getAttributes().set(a, attributes.get(nElement));
                    }
                }
            }
            for (int i=0; i<attributes.size();i++) {
                for (int a=0; a<attributes.get(i).getAttributes().size();a++) {
                    int nElement=searchAttribute(attributes.get(i).getAttributes().get(a));
                    if(nElement!=-1){
                        attributes.get(i).getAttributes().set(a, attributes.get(nElement));
                    }
                }
            }
            adjustScreen(canvas, minWidth, minHeight);
            paint(canvas, showPoints);
        }
    }
    
    /**
     *Busca una entidad dentro de un Array y devuelve su ubicación
     * @param entity
     * @return
     */
    public int search(Entity entity){
        for(int i=0; i<entities.size();i++){
            if(entities.get(i).getName().equals(entity.getName())){
                return i;
            }
        }
        return -1;
    }
    
    public int searchAttribute(Attribute attribute){
        for(int i=0; i<attributes.size();i++){
            if(attributes.get(i).getName().equals(attribute.getName())){
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
            if( "Attribute".equals(type) ){
                attributes.get(iElement).setSelected(false);
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
        attributes.clear();
        canvas.setWidth(minWidth);
        canvas.setHeight(minHeight);
    }
    
    /**
     *Devuelve el numero de entidades seleccionadas
     * @return
     */
    public int numberOfEntitiesSelect (){
        int count = 0;
        for (Entity entitie : this.entities) {
            if(entitie.selected){
                count++;
            }
        }
        return count;
    }
    
    /**
     *Devuelve un ArrayList con las entidades seleccionadas
     * @return
     */
    public ArrayList<Entity> entitiesSelect (){
        ArrayList<Entity> entities= new ArrayList<>();
        for (Entity entitie : this.entities) {
            if(entitie.selected){
                entities.add(entitie);
            }
        }
        return entities;
    }
    
    /**
     *Deselecciona todas las entidades seleccionadas
     */
    public void deselectAllEntities (){
        for (Entity entitie : this.entities) {
            entitie.setSelected(false);
        }
        selectedElement=null;
    }
    
    /**
     *
     * @return
     */
    public Element getSelectedElement() {
        return selectedElement;
    }

    /**
     *
     * @param selectedElement
     */
    public void setSelectedElement(Element selectedElement) {
        this.selectedElement = selectedElement;
    }
    
    /**
     *
     * @return
     */
    public boolean isSomethingSelect (){
        return this.selectedElement!=null;
    }
    
    /**
     *Método que crea los conectores entre relaciones y entidades para dibujar las lineas
     */
    public void createConnectors(){
        int j=0;
        ArrayList<Attribute> attributes1=new ArrayList<>();
        for(int i=0;i<relations.size();i++){
            for(int a=0;a<relations.get(i).getEntities().size();a++){
                
                Connector connector= new Connector(relations.get(i),relations.get(i).getFigure().getCenter()
                        ,relations.get(i).getEntities().get(a),new Point((relations.get(i).getEntities().get(a).getFigure().getPosX()),relations.get(i).getEntities().get(a).getFigure().getPosY()),
                " ",false,attributes1);
                connectors.add(connector);
            }
        }
        for(int i=0;i<relations.size();i++){
            for(int a=0;a<relations.get(i).getAttributes().size();a++){
                Connector connector= new Connector(relations.get(i),new Point(relations.get(i).figure.getPosX(),relations.get(i).figure.getPosY()),
                    relations.get(i).getAttributes().get(a),new Point(relations.get(i).getAttributes().get(a).figure.getPosX(),relations.get(i).getAttributes().get(a).figure.getPosY()),
                " ",false,attributes1);
                connectors.add(connector);
            }
        }
        for(int i=0;i<entities.size();i++){
            for(int a=0;a<entities.get(i).getAttributes().size();a++){
                Connector connector= new Connector(entities.get(i),new Point(entities.get(i).figure.getPosX(),entities.get(i).figure.getPosY()),
                    entities.get(i).getAttributes().get(a),new Point(entities.get(i).getAttributes().get(a).figure.getPosX(),entities.get(i).getAttributes().get(a).figure.getPosY()),
                " ",false,attributes1);
                connectors.add(connector);
            }
        }
        for(int i=0;i<attributes.size();i++){
            for(int a=0;a<attributes.get(i).getAttributes().size();a++){
                Connector connector= new Connector(attributes.get(i),new Point(attributes.get(i).figure.getPosX(),attributes.get(i).figure.getPosY()),
                    attributes.get(i).getAttributes().get(a),new Point(attributes.get(i).getAttributes().get(a).figure.getPosX(),attributes.get(i).getAttributes().get(a).figure.getPosY()),
                " ",false,attributes1);
                connectors.add(connector);
            }
        }
    }

    /**
     *Busca el punto medio entre dos puntos
     * @param point1
     * @param point2
     * @return
     */
    public Point middlePoint(Point point1, Point point2){
        int x= (point1.getX()+point2.getX())/2;
        int y= (point1.getY()+point2.getY())/2;
        return new Point(x,y);
    }
    
    /**
     *Busca el punto mas cercano de la relación con los 4 puntos de la entidad
     * @param pointRelation
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public Point closestPoint(Point pointRelation,Point p1,Point p2, Point p3,Point p4){
        int restax=p1.getX()-pointRelation.getX();
        int restay=p1.getY()-pointRelation.getY();
        int d1=(int) Math.sqrt(restax*restax + restay*restay);
        restax=p2.getX()-pointRelation.getX();
        restay=p2.getY()-pointRelation.getY();
        int d2=(int) Math.sqrt(restax*restax + restay*restay);
        restax=p3.getX()-pointRelation.getX();
        restay=p3.getY()-pointRelation.getY();
        int d3=(int) Math.sqrt(restax*restax + restay*restay);
        restax=p4.getX()-pointRelation.getX();
        restay=p4.getY()-pointRelation.getY();
        int d4=(int) Math.sqrt(restax*restax + restay*restay);
        if(d1 < d2 && d1 < d3 && d1 < d4 && p1.isDisponible()){
            return p1;
        }else{
            if(d2 < d1 && d2 < d3 && d2 < d4 && p2.isDisponible()){
                return p2;
            }else{
                if(d3 < d1 && d3 < d2 && d3 < d4 && p3.isDisponible()){
                    return p3;
                }else
                    return p4;
            }
        }
    }
    //Agrega un atributo a una relacion o entidad y al diagrama, pero tiene problemas con el click hay que solucionarlo
    public void agregarAtributo(MouseEvent event, Canvas canvas, boolean showPoints) throws IOException{
        boolean ready = false;
        for (Entity entity : entities) {
            if(entity.isInFigure(event) && ready == false){
                popAddAttribute();
                ready = true;
                if(!"".equals(nameAttribute)){
                    ArrayList<Attribute> attributes1=new ArrayList<>();
                    entity.getAttributes().add(new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1));
                    MainController.diagram.getAttributes().add(new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1));
                    nameAttribute="";
                }
                break;
            }
        }
        for (Relation relation : relations) {
            if(relation.isInFigure(event) && ready == false){
                popAddAttribute();
                ready = true ;
                if(!"".equals(nameAttribute)){
                    ArrayList<Attribute> attributes1=new ArrayList<>();
                    relation.getAttributes().add(new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1));
                    MainController.diagram.getAttributes().add(new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1));
                    nameAttribute="";
                }
                break;
            }
        }
        for (Attribute attribute : attributes) {
            if(attribute.isInFigure(event) && ready == false){
                if(attribute.getTipo().equals(AttributeType.COMPOUND)){
                    popAddAttribute();
                    ready = true ;
                    if(!"".equals(nameAttribute)){
                        ArrayList<Attribute> attributes1=new ArrayList<>();
                        attribute.getAttributes().add(new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1));
                        MainController.diagram.getAttributes().add(new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1));
                        nameAttribute="";
                    }
                    break;
                }
                else{
                    alertOfTypeIncorrect();
                    break;
                }
            }
        }
        ready = false;
        paint(canvas, showPoints);
    }
    /**
     *Dibuja en la pantalla los conectores
     * @param canvas
     */
    public void paintConnector(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
        createConnectors();
        for(int i=0;i<connectors.size();i++){
           gc.strokeLine(connectors.get(i).getPointElement1().getX(),connectors.get(i).getPointElement1().
                   getY(), connectors.get(i).getPointElement2().getX(), connectors.get(i).getPointElement2().getY());
        }
    }
    //pinta los atributos dentro del canvas
    public void paintAttributes(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
        gc.setFill(Color.BLUEVIOLET);
        for(int i=0;i<attributes.size();i++){
            gc.strokeArc(attributes.get(i).figure.getPosX(), attributes.get(i).figure.getPosY(), 200, 100, 300, 400, ArcType.ROUND);
        }
    }
    /**
     *Identifica el elemento que se desea editar y lo modifica
     * @param event
     * @param canvas
     * @param showPoints
     * @throws IOException
     */
    public void selectElementEdit(MouseEvent event, Canvas canvas, boolean showPoints) throws IOException{
        boolean ready = false;
        int iE=0;
        for (Entity entity : entities) {
            if(entity.isInFigure(event) && ready == false){
                selectedElement=entity;
                enteredName= entity.getName();
                popEditEntity();
                ready = true;
                if(!"".equals(enteredName)){
                    entities.set(iE, new Entity(newentity.getName(),newentity.figure.getPosX(),newentity.figure.getPosY(),false,newentity.getType(),newentity.getAttributes()));
                    enteredName="";
                }
                break;
            }
            iE++;
        }
        iE=0;
        for (Relation relation : relations) {
            if(relation.isInFigure(event) && ready == false){
                selectedElement=relation;
                enteredNameR=relation.getName();
                popEdit();
                ready = true ;
                if(!"".equals(enteredName)){
                    relations.set(iE, new Relation(newrelation.getName(),newrelation.getEntities().size(),newrelation.getFigure().getPosX(),newrelation.getFigure().getPosY(),false,newrelation.getEntities(),newrelation.getAttributes()));      
                    enteredName="";
                }
                break;
            }
            iE++;
        }
        for (Attribute attribute : attributes) {
            if(attribute.isInFigure(event) && ready == false){
                popEditElement();
                ready = true ;
                if(!"".equals(enteredName)){
                    attribute.setName(enteredName);
                    attribute.figure.setName(enteredName);
                    enteredName="";
                }
                break;
            }
        }
        ready = false;
        paint(canvas, showPoints);
    }
    
    
    /**
     * Método para eliminar alguna entidad o relación presente en el canvas
     * @param event con las coordenadas donde se ha hecho click
     * @param canvas donde se dibujan las figuras
     * @param showPoints variable para mostrar los puntos de control
     * @throws java.io.IOException
     */
    public void delete(MouseEvent event, Canvas canvas, boolean showPoints) throws IOException{
        boolean ready = false;
        //Eliminar una Relación
        for (int i = 0; i < this.relations.size(); i++) {
            if(this.relations.get(i).isInFigure(event) && ready == false){
                ready = true;
                Relation relation= this.relations.get(i);
                while(!relation.getAttributes().isEmpty()){
                    for (int j = 0; j <relation.getAttributes().size(); j++) {
                        deleteAttribute(relation.getAttributes().get(j));
                        relation.getAttributes().remove(j);
                        paint(canvas, showPoints); 
                    }
                }
                this.relations.remove(i);              
            }
        }
        //Eliminar una entidad
        for (int i = 0; i < this.entities.size(); i++) {
            if(this.entities.get(i).isInFigure(event) && ready == false){
                ready = true;
                Entity entity = this.entities.get(i);
                while(!entity.getAttributes().isEmpty()){
                    for (int j = 0; j <entity.getAttributes().size(); j++) {
                        deleteAttribute(entity.getAttributes().get(j));
                        entity.getAttributes().remove(j);
                        paint(canvas, showPoints); 
                    }
                }
                while(hasAnyRelation(entity)){
                    for (int j = 0; j <this.relations.size(); j++) {
                        if(this.relations.get(j).hasThisEntity(entity)){
                            Relation relation = this.relations.get(j);
                            if (relation.getEntities().size()<=1){
                                deleteSomeAttributes(this.relations.get(i));
                                this.relations.get(j).getAttributes().clear();
                                this.relations.remove(j);                      
                            }
                            else{
                                relation.removeEntity(entity);
                                ArrayList<Entity> entitiesCopy = new ArrayList<>();
                                entitiesCopy=(ArrayList<Entity>) relation.getEntities().clone();
                                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                                attributesCopy=(ArrayList<Attribute>) relation.getAttributes().clone();
                                this.relations.set(j, new Relation(relation.name,relation.figure.getSides()-1,relation.figure.getPosX(),relation.figure.getPosY(),relation.selected,entitiesCopy,attributesCopy));
                                paint(canvas, showPoints); 
                            }
                            j=0;
                        }
                    }
                }
                if (!hasAnyRelation(this.entities.get(i))){
                    this.entities.remove(i);
                }
            }
        }
        //Eliminar un atributo
        for (int i = 0; i < this.attributes.size(); i++) {
            if(this.attributes.get(i).isInFigure(event) && ready == false){
                ready = true;
                for (int j = 0; j <this.entities.size(); j++) {
                    int indexAttribute=this.entities.get(j).findAttribute(this.attributes.get(i));
                    if(indexAttribute!=-1){
                        this.entities.get(j).getAttributes().remove(indexAttribute);
                    }
                }
                for (int j = 0; j <this.relations.size(); j++) {
                    int indexAttribute=this.relations.get(j).findAttribute(this.attributes.get(i));
                    if(indexAttribute!=-1){
                        this.relations.get(j).getAttributes().remove(indexAttribute);
                    }
                }
                this.attributes.remove(i);              
            }
        }
        ready = false;
        paint(canvas, showPoints);
    }
    
    /**
     * Método para saber si ya existe, dentro del sistema, el nombre ingresado
     * @param name es el nombre a buscar
     * @return 
     */
    public boolean thisNameExists (String name){
        for (Entity entitie : this.entities) {
            if (entitie.getName().equals(name)){
                return true;                
            }
        }
        
        for (Relation relation : this.relations) {
            if (relation.getName().equals(name)){
                return true;                
            }
        }
        return false;
    }
    
    /**
     * Método para saber si la entidad escogida existe dentro de una relación
     * @param entity
     * @return 
     */
    public boolean hasAnyRelation (Entity entity){
        for (int i = 0; i < this.relations.size(); i++) {
            if(this.relations.get(i).hasThisEntity(entity)){
                return true;
            }
        }
        return false;
    }
    
    //Metodo para eliminar un atributo dentro del diagrama (sin conocer su indice).
    public void deleteAttribute (Attribute attribute){
        for (int i = 0; i <this.attributes.size(); i++) {
            if(this.attributes.get(i).equals(attribute)){
                this.attributes.remove(i);
            }
        }
    }
    
    //Metodo para eliminar algunos atributos dentro del diagrama de una relacion o entidad.
    public void deleteSomeAttributes (Element element){
        for (int i = 0; i <this.attributes.size(); i++) {
            for (int j = 0; j <element.getAttributes().size(); j++) {
                if(this.attributes.get(i).equals(element.getAttributes().get(j))){
                    this.attributes.remove(i);
                }
            }
            
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
}
