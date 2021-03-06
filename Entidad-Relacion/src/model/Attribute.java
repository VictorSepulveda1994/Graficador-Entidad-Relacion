/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Attribute extends Element{
    
    /**
     * El tipo de atributo.
     */
    AttributeType type;
    int id;

    /**
     *Constructor para crear atributos 
     * @param tipo
     * @param name
     * @param selected
     * @param posX
     * @param posY
     * @param attributes
     */
    public Attribute(AttributeType tipo, String name, boolean selected,int posX, int posY, ArrayList<Attribute> attributes, int id ) {
        super(name, selected,attributes);
        this.type = tipo;
        this.figure= new Figure(name,30,posX,posY);
        if(tipo.equals(AttributeType.MULTIVALED)){
            figure.addDoubleLineEllipse();
        }
        this.id=id;
    }

    /**
     * Entrega el tipo de atributo
     * @return
     */
    public AttributeType getTipo() {
        return type;
    }

    /**
     * Agrega el tipo de atributo
     * @param tipo
     */
    public void setTipo(AttributeType tipo) {
        this.type = tipo;
    }

    @Override
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    
}
