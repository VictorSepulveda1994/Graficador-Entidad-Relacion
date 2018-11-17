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
    
    public Heritage(String name, int posX, int posY, boolean selected, ArrayList<Attribute> attributes, ArrayList<Entity> entities, HeritageType heritageType) {
        super(name, selected, attributes);
        this.heritageType = heritageType;
        this.parentEntity = entities.get(0);
        this.attributes = this.parentEntity.attributes;
        this.daughtersEntities = new ArrayList<>();
        for (int i = 1; i < entities.size(); i++) {
            Entity entityAux = entities.get(i);
            entityAux.setAttributes( this.parentEntity.getAttributes() );
            this.daughtersEntities.add(entityAux);
        }
        assignName();
        figure = new Figure(this.name, 29, posX, posY);
    }
       
    private void assignName() {
        if(this.heritageType == HeritageType.DISJUNCTION){
            this.name = "d";
        }
        else if(this.heritageType == HeritageType.OVERLAP){
            this.name = "S";
        }
    }

    public HeritageType getHeritageType() {
        return heritageType;
    }

    public void setHeritageType(HeritageType heritageType) {
        this.heritageType = heritageType;
    }

    public Entity getParentEntity() {
        return parentEntity;
    }

    public void setParentEntity(Entity parentEntity) {
        this.parentEntity = parentEntity;
    }

    public ArrayList<Entity> getDaughtersEntities() {
        return daughtersEntities;
    }

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
    
}
