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

    public Point(int posx, int posy) {
        this.posX = posx;
        this.posY = posy;
    }

    /**
     *
     * @return
     */
    public int getPosX() {
        return posX;
    }

    /**
     *
     * @param x
     */
    public void setPosX(int posx) {
        this.posX = posx;
    }

    /**
     *
     * @return
     */
    public int getPosY() {
        return posY;
    }

    /**
     *
     * @param y
     */
    public void setPosY(int posy) {
        this.posY = posy;
    }
    
    
}
