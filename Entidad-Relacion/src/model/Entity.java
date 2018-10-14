package model;

/**
 *
 * @author Equipo Rocket
 */
public class Entity extends Element {
    
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
    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    
    
}
