package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Entity extends Element {
    
    private ArrayList<Attribute> attributes = new ArrayList<>();
    /**
     *Constructor de entidad
     * @param name
     * @param posX
     * @param posY
     * @param selected
     */
    public Entity(String name, int posX, int posY, boolean selected) {
        super(name,selected);
        figure = new Figure(name, posX, posY);
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attribute) {
        this.attributes = attribute;
    }
    
    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
    }
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    
    
}
