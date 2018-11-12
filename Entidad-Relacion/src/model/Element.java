package model;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Equipo Rocket
 */
public abstract class Element {
    
    /**
     *
     */
    public Figure figure;
    protected String name;
    protected boolean selected;
    protected ArrayList<Attribute> attributes = new ArrayList<>();

    /**
     *
     * @param name
     * @param selected
     */
    public Element(String name, boolean selected, ArrayList<Attribute> attributes) {
        this.name = name;
        this.selected = selected;
        this.attributes=(ArrayList<Attribute>) attributes.clone();
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

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    //Metodo que retorna la posicion de un atributo dentro de la lista atributos.(retorna -1 sino lo encuentra).
    public int findAttribute(Attribute attribute){
        for(int i=0;i<this.attributes.size();i++){
            if(this.attributes.get(i).equals(attribute)){
                return i;
            }
        }
        return -1;
    }
    
    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }
    
}
