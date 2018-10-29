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
    AttributeType tipo;

    public Attribute(AttributeType tipo, String name, boolean selected,int posX, int posY, ArrayList<Attribute> attributes ) {
        super(name, selected,attributes);
        this.tipo = tipo;
        this.figure= new Figure(name,30,posX,posY);
        if(tipo.equals(AttributeType.MULTIVALUADO)){
            figure.addDoubleLineEllipse();
        }
    }

    public AttributeType getTipo() {
        return tipo;
    }

    public void setTipo(AttributeType tipo) {
        this.tipo = tipo;
    }
    
    
}
