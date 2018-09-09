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
public class Polygon {
    
    private ArrayList <Point> points = new ArrayList <>();
    private int sides;

    /**
     *
     * @return
     */
    public int getSides() {
        return sides;
    }

    /**
     *
     * @param sides
     */
    public void setSides(int sides) {
        this.sides = sides;
    }
    
    /**
     *
     * @param point
     */
    public void addPoint (Point point){
        this.points.add(point);
    }
    
    /**
     *
     * @param index
     * @return
     */
    public Point getPoint (int index){
        return this.points.get(index);
    }

    /**
     *
     * @param elements
     */
    public void drawConnnectors (ArrayList <Element> elements){
        
    }
    
    /**
     *
     * @param name
     */
    public void drawRectangle (String name){
        
    }
    
    /**
     *
     * @param name
     */
    public void drawTriangle (String name){
        
    }
    
    /**
     *
     * @param name
     */
    public void drawDiamond (String name){
        
    }
    
    /**
     *
     * @param name
     */
    public void drawPentagon (String name){
        
    }
    
    /**
     *
     * @param name
     */
    public void drawHexagon (String name){
        
    }
      
    
}
