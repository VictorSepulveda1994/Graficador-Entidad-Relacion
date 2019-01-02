package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Relation extends Element {

    private ArrayList <Entity> entities;

    /**
     * El tipo de la relación, débil o fuerte.
     */
    public FigureType type;
    public Cardinality typeCardinality;
    
    public enum Cardinality {
        ONE_TO_ONE,ONE_TO_MANY,MANY_TO_MANY,MANY_TO_ONE;
    }
    
    /**
     *Constructor de la relación
     * @param name
     * @param sides
     * @param posX
     * @param posY
     * @param selected
     * @param entities
     * @param attributes
     * @param type
     */
    public Relation(String name, int sides, int posX, int posY, boolean selected, ArrayList<Entity> entities,ArrayList<Attribute> attributes, FigureType type, Cardinality typeCardinality) {
        super(name,selected,attributes);
        this.entities = (ArrayList<Entity>) entities.clone();
        this.type=type;
        figure = new Figure(name, sides, posX, posY);
        if(type==FigureType.WEAK){
            figure.addDoubleLinePolygon();
        }
        this.typeCardinality=typeCardinality;
    }
    
    /**
     *Constructor para crear una relacion en base a otra
     * @param relation
     */
    public Relation(Relation relation){
        super(relation.getName(),false,relation.getAttributes());
        this.entities = relation.getEntities();
        this.type=relation.getType();
        figure = new Figure(relation.getName(),relation.figure.getSides(),relation.figure.getPosX(),relation.figure.getPosY());
        if(type==FigureType.WEAK){
            figure.addDoubleLinePolygon();
        }
        this.typeCardinality=relation.typeCardinality;
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

    /**
     *
     * @return
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     *
     * @param entities
     */
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    
    /**
     * Método para saber si la relación contiene la entidad ingresada
     * @param entity
     * @return verdadero si la relacion contiene esa entidad, falso en caso contrario.
     */
    public boolean hasThisEntity (Entity entity){
        for (int i = 0; i < this.entities.size(); i++) {
            if(entity.getName().equals(this.entities.get(i).getName())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método para eliminar la entidad ingresada.
     * @param entity la entidad a eliminar
     */
    public void removeEntity (Entity entity){
        for (int i = 0; i < this.entities.size(); i++) {
            if(entity.equals(this.entities.get(i))){
                this.entities.remove(i);
            }
        }
    }

    public Cardinality getTypeCardinality() {
        return typeCardinality;
    }

    public void setTypeCardinality(Cardinality typeCardinality) {
        this.typeCardinality = typeCardinality;
    }
    
    /**
     *
     * @return
     */
    public FigureType getType() {
        return type;
    }

    /**
     * Devuelve el numero de entidades debiles 
     * @return
     */
    public int numberOfEntitiesWeak(){
        int count=0;
        for (Entity entitie : this.entities) {
            if(entitie.getType()==FigureType.WEAK){
                count++;
            }
        }
        return count;
    }
    
    /***
     * Este metodo se encarga de actualizar el tipo (débil o fuerte) de la relación.
     */
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
    
    public boolean hasAStrongEntity (){
        int strongEntities=0;
        for (int i = 0; i <this.entities.size(); i++) {
            if(this.entities.get(i).type.equals(FigureType.STRONG)){
                strongEntities++;
            }
        }
        return strongEntities>0;
    }
    /**
     *
     * @param type
     */
    public void setType(FigureType type) {
        this.type = type;
    }
}
