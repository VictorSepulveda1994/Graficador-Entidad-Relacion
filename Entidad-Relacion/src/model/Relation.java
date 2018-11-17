package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Relation extends Element {

    private ArrayList <Entity> entities;
    public FigureType type;
    
    /**
     *Constructor de la relación
     * @param name
     * @param sides
     * @param posX
     * @param posY
     * @param selected
     */
    public Relation(String name, int sides, int posX, int posY, boolean selected, ArrayList<Entity> entities,ArrayList<Attribute> attributes, FigureType type) {
        super(name,selected,attributes);
        this.entities = (ArrayList<Entity>) entities.clone();
        if((numberOfEntitiesWeak()==1 && this.entities.size()>1) || numberOfEntitiesWeak()==0){
            this.type=type;
        }
        else{
            this.type=FigureType.WEAK;
        }
        figure = new Figure(name, sides, posX, posY);
        if(type==FigureType.WEAK){
            figure.addDoubleLinePolygon();
        }

    }
    
    public Relation(Relation relation){
        super(relation.getName(),false,relation.getAttributes());
        this.entities = relation.getEntities();
        if((numberOfEntitiesWeak()==1 && this.entities.size()>1) || numberOfEntitiesWeak()==0){
            this.type=relation.getType();
        }
        else{
            this.type=FigureType.WEAK;
        }
        figure = new Figure(relation.getName(),relation.figure.getSides(),relation.figure.getPosX(),relation.figure.getPosY());
        if(type==FigureType.WEAK){
            figure.addDoubleLinePolygon();
        }
    }
    
    /**
     *
     * @param entity
     */
    public void addEntity (Entity entity){
        this.entities.add(entity);
    }
    
    public void setDoubleLines(){
        this.figure.addDoubleLinePolygon();
    }
        
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    
    /**
     * Método para saber si la relación contiene la entidad ingresada
     * @param entity
     */
    public boolean hasThisEntity (Entity entity){
        for (int i = 0; i < this.entities.size(); i++) {
            if(entity.equals(this.entities.get(i))){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método para eliminar la entidad ingresada
     * @param la entidad a eliminar
     */
    public void removeEntity (Entity entity){
        for (int i = 0; i < this.entities.size(); i++) {
            if(entity.equals(this.entities.get(i))){
                this.entities.remove(i);
            }
        }
    }

    public FigureType getType() {
        return type;
    }

    public int numberOfEntitiesWeak(){
        int count=0;
        for (Entity entitie : this.entities) {
            if(entitie.getType()==FigureType.WEAK){
                count++;
            }
        }
        return count;
    }
    
    public void updateType (){
        if((numberOfEntitiesWeak()==1 && this.entities.size()>1) || numberOfEntitiesWeak()==0){
            this.type=type;
        }
        else{
            this.type=FigureType.WEAK;
        }
        if(type==FigureType.WEAK){
            figure.addDoubleLinePolygon();
        }
    }
    
    public void setType(FigureType type) {
        this.type = type;
    }
    
}
