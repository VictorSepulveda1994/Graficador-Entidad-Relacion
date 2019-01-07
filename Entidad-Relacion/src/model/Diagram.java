package model;

import controller.CallPop;
import controller.MainController;
import controller.PopAddAttributeController;
import static controller.PopAddAttributeController.attributeType;
import static controller.PopAddAttributeController.nameAttribute;
import static controller.PopEditRelationController.enteredNameR;
import static controller.PopEditRelationController.newrelation;
import static controller.PopEditAttributeController.enteredNameA;
import static controller.PopEditConnectorController.isDoubleConnector;
import static controller.PopEditConnectorController.newConnector;
import static controller.PopEditEntityController.enteredName;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import static controller.PopEditEntityController.newEntity;
import static controller.PopEditHeritageController.newHeritage;
import javax.lang.model.util.Elements;

/**
 *
 * @author Equipo Rocket
 */
public class Diagram extends CallPop implements Cloneable {
    private ArrayList <Entity> entities;
    private ArrayList <Aggregation> aggregations;
    private ArrayList <Relation> relations;
    private ArrayList <Connector> connectors;
    private ArrayList <Connector> connectorsRelations;
    private ArrayList <Attribute> attributes;
    public ArrayList <Heritage> heritages;
    ArrayList <Element> elementsDelete = new ArrayList <>();

    /**
     *Elemento seleccionado
     */
    public static Element selectedElement;

    /**
     *Contador para el nombre automatico
     */
    public static int count;
    public static int countAttribute;
    private int iElement;

    /**
     *Constructor del diagrama
     */
    public Diagram() {
        entities = new ArrayList <>();
        relations = new ArrayList <>();
        connectors = new ArrayList <>();
        connectorsRelations = new ArrayList <>();
        attributes = new ArrayList<>();
        heritages = new ArrayList<>();
        aggregations = new ArrayList<>();
        count=0;
        countAttribute=0;
    }
    
