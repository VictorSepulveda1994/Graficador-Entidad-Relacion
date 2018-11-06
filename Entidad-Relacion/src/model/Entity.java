package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Entity extends Element {
    

    FigureType type;
    
    /**
     *Constructor de entidad
     * @param name
     * @param posX
     * @param posY
     * @param selected
     * @param type
     */
    public Entity(String name, int posX, int posY, boolean selected,FigureType type,ArrayList<Attribute> attributes) {
        super(name,selected,attributes);
        this.type=type;
        figure = new Figure(name, posX, posY);
        if(type==FigureType.WEAK){
            figure.addDoubleLineRectangle();
        }
    }
    public Entity(Entity entity){
        super(entity.getName(),false,entity.getAttributes());
        this.type=entity.getType();
        figure = new Figure(entity.getName(),entity.figure.getPosX(),entity.figure.getPosY());
        if(type==FigureType.WEAK){
            figure.addDoubleLineRectangle();
        }
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public FigureType getType() {
        return type;
    }

    public void setType(FigureType type) {
        this.type = type;
    }
    
    
    
}
