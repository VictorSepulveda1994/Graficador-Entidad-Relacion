package model;

import java.util.ArrayList;

/**
 *
 * @author Equipo Rocket
 */
public class Aggregation extends Entity {


    public Aggregation(boolean selected, String name, ArrayList<Element> elements) {
        super(selected, name, elements);
        this.figure = new Figure(name, minimumPoint(), maximumPoint());
    }
    
    public Aggregation(Aggregation aggregation){
        super(aggregation.selected,aggregation.name,aggregation.getElements());
        this.figure= new Figure(aggregation.name,aggregation.minimumPoint(),aggregation.maximumPoint());
        
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
            if(minX > element.minPoint().getX()){
                minX = element.minPoint().getX();
            }
            if(minY > element.minPoint().getY()){
                minY = element.minPoint().getY();
            }
        }
        return (new Point(minX, minY));
    }
    
    public boolean hasThisElement (Element element){
        for (Element element1 : this.elements){
            if(compareElement(element,element1)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isTheLastElement (Element element){
        return this.elements.size()==1 && compareElement(element,this.elements.get(0));
    }
    
    //Metodo para comparar, si el elemento 1 es igual al 2.
    public boolean compareElement (Element element1,Element element2){
        if((element1 instanceof Entity && element2 instanceof Entity) || (element1 instanceof Relation && element2 instanceof Relation)){
            if(element1.name.equals(element2.name)){
                return true;
            }   
        }
        else if(element1 instanceof Attribute && element2 instanceof Attribute){
            Attribute attribute1 = (Attribute)element1;
            Attribute attribute2 = (Attribute)element2;
            if((attribute1.id==attribute2.id) && (attribute1.type.equals(attribute2.type)) && (attribute1.figure.getPosX()==attribute2.figure.getPosX()) && (attribute1.figure.getPosY()==attribute2.figure.getPosY()) && (attribute1.name.equals(attribute2.name))){
                return true;
            }    
        }
        else{
            return false;
        }
        return false;
    }
    
    public void deleteElement (Element element){
        for (int i = 0; i <this.elements.size(); i++) {
            if(compareElement(element,this.elements.get(i))){
                this.elements.remove(i);
                break;
            }
        }
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
