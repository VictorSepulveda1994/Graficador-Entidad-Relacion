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
public class Polygon {
    
    private ArrayList <Point> points = new ArrayList <>();
    private int sides;

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }
    
    public void addPoint (Point point){
        this.points.add(point);
    }
    
    public Point getPoint (int index){
        return this.points.get(index);
    }

    public void drawConnnectors (ArrayList <Element> elements){
        
    }
    
    public void drawRectangle (String name){
        
    }
    
    public void drawTriangle (String name){
        
    }
    
    public void drawDiamond (String name){
        
    }
    
    public void drawPentagon (String name){
        
    }
    
    public void drawHexagon (String name){
        
    }
      
}
