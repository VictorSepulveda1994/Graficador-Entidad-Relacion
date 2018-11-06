package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Entity extends Element {
    

    public EntityType type;
    public ArrayList<Entity> entities;
    
    /**
     *Constructor de entidad
     * @param name
     * @param posX
     * @param posY
     * @param selected
     * @param type
     */
    public Entity(String name, int posX, int posY, boolean selected,EntityType type,ArrayList<Attribute> attributes) {
        super(name,selected,attributes);
        this.type=type;
        figure = new Figure(name, posX, posY);
        if(type==EntityType.WEAK){
            figure.addDoubleLineRectangle();
        }
        this.entities= new ArrayList<>();
    }
    public Entity(Entity entity){
        super(entity.getName(),false,entity.getAttributes());
        this.type=entity.getType();
        figure = new Figure(entity.getName(),entity.figure.getPosX(),entity.figure.getPosY());
        if(type==EntityType.WEAK){
            figure.addDoubleLineRectangle();
        }
        this.entities=entity.getEntities();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity entiti) {
        this.entities.add(entiti);
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
