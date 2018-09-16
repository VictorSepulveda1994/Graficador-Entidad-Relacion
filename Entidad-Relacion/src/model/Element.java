/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.canvas.Canvas;

/**
 *
 * @author Equipo Rocket
 */
public abstract class Element {
    
    /**
     *
     */
    protected Figure figure;
    protected String name;

    /**
     *
     * @param name
     */
    public Element(String name) {
        this.name = name;
    }
    
    public void paint(Canvas canvas){
        figure.paintLines(canvas);
    }
    
    /**
     *
     * @return
     */
    public Figure getFigure() {
        return figure;
    }

    /**
     *
     * @param figure
     */
    public void setPolygon(Figure figure) {
        this.figure = figure;
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
