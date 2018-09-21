package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Relation extends Element {

    private ArrayList <Entity> entities;
    
    //AYLINE MODIFICO AQUI el 21/09
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
//AYLINE MODIFICO AQUI
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    
}

