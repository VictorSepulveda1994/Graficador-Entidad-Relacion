package model;

import java.util.ArrayList;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * @author Equipo Rocket
 */
public class Figure {
    private String name;
    private int posX,posY;
    private int radiusPolygon = 80;
    private int sides;
    private int diamondDiagonal1 = 80;
    private int diamondDiagonal2 = 60;
    private final int rectangleWidth = 70;
    private final int rectangleHeight = 40;
    private ArrayList<Point> points;
    private ArrayList<Point> pointsInside;

    /**
     * Constructor para crear polígonos
     * @param name,sides,posX,posY
     * @param sides
     * @param posX
     * @param posY
     */
    public Figure(String name, int sides, int posX, int posY) {
        points = new ArrayList<>();
        pointsInside = new ArrayList<>();
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.sides = sides;
        createPointsPolygon();
    }
    
    /**
     *Constructor para crear rectangulos
     * @param name
     * @param posX
     * @param posY
     */

    public Figure(String name, int posX, int posY) {
        points = new ArrayList<>();
        pointsInside = new ArrayList<>();
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        createPointsRectangle();
    }
    
    /**
     *Constructor para crear lineas
     * @param point1
     * @param point2
     */
    public Figure(Point point1,Point point2){
        points = new ArrayList<>();
        pointsInside = new ArrayList<>();
        createPointsLine(point1,point2);
    }

    /**
     *Crea los puntos de las lineas para despues marcar los puntos de control
     * @param point1
     * @param point2
     */
    public void createPointsLine(Point point1,Point point2){
        points.add(point1);
        points.add(point2);
    }
    
