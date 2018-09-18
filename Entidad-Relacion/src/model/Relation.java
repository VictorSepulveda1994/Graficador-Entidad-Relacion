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
    public Relation(String name, int sides, int posX, int posY, boolean selected) {
        super(name,selected);
        entities = new ArrayList <>();
        figure = new Figure(name, sides, posX, posY);
    }
    
    /**
     *
     * @param entity
     */
    public void addEntity (Entity entity){
        this.entities.add(entity);
    }
    
}
