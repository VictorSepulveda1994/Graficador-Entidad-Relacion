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
    protected Figure figure;
    protected String name;

    /**
     *
     * @param name
     */
    public Element(String name) {
        this.name = name;
    }
    
    /**
     * Método que llama al objeto "figure" y llama a sus métodos "paintLines" o "paintPoints"
     */
    public void paint(Canvas canvas , boolean showPoints){
        figure.paintLines(canvas);
        if(showPoints){
            figure.paintPoints(canvas);
        }
    }
    
    /**
     * Método que retorna el punto mínimo en la figura del elemento
     */
    public Point minPoint(){
        return figure.minPoint();
    }
    
    /**
     * Método que retorna el punto máximo en la figura del elemento
     */
    public Point maxPoint(){
        return figure.maxPoint();
    }
    
    public boolean isInFigure(MouseEvent event){
        return figure.isInFigure(event);
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
    public void setFigure(Figure figure) {
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
