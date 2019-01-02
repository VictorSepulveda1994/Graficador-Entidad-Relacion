package model;

import java.util.ArrayList;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * @author Equipo Rocket
 */
public class Figure {
    private String name;
    private int posX,posY;
    private Point min,max;
    private int radiusPolygon = 80;
    private int radiusCircle = 15;
    private int sides;
    private int diamondDiagonal1 = 80;
    private int diamondDiagonal2 = 60;
    private int rectangleWidth = 70;
    private int rectangleHeight = 40;
    private int ellipseDiagonal1 = 80;
    private int ellipseDiagonal2 = 40;
    private double startAngle;
    private ArrayList<Point> points;
    private ArrayList<Point> pointsLines;
    private ArrayList<Point> pointsInside;
    private boolean withArc;
    private boolean doble;
    private Point posArc;
    int d = 25;

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
        pointsLines = new ArrayList<>();
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.sides = sides;
        doble=false;
        createPointsPolygon();
    }
    
    /**
     *Constructor para crear rectangulos de entidades
     * @param name
     * @param posX
     * @param posY
     */

    public Figure(String name, int posX, int posY) {
        points = new ArrayList<>();
        pointsInside = new ArrayList<>();
        pointsLines = new ArrayList<>();
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        createPointsRectangle();
    }

    /**
     *Constructor para crear rectangulos de agregaciones
     * @param name
     * @param posX
     * @param posY
     */
    public Figure(String name, Point min, Point max) {
        points = new ArrayList<>();
        this.posX = (min.getX() + max.getX()) / 2;
        this.posY = (min.getY() + max.getY()) / 2;
        this.min = min;
        this.max = max;
        this.name = name;
        createPointsAggregation();
    }
    
    /**
     *Constructor para crear lineas
     * @param point1
     * @param point2
     * @param withArc
     */

    public Figure(Point point1,Point point2,boolean withArc){
        this.points = new ArrayList<>();
        this.pointsInside = new ArrayList<>();
        this.withArc = withArc;
        pointsLines = new ArrayList<>();
        createPointsLine(point1,point2);
    }
    
    public Figure(Point point1,Point point2,boolean withArc,boolean doble,boolean isUnitaryRelation){
        this.points = new ArrayList<>();
        this.pointsInside = new ArrayList<>();
        pointsLines = new ArrayList<>();
        this.doble=doble;
        createPointsLine(point1,point2);
        if(doble && !isUnitaryRelation){
            Point pointOne = new Point(point1.getX()+7,point1.getY()+7);
            Point pointTwo = new Point(point2.getX()+7,point2.getY()+7);
            pointsInside.add(pointOne);
            pointsInside.add(pointTwo);
        }       
    }

    /**
     *Crea los puntos de las lineas para despues marcar los puntos de control
     * @param point1
     * @param point2
     */
    public void createPointsLine(Point point1,Point point2){
        pointsLines.add(point1);
        pointsLines.add(point2);
        points.add(point1);
        points.add(point2);
        createPointsArc(point1, point2);
    }
    
    /**
     * Pinta las lineas en el "canvas" segun los puntos que hayan para el rectangulo de agregación
     * @param canvas
     * @param selected
     */
    public void paintLinesAggregation(Canvas canvas, boolean selected){
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
        gc.setTextBaseline(VPos.BOTTOM);
        gc.setFont(Font.font(20));
        gc.fillText(name, min.getX(), min.getY());
        int size = points.size();
        for (int i = 0; i+1 < size; i++) {
            if(i%2==0){
                Point point1 = points.get(i);
                Point point2 = points.get(i+1);
                gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            }
        }
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
        if(this.withArc){
            addArc(canvas, selected);
        }
    }
    
    public void paintCardinality (Canvas canvas,Element element1,Element element2,String cardinality){
        if(!(element1 instanceof Heritage) && !(element1 instanceof Attribute) && !(element2 instanceof Heritage) && !(element2 instanceof Attribute)){
            ArrayList <Attribute> attributes = new ArrayList <> ();
            Connector connector = new Connector (element1,element1.figure.getCenter(),element2,element2.figure.getCenter()," ",false,attributes);
            Point point = connector.figure.addLineConnector(connector.getPointElement1(),connector.getPointElement2());
            if(element1 instanceof Entity && element2 instanceof Relation){
                Relation relation = (Relation) element2;
                if(relation.getEntities().size()==1){
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    int x = point.getX();
                    int y = point.getY();
                    Figure circule = new Figure(null,29,x,y);
                    circule.fillPolygon(canvas);
                    circule.paintLines(canvas, false);
                    gc.setTextAlign(TextAlignment.CENTER);
                    gc.setTextBaseline(VPos.CENTER);
                    gc.setFont(Font.font("default", FontWeight.LIGHT, 24));
                    gc.setFill(Color.web("#000000"));
                    switch (relation.getTypeCardinality()) {
                        case MANY_TO_ONE:
                            gc.fillText("1",x,y);
                            break;
                        case ONE_TO_MANY:
                            gc.fillText("N",x,y);
                            break;
                        case MANY_TO_MANY:
                            gc.fillText("N",x,y);
                            break;
                        case ONE_TO_ONE:
                            gc.fillText("1",x,y);
                            break;
                    }
                }    
            }
            else if(element2 instanceof Entity && element1 instanceof Relation){
               Relation relation = (Relation) element1;
                if(relation.getEntities().size()==1){
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    int x = point.getX();
                    int y = point.getY();
                    Figure circule = new Figure(null,29,x,y);
                    circule.fillPolygon(canvas);
                    circule.paintLines(canvas, false);
                    gc.setTextAlign(TextAlignment.CENTER);
                    gc.setTextBaseline(VPos.CENTER);
                    gc.setFont(Font.font("default", FontWeight.LIGHT, 24));
                    gc.setFill(Color.web("#000000"));
                    switch (relation.getTypeCardinality()) {
                        case MANY_TO_ONE:
                            gc.fillText("1",x,y);
                            break;
                        case ONE_TO_MANY:
                            gc.fillText("N",x,y);
                            break;
                        case MANY_TO_MANY:
                            gc.fillText("N",x,y);
                            break;
                        case ONE_TO_ONE:
                            gc.fillText("1",x,y);
                            break;
                    }
                } 
            }
            GraphicsContext gc = canvas.getGraphicsContext2D();
            int x = ((element1.figure.getCenter().getX()+element2.figure.getCenter().getX())/2);
            int y = ((element1.figure.getCenter().getY()+element2.figure.getCenter().getY())/2);
            Figure circule = new Figure(null,29,x,y);
            circule.fillPolygon(canvas);
            circule.paintLines(canvas, false);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.setFont(Font.font("default", FontWeight.LIGHT, 24));
            gc.setFill(Color.web("#000000"));
            gc.fillText(cardinality,x,y);
        }
        
    }
        
    /**
     *Dibuja lineas punteadas
     * @param canvas
     * @param selected
     */
    public void paintDottedLines(Canvas canvas, boolean selected){
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
        gc.fillText(name, posX, posY);
        int size = points.size();
        for (int i = 0; i+1 < size; i++) {
            if(i%2==0){
                Point point1 = points.get(i);
                Point point2 = points.get(i+1);
                gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
            }
        }
    }
    
    
    /**
     *Dibuja una linea debajo del texto 
     * @param canvas
     * @param selected
     */
    public void paintUnderlinedText(Canvas canvas, boolean selected){
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
        gc.fillText(name, posX, posY);
        gc.strokeLine(posX-55, posY+12, posX+55, posY+12);
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
     *Dibuja una linea punteada debajo del texto
     * @param canvas
     * @param selected
     */
    public void paintDottedText(Canvas canvas, boolean selected){
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
        gc.fillText(name, posX, posY);
        int posx=posX-55;
        int posy=posY+12;
        for(int i=0;i<12;i++){
            gc.strokeLine(posx, posy, posx+5, posy);
            posx+=10;
            
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
    }
    
    /**
     *Pinta interior de entidades 
     * @param canvas
     */
    public void fillEntity(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(7);
        gc.setStroke(Color.WHITE);
        ArrayList<Point> pts = points;
        int rW = rectangleWidth;
        int rH = rectangleHeight;
        
        while(rectangleWidth >= 0 && rectangleHeight>=0){
                int size = points.size();
                for (int i = 0; i+1 < size; i++) {
                    Point point1 = points.get(i);
                    Point point2 = points.get(i+1);
                    gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
                }
                Point point1 = points.get(0);
                Point point2 = points.get(size-1);
                gc.strokeLine(point2.getX(), point2.getY(), point1.getX(), point1.getY());
                rectangleWidth = rectangleWidth - 5;
                rectangleHeight = rectangleHeight - 5;
                points.clear();
                createPointsRectangle();
            }
            points = pts;
            rectangleWidth = rW;
            rectangleHeight = rH;
            points.clear();
            createPointsRectangle();
    }
    
    /**
     *Pinta interior de agregaciones 
     * @param canvas
     */
    public void fillAggregation(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(7);
        gc.setStroke(Color.WHITE);
        //double aggregationWidth = Math.sqrt( Math.pow(max.getX()-min.getX(), 2) + Math.pow(min.getY()-min.getY(), 2));
        //double aggregationHeight = Math.sqrt( Math.pow(min.getX()-min.getX(), 2) + Math.pow(max.getY()-min.getY(), 2));
        Point minP = new Point(min.getX(), min.getY());
        Point maxP = new Point(max.getX(), max.getY());
        while(minP.getY()-d < maxP.getY()+d){
            gc.strokeLine(minP.getX()-d, minP.getY()-d, maxP.getX()+d, minP.getY()-d);
            minP.setY(minP.getY() + 5);
        }
    }
    
    /**
     *Pinta interior de poligonos
     * @param canvas
     */
    public void fillPolygon(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(7);
        gc.setStroke(Color.WHITE);
        ArrayList<Point> pts = points;
        int rP = radiusPolygon;
        int rC = radiusCircle;
        int dD1 = diamondDiagonal1;
        int dD2 = diamondDiagonal2;
        int eD1 = ellipseDiagonal1;
        int eD2 = ellipseDiagonal2;
        if(sides > 0 && sides < 3){
            while(diamondDiagonal1 >= 0 && diamondDiagonal2>=0){
                int size = points.size();
                for (int i = 0; i+1 < size; i++) {
                    Point point1 = points.get(i);
                    Point point2 = points.get(i+1);
                    gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
                }
                Point point1 = points.get(0);
                Point point2 = points.get(size-1);
                gc.strokeLine(point2.getX(), point2.getY(), point1.getX(), point1.getY());
                diamondDiagonal1 = diamondDiagonal1 - 5;
                diamondDiagonal2 = diamondDiagonal2 - 5;
                points.clear();
                createPointsPolygon();
            }
            points = pts;
            diamondDiagonal1 = dD1;
            diamondDiagonal2 = dD2;
            points.clear();
            createPointsPolygon();
        } else if(sides > 2 && sides < 29){
            while(radiusPolygon >= 0){
                int size = points.size();
                for (int i = 0; i+1 < size; i++) {
                    Point point1 = points.get(i);
                    Point point2 = points.get(i+1);
                    gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
                }
                Point point1 = points.get(0);
                Point point2 = points.get(size-1);
                gc.setLineWidth(5);
                gc.setStroke(Color.WHITE);
                gc.strokeLine(point2.getX(), point2.getY(), point1.getX(), point1.getY());
                radiusPolygon = radiusPolygon - 5;
                points.clear();
                createPointsPolygon();
            }
            points = pts;
            radiusPolygon = rP;
            points.clear();
            createPointsPolygon();
        } else if( sides == 29){
            while(radiusCircle >= 0){
                int size = points.size();
                for (int i = 0; i+1 < size; i++) {
                    Point point1 = points.get(i);
                    Point point2 = points.get(i+1);
                    gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
                }
                Point point1 = points.get(0);
                Point point2 = points.get(size-1);
                gc.setLineWidth(5);
                gc.setStroke(Color.WHITE);
                gc.strokeLine(point2.getX(), point2.getY(), point1.getX(), point1.getY());
                radiusCircle = radiusCircle - 5;
                points.clear();
                createPointsPolygon();
            }
            points = pts;
            radiusCircle = rC;
            points.clear();
            createPointsPolygon();
        } else if(sides >= 30){
            while(ellipseDiagonal1 >= 0 && ellipseDiagonal2>=0){
                int size = points.size();
                for (int i = 0; i+1 < size; i++) {
                    Point point1 = points.get(i);
                    Point point2 = points.get(i+1);
                    gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
                }
                Point point1 = points.get(0);
                Point point2 = points.get(size-1);
                gc.strokeLine(point2.getX(), point2.getY(), point1.getX(), point1.getY());
                ellipseDiagonal1 = ellipseDiagonal1 - 5;
                ellipseDiagonal2 = ellipseDiagonal2 - 5;
                points.clear();
                createPointsPolygon();
            }
            points = pts;
            ellipseDiagonal1 = eD1;
            ellipseDiagonal2 = eD2;
            points.clear();
            createPointsPolygon();
        }
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
        if(withArc){
            gc.setStroke(Color.ORANGERED);
            gc.strokeArc(posArc.getX(), posArc.getY(), 3, 3,360,300, ArcType.ROUND);
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
        if (sides > 0 && sides < 3){
            point = new Point ( (int)(posX + diamondDiagonal1), (int)(posY));
            points.add(point);
            point = new Point ( (int)(posX), (int)(posY - diamondDiagonal2));
            points.add(point);
            point = new Point ( (int)(posX - diamondDiagonal1), (int)(posY));
            points.add(point);
            point = new Point ( (int)(posX), (int)(posY + diamondDiagonal2));
            points.add(point);
        }
        else if (sides > 2 && sides < 29){
            for(int i=0; i<sides; i++){
                point = new Point ( (int)(posX + radiusPolygon * Math.cos(i * 2 * Math.PI / sides)), 
                        (int)(posY - radiusPolygon * Math.sin(i * 2 * Math.PI / sides)));
                points.add(point);
            }
        }
        else if(sides == 29){
            for(int i=0; i<sides; i++){
                point = new Point ( (int)(posX + radiusCircle * Math.cos(i * 2 * Math.PI / sides)), 
                        (int)(posY - radiusCircle * Math.sin(i * 2 * Math.PI / sides)));
                points.add(point);
            }
        }
        else if(sides >= 30){
            for(int i=0; i<sides; i++){
                point = new Point ( (int)(posX + ellipseDiagonal1 * Math.cos(i * 2 * Math.PI / sides)), 
                        (int)(posY - ellipseDiagonal2 * Math.sin(i * 2 * Math.PI / sides)));
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
     * Crea los puntos del rectangulo para hacer doble linea
     */
    public void addDoubleLineRectangle(){
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
    
    /**
     *Crea lineas dobles en los poligonos
     */
    public void addDoubleLinePolygon(){
        Point point;
        if (sides>0 && sides<3){
            point = new Point ( (int)(posX + (diamondDiagonal1-8)), (int)(posY));
            pointsInside.add(point);
            point = new Point ( (int)(posX), (int)(posY - (diamondDiagonal2-8)));
            pointsInside.add(point);
            point = new Point ( (int)(posX - (diamondDiagonal1-8)), (int)(posY));
            pointsInside.add(point);
            point = new Point ( (int)(posX), (int)(posY + (diamondDiagonal2-8)));
            pointsInside.add(point);
        }
        //El triangulo se hace aparte por tema de estética. (necesita mas espacio para la doble linea).
        if(sides==3){
            for(int i=0; i<sides; i++){
                point = new Point ( (int)(posX + (radiusPolygon-10) * Math.cos(i * 2 * Math.PI / sides)), 
                        (int)(posY - (radiusPolygon-10) * Math.sin(i * 2 * Math.PI / sides)));
                pointsInside.add(point);
            }
        }
        else if (sides>3 && sides<20){
            for(int i=0; i<sides; i++){
                point = new Point ( (int)(posX + (radiusPolygon-8) * Math.cos(i * 2 * Math.PI / sides)), 
                        (int)(posY - (radiusPolygon-8) * Math.sin(i * 2 * Math.PI / sides)));
                pointsInside.add(point);
            }
        }
    }
    
    /**
     * Crea los puntos de la elipse para hacer doble linea
     */
    public void addDoubleLineEllipse(){
        Point point;
        for(int i=0; i<sides; i++){
            point = new Point ( (int)(posX + (ellipseDiagonal1-5) * Math.cos(i * 2 * Math.PI / sides)), 
                    (int)(posY - (ellipseDiagonal2-5) * Math.sin(i * 2 * Math.PI / sides)));
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
    
    /**
     *
     * @return
     */
    public Point getCenter(){
        return (new Point(posX, posY));
    }
    
    /**
     *
     * Agrega un conector más para las relaciones unitarias
     */
    public Point addLineConnector(Point point1, Point point2){
        int x = 0;
        int y = 0;
        createPointsArc(point1, point2);
        if(point1.getX() <= point2.getX()){
            x = point2.getX() - rectangleWidth;
        }
        if(point1.getX() > point2.getX()){
            x = point2.getX() + rectangleWidth;
        }
        if(point1.getY() <= point2.getY()){
            y = point2.getY()- rectangleHeight;
        }
        if(point1.getY()> point2.getY()){
            y = point2.getY()+ rectangleHeight;
        }
        if(startAngle>50 && startAngle<70){
            x = point2.getX() + rectangleWidth;
            y = point2.getY()- rectangleHeight;
        }
        if(startAngle>110 && startAngle<130){
            x = point2.getX() - rectangleWidth;
            y = point2.getY()- rectangleHeight;
        }
        if(startAngle>230 && startAngle<250){
            x = point2.getX() - rectangleWidth;
            y = point2.getY()+ rectangleHeight;
        }
        if(startAngle>290 && startAngle<310){
            x = point2.getX() + rectangleWidth;
            y = point2.getY()+ rectangleHeight;
        }
        this.points.add(new Point(x, y));
        return new Point(x, y);
    }
      
    /**
     *
     *Dibuja los arcos en las lineas correspondientes
     */
    private void addArc(Canvas canvas, boolean selected){
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
        gc.strokeArc(this.posArc.getX(), this.posArc.getY(), 30, 30, this.startAngle, 180, ArcType.OPEN);
    }

    /**
     *
     *Crea los puntos de los arcos
     */
    public void createPointsArc(Point point1, Point point2) {
        int x =(( point1.getX() + point2.getX() - 30) / 2);
        int y =(( point1.getY() + point2.getY() - 30) / 2);
        this.posArc = new Point(x, y);
        this.startAngle = (Math.toDegrees(Math.atan2(point2.getX() - point1.getX(), point2.getY() - point1.getY())) ) + 180;
    }

    private void createPointsAggregation() {
        int x = min.getX()-d;
        int y = min.getY()-d;
        while(x < max.getX()+d){
            points.add(new Point(x, y));
            x+=5;
        }
        x = max.getX()+d;
        y = min.getY()-d;
        while(y < max.getY()+d){
            points.add(new Point(x, y));
            y+=5;
        }
        x = max.getX()+d;
        y = max.getY()+d;
        while(x > min.getX()-d){
            points.add(new Point(x, y));
            x-=5;
        }
        x = min.getX()-d;
        y = max.getY()+d;
        while(y > min.getY()-d){
            points.add(new Point(x, y));
            y-=5;
        }
    }
    
    public double getStartAngle() {
        return startAngle;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }  
}