    /**
     * Pinta las lineas en el "canvas" segun los puntos que hayan en "points"
     * @param canvas
     * @param selected
     */
    public void paintLines(Canvas canvas, boolean selected){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
        if( selected ){
            gc.setFill(Color.BLUE);
            gc.setStroke(Color.BLUE);
        }
        else{
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.BLACK);
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(20));
        if(points.size()==20){
            gc.fillText(name, posX, posY-80);
        }
        else{
            gc.fillText(name, posX, posY);
        }
        int size = points.size();
        for (int i = 0; i+1 < size; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i+1);
            gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
        }
        Point point1 = points.get(0);
        Point point2 = points.get(size-1);
        gc.strokeLine(point2.getX(), point2.getY(), point1.getX(), point1.getY());
        
        if(!pointsInside.isEmpty()){
            size = pointsInside.size();
            for (int i = 0; i+1 < size; i++) {
                point1 = pointsInside.get(i);
                point2 = pointsInside.get(i+1);
                gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            }
            point1 = pointsInside.get(0);
            point2 = pointsInside.get(size-1);
            gc.strokeLine(point2.getX(), point2.getY(), point1.getX(), point1.getY());
        }  
    }
    
    public void paintLinesPunteadas(Canvas canvas, boolean selected){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3);
        if( selected ){
            gc.setFill(Color.BLUE);
            gc.setStroke(Color.BLUE);
        }
        else{
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.BLACK);
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(20));
        gc.fillText(name, posX, posY-80);
        int size = points.size();
        for (int i = 0; i+1 < size; i++) {
            if(i%2==0){
                Point point1 = points.get(i);
                Point point2 = points.get(i+1);
                gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            }
        }
    }
    
    public void pintarAdentroEntidad(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(20);
        gc.setStroke(Color.WHITE);
        int size = points.size();
        Point pInicio=minPoint();
        if(size==4){
            int i=0;
            while(i<7){
                gc.strokeLine(pInicio.getX()+12, pInicio.getY()+12,pInicio.getX()+(rectangleWidth*2)-12,pInicio.getY()+12);
                i++;
                pInicio.setY(pInicio.getY()+10);
            }
        }
    }
    
    public void pintarAdentroPoligono(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(10);
        gc.setStroke(Color.WHITE);
        int j=0;
        int diagonal= diamondDiagonal1;
        int diagonal2= diamondDiagonal2;
        int radio= radiusPolygon;
        while(j<5){
            Figure figure= new Figure(name,sides,posX,posY);
            for (int i = 0; i+1 < figure.points.size(); i++) {
                Point point1 = figure.points.get(i);
                Point point2 = figure.points.get(i+1);
                gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            }
            Point point1 = points.get(0);
            Point point2 = points.get(points.size()-1);
            gc.strokeLine(point2.getX(), point2.getY(), point1.getX(), point1.getY());
            j++;
            diamondDiagonal1-=10;
            diamondDiagonal2-=10;
            radiusPolygon-=10;
            System.out.println(" "+diamondDiagonal1+" "+diamondDiagonal2+" "+radiusPolygon);
        }
        diamondDiagonal1=diagonal;
        diamondDiagonal2=diagonal2;
        radiusPolygon=radio;
  
    }
    /**
     * Método que realiza un circulo en cada punto para resaltarlo
     * @param canvas
     */
    public void paintPoints(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(7);
        for (Point point : points) {
            gc.setStroke(Color.ORANGERED);
            gc.strokeArc(point.getX(), point.getY(), 3, 3,360,300, ArcType.ROUND);
        }
        gc.setStroke(Color.BLACK);
    }
    
    /**
     * Método que retorna el punto mínimo presente en "points"
     * @return Point
     */
    public Point minPoint(){
        int minX = points.get(0).getX();
        int minY = points.get(0).getY();
        for (Point point : points) {
            if(minX > point.getX()){
                minX = point.getX();
            }
            if(minY > point.getY()){
                minY = point.getY();
            }
        }
        return (new Point(minX, minY));
    }
    
    /**
     * Método que retorna el punto máximo presente en "points"
     * @return Point
     */
    public Point maxPoint(){
        int maxX = points.get(0).getX();
        int maxY = points.get(0).getY();
        for (Point point : points) {
            if(maxX < point.getX()){
                maxX = point.getX();
            }
            if(maxY < point.getY()){
                maxY = point.getY();
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
        if( event.getX() > minPoint.getX() && event.getY() > minPoint().getY() ){
            if( event.getX() < maxPoint.getX() && event.getY() < maxPoint().getY() ){
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
        else if (sides>2 && sides<20){
            for(int i=0; i<sides; i++){
                point = new Point ( (int)(posX + radiusPolygon * Math.cos(i * 2 * Math.PI / sides)), 
                        (int)(posY - radiusPolygon * Math.sin(i * 2 * Math.PI / sides)));
                points.add(point);
            }
        }
        else if(sides==20){
            for(int i=0; i<sides; i++){
                point = new Point ( (int)(posX + radiusPolygon * Math.cos(i * 2 * Math.PI / sides)), 
                        (int)(posY - radiusPolygon+40 * Math.sin(i * 2 * Math.PI / sides)));
                points.add(point);
            }
        }
    }
    
    /**
     * Crea los puntos del rectangulo y los almacena en "points"
     */
    public void createPointsRectangle(){
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
     * Crea los puntos del rectangulo para hacer doble linea
     */
    public void addDoubleLine(){
        Point point;
        point = new Point ( (int)(posX + (rectangleWidth-5)), (int)(posY + (rectangleHeight-5)));
        pointsInside.add(point);
        point = new Point ( (int)(posX + (rectangleWidth-5)), (int)(posY - (rectangleHeight-5)));
        pointsInside.add(point);
        point = new Point ( (int)(posX - (rectangleWidth-5)), (int)(posY - (rectangleHeight-5)));
        pointsInside.add(point);
        point = new Point ( (int)(posX - (rectangleWidth-5)), (int)(posY + (rectangleHeight-5)));
        pointsInside.add(point);  
    }
    
    public void addDoubleLinePolygon(){
        Point point;
        for(int i=0; i<sides; i++){
                point = new Point ( (int)(posX + radiusPolygon * Math.cos(i * 2 * Math.PI / sides)-5), 
                        (int)(posY - radiusPolygon+40 * Math.sin(i * 2 * Math.PI / sides)-5));
                pointsInside.add(point);
            }
    }

    /**
     * @return sides
     */
    public int getSides() {
        return sides;
    }
    
    /**
     *
     * @param sides
     */
    public void setSides(int sides) {
        this.sides = sides;
    }

    /**
     *
     * @return
     */
    public ArrayList<Point> getPoints() {
        return points;
    }  
    
    /**
     *
     * @return
     */
    public int getPosX() {
        return posX;
    }

    /**
     *
     * @param posX
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     *
     * @return
     */
    public int getPosY() {
        return posY;
    }

    /**
     *
     * @param posY
     */
    public void setPosY(int posY) {
        this.posY = posY;
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
