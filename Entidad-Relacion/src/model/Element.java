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
public abstract class Element {
    
    /**
     *
     */
    protected Polygon polygon;

    /**
     *
     */
    protected String name;

    /**
     *
     * @param name
     */
    public Element(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Polygon getPolygon() {
        return polygon;
    }

    /**
     *
     * @param polygon
     */
    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
 
}
