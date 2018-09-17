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
public class Point {
    
    private int posX;
    private int posY;

    public Point(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    /**
     *
     * @return
     */
    public double getX() {
        return posX;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.posX = x;
    }

    /**
     *
     * @return
     */
    public double getY() {
        return posY;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.posY = y;
    }
    
    
}
