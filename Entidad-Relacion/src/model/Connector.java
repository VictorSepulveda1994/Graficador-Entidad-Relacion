/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Equipo Rocket
 */
public class Connector extends Element{
    
    private Element element1;
    private Point pointElement1;
    private Element element2;
    private Point pointElement2;
    private boolean withArc;
    
    public Connector(Element element1, Point pointElement1, Element element2, Point pointElement2, String name, boolean selected,ArrayList<Attribute> attributes) {
        super(name, selected,attributes);
        this.element1 = element1;
        this.pointElement1 = pointElement1;
        this.element2 = element2;
        this.pointElement2 = pointElement2;
        this.withArc = false;
        figure = new Figure(this.pointElement1,this.pointElement2,this.withArc);
    }

    public Connector(Element element1, Element element2, String name, boolean selected, ArrayList<Attribute> attributes, boolean withArc) {
        super(name, selected, attributes);
        this.element1 = element1;
        this.element2 = element2;
        this.pointElement1 = element1.getFigure().getCenter();
        this.pointElement2 = element2.getFigure().getCenter();
        this.withArc = withArc;
        figure = new Figure(element1.getFigure().getCenter(), element2.getFigure().getCenter(),this.withArc);
    }

    public Point getPointElement1() {
        return pointElement1;
    }

    public void setPointElement1(Point pointElement1) {
        this.pointElement1 = pointElement1;
    }

    public Point getPointElement2() {
        return pointElement2;
    }

    public void setPointElement2(Point pointElement2) {
        this.pointElement2 = pointElement2;
    }

    /**
     *
     * @return
     */
    public Element getElement1() {
        return element1;
    }

    /**
     *
     * @param element1
     */
    public void setElement1(Element element1) {
        this.element1 = element1;
    }

    /**
     *
     * @return
     */
    public Element getElement2() {
        return element2;
    }

    /**
     *
     * @param element2
     */
    public void setElement2(Element element2) {
        this.element2 = element2;
    }

}
