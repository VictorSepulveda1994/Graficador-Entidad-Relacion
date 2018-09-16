package model;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Equipo Rocket
 */
public class Diagram {
    private ArrayList <Entity> entities;
    private ArrayList <Relation> relations;
    private ArrayList <Connector> connectors;

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
    public void paint(Canvas canvas){
        for (Entity entity : entities) {
            entity.paint(canvas);
        }
        for (Relation relation : relations) {
            relation.paint(canvas);
        }
        /*
        for (Connector connector : connectors) {
            connector.paint(canvas.getGraphicsContext2D());
        }*/
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
     * Elimina todo el contenido en el diagrama y en el canvas
     */
    public void clearAll(Canvas canvas){
        double width=canvas.getWidth();
        double height=canvas.getHeight();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.entities.clear();
        this.relations.clear();
        this.connectors.clear();
        gc.clearRect(0,0 , width, height);
    }
    
}
