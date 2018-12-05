package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Entity extends Element {

    public FigureType type;
    public ArrayList<Entity> entities;
    
    /**
     *Constructor de entidad
     * @param name
     * @param posX
     * @param posY
     * @param selected
     * @param type
     * @param attributes
     */
    public Entity(String name, int posX, int posY, boolean selected,FigureType type,ArrayList<Attribute> attributes) {
        super(name,selected,attributes);
        this.type=type;
        figure = new Figure(name, posX, posY);
        if(type==FigureType.WEAK){
            figure.addDoubleLineRectangle();
        }
        this.entities= new ArrayList<>();
    }
    
    /**
     *Constructor que crea una entidad igual a otra
     * @param entity
     */
    public Entity(Entity entity){
        super(entity.getName(),false,entity.getAttributes());
        this.type=entity.getType();
        figure = new Figure(entity.getName(),entity.figure.getPosX(),entity.figure.getPosY());
        if(type==FigureType.WEAK){
            figure.addDoubleLineRectangle();
        }
        this.entities=entity.getEntities();
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    
    /**
     *
     * @param entiti
     */
    public void addEntity(Entity entiti) {
        this.entities.add(entiti);
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     *
     * @return
     */
    public FigureType getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(FigureType type) {
        this.type = type;
    }
    
    public boolean haveAttributeParcial(){
        for(int i=0;i<attributes.size();i++){
            if(attributes.get(i).getTipo()== AttributeType.PARTIALKEY){
                return true;
            }
        }
        return false;
    }
            
    
}
