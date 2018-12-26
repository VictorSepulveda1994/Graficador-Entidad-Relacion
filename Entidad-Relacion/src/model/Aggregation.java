package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Aggregation extends Entity{
    
    public Aggregation(Entity entity) {
        super(entity);
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
        createFigure();
    }

    private void createFigure() {
        this.figure = new Figure(name, minPoint(), maxPoint());
    }
    
    public Point minPoint(){
        int minX = 0,minY = 0;
        if(this.elements.size() > 0){
            minX = this.elements.get(0).maxPoint().getX();
            minY = this.elements.get(0).maxPoint().getY();
        }
        for (Element element : this.elements) {
            if(minX > element.minPoint().getX()){
                minX = element.minPoint().getX();
            }
            if(minY > element.minPoint().getY()){
                minY = element.minPoint().getY();
            }
        }
        return (new Point(minX, minY));
    }
    
    /**
     * Método que retorna el punto máximo presente en "diagram"
     * @return 
     */
    public Point maxPoint(){
        int maxX = 0,maxY = 0;
        if(this.elements.size() > 0){
            maxX = entities.get(0).maxPoint().getX();
            maxY = entities.get(0).maxPoint().getY();
        }
        for (Element element : this.elements) {
            if(maxX < element.maxPoint().getX()){
                maxX = element.maxPoint().getX();
            }
            if(maxY < element.maxPoint().getY()){
                maxY = element.maxPoint().getY();
            }
        }
        return (new Point(maxX, maxY));
    }
    
}
