package model;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Equipo Rocket
 */
public abstract class Element {
    
    public Figure figure;
    protected String name;
    protected boolean selected;
    protected ArrayList<Attribute> attributes = new ArrayList<>();
    public boolean doubleConnector;
    protected ArrayList<Element> elements;

    /**
     *Constructor para crear un elemento
     * @param name
     * @param selected
     * @param attributes
     */
    public Element(String name, boolean selected, ArrayList<Attribute> attributes) {
        this.name = name;
        this.selected = selected;
        this.attributes=(ArrayList<Attribute>) attributes.clone();
        this.doubleConnector=false;
    }
    
    /**
     *Constructor para crear agregaciones
     * 
     */
    public Element(boolean selected, String name, ArrayList<Element> elements) {
        this.name = name;
        this.selected = selected;
        this.elements = elements;
    }
    
    /**
     * Método que llama al objeto "figure" y llama a sus métodos "paintLines" o "paintPoints"
     * @param canvas
     * @param showPoints
     */
    public void paint(Canvas canvas, boolean showPoints){
        figure.paintLines(canvas,selected);
        if(showPoints){
            figure.paintPoints(canvas);
        }
    }
    
    /**
     * Método que se encarga de llamar al metodo paint del atributo tipo derivado.
     * @param canvas
     * @param showPoints
     */
    public void paintDerivateAttribute(Canvas canvas, boolean showPoints){
        figure.paintDottedLines(canvas, selected);
        if(showPoints){
            figure.paintPoints(canvas);
        }
    }
    
     /**
     * Método que se encarga de llamar al metodo paint del atributo tipo clave.
     * @param canvas
     * @param showPoints
     */
    public void paintKeyAttribute(Canvas canvas, boolean showPoints){
        figure.paintUnderlinedText(canvas, selected);
        if(showPoints){
            figure.paintPoints(canvas);
        }
    }
    
    /**
    * Método que se encarga de llamar al metodo paint del atributo tipo clave parcial.
    * @param canvas
    * @param showPoints
    */
    public void paintPartialKeyAttribute(Canvas canvas, boolean showPoints){
        figure.paintDottedText(canvas, selected);
        if(showPoints){
            figure.paintPoints(canvas);
        }
    }
    
    /**
     * Método que retorna el punto mínimo en la figura del elemento
     * @return Point
     */
    public Point minPoint(){
        return figure.minPoint();
    }
    
    /**
     * Método que retorna el punto máximo en la figura del elemento
     * @return Point
     */
    public Point maxPoint(){
        return figure.maxPoint();
    }
    
    /**
     * Método que retorna true si las coordenadas del evento estan dentro de la figura o false como caso contrario
     * @param event
     * @return true o false dependiendo de la ocación
     */
    public boolean isInFigure(MouseEvent event){
        return figure.isInFigure(event);
    }
    
    /**
     *
     * @return figure
     */
    public Figure getFigure() {
        return figure;
    }

    /**
     *
     * @param figure
     */
    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    /**
     *
     * @return name
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

    /**
     *
     * @param selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     *
     * @return
     */
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes
     */
    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * Encuentra un atributo dentro del diagrama y retorna su ubicacion
     * @param attribute
     * @return
     */
    public int findAttribute(Attribute attribute){
        for(int i=0;i<this.attributes.size();i++){
            if(this.attributes.get(i).equals(attribute)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     *
     * @param attribute
     */
    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public boolean isDoubleConnector() {
        return doubleConnector;
    }

    public void setDoubleConnector(boolean doubleConnector) {
        this.doubleConnector = doubleConnector;
    }
    
    
}
