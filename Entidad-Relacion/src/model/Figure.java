package model;

import java.util.ArrayList;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * @author Equipo Rocket
 */
public class Figure {
    private String name;
    private int posX,posY;
    private final int radiusPolygon = 50;
    private int sides;
    private final int diamondDiagonal1 = 50;
    private final int diamondDiagonal2 = 30;
    private final int rectangleWidth = 50;
    private final int rectangleHeight = 30;
    private ArrayList<Point> points;

    /**
     * Constructor para crear polígonos
     * @param name,sides,posX,posY
     */
    public Figure(String name, int sides, int posX, int posY) {
        points = new ArrayList<>();
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.sides = sides;
        createPointsPolygon();
    }
    
    /**
     * Constructor para crear rectangulos
     * @param name,posX,posY
     * @param posX
     * @param posY
     */
    public Figure(String name, int posX, int posY) {
        points = new ArrayList<>();
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        createPointsRectangle();
    }
    
    /**
     * Pinta las lineas en el "canvas" segun los puntos que hayan en "points"
     * @param canvas
     */
    public void paintLines(Canvas canvas, boolean selected){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if( selected ){
            gc.setFill(Color.DARKGREEN);
            gc.setStroke(Color.DARKGREEN);
        }
        else{
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.BLACK);
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(20));
        gc.fillText(name, posX, posY);
        int size = points.size();
        for (int i = 0; i+1 < size; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i+1);
            gc.strokeLine(point1.getPosX(), point1.getPosY(), point2.getPosX(), point2.getPosY());
        }
        Point point1 = points.get(0);
        Point point2 = points.get(size-1);
        gc.strokeLine(point2.getPosX(), point2.getPosY(), point1.getPosX(), point1.getPosY());
    }
    
    /**
     * Método que realiza un circulo en cada punto para resaltarlo
     */
    public void paintPoints(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setLineWidth(6);
        for (Point point : points) {
            gc.strokeArc(point.getPosX(), point.getPosY(), 2, 2,360,300, ArcType.ROUND);
        }
        gc.setLineWidth(1);
    }
    
    /**
     * Método que retorna el punto mínimo presente en "points"
     * @return Point
     */
    public Point minPoint(){
        int minX = points.get(0).getPosX();
        int minY = points.get(0).getPosY();
        for (Point point : points) {
            if(minX > point.getPosX()){
                minX = point.getPosX();
            }
            if(minY > point.getPosY()){
                minY = point.getPosY();
            }
        }
        return (new Point(minX, minY));
    }
    
    /**
     * Método que retorna el punto máximo presente en "points"
     * @return Point
     */
    public Point maxPoint(){
        int maxX = points.get(0).getPosX();
        int maxY = points.get(0).getPosY();
        for (Point point : points) {
            if(maxX < point.getPosX()){
                maxX = point.getPosX();
            }
            if(maxY < point.getPosY()){
                maxY = point.getPosY();
            }
        }
        return (new Point(maxX, maxY));
    }
    
    /**
     * Método que revisa si el punto de "event" se encuentra dentro de la figura
     * @param event
     * @return 
     */
    public boolean isInFigure(MouseEvent event){
        boolean inside = false;
        Point minPoint = minPoint();
        Point maxPoint = maxPoint();
        if( event.getX() > minPoint.getPosX() && event.getY() > minPoint().getPosY() ){
            if( event.getX() < maxPoint.getPosX() && event.getY() < maxPoint().getPosY() ){
                inside = true;
            }
        }
        return inside;   
    }
    
    /**
     * Crea los puntos del poligono y los almacena en "points"
     */
    private void createPointsPolygon(){
        Point point;
        if (sides>0 && sides<3){
            point = new Point ( (int)(posX + diamondDiagonal1), (int)(posY));
            points.add(point);
            point = new Point ( (int)(posX), (int)(posY - diamondDiagonal2));
            points.add(point);
            point = new Point ( (int)(posX - diamondDiagonal1), (int)(posY));
            points.add(point);
            point = new Point ( (int)(posX), (int)(posY + diamondDiagonal2));
            points.add(point);
        }
        else if (sides>2){
            for(int i=0; i<sides; i++){
                point = new Point ( (int)(posX + radiusPolygon * Math.cos(i * 2 * Math.PI / sides)), 
                        (int)(posY - radiusPolygon * Math.sin(i * 2 * Math.PI / sides)));
                points.add(point);
            }
        }
    }
    
    /**
     * Crea los puntos del rectangulo y los almacena en "points"
     */
    private void createPointsRectangle(){
        Point point;
        point = new Point ( (int)(posX + rectangleWidth), (int)(posY + rectangleHeight));
        points.add(point);
        point = new Point ( (int)(posX + rectangleWidth), (int)(posY - rectangleHeight));
        points.add(point);
        point = new Point ( (int)(posX - rectangleWidth), (int)(posY - rectangleHeight));
        points.add(point);
        point = new Point ( (int)(posX - rectangleWidth), (int)(posY + rectangleHeight));
        points.add(point);
    }

    /**
     * @return sides
     */
    public int getSides() {
        return sides;
    }
    
    public void setSides(int sides) {
        this.sides = sides;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }  
//AYLINE MODIFICO AQUI el 20/09
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    
    
}
