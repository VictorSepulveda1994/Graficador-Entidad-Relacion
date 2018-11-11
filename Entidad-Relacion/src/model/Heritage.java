package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Heritage extends Element{
    private Entity parentEntity;
    private ArrayList<Entity> daughtersEntities;
    
    public Heritage(String name, boolean selected, ArrayList<Attribute> attributes) {
        super(name, selected, attributes);
    }
    
}
