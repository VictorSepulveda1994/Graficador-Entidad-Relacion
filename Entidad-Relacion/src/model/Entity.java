package model;

/**
 *
 * @author Equipo Rocket
 */
public class Entity extends Element {
    
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
