package model;

import java.util.ArrayList;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    public void paintLines(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font(20));
        gc.fillText(name, posX, posY);
        int size = points.size();
        for (int i = 0; i+1 < size; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i+1);
            gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
        }
        Point point1 = points.get(0);
        Point point2 = points.get(size-1);
        gc.strokeLine(point2.getX(), point2.getY(), point1.getX(), point1.getY());
    }
    
    /**
     * Método que realiza un circulo en cada punto para resaltarlo
     */
    public void paintPoints(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setLineWidth(6);
        for (Point point : points) {
            gc.strokeArc(point.getX(), point.getY(), 2, 2,360,300, ArcType.ROUND);
        }
        gc.setLineWidth(1);
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
    
}
