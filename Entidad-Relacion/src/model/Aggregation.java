package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Aggregation extends Entity{

    public Aggregation(boolean selected, String name, ArrayList<Element> elements) {
        super(selected, name, elements);
        this.figure = new Figure(name, minimumPoint(), maximumPoint());
    }
    
    public ArrayList<Integer> searchElement(Element selectedElement){
        int i = -1;
        ArrayList<Integer> positions = new ArrayList<>();
        for (Element element : this.elements) {
            i++;
            if(selectedElement.getFigure().getPosX() == element.getFigure().getPosX() && selectedElement.getFigure().getPosY() == element.getFigure().getPosY()){
                positions.add(i);
            }
        }
        return positions;
    }
    
    public Point minimumPoint(){
        int minX = 0,minY = 0;
        if(this.elements.size() > 0){
            minX = this.elements.get(0).maxPoint().getX();
            minY = this.elements.get(0).maxPoint().getY();
        }
        for (Element element : this.elements) {
            System.out.println(element.getClass());
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
    public Point maximumPoint(){
        int maxX = 0,maxY = 0;
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
