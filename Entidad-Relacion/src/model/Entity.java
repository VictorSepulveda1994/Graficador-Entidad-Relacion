/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Equipo Rocket
 */
public class Entity extends Element {
   
    private final int width;
    private final int height;

    /**
     *
     * @param name
     */
    public Entity(String name) {
        super(name);
        this.width = 100;
        this.height = 50;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    

}
