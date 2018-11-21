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
    private boolean disponible;

    /**
     *Constructor para crear un punto
     * @param x
     * @param y
     */
    public Point(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.disponible=true;
    }

    /**
     *
     * @return
     */
    public int getX() {
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
    public int getY() {
        return posY;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.posY = y;
    }

    /**
     *
     * @return
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     *
     * @param disponible
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
}
