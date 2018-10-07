package model;

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

    /**
     *
     * @param name
     * @param selected
     */
    public Element(String name,boolean selected) {
        this.name = name;
        this.selected = selected;
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
    
}
