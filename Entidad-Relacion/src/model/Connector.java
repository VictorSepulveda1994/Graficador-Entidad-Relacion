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
public class Connector extends Element{
    
    private Element element1;
    private Point pointElement1;
    private Element element2;
    private Point pointElement2;
    public  String cardinalityLetter;
    private boolean withArc;
    private boolean doble;
    
    /**
     *Constructor para crear un conector
     * @param element1
     * @param pointElement1
     * @param element2
     * @param pointElement2
     * @param name
     * @param selected
     * @param attributes
     */
    public Connector(Element element1, Point pointElement1, Element element2, Point pointElement2, String name, boolean selected,ArrayList<Attribute> attributes) {
        super(name, selected,attributes);
        this.element1 = element1;
        this.pointElement1 = pointElement1;
        this.element2 = element2;
        this.pointElement2 = pointElement2;
        this.withArc = false;
        figure = new Figure(this.pointElement1,this.pointElement2,this.withArc);
        String nameE1 = element1.getClass().getName().substring(6);
        String nameE2 = element2.getClass().getName().substring(6);
        if("Relation".equals(nameE1) && "Entity".equals(nameE2)){
            Relation rAux = (Relation) element1;
            if(rAux.getEntities().size()==1){
                figure.addLineConnector(pointElement1, pointElement2, this.element2);
            }
        }       
    }

    /**
     *Constructor para crear conectores de herencia
     * @param element1
     * @param element2
     * @param name
     * @param selected
     * @param attributes
     * @param withArc
     */
    public Connector(Element element1, Element element2, String name, boolean selected, ArrayList<Attribute> attributes, boolean withArc) {
        super(name, selected, attributes);
        this.element1 = element1;
        this.element2 = element2;
        this.pointElement1 = element1.getFigure().getCenter();
        this.pointElement2 = element2.getFigure().getCenter();
        this.withArc = withArc;
        figure = new Figure(element1.getFigure().getCenter(), element2.getFigure().getCenter(),this.withArc);
    }
    
    public Connector(Element element1, Element element2, String name, boolean selected, ArrayList<Attribute> attributes, boolean withArc,boolean doble) {
        super(name, selected, attributes);
        this.element1 = element1;
        this.element2 = element2;
        this.doble= doble;
        this.pointElement1 = element1.getFigure().getCenter();
        this.pointElement2 = element2.getFigure().getCenter();
        this.withArc = withArc;
        figure = new Figure(element1.getFigure().getCenter(), element2.getFigure().getCenter(),this.withArc,this.doble);
    }

    public boolean isDoble() {
        return doble;
    }

    public void setDoble(boolean doble) {
        this.doble = doble;
    }
   

    /**
     *
     * @return
     */
    public Point getPointElement1() {  
        return pointElement1;
    }

    /**
     *
     * @param pointElement1
     */
    public void setPointElement1(Point pointElement1) {
        this.pointElement1 = pointElement1;
    }

    /**
     *
     * @return
     */
    public Point getPointElement2() {
        return pointElement2;
    }

    /**
     *
     * @param pointElement2
     */
    public void setPointElement2(Point pointElement2) {
        this.pointElement2 = pointElement2;
    }

    public String getCardinalityLetter() {
        return cardinalityLetter;
    }

    public void setCardinalityLetter(String cardinalityLetter) {
        this.cardinalityLetter = cardinalityLetter;
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
