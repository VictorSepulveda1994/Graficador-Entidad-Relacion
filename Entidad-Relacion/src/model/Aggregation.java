package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Aggregation extends Element{

    public Aggregation(boolean selected, String name, ArrayList<Element> elements) {
        super(selected, name, elements);
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
        createFigure();
    }
    
    @Override
    public void setName(String name){
        this.name = name;
    }

    private void createFigure() {
        this.figure = new Figure(name, minPoint(), maxPoint());
    }
    
    @Override
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
     * Método que retorna el punto máximo presente en "elements"
     * @return 
     */
    @Override
    public Point maxPoint(){
        int maxX = 0,maxY = 0;
        if(this.elements.size() > 0){
            //System.out.println(entities.size());
            //maxX = entities.get(0).maxPoint().getX();
            //maxY = entities.get(0).maxPoint().getY();
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
