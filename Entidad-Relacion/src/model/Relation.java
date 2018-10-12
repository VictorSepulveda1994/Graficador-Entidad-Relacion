package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Relation extends Element {

    private ArrayList <Entity> entities;
    
    /**
     *
     * @param name
     * @param sides
     * @param posX
     * @param posY
     * @param selected
     */
    public Relation(String name, int sides, int posX, int posY, boolean selected, ArrayList<Entity> entities) {
        super(name,selected);
        this.entities = (ArrayList<Entity>) entities.clone();
        figure = new Figure(name, sides, posX, posY);
    }
    
    /**
     *
     * @param entity
     */
    public void addEntity (Entity entity){
        this.entities.add(entity);
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
}
