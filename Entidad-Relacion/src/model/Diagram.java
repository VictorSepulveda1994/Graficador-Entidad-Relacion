/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Milagod
 */
public class Diagram {
    
    private ArrayList <Entity> entities = new ArrayList <>();
    private ArrayList <Relation> relations = new ArrayList <>();
    private ArrayList <Connector> connectors = new ArrayList <>();
    
      public void addEntity (Entity entity){
        this.entities.add(entity);
    }
      
    public void addRelation (Relation relation){
        this.relations.add(relation);
    }
        
    public void addConnector (Connector connector){
        this.connectors.add(connector);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Relation> getRelations() {
        return relations;
    }

    public ArrayList<Connector> getConnectors() {
        return connectors;
    }
    
    public void deleteLastEntity(){
        if(this.entities.isEmpty()==false){
            this.entities.remove(this.entities.size()-1);
        }    
    }
    
    public void deleteLastRelation(){
        if(this.relations.isEmpty()==false){
            this.relations.remove(this.relations.size()-1);
        }
    }
    
    public void deleteLastConnector(){
        if(this.connectors.isEmpty()==false){
            this.connectors.remove(this.connectors.size()-1);
        }
    }
    
    public void clearAll(){
        this.entities.clear();
        this.relations.clear();
        this.connectors.clear();
    }
    
}
