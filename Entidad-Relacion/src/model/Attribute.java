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
    
    AttributeType type;

    /**
     *Constructor para crear atributos 
     * @param tipo
     * @param name
     * @param selected
     * @param posX
     * @param posY
     * @param attributes
     */
    public Attribute(AttributeType tipo, String name, boolean selected,int posX, int posY, ArrayList<Attribute> attributes ) {
        super(name, selected,attributes);
        this.type = tipo;
        this.figure= new Figure(name,30,posX,posY);
        if(tipo.equals(AttributeType.MULTIVALED)){
            figure.addDoubleLineEllipse();
        }
    }

    /**
     *Entrega el tipo de atributo
     * @return
     */
    public AttributeType getTipo() {
        return type;
    }

    /**
     *Agrega el tipo de atributo
     * @param tipo
     */
    public void setTipo(AttributeType tipo) {
        this.type = tipo;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    
}
