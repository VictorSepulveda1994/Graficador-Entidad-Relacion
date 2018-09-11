/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Equipo Rocket
 */
public class Diagram {
    
    private ArrayList <Entity> entities = new ArrayList <>();
    private ArrayList <Relation> relations = new ArrayList <>();
    private ArrayList <Connector> connectors = new ArrayList <>();
    
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
     * @return
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     *
     * @return
     */
    public ArrayList<Relation> getRelations() {
        return relations;
    }

    /**
     *
     * @return
     */
    public ArrayList<Connector> getConnectors() {
        return connectors;
    }
    
    /**
     *Remove the last entity entered
     */
    public void deleteLastEntity(){
        if(this.entities.isEmpty()==false){
            this.entities.remove(this.entities.size()-1);
        }    
    }
    
    /**
     *Remove the last relation entered
     */
    public void deleteLastRelation(){
        if(this.relations.isEmpty()==false){
            this.relations.remove(this.relations.size()-1);
        }
    }
    
    /**
     *Remove the last connector entered
     */
    public void deleteLastConnector(){
        if(this.connectors.isEmpty()==false){
            this.connectors.remove(this.connectors.size()-1);
        }
    }
    
    /**
     *Remove everything in the diagram
     */
    public void clearAll(Canvas canvas){
        this.entities.clear();
        this.relations.clear();
        this.connectors.clear();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double width=canvas.getWidth();
        double height=canvas.getHeight();
        gc.clearRect(0,0 , width, height);
        
    }
    
}
