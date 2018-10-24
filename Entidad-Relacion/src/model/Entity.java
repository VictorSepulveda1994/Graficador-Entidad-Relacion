package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Entity extends Element {
    
    private ArrayList<Attribute> attributes = new ArrayList<>();

    EntityType type;
    
    /**
     *Constructor de entidad
     * @param name
     * @param posX
     * @param posY
     * @param selected
     * @param type
     */
    public Entity(String name, int posX, int posY, boolean selected,EntityType type) {
        super(name,selected);
        this.type=type;
        figure = new Figure(name, posX, posY);
        if(type==EntityType.WEAK){
            figure.addDoubleLine();
        }
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

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }
    
    
    
}
