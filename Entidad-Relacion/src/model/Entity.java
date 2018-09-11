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
        this.polygon = new Polygon ();
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
    
    public void setPointsPolygon (double x, double y){
        this.polygon.addPoint(new Point(x-(this.width/2),y-(this.height/2)));//Point1
        this.polygon.addPoint(new Point(x+(this.width/2),y-(this.height/2)));//Point2
        this.polygon.addPoint(new Point(x+(this.width/2),y+(this.height/2)));//Point3
        this.polygon.addPoint(new Point(x-(this.width/2),y+(this.height/2)));//Point4  
    }
    
    
}
