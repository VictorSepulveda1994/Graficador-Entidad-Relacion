package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Aggregation extends Entity{
    
    public Aggregation(Entity entity) {
        super(entity);
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }
    
}
