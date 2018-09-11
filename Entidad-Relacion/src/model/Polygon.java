/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
    public void drawRectangle (String name,GraphicsContext gc){
        
        Point pointOne = this.points.get(0);
        Point pointTwo = this.points.get(1);
        Point pointThree = this.points.get(2);
        Point pointFour = this.points.get(3);
        
        gc.setFill(Color.BLACK);
        gc.strokeLine(pointOne.getX(),pointOne.getY(),pointTwo.getX(),pointTwo.getY());
        gc.strokeLine(pointTwo.getX(),pointTwo.getY(),pointThree.getX(),pointThree.getY());
        gc.strokeLine(pointThree.getX(),pointThree.getY(),pointFour.getX(),pointFour.getY());
        gc.strokeLine(pointFour.getX(),pointFour.getY(),pointOne.getX(),pointOne.getY());   
    }
    
    /**
     *
     * @param name
     */
    public void drawDiamond (String name,GraphicsContext gc){
        
        Point pointOne = this.points.get(0);
        Point pointTwo = this.points.get(1);
        Point pointThree = this.points.get(2);
        Point pointFour = this.points.get(3);
        
        gc.setFill(Color.BLACK);
        gc.strokeLine(pointOne.getX(),pointOne.getY(),pointTwo.getX(),pointTwo.getY());
        gc.strokeLine(pointTwo.getX(),pointTwo.getY(),pointThree.getX(),pointThree.getY());
        gc.strokeLine(pointThree.getX(),pointThree.getY(),pointFour.getX(),pointFour.getY());
        gc.strokeLine(pointFour.getX(),pointFour.getY(),pointOne.getX(),pointOne.getY());  
    }
    
    /**
     *
     * @param name
     */
    public void drawTriangle (String name,GraphicsContext gc){
        
        Point pointOne = this.points.get(0);
        Point pointTwo = this.points.get(1);
        Point pointThree = this.points.get(2);
        
        gc.setFill(Color.BLACK);
        gc.strokeLine(pointOne.getX(),pointOne.getY(),pointTwo.getX(),pointTwo.getY());
        gc.strokeLine(pointTwo.getX(),pointTwo.getY(),pointThree.getX(),pointThree.getY());
        gc.strokeLine(pointThree.getX(),pointThree.getY(),pointOne.getX(),pointOne.getY()); 
    }
    
    
    /**
     *
     * @param name
     */
    public void drawPentagon (String name,GraphicsContext gc){
        
        Point pointOne = this.points.get(0);
        Point pointTwo = this.points.get(1);
        Point pointThree = this.points.get(2);
        Point pointFour = this.points.get(3);
        Point pointFive = this.points.get(4);
        
        gc.setFill(Color.BLACK);
        gc.strokeLine(pointOne.getX(),pointOne.getY(),pointTwo.getX(),pointTwo.getY());
        gc.strokeLine(pointTwo.getX(),pointTwo.getY(),pointThree.getX(),pointThree.getY());
        gc.strokeLine(pointThree.getX(),pointThree.getY(),pointFour.getX(),pointFour.getY());
        gc.strokeLine(pointFour.getX(),pointFour.getY(),pointFive.getX(),pointFive.getY());  
        gc.strokeLine(pointFive.getX(),pointFive.getY(),pointOne.getX(),pointOne.getY());
    }
    
    /**
     *
     * @param name
     */
    public void drawHexagon (String name,GraphicsContext gc){
        Point pointOne = this.points.get(0);
        Point pointTwo = this.points.get(1);
        Point pointThree = this.points.get(2);
        Point pointFour = this.points.get(3);
        Point pointFive = this.points.get(4);
        Point pointSix = this.points.get(5);
        
        gc.setFill(Color.BLACK);
        gc.strokeLine(pointOne.getX(),pointOne.getY(),pointTwo.getX(),pointTwo.getY());
        gc.strokeLine(pointTwo.getX(),pointTwo.getY(),pointThree.getX(),pointThree.getY());
        gc.strokeLine(pointThree.getX(),pointThree.getY(),pointFour.getX(),pointFour.getY());
        gc.strokeLine(pointFour.getX(),pointFour.getY(),pointFive.getX(),pointFive.getY());  
        gc.strokeLine(pointFive.getX(),pointFive.getY(),pointSix.getX(),pointSix.getY());
        gc.strokeLine(pointSix.getX(),pointSix.getY(),pointOne.getX(),pointOne.getY());
        
    }
      
    
}
