package model;

/**
 *
 * @author Equipo Rocket
 */
public class Entity extends Element {
    
    /**
     *
     * @param name,posX,posY
     */
    public Entity(String name, int posX, int posY) {
        super(name);
        figure = new Figure(name, posX, posY);
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    
    
}
