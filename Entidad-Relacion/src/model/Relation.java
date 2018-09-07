/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Milagod
 */
public class Relation extends Element {

    private ArrayList <Entity> entities = new ArrayList <>();

    public Relation(String name) {
        super(name);
    }
    
    public void addEntity (Entity entity){
        this.entities.add(entity);
    }

}
