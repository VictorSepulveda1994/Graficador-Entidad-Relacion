package model;

import controller.CallPop;
import controller.MainController;
import controller.PopAddAttributeController;
import static controller.PopAddAttributeController.attributeType;
import static controller.PopAddAttributeController.nameAttribute;
import static controller.PopChangeController.enteredNameR;
import static controller.PopChangeController.newrelation;
import static controller.PopChangeName.enteredName;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import static controller.PopChangeEntity.newEntity;

/**
 *
 * @author Equipo Rocket
 */
public class Diagram extends CallPop {
    private ArrayList <Entity> entities;
    private ArrayList <Relation> relations;
    private ArrayList <Connector> connectors;
    private ArrayList <Attribute> attributes;
    public static ArrayList <Heritage> heritages;
    public static Element selectedElement;
    public static int contador;
    private int iElement;

    /**
     *Constructor del diagrama
     */
    public Diagram() {
        entities = new ArrayList <>();
        relations = new ArrayList <>();
        connectors = new ArrayList <>();
        attributes = new ArrayList<>();
        heritages = new ArrayList<>();
        contador=0;
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
     * @param heritage
     */
    public void addHeritage (Heritage heritage){
        this.heritages.add(heritage);
    }

    /**
     *
     * @param entities
     */
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public ArrayList<Heritage> getHeritages() {
        return heritages;
    }

    /**
     *
     * @param relations
     */
    public void setRelations(ArrayList<Relation> relations) {
        this.relations = relations;
    }
    
    /**
     * Método que recorre "entities","relations","connectors" y dibuja dichos objetos en el "canvas"
     * @param canvas
     * @param showPoints
     */
    public void paint(Canvas canvas, boolean showPoints){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        connectors.clear();
        createConnectors();
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
        for (Heritage heritage : heritages) {
            heritage.figure.fillPolygon(canvas);
            heritage.paint(canvas, showPoints);
        }
        for(Attribute attribute : attributes){
            attribute.figure.fillPolygon(canvas);
            switch (attribute.getTipo()) {
                case DERIVATIVE:
                    attribute.paintDerivateAttribute(canvas, showPoints);
                    break;
                case KEY:
                    attribute.paintKeyAttribute(canvas, showPoints);
                    break;
                case PARTIALKEY:
                    attribute.paintPartialKeyAttribute(canvas, showPoints);
                    break;
                default:
                    attribute.paint(canvas, showPoints);
                    break;
            }
        }
        //Se vuelven a pintar los puntos de control los conectores para que se puedan visualizar.
        for (Connector connector : connectors) {
            if(showPoints){
                connector.figure.paintPoints(canvas);
            }
        }
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
        for (Heritage heritage : heritages) {
            if(heritage.isInFigure(event) && ready == false){
                ready = true;
                selectedElement = heritage;
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
                relations.set(iElement, new Relation(selectedElement.name, selectedElement.figure.getSides(), (int)event.getX(), (int) event.getY(), selectedElement.selected,entitiesCopy,attributesCopy,((Relation)selectedElement).getType()));
                selectedElement = relations.get(iElement);
            }
            else if( "Attribute".equals(type)){
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                attributesCopy=(ArrayList<Attribute>) attributes.get(iElement).getAttributes().clone();
                attributes.set(iElement, new Attribute(((Attribute)selectedElement).getTipo(),selectedElement.name,selectedElement.selected,(int)event.getX(), (int) event.getY(),attributesCopy));
                selectedElement = attributes.get(iElement);
            }
            else if( "Heritage".equals(type)){
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                attributesCopy=(ArrayList<Attribute>) heritages.get(iElement).getParentEntity().getAttributes().clone();
                ArrayList<Entity> entitiesCopy= new ArrayList<>();
                heritages.get(iElement).getDaughtersEntities().add(0,heritages.get(iElement).getParentEntity());
                entitiesCopy=(ArrayList<Entity>) heritages.get(iElement).getDaughtersEntities().clone();
                HeritageType type1= heritages.get(iElement).getHeritageType();
                heritages.set(iElement, new Heritage(selectedElement.name,(int)event.getX(),(int) event.getY(), selectedElement.selected,attributesCopy,entitiesCopy,type1));
                selectedElement = heritages.get(iElement);
            }
            
            //Guarda las entidades dentro de las relaciones
            for (int i=0; i<relations.size();i++) {
                for (int a=0; a<relations.get(i).getEntities().size();a++) {
                    int nElement=searchEntity(relations.get(i).getEntities().get(a));
                    if(nElement!=-1){
                        relations.get(i).getEntities().set(a, entities.get(nElement));
                    }
                }
            }
            //Guarda los atributos dentro de las relaciones
            for (int i=0; i<relations.size();i++) {
                for (int a=0; a<relations.get(i).getAttributes().size();a++) {
                    int nElement=searchAttribute(relations.get(i).getAttributes().get(a));
                    if(nElement!=-1){
                        relations.get(i).getAttributes().set(a, attributes.get(nElement));
                    }
                }
            }
            
            //Guarda los attributos dentro de las entidades
            for (int i=0; i<entities.size();i++) {
                for (int a=0; a<entities.get(i).getAttributes().size();a++) {
                    int nElement=searchAttribute(entities.get(i).getAttributes().get(a));
                    if(nElement!=-1){
                        entities.get(i).getAttributes().set(a, attributes.get(nElement));
                    }
                }
            }
            
            //Guarda los atributos dentro de los atributos
            for (int i=0; i<attributes.size();i++) {
                for (int a=0; a<attributes.get(i).getAttributes().size();a++) {
                    int nElement=searchAttribute(attributes.get(i).getAttributes().get(a));
                    if(nElement!=-1){
                        attributes.get(i).getAttributes().set(a, attributes.get(nElement));
                    }
                }
            }
            //Guarda nuevamente los atributos y padre de la herencia
            for (int i=0; i<heritages.size();i++) {
                for (int a=0; a<heritages.get(i).getDaughtersEntities().size();a++) {
                    int nElement=searchEntity(heritages.get(i).getDaughtersEntities().get(a));
                    if(nElement!=-1){
                        heritages.get(i).getDaughtersEntities().set(a, entities.get(nElement));
                    }
                }   
                int nElement=searchEntity(heritages.get(i).getParentEntity());
                if(nElement!=-1){
                    heritages.get(i).setParentEntity(entities.get(nElement));  
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
    public int searchEntity(Entity entity){
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
    
    public int searchRelation(Relation relation){
        for(int i=0; i<relations.size();i++){
            if(relations.get(i).getName().equals(relation.getName())){
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
        this.heritages.clear();
        this.attributes.clear();
        entitiesSelect().clear();
        MainController.entitiesSelect.clear();
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
        ArrayList<Entity> entitiesAux= new ArrayList<>();
        for (Entity entitie : this.entities) {
            if(entitie.selected){
                entitiesAux.add(entitie);
            }
        }
        return entitiesAux;
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
        ArrayList<Attribute> attributes1=new ArrayList<>();
        for(int i=0;i<relations.size();i++){
            for(int a=0;a<relations.get(i).getEntities().size();a++){
                Connector connector= new Connector(relations.get(i),relations.get(i).getFigure().getCenter()
                        ,relations.get(i).getEntities().get(a),new Point((relations.get(i).getEntities().get(a).getFigure().getPosX()),relations.get(i).getEntities().get(a).getFigure().getPosY()),
                " ",false,attributes1);
                connectors.add(connector);
                if((relations.get(i).getType()==FigureType.WEAK) && (relations.get(i).getEntities().get(a).getType()==FigureType.WEAK)){
                    Point pointRelation = relations.get(i).getFigure().getCenter();
                    pointRelation.setY(pointRelation.getY()-7);
                    pointRelation.setX(pointRelation.getX()-7);
                    connector= new Connector(relations.get(i),pointRelation
                            ,relations.get(i).getEntities().get(a),new Point(((relations.get(i).getEntities().get(a).getFigure().getPosX()-7)),(relations.get(i).getEntities().get(a).getFigure().getPosY()-7)),
                    " ",false,attributes1);
                    connectors.add(connector);
                }
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
                Connector connector = new Connector(attributes.get(i),new Point(attributes.get(i).figure.getPosX(),attributes.get(i).figure.getPosY()),
                    attributes.get(i).getAttributes().get(a),new Point(attributes.get(i).getAttributes().get(a).figure.getPosX(),attributes.get(i).getAttributes().get(a).figure.getPosY()),
                " ",false,attributes1);
                connectors.add(connector);
            }
        }
        for (Heritage heritage : heritages) {
            Connector connector = new Connector(heritage.getParentEntity(), heritage, "", false, heritage.attributes, false);
            connectors.add(connector);
            for (Entity daughterEntity : heritage.getDaughtersEntities()) {
                Connector connectorAux = new Connector(heritage, daughterEntity, "", false, heritage.attributes, true);
                connectors.add(connectorAux);
            }
        }
    }
    
    //Agrega un atributo a una relacion o entidad y al diagrama, pero tiene problemas con el click hay que solucionarlo
    public void addAttribute(MouseEvent event, Canvas canvas, boolean showPoints) throws IOException{
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
                    PopAddAttributeController.onlyCompound=true;
                    popAddAttribute();
                    ready = true ;
                    if(!"".equals(nameAttribute)){
                        ArrayList<Attribute> attributes1=new ArrayList<>();
                        attribute.getAttributes().add(new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1));
                        MainController.diagram.getAttributes().add(new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1));
                        nameAttribute="";
                        PopAddAttributeController.onlyCompound=false;
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
                enteredName=entity.getName();
                popEditEntity();
                ready = true;
                if(!"".equals(enteredName)){
                    entities.set(iE, new Entity(newEntity.getName(),newEntity.figure.getPosX(),newEntity.figure.getPosY(),false,newEntity.getType(),newEntity.getAttributes()));
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
                if(!"".equals(enteredNameR)){
                    relations.set(iE, new Relation(newrelation.getName(),newrelation.getEntities().size(),newrelation.getFigure().getPosX(),newrelation.getFigure().getPosY(),false,newrelation.getEntities(),newrelation.getAttributes(),newrelation.getType()));      
                    enteredNameR="";
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
                                this.relations.set(j, new Relation(relation.name,relation.figure.getSides()-1,relation.figure.getPosX(),relation.figure.getPosY(),relation.selected,entitiesCopy,attributesCopy,relation.getType()));
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
        
        for (Attribute attribute : this.attributes) {
            if (attribute.getName().equals(name)){
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
    
    public Element foundElement (MouseEvent event){
        for (Entity entity : this.entities){
            if(entity.figure.isInFigure(event)){
                return entity;
            }
        }
        for (Attribute attribute : this.attributes){
            if(attribute.figure.isInFigure(event)){
                return attribute;
            }
        }
        for (Relation relation : this.relations){
            if(relation.figure.isInFigure(event)){
                return relation;
            }
        }
        return null;
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
    
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute attribute) {
        this.attributes.add(attribute);
    }
    
    public void updateRelations (Canvas canvas,Boolean showPoints){
        for (int i = 0; i <relations.size(); i++) {
            Relation relation = relations.get(i);
            this.relations.set(i,new Relation(relation.getName(),relation.figure.getSides(),relation.figure.getPosX(),relation.figure
            .getPosX(),relation.selected,relation.getEntities(),relation.getAttributes(),relation.getType()));
        }
        paint(canvas, showPoints);
    }
}
