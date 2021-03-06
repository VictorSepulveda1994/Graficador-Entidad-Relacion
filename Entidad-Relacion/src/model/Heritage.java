package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Heritage extends Element{
    private HeritageType heritageType;
    private Entity parentEntity;
    private ArrayList<Entity> daughtersEntities;
    
    /**
     *Constructor para crear una herencia 
     * @param name
     * @param posX
     * @param posY
     * @param selected
     * @param attributes
     * @param entities
     * @param heritageType
     */
    public Heritage(String name, int posX, int posY, boolean selected, ArrayList<Attribute> attributes, ArrayList<Entity> entities, HeritageType heritageType) {
        super(name, selected, attributes);
        this.heritageType = heritageType;
        this.parentEntity = entities.get(0);
        this.attributes = this.parentEntity.attributes;
        this.daughtersEntities = new ArrayList<>(); 
        for (int i = 1; i < entities.size(); i++) {
            Entity entityAux = entities.get(i);
            //entityAux.setAttributes( this.parentEntity.getAttributes() );
            this.daughtersEntities.add(entityAux);
        }
        assignName();
        figure = new Figure(this.name, 29, posX, posY);
    }

    /**
     *
     * Se le asigna el nombre que llevara el circulo de la herencia segun el tipo
     */
    private void assignName() {
        if(this.heritageType == HeritageType.DISJUNCTION){
            this.name = "d";
        }
        else if(this.heritageType == HeritageType.OVERLAP){
            this.name = "S";
        }
    }

    /**
     *
     * @return
     */
    public HeritageType getHeritageType() {
        return heritageType;
    }

    /**
     *
     * @param heritageType
     */
    public void setHeritageType(HeritageType heritageType) {
        this.heritageType = heritageType;
    }

    /**
     *
     * @return
     */
    public Entity getParentEntity() {
        return parentEntity;
    }

    /**
     *
     * @param parentEntity
     */
    public void setParentEntity(Entity parentEntity) {
        this.parentEntity = parentEntity;
    }

    /**
     *
     * @return
     */
    public ArrayList<Entity> getDaughtersEntities() {
        return daughtersEntities;
    }

    /**
     *
     * @param daughtersEntities
     */
    public void setDaughtersEntities(ArrayList<Entity> daughtersEntities) {
        this.daughtersEntities = daughtersEntities;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public boolean hasThisEntity(Entity entity){
        if(this.parentEntity.equals(entity)){
            return true;
        }
        else{
            for (Entity daughtersEntitie : this.daughtersEntities) {
                if(daughtersEntitie.equals(entity)){
                    return true;
                }
            }
        }
        return false;        
    }
    
    /**
     * Este metodo se encarga de eliminar una entidad dentro de la lista de entidades hijas.
     * @param entity La entidad a eliminar.
     */
    public void removeDaughterEntitie (Entity entity){
        for (int i = 0; i <this.daughtersEntities.size(); i++) {   
            if(this.daughtersEntities.get(i).equals(entity)){
                this.daughtersEntities.remove(i);
            }
        }   
    }
}