    public Diagram getClone() { 
        try { 
            Diagram diagram= (Diagram) super.clone(); 
            diagram.attributes= (ArrayList<Attribute>) attributes.clone();
            for(int i=0; i<diagram.getAttributes().size();i++){
                diagram.getAttributes().get(i).setAttributes
                    ((ArrayList<Attribute>) attributes.get(i).getAttributes().clone());
            }
            diagram.entities= (ArrayList<Entity>) entities.clone();
            for(int i=0; i<diagram.getEntities().size();i++){
                diagram.getEntities().get(i).setAttributes
                    ((ArrayList<Attribute>) entities.get(i).getAttributes().clone());
                diagram.getEntities().get(i).setEntities
                    ((ArrayList<Entity>) entities.get(i).getEntities().clone());
            }
            diagram.relations= (ArrayList<Relation>) relations.clone();
            for(int i=0; i<diagram.getRelations().size();i++){
                diagram.getRelations().get(i).setAttributes
                    ((ArrayList<Attribute>) relations.get(i).getAttributes().clone());
                diagram.getRelations().get(i).setEntities
                    ((ArrayList<Entity>) relations.get(i).getEntities().clone());
            }
            diagram.heritages= (ArrayList<Heritage>) heritages.clone();
            for(int i=0; i<diagram.getHeritages().size();i++){
                diagram.getHeritages().get(i).setAttributes
                    ((ArrayList<Attribute>) heritages.get(i).getAttributes().clone());
                diagram.getHeritages().get(i).setDaughtersEntities
                    ((ArrayList<Entity>) heritages.get(i).getDaughtersEntities().clone());
            }
            diagram.aggregations= (ArrayList<Aggregation>) aggregations.clone();
            for(int i=0; i<diagram.getAggregations().size();i++){
                diagram.getAggregations().get(i).setElements
                    ((ArrayList<Element>) aggregations.get(i).getElements().clone());
            }
            
            diagram.connectorsRelations= (ArrayList<Connector>) connectorsRelations.clone();
            diagram.connectors= new ArrayList<>();
            diagram.setCount(count);
            diagram.setCountAttribute(countAttribute);
            diagram.selectedElement= selectedElement;
            return diagram;
        } catch (CloneNotSupportedException e) { 
            System.out.println (" Cloning not allowed. " );
            return this; 
        } 
    }
    
    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Diagram.count = count;
    }

    public static int getCountAttribute() {
        return countAttribute;
    }

    public static void setCountAttribute(int countAttribute) {
        Diagram.countAttribute = countAttribute;
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
     * @param aggregation
     */
    public void addAggregation (Aggregation aggregation){
        this.aggregations.add(aggregation);
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

    /**
     *
     * @return
     */
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
        //crea los conectores
        createConnectors();
        //dibuja los conectores
        for (Connector connector : connectors) {
            connector.paint(canvas,showPoints);  
            connector.figure.paintCardinality(canvas, connector.getElement1(), connector.getElement2(),connector.cardinalityLetter); 
        }
        for (Connector connector : connectorsRelations) {
            connector.paint(canvas,showPoints);
        }
        //dibuja las agregaciones
        for (Aggregation aggregation : aggregations) {
            //aggregation.figure.fillAggregation(canvas);
            aggregation.paintAggregation(canvas, showPoints);
        }
        //dibuja las entidades
        for (Entity entity : entities) {
            entity.figure.fillEntity(canvas);
            entity.paint(canvas,showPoints);
        }
        //dibuja las relaciones
        for (Relation relation : relations) {
            relation.figure.fillPolygon(canvas);
            relation.paint(canvas,showPoints);
        }
        //dibuja la herencia
        for (Heritage heritage : heritages) {
            heritage.figure.fillPolygon(canvas);
            heritage.paint(canvas, showPoints);
        }
        //dibuja los atributos correspondientes
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
            connector.figure.paintCardinality(canvas, connector.getElement1(), connector.getElement2(), connector.cardinalityLetter);
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
        for (Attribute attribute : attributes) {
            if(minX > attribute.minPoint().getX()){
                minX = attribute.minPoint().getX();
            }
            if(minY > attribute.minPoint().getY()){
                minY = attribute.minPoint().getY();
            }
        }
        for (Aggregation aggregation : aggregations) {
            if(minX > aggregation.minPoint().getX()){
                minX = aggregation.minPoint().getX();
            }
            if(minY > aggregation.minPoint().getY()){
                minY = aggregation.minPoint().getY();
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
        for (Attribute attribute : attributes) {
            if(maxX < attribute.maxPoint().getX()){
                maxX = attribute.maxPoint().getX();
            }
            if(maxY < attribute.maxPoint().getY()){
                maxY = attribute.maxPoint().getY();
            }
        }
        for (Aggregation aggregation : aggregations) {
            if(maxX < aggregation.maxPoint().getX()){
                maxX = aggregation.maxPoint().getX();
            }
            if(maxY < aggregation.maxPoint().getY()){
                maxY = aggregation.maxPoint().getY();
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
        iE = 0;
        for (Aggregation aggregation : aggregations) {
            if(aggregation.isInFigure(event) && ready == false){
                ready = true;
                selectedElement = aggregation;
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
        boolean modificate = false;
        if( selectedElement != null && event.getX()-70 > 0  && event.getY()-40 > 0){
            String type = selectedElement.getClass().getName().substring(6);
            ArrayList<Point> positions = isInAggregation(selectedElement);
            if(positions.size() > 0){
                modificate = true;
            }   
            if( "Entity".equals(type) ){
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                attributesCopy=(ArrayList<Attribute>) entities.get(iElement).getAttributes().clone();
                entities.set(iElement, new Entity(selectedElement.name, (int)event.getX(), (int) event.getY(), selectedElement.selected,((Entity)selectedElement).getType(),attributesCopy));
                selectedElement = entities.get(iElement);
                for(int i=0;i<connectorsRelations.size();i++){
                    if(connectorsRelations.get(i).getElement2().getName().equals(entities.get(iElement).getName())){
                        connectorsRelations.set(i, new Connector(connectorsRelations.get(i).getElement1(),entities.get(iElement)," ",false, (ArrayList<Attribute>) connectorsRelations.get(i).getAttributes().clone(),false,connectorsRelations.get(i).isDoble()));
                    }
                }
            }
            else if( "Relation".equals(type) ){
                ArrayList<Entity> entitiesCopy= new ArrayList<>();
                entitiesCopy=(ArrayList<Entity>) relations.get(iElement).getEntities().clone();
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                attributesCopy=(ArrayList<Attribute>) relations.get(iElement).getAttributes().clone();
                relations.set(iElement, new Relation(selectedElement.name, selectedElement.figure.getSides(), (int)event.getX(), (int) event.getY(), selectedElement.selected,entitiesCopy,attributesCopy,((Relation)selectedElement).getType(),((Relation)selectedElement).getTypeCardinality()));
                selectedElement = relations.get(iElement);
                for(int i=0;i<connectorsRelations.size();i++){
                    if(connectorsRelations.get(i).getElement1().getName().equals(relations.get(iElement).getName())){
                        connectorsRelations.set(i, new Connector(relations.get(iElement),connectorsRelations.get(i).getElement2()," ",false, (ArrayList<Attribute>) connectorsRelations.get(i).getAttributes().clone(),false,connectorsRelations.get(i).isDoble()));
                    }
                }
            }
            else if( "Attribute".equals(type)){
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                attributesCopy=(ArrayList<Attribute>) attributes.get(iElement).getAttributes().clone();
                attributes.set(iElement, new Attribute(((Attribute)selectedElement).getTipo(),selectedElement.name,selectedElement.selected,(int)event.getX(), (int) event.getY(),attributesCopy, ((Attribute)selectedElement).id));
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
            
            else if("Aggregation".equals(type)){
                Point point=aggregations.get(iElement).maximumPoint();
                Point point2=aggregations.get(iElement).minimumPoint();
                int x=(int) (point.getX()-event.getX());
                int y=(int) (point.getY()-event.getY());
                int x1=(int) (point2.getX()-event.getX());
                int y1=(int) (point2.getY()-event.getY());
                point.setX(point.getX()-x);
                x=point.getX()-x;
                point.setY(point.getY()-y);
                y=point.getY()-y;
                point2.setX(point2.getX()-x1);
                point2.setY(point2.getY()-y1);
                System.out.println("numero de elementos: "+aggregations.get(iElement).getElements().size());
                for(int i=0;i<aggregations.get(iElement).getElements().size();i++){
                    if(aggregations.get(iElement).getElements().get(i).getClass().getName().equals("model.Aggregation")){
                        int ubicacion= searchAggregation((Aggregation)aggregations.get(iElement).getElements().get(i));
                        moverA(x1,y1,ubicacion);
                        System.out.println("ubicacion: "+ubicacion);
                        aggregations.set(ubicacion, new Aggregation((Aggregation) aggregations.get(iElement).getElements().get(i)));
                        System.out.println("i: "+i);
                        aggregations.get(iElement).getElements().set(i, new Aggregation(aggregations.get(ubicacion)));
                    }
                    if(aggregations.get(iElement).getElements().get(i).getClass().getName().equals("model.Entity")){
                        ArrayList<Attribute> attributesCopy= new ArrayList<>();
                        int ubicacion=searchEntity((Entity)aggregations.get(iElement).getElements().get(i));
                        attributesCopy=(ArrayList<Attribute>) entities.get(ubicacion).getAttributes().clone();
                        int x2=(int) (entities.get(ubicacion).getFigure().getCenter().getX()-x1);
                        int y2=(int) (entities.get(ubicacion).getFigure().getCenter().getY()-y1);
                        entities.set(ubicacion, new Entity(aggregations.get(iElement).getElements().get(i).getName(), x2,y2,false,((Entity) aggregations.get(iElement).getElements().get(i)).getType(),attributesCopy));
                        aggregations.get(iElement).getElements().set(i,entities.get(ubicacion));
                        for(int a=0;a<connectorsRelations.size();a++){
                            if(connectorsRelations.get(a).getElement2().getName().equals(entities.get(ubicacion).getName())){
                                connectorsRelations.set(a, new Connector(connectorsRelations.get(a).getElement1(),entities.get(ubicacion)," ",false, (ArrayList<Attribute>) connectorsRelations.get(a).getAttributes().clone(),false,connectorsRelations.get(a).isDoble()));
                            }
                        }
                    }
                    if(aggregations.get(iElement).getElements().get(i).getClass().getName().equals("model.Relation")){
                        int ubicacion=searchRelation((Relation)aggregations.get(iElement).getElements().get(i));
                        ArrayList<Entity> entitiesCopy= new ArrayList<>();
                        entitiesCopy=(ArrayList<Entity>) relations.get(ubicacion).getEntities().clone();
                        ArrayList<Attribute> attributesCopy= new ArrayList<>();
                        attributesCopy=(ArrayList<Attribute>) relations.get(ubicacion).getAttributes().clone();
                        int x2=(int) (relations.get(ubicacion).getFigure().getCenter().getX()-x1);
                        int y2=(int) (relations.get(ubicacion).getFigure().getCenter().getY()-y1);
                        relations.set(ubicacion, new Relation(aggregations.get(iElement).getElements().get(i).name, aggregations.get(iElement).getElements().get(i).figure.getSides(),x2,y2, aggregations.get(iElement).getElements().get(i).selected,entitiesCopy,attributesCopy,((Relation)aggregations.get(iElement).getElements().get(i)).getType(),((Relation)aggregations.get(iElement).getElements().get(i)).getTypeCardinality()));
                        aggregations.get(iElement).getElements().set(i,relations.get(ubicacion));
                        for(int a=0;a<connectorsRelations.size();a++){
                            if(connectorsRelations.get(a).getElement1().getName().equals(relations.get(ubicacion).getName())){
                                connectorsRelations.set(a, new Connector(relations.get(ubicacion),connectorsRelations.get(a).getElement2()," ",false, (ArrayList<Attribute>) connectorsRelations.get(a).getAttributes().clone(),false,connectorsRelations.get(a).isDoble()));
                            }
                        }
                    }
                    
                    if(aggregations.get(iElement).getElements().get(i).getClass().getName().equals("model.Attribute")){
                        ArrayList<Attribute> attributesCopy= new ArrayList<>();
                        int ubicacion=searchAttribute((Attribute)aggregations.get(iElement).getElements().get(i));
                        if(ubicacion>-1){
                            attributesCopy=(ArrayList<Attribute>) attributes.get(ubicacion).getAttributes().clone();
                            int x2=(int) (attributes.get(ubicacion).getFigure().getCenter().getX()-x1);
                            int y2=(int) (attributes.get(ubicacion).getFigure().getCenter().getY()-y1);
                            attributes.set(ubicacion, new Attribute(attributes.get(ubicacion).getTipo(),attributes.get(ubicacion).name,attributes.get(ubicacion).selected,x2,y2,attributesCopy, attributes.get(ubicacion).id));
                            aggregations.get(iElement).getElements().set(i,attributes.get(ubicacion));
                        }  
                    } 
                }
                   
                Aggregation aggregation =  new Aggregation(aggregations.get(iElement));
                System.out.println(aggregation.name);
                System.out.println(aggregation.figure.getPosX());
                System.out.println(aggregation.figure.getPosY());
                
                for(int i=0;i<this.aggregations.size();i++){
                    for(int a=0;a<this.aggregations.get(i).getElements().size();a++){
                        if(this.aggregations.get(i).getElements().get(a).name.equals(aggregation.name)){
                            this.aggregations.get(i).getElements().set(a,new Aggregation(aggregation));
                        }
                    }
                    this.aggregations.set(i, new Aggregation(aggregations.get(i)));
                }
                for (int i = 0; i <this.relations.size(); i++) {
                    for (int j = 0; j <this.relations.get(i).getEntities().size(); j++) {
                        if(this.relations.get(i).getEntities().get(j).name.equals(aggregation.name)){
                            this.relations.get(i).getEntities().set(j,new Aggregation(aggregation));
                        }   
                    }
                }
                aggregations.set(iElement,new Aggregation(aggregation));
                selectedElement=aggregations.get(iElement);
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
           
            
            if(modificate){
                for (Point position : positions) {
                    aggregations.get(position.getX()).getElements().set(position.getY(), selectedElement);
                    Aggregation aggregation = new Aggregation(aggregations.get(position.getX()).selected, aggregations.get(position.getX()).getName(), aggregations.get(position.getX()).getElements());
                    aggregations.set(position.getX(), new Aggregation(aggregation));
                }
            }
        }
        actualizar();
        adjustScreen(canvas, minWidth, minHeight);
        paint(canvas, showPoints);
    }
    
    public void moverA(int x1,int y1,int iElement){
        for(int i=0;i<aggregations.get(iElement).getElements().size();i++){
            if(aggregations.get(iElement).getElements().get(i).getClass().getName().equals("model.Entity")){
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                int ubicacion=searchEntity((Entity)aggregations.get(iElement).getElements().get(i));
                attributesCopy=(ArrayList<Attribute>) entities.get(ubicacion).getAttributes().clone();
                int x2=(int) (entities.get(ubicacion).getFigure().getCenter().getX()-x1);
                int y2=(int) (entities.get(ubicacion).getFigure().getCenter().getY()-y1);
                entities.set(ubicacion, new Entity(aggregations.get(iElement).getElements().get(i).getName(), x2,y2,false,((Entity) aggregations.get(iElement).getElements().get(i)).getType(),attributesCopy));
                aggregations.get(iElement).getElements().set(i,entities.get(ubicacion));
                for(int a=0;a<connectorsRelations.size();a++){
                    if(connectorsRelations.get(a).getElement2().getName().equals(entities.get(ubicacion).getName())){
                        connectorsRelations.set(a, new Connector(connectorsRelations.get(a).getElement1(),entities.get(ubicacion)," ",false, (ArrayList<Attribute>) connectorsRelations.get(a).getAttributes().clone(),false,connectorsRelations.get(a).isDoble()));
                    }
                }
            }
            if(aggregations.get(iElement).getElements().get(i).getClass().getName().equals("model.Relation")){
                int ubicacion=searchRelation((Relation)aggregations.get(iElement).getElements().get(i));
                ArrayList<Entity> entitiesCopy= new ArrayList<>();
                entitiesCopy=(ArrayList<Entity>) relations.get(ubicacion).getEntities().clone();
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                attributesCopy=(ArrayList<Attribute>) relations.get(ubicacion).getAttributes().clone();
                int x2=(int) (relations.get(ubicacion).getFigure().getCenter().getX()-x1);
                int y2=(int) (relations.get(ubicacion).getFigure().getCenter().getY()-y1);
                relations.set(ubicacion, new Relation(aggregations.get(iElement).getElements().get(i).name, aggregations.get(iElement).getElements().get(i).figure.getSides(),x2,y2, aggregations.get(iElement).getElements().get(i).selected,entitiesCopy,attributesCopy,((Relation)aggregations.get(iElement).getElements().get(i)).getType(),((Relation)aggregations.get(iElement).getElements().get(i)).getTypeCardinality()));
                aggregations.get(iElement).getElements().set(i,relations.get(ubicacion));
                for(int a=0;a<connectorsRelations.size();a++){
                    if(connectorsRelations.get(a).getElement1().getName().equals(relations.get(ubicacion).getName())){
                        connectorsRelations.set(a, new Connector(relations.get(ubicacion),connectorsRelations.get(a).getElement2()," ",false, (ArrayList<Attribute>) connectorsRelations.get(a).getAttributes().clone(),false,connectorsRelations.get(a).isDoble()));
                    }
                }
            }

            if(aggregations.get(iElement).getElements().get(i).getClass().getName().equals("model.Attribute")){
                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                int ubicacion=searchAttribute((Attribute)aggregations.get(iElement).getElements().get(i));
                if(ubicacion>-1){
                    attributesCopy=(ArrayList<Attribute>) attributes.get(ubicacion).getAttributes().clone();
                    int x2=(int) (attributes.get(ubicacion).getFigure().getCenter().getX()-x1);
                    int y2=(int) (attributes.get(ubicacion).getFigure().getCenter().getY()-y1);
                    attributes.set(ubicacion, new Attribute(attributes.get(ubicacion).getTipo(),attributes.get(ubicacion).name,attributes.get(ubicacion).selected,x2,y2,attributesCopy, attributes.get(ubicacion).id));
                    aggregations.get(iElement).getElements().set(i,attributes.get(ubicacion));
                }  
            } 
        }
        aggregations.set(iElement, new Aggregation(aggregations.get(iElement)));
        
        for (int i = 0; i <this.relations.size(); i++) {
            for (int j = 0; j <this.relations.get(i).getEntities().size(); j++) {
                if(this.relations.get(i).getEntities().get(j).name.equals(aggregations.get(iElement).name)){
                    this.relations.get(i).getEntities().set(j,new Aggregation(aggregations.get(iElement)));
                }

            }
        }
        actualizar();
    }
        
    public ArrayList<Point> isInAggregation(Element selectedElement){
        int i = -1 , j = -1;
        ArrayList<Point> positions = new ArrayList<>();
        for (Aggregation aggregation : this.aggregations) {
            i++;
            ArrayList<Integer> p = aggregation.searchElement(selectedElement);
            if(p.size() > 0){
                for (Integer integer : p) {
                    Point pos = new Point(i, integer);
                    positions.add(pos);
                }
            }
        }
        return positions;
    }
    
    public void actualizar(){
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
        
        for (int i=0; i<aggregations.size();i++) {
            for (int a=0; a<aggregations.get(i).getElements().size();a++) {
                if(aggregations.get(i).getElements().get(a).getClass().getName().equals("model.Aggregation")){
                    int nElement=searchAggregation((Aggregation) aggregations.get(i).getElements().get(a));
                    if(nElement!=-1){
                        aggregations.get(i).getElements().set(a, new Aggregation(aggregations.get(nElement)));
                    }
                }
                if(aggregations.get(i).getElements().get(a).getClass().getName().equals("model.Entity")){
                    int nElement=searchEntity((Entity) aggregations.get(i).getElements().get(a));
                    if(nElement!=-1){
                        aggregations.get(i).getElements().set(a, entities.get(nElement));
                    }
                }
                if(aggregations.get(i).getElements().get(a).getClass().getName().equals("model.Relation")){
                    int nElement=searchRelation((Relation) aggregations.get(i).getElements().get(a));
                    if(nElement!=-1){
                        aggregations.get(i).getElements().set(a, new Relation(relations.get(nElement)));
                    }
                }
                if(aggregations.get(i).getElements().get(a).getClass().getName().equals("model.Attribute")){
                    int nElement=searchAttribute((Attribute) aggregations.get(i).getElements().get(a));
                    if(nElement!=-1){
                        aggregations.get(i).getElements().set(a, attributes.get(nElement));
                    }
                }
            }
            aggregations.set(i, new Aggregation(aggregations.get(i)));
        }
        
        for(int a =0; a< aggregations.size();a++){
            for (int i = 0; i <this.relations.size(); i++) {
                for (int j = 0; j <this.relations.get(i).getEntities().size(); j++) {
                    if(this.relations.get(i).getEntities().get(j).name.equals(aggregations.get(a).name)){
                        this.relations.get(i).getEntities().set(j,new Aggregation(aggregations.get(a)));
                    }
                }
            }
        }
        for (int i=0; i<connectorsRelations.size();i++) {  
            Connector connector=foundConnector(connectorsRelations.get(i).getElement1(),connectorsRelations.get(i).getElement2());
            if(connector!=null){
                connectorsRelations.set(i, connector);
            }
        }
        connectors.clear();
        createConnectors();
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
    
    public int searchAggregation(Aggregation aggregation){
        for(int i=0; i<aggregations.size();i++){
            if(aggregations.get(i).getName().equals(aggregation.getName())){
                return i;
            }
        }
        return -1;
    }
    
    /**
     *Busca un atributo dentro del Arrays de atributos y devuelve su ubicacion
     * @param attribute
     * @return
     */
    public int searchAttribute(Attribute attribute){
        for(int i=0; i<attributes.size();i++){
            if(attributes.get(i).id == attribute.id){
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
            if( "Entity".equals(type)){
                entities.get(iElement).setSelected(false);
            }
            if( "Relation".equals(type)){
                relations.get(iElement).setSelected(false);
            }
            if( "Attribute".equals(type)){
                attributes.get(iElement).setSelected(false);
            }
            if( "Heritage".equals(type)){
                heritages.get(iElement).setSelected(false);
            }
            if( "Aggregation".equals(type)){
                aggregations.get(iElement).setSelected(false);
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
        this.connectorsRelations.clear();
        this.heritages.clear();
        this.attributes.clear();
        this.aggregations.clear();
        entitiesSelect().clear();
        MainController.entitiesSelect.clear();
        MainController.elementsSelect.clear();
        canvas.setWidth(minWidth);
        canvas.setHeight(minHeight);
    }
    
    /**
     *Devuelve el numero de entidades seleccionadas
     * @return
     */
    public int numberOfEntitiesSelect (){
        int count = 0;
        for (Entity entity : this.entities) {
            if(entity.selected){
                count++;
            }
        }
        for (Aggregation aggregation : this.aggregations) {
            if(aggregation.selected){
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
        for (Entity entity : this.entities) {
            if(entity.selected){
                entitiesAux.add(entity);
            }
        }
        for (Aggregation aggregation : this.aggregations) {
            if(aggregation.selected){
                entitiesAux.add(aggregation);
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
     *Deselecciona todas las entidades seleccionadas
     */
    public void deselectAll (){
        for (Entity entitie : this.entities) {
            entitie.setSelected(false);
        }
        for (Relation relation : this.relations) {
            relation.setSelected(false);
        }
        for (Attribute attribute : this.attributes) {
            attribute.setSelected(false);
        }
        for (Connector connector : this.connectors) {
            connector.setSelected(false);
        }
        for (Heritage heritage : heritages) {
            heritage.setSelected(false);
        }
        for (Aggregation aggregation : this.aggregations) {
            aggregation.setSelected(false);
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
     *Ve si a sido seleccionado algun objeto
     * @return
     */
    public boolean isSomethingSelect (){
        return this.selectedElement!=null;
    }
    
    /**
     *Método que crea los conectores entre relaciones y entidades para dibujar las lineas
     */
    public void createConnectors(){
        Boolean ready1=true;
        Boolean ready2=true;
        ArrayList<Attribute> attributes1=new ArrayList<>();
        for(int i=0;i<relations.size();i++){
            for(int a=0;a<relations.get(i).getEntities().size();a++){            
                Connector connector= new Connector(relations.get(i),relations.get(i).getFigure().getCenter()
                        ,relations.get(i).getEntities().get(a),new Point((relations.get(i).getEntities().get(a).getFigure().getPosX()),relations.get(i).getEntities().get(a).getFigure().getPosY()),
                " ",false,attributes1);
                if(relations.get(i).getEntities().size()==2){
                switch (relations.get(i).typeCardinality) {
                        case MANY_TO_MANY:
                            connector.setCardinalityLetter("N");
                            break;
                        case ONE_TO_ONE:
                            connector.setCardinalityLetter("1");
                            break;
                        case ONE_TO_MANY:
                            if(ready1){
                                connector.setCardinalityLetter("1");
                                ready1=false;
                            }
                            else{
                                connector.setCardinalityLetter("N");
                                ready1=true;
                            }
                            break;
                        case MANY_TO_ONE:
                            if(ready2){
                                connector.setCardinalityLetter("N");
                                ready2=false;
                            }
                            else{
                                connector.setCardinalityLetter("1");
                                ready2=true;
                            }
                            break;
                    }
                }
                else{
                    switch (relations.get(i).typeCardinality) {
                        case MANY_TO_MANY:
                            connector.setCardinalityLetter("N");
                            break;
                        case ONE_TO_ONE:
                            connector.setCardinalityLetter("1");
                            break;
                        case ONE_TO_MANY:
                            connector.setCardinalityLetter("1");
                            break;
                        case MANY_TO_ONE:
                            connector.setCardinalityLetter("N");
                            break;
                    }
                }
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
        for (int i = 0; i <this.connectorsRelations.size(); i++) {
            Connector connector1 = this.connectorsRelations.get(i);
            if(connector1.isDoble() && connector1.isUnitaryRelation()){
                Point point1 = new Point (connector1.getElement1().figure.getCenter().getX()-8,connector1.getElement1().figure.getCenter().getY()-8);
                Point point2 = new Point (connector1.getElement2().figure.getCenter().getX()-8,connector1.getElement2().figure.getCenter().getY()-8);
                ArrayList <Attribute> attributes = (ArrayList<Attribute>) connector1.attributes.clone();
                Connector connector2 = new Connector(connector1.getElement1(),point1,connector1.getElement2(),point2,"",connector1.selected,attributes);
                if(connector1.getElement1() instanceof Entity && connector1.getElement2() instanceof Relation){
                    Relation relation = (Relation)connector1.getElement2();
                    switch (relation.typeCardinality) {
                        case MANY_TO_MANY:
                            connector2.setCardinalityLetter("N");
                            break;
                        case ONE_TO_ONE:
                            connector2.setCardinalityLetter("1");
                            break;
                        case ONE_TO_MANY:
                            connector2.setCardinalityLetter("1");
                            break;
                        case MANY_TO_ONE:
                            connector2.setCardinalityLetter("N");
                            break;
                    }   
                }
                else if(connector1.getElement2() instanceof Entity && connector1.getElement1() instanceof Relation){
                    Relation relation = (Relation)connector1.getElement1();
                    switch (relation.typeCardinality) {
                        case MANY_TO_MANY:
                            connector2.setCardinalityLetter("N");
                            break;
                        case ONE_TO_ONE:
                            connector2.setCardinalityLetter("1");
                            break;
                        case ONE_TO_MANY:
                            connector2.setCardinalityLetter("1");
                            break;
                        case MANY_TO_ONE:
                            connector2.setCardinalityLetter("N");
                            break;
                    }
                }
                connectors.add(connector2);
            } 
        }
    }
    
    public void createConnectorR(Element element1, Element element2){
        ArrayList<Attribute> attributes1=new ArrayList<>();
        Connector conector1=new Connector(element1,element2," ",false,attributes1,false,false);
        connectorsRelations.add(conector1);
    }

    /**
     *Se agrega un atributo a una entidad, relacion o atributo compuesto seleccionado
     * @param event
     * @param canvas
     * @param showPoints
     * @throws IOException
     */
    public void addAttribute(MouseEvent event, Canvas canvas, boolean showPoints) throws IOException{
        boolean ready = false;
        for (Entity entity : entities) {
            if(entity.isInFigure(event) && ready == false){
                popAddAttribute();
                ready = true;
                if(!"".equals(nameAttribute)){
                    ArrayList<Attribute> attributes1=new ArrayList<>();
                    Attribute attribute= new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1,countAttribute);
                    countAttribute++;
                    entity.getAttributes().add(attribute);
                    MainController.diagram.getAttributes().add(attribute);
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
                    Attribute attribute= new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1,countAttribute);
                    countAttribute++;
                    relation.getAttributes().add(attribute);
                    MainController.diagram.getAttributes().add(attribute);
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
                        Attribute attribute1= new Attribute(attributeType,nameAttribute,false,(int)event.getX(),(int)event.getY(),attributes1,countAttribute);
                        countAttribute++;
                        attribute.getAttributes().add(attribute1);
                        MainController.diagram.getAttributes().add(attribute1);
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
        actualizar();
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
                    for(int i=0;i<connectorsRelations.size();i++){
                        if(connectorsRelations.get(i).getElement2().equals(selectedElement)){
                            connectorsRelations.set(i, new Connector(connectorsRelations.get(i).getElement1(),entities.get(iE)," ",false, (ArrayList<Attribute>) connectorsRelations.get(i).getAttributes().clone(),false,connectorsRelations.get(i).isDoble()));
                        }
                    }
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
                    relations.set(iE, new Relation(newrelation.getName(),newrelation.getEntities().size(),newrelation.getFigure().getPosX(),newrelation.getFigure().getPosY(),false,newrelation.getEntities(),newrelation.getAttributes(),newrelation.getType(),newrelation.getTypeCardinality()));      
                    enteredNameR="";
                    int i=0;
                    while(i<connectorsRelations.size()){
                        if(connectorsRelations.get(i).getElement1().equals(selectedElement)){
                            connectorsRelations.remove(i);
                            if(i>0){
                                i--;
                            }
                        }
                        else{
                            i++;
                        }
                    }
                    actualizar();
                    for(int a=0;a<relations.get(iE).getEntities().size();a++){
                        createConnectorR(relations.get(iE),relations.get(iE).getEntities().get(a));
                    }
                }
                break;
            }
            iE++;
        }
        for (Attribute attribute : attributes) {
            if(attribute.isInFigure(event) && ready == false){
                selectedElement=attribute;
                String oldNameA=attribute.getName();
                enteredNameR=attribute.getName();
                popEditElement();
                ready = true ;
                if(!"".equals(enteredNameA)){
                    for (int i = 0; i < relations.size(); i++) {
                        updateNameAttribute(relations.get(i),attribute,oldNameA);
                    }
                    for (int i = 0; i < entities.size(); i++) {
                        updateNameAttribute(entities.get(i),attribute,oldNameA);
                    }
                    for (int i = 0; i <attributes.size(); i++) {
                        if(attributes.get(i).type.equals(AttributeType.COMPOUND) && !attributes.get(i).getAttributes().isEmpty()){
                            updateNameAttribute(attributes.get(i),attribute,oldNameA);
                        }
                    }               
                    attribute.setName(enteredNameA);
                    attribute.figure.setName(enteredNameA);
                    enteredNameA="";
                }
                break;
            }
        }
        iE=0;
        for (Heritage heritage : heritages) {
            if(heritage.isInFigure(event) && ready == false){
                selectedElement=heritage;
                popEditHeritage();
                ready = true ;
                ArrayList<Entity> entitiesCopy= new ArrayList<>();
                newHeritage.getDaughtersEntities().add(0,newHeritage.getParentEntity());
                entitiesCopy=(ArrayList<Entity>) newHeritage.getDaughtersEntities().clone();
                heritages.set(iE,new Heritage(newHeritage.getName(),heritage.figure.getPosX(),heritage.figure.getPosY(),heritage.selected,heritage.getParentEntity().getAttributes(),entitiesCopy,heritage.getHeritageType()));
                break;
            }
            iE++;
        }
        iE=0;
        for (Connector connector : connectorsRelations) {
            if(connector.isInFigure(event) && ready == false){
                selectedElement=connector;
                popEditConnector();
                ready = true ;
                Connector connector1 = new Connector(newConnector.getElement1(),newConnector.getElement2(),newConnector.name,newConnector.selected,newConnector.getAttributes(),false,isDoubleConnector);
                connectorsRelations.set(iE,connector1); 
                break;
            }
            iE++;
        }
        ready = false;
        actualizar();
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
        actualizar();
        boolean ready = false;
        //Eliminar una Relación
        for (int i = 0; i < this.relations.size(); i++) {
            if(this.relations.get(i).isInFigure(event) && ready == false){
                ready = true;
                Relation relation= this.relations.get(i);
                while(!relation.getAttributes().isEmpty()){
                    for (int j = 0; j <relation.getAttributes().size(); j++) {
                        if((relation.getAttributes().get(j).type.equals(AttributeType.COMPOUND)) && (!relation.getAttributes().get(j).attributes.isEmpty())){
                            for (int k = 0; k < relation.getAttributes().get(j).attributes.size(); k++) {
                                deleteAttribute(relation.getAttributes().get(j).attributes.get(k));
                                elementsDelete.add(relation.getAttributes().get(j).attributes.get(k));
                            }
                        } 
                        elementsDelete.add(relation.getAttributes().get(j));
                        deleteAttribute(relation.getAttributes().get(j));
                        relation.getAttributes().remove(j);
                    }
                }
                elementsDelete.add(relation);
                deleteOneConnectorsRelations(this.relations.get(i));
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
                        if((entity.getAttributes().get(j).type.equals(AttributeType.COMPOUND)) && (!entity.getAttributes().get(j).attributes.isEmpty())){
                            for (int k = 0; k < entity.getAttributes().get(j).attributes.size(); k++) {
                                deleteAttribute(entity.getAttributes().get(j).attributes.get(k));
                                elementsDelete.add(entity.getAttributes().get(j).attributes.get(k));
                            }
                        }                 
                        elementsDelete.add(entity.getAttributes().get(j));
                        deleteAttribute(entity.getAttributes().get(j));
                        entity.getAttributes().remove(j);                        
                    }
                }
                while(hasAnyRelation(entity)){
                    for (int j = 0; j <this.relations.size(); j++) {
                        if(this.relations.get(j).hasThisEntity(entity)){
                            Relation relation = this.relations.get(j);
                            if (relation.getEntities().size()<=1){
                                deleteSomeAttributes(this.relations.get(j));
                                this.relations.get(j).getAttributes().clear();
                                elementsDelete.add(this.relations.get(j));
                                this.relations.remove(j);                      
                            }
                            else{
                                relation.removeEntity(entity);
                                ArrayList<Entity> entitiesCopy = new ArrayList<>();
                                entitiesCopy=(ArrayList<Entity>) relation.getEntities().clone();
                                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                                attributesCopy=(ArrayList<Attribute>) relation.getAttributes().clone();
                                this.relations.set(j, new Relation(relation.name,relation.figure.getSides()-1,relation.figure.getPosX(),relation.figure.getPosY(),relation.selected,entitiesCopy,attributesCopy,relation.getType(),relation.typeCardinality));
                            }
                            j=0;
                        }
                    }
                }
                while(isInAHeritage(entity)){
                    for (int j = 0; j <heritages.size(); j++) {
                        if(heritages.get(j).hasThisEntity(entity)){
                            if(heritages.get(j).getParentEntity().equals(entity) || heritages.get(j).getDaughtersEntities().size()<=1){
                                heritages.remove(j);
                            }
                            else{
                                heritages.get(j).removeDaughterEntitie(entity);
                            }
                        }
                    }
                }
                if (!hasAnyRelation(this.entities.get(i))){
                    elementsDelete.add(this.entities.get(i));
                    deleteOneConnectorsRelations(this.entities.get(i));
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
                if((this.attributes.get(i).type.equals(AttributeType.COMPOUND)) && (!this.attributes.get(i).attributes.isEmpty())){
                    for (int k = 0; k <this.attributes.get(i).attributes.size(); k++) {
                        deleteAttribute (this.attributes.get(i).attributes.get(k));
                    }
                    this.attributes.get(i).attributes.clear(); 
                }
                for (int j = 0; j <this.attributes.size(); j++) {
                    int indexAttribute=this.attributes.get(j).findAttribute(this.attributes.get(i));
                    if(indexAttribute!=-1){
                        this.attributes.get(j).attributes.remove(indexAttribute);
                    }
                }
                elementsDelete.add(this.attributes.get(i));
                this.attributes.remove(i);              
            }
        }
        
        //Eliminar Herencia
        for (int i = 0; i <heritages.size(); i++) {
            if(heritages.get(i).isInFigure(event) && ready == false){
                ready = true;
                heritages.remove(i);                          
            }
        }
                
        //Eliminar Agregación
        for (int i = 0; i <aggregations.size(); i++) {
            if(aggregations.get(i).isInFigure(event) && ready == false){
                ready = true;
                Aggregation aggregation = aggregations.get(i);
                while(hasAnyRelation(aggregation)){
                    for (int j = 0; j <this.relations.size(); j++) {
                        if(this.relations.get(j).hasThisEntity(aggregation)){
                            Relation relation = this.relations.get(j);
                            if (relation.getEntities().size()<=1){
                                deleteSomeAttributes(this.relations.get(j));
                                this.relations.get(j).getAttributes().clear();
                                this.relations.remove(j);
                            }
                            else{
                                relation.removeEntity(aggregation);
                                ArrayList<Entity> entitiesCopy = new ArrayList<>();
                                entitiesCopy=(ArrayList<Entity>) relation.getEntities().clone();
                                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                                attributesCopy=(ArrayList<Attribute>) relation.getAttributes().clone();
                                this.relations.set(j, new Relation(relation.name,relation.figure.getSides()-1,relation.figure.getPosX(),relation.figure.getPosY(),relation.selected,entitiesCopy,attributesCopy,relation.getType(),relation.typeCardinality)); 
                            }
                            j=0;
                        }
                    }
                }
                if (!hasAnyRelation(aggregations.get(i))){
                    deleteOneConnectorsRelations(aggregations.get(i));
                    aggregations.remove(i);
                } 
            }
        }
        int sizeAggregations=this.aggregations.size();
        for (int i = 0; i <sizeAggregations; i++) {
            if(this.aggregations.get(i).hasAllThisElements(elementsDelete)){
                Aggregation aggregation = this.aggregations.get(i);
                while(hasAnyRelation(aggregation)){
                    for (int j = 0; j <this.relations.size(); j++) {
                        if(this.relations.get(j).hasThisEntity(aggregation)){
                            Relation relation = this.relations.get(j);
                            if (relation.getEntities().size()<=1){
                                deleteSomeAttributes(this.relations.get(j));
                                this.relations.get(j).getAttributes().clear();
                                this.relations.remove(j);
                            }
                            else{
                                relation.removeEntity(aggregation);
                                ArrayList<Entity> entitiesCopy = new ArrayList<>();
                                entitiesCopy=(ArrayList<Entity>) relation.getEntities().clone();
                                ArrayList<Attribute> attributesCopy= new ArrayList<>();
                                attributesCopy=(ArrayList<Attribute>) relation.getAttributes().clone();
                                this.relations.set(j, new Relation(relation.name,relation.figure.getSides()-1,relation.figure.getPosX(),relation.figure.getPosY(),relation.selected,entitiesCopy,attributesCopy,relation.getType(),relation.typeCardinality)); 
                            }
                            j=0;
                        }
                    }
                }
                if (!hasAnyRelation(aggregations.get(i))){
                    deleteOneConnectorsRelations(aggregations.get(i));
                    aggregations.remove(i);
                } 
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
    

    /**
     *Metodo para eliminar un atributo dentro del diagrama (sin conocer su indice)
     * @param attribute
     */
    public void deleteAttribute (Attribute attribute){
        for (int i = 0; i <this.attributes.size(); i++) {
            if(this.attributes.get(i).equals(attribute)){
                this.attributes.remove(i);
            }
        }
    }

    /**
     *Metodo para eliminar algunos atributos dentro del diagrama de una relacion o entidad.
     * @param element
     */
    public void deleteSomeAttributes (Element element){
        for (int i = 0; i <this.attributes.size(); i++) {
            for (int j = 0; j <element.getAttributes().size(); j++) {
                if(this.attributes.get(i).equals(element.getAttributes().get(j))){
                    if(this.attributes.get(i).type.equals(AttributeType.COMPOUND) && !this.attributes.get(i).attributes.isEmpty()){
                        for (int k = 0; k <this.attributes.get(i).attributes.size(); k++) {
                            deleteAttribute(this.attributes.get(i).attributes.get(k));
                            elementsDelete.add(this.attributes.get(i).attributes.get(k));
                        }
                    }
                    elementsDelete.add(this.attributes.get(i));
                    this.attributes.remove(i);
                }
            }
            
        }
    }
    
    public void deleteOneConnectorsRelations (Element element){
        while(isInConnectorsRelations(element)){
            for (int i = 0; i <this.connectorsRelations.size(); i++) {
                if((element.name.equals(this.connectorsRelations.get(i).getElement1().name)) || (element.name.equals(this.connectorsRelations.get(i).getElement2().name))){
                    this.connectorsRelations.remove(i);
                }
            }
        }
    }
    
    public boolean isInConnectorsRelations (Element element){
        for (int i = 0; i <this.connectorsRelations.size(); i++) {
            if((element.name.equals(this.connectorsRelations.get(i).getElement1().name)) || (element.name.equals(this.connectorsRelations.get(i).getElement2().name))){
                return true;
            }
        }
        return false;
    }
     
    public int foundIndexElement (Element element){
        for (int i = 0; i <this.entities.size();i++) {
            if(this.entities.get(i).getName().equals(element.getName())){
                return i;
            }
        }
        for (int i = 0; i <this.relations.size();i++) {
            if(this.relations.get(i).getName().equals(element.getName())){
                return i;
            }
        }
        for (int i = 0; i <this.attributes.size();i++) {
            if(this.attributes.get(i).id== ((Attribute)element).id){
                return i;
            }
        }
        for (int i = 0; i <this.heritages.size();i++) {
            if(this.heritages.get(i).getName().equals(element.getName())){
                return i;
            }
        }
        for (int i = 0; i <this.connectors.size();i++) {
            if(this.connectors.get(i).getName().equals(element.getName())){
                return i;
            }
        }
        return 0;
    }
    /**
     *
     * @param event
     * @return
     */
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
    
    /**
     *
     * @return
     */
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setConnectors(ArrayList<Connector> connectors) {
        this.connectors = connectors;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void setHeritages(ArrayList<Heritage> heritages) {
        this.heritages = heritages;
    }
    
    public void updateRelations (Canvas canvas,Boolean showPoints){
        for (Relation relation : this.relations) {
            relation.updateType();
        }
        paint(canvas, showPoints);
    }
    
    public void updateAggregation(MouseEvent event){
        int i = 0;
        ArrayList<Integer> positions = new ArrayList<>();
        ArrayList<Element> elements = new ArrayList<>();
        Aggregation ag= new Aggregation(false,"",elements);
        
        for (Aggregation aggregation : aggregations) {
 
            if(aggregation.getFigure().isInFigure(event)){
                positions.add(i);
            }
            i++;
        }
        for (Integer position : positions) {
            ag = aggregations.get(position);
            for (Element element : ag.getElements()) {
                String type = element.getClass().getName().substring(6);
                if("Aggregation".equals(type)){
                    elements.add((Aggregation)element);
                    Aggregation aggregation= (Aggregation) element;
                    if(aggregation.elements!=null){
                        for (Element element1 : aggregation.elements) {
                           //elements.add(element1);
                        }
                    }
                }
                if("Relation".equals(type)){
                    elements.add((Relation) element);
                    Relation relation = (Relation)element;
                    if(relation.elements != null){
                        for (Element element1 : relation.elements) {
                            elements.add(element1);
                        }
                    }
                    if(relation.getAttributes().size() > 0){
                        for (Attribute attribute : relation.getAttributes()) {
                            elements.add((Attribute) attribute);
                            if(attribute.getAttributes().size() > 0){
                                for (Attribute attribute1 : attribute.getAttributes()) {
                                    elements.add((Attribute) attribute1);
                                }
                            }
                        }
                    }
                    for (Entity entity : relation.getEntities()) {
                        elements.add(entity);
                        for (Attribute attribute : entity.getAttributes()) {
                            elements.add(attribute);
                            if(attribute.getAttributes().size() > 0){
                                for (Attribute attribute1 : attribute.getAttributes()) {
                                    elements.add((Attribute) attribute1);
                                }
                            }
                        }
                    }
                }
            }
            ag = new Aggregation(ag.selected, ag.name, elements);
            aggregations.set(position, ag);
        }
        createConnectors();
    }
    
    /**
     * Metodo para saber si una entidadd esta dentro de una herencia en el diagrama.
     * @return verdadero la entidad ingresada esta en una herencia dentro del diagrama o falso en caso contrario.
     */
    public boolean isInAHeritage(Entity entity){
        for (Heritage heritage : heritages) {
            if(heritage.hasThisEntity(entity)){
                return true;                
            }
        }
        return false;
    }
    
    /**
     * Metodo para saber si un elemento esta dentro de una agregacion en el diagrama.
     * @return verdadero el elemento ingresado esta en una agregacion dentro del diagrama o falso en caso contrario.
     */
    public boolean hasAnyAggregation(Element element){
        for (int i = 0; i <this.aggregations.size(); i++) {
            if(this.aggregations.get(i).hasThisElement(element)){
                return true;
            }
        }
        return false;
    }
    
    /***
     * Este metodo se encarga de actualizar el nombre de un attributo dentro de la lista de un elemento del diagrama.
     * @param element El elemento en donde se va a buscar el attributo.
     * @param attribute El attributo con el nuevo nombre.
     * @param oldName  El antiguo nombre que tenia el attributo.
     */
    public void updateNameAttribute(Element element,Attribute attribute,String oldName){
        for (int i = 0; i <element.getAttributes().size(); i++) {
            if((element.getAttributes().get(i).id==attribute.id)){
                element.getAttributes().get(i).setName(attribute.getName());
                element.getAttributes().get(i).figure.setName(attribute.getName());
            }
        } 
    }

    public ArrayList<Aggregation> getAggregations() {
        return aggregations;
    }
    
    public Connector foundConnector (Element element1, Element element2){
        for (int i = 0; i <this.connectorsRelations.size(); i++) {
            Connector connector = this.connectorsRelations.get(i);
            if(connector.getElement1().name.equals(element1.name) && connector.getElement2().name.equals(element2.name)){
                return connector;
            }
            else if(connector.getElement2().name.equals(element1.name) && connector.getElement1().name.equals(element2.name)){
                return connector;
            }
        }
        return null;
    }
    
}
