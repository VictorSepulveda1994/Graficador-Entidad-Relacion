/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static model.Diagram.selectedElement;
import model.Entity;
import model.FigureType;
import model.Relation;

/**
 * FXML Controller class
 * Esta clase permite editar una relación.
 * @author Equipo Rocket
 */
public class PopEditRelationController extends CallPop implements Initializable {
    
    /**
     * Panel donde se realizaran las acciones.
     */
    @FXML
    private AnchorPane root;
    
    /**
     * Panel donde se muestran las opciones disponibles.
     */ 
    @FXML
    private AnchorPane rootOptions = new AnchorPane();
    
    /**
     * Panel donde se muestran las entidades disponibles.
     */
    @FXML
    private AnchorPane entitiesAvaliables = new AnchorPane();  
    
    /**
     * Entrada por donde se recibe el nombre de la relación a editar.
     */
    @FXML
    public TextField newName;
    
    /**
     * Casilla donde se elige si la relación es debil o no.
     */
    @FXML
    public CheckBox option;
    
     @FXML
    public ChoiceBox optionsCardinality;
    
    ObservableList <String> typesOfCardinality = FXCollections.observableArrayList("Uno a uno","Uno a muchos","Muchos a muchos","Muchos a uno");
    
    public static Relation.Cardinality typeCardinality;
    /**
     * Donde se guarda el nombre editado de la relación.
     */
    public static String enteredNameR;
    
    /**
     * Donde se guarda el tipo editado de la relación (Debil/Fuerte).
     */
    public static FigureType type;
    
    /**
     * Donde se guarda la nueva relación con los cambios de edición.
     */
    public static Relation newrelation;
    
    /**
     * Lista en donde se guardan los CheckBox a utilizar.
     */
    ArrayList<CheckBox> cbs;
    
    /**
     * Lista en donde se guardan las entidades disponibles.
     */
    ArrayList<CheckBox> avaliables;
    
    /**
     * Lista en donde se guardan los nombres de las entidades disponibles.
     */
    ArrayList<String> names;
         
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbs = new ArrayList<>();
        avaliables = new ArrayList<>();
        newrelation= new Relation((Relation) selectedElement);
        names=new ArrayList<>();
        newName.setText(enteredNameR);
        actualizarRoot();
        type=newrelation.getType();
        if(type.equals(FigureType.WEAK)){
            option.setSelected(true);
        }
        else{
            option.setSelected(false);
        }
        if(newrelation.numberOfEntitiesWeak()==1 && newrelation.getEntities().size()<=1){
            option.setDisable(true);
        }
        optionsCardinality.setItems(typesOfCardinality);
        switch (newrelation.getTypeCardinality()) {
            case MANY_TO_MANY:
                optionsCardinality.setValue(typesOfCardinality.get(2));
                break;
            case ONE_TO_MANY:
                optionsCardinality.setValue(typesOfCardinality.get(1));
                break;
            case ONE_TO_ONE:
                optionsCardinality.setValue(typesOfCardinality.get(0));
                break;
            case MANY_TO_ONE:
                optionsCardinality.setValue(typesOfCardinality.get(3));
                break;
        }
    }    
    
    /**
     * Metodo que se encarga de guardar los cambios de edición.
     */
    public void addToScreen(){
        enteredNameR=newName.textProperty().get();
        if(enteredNameR.isEmpty() || enteredNameR.length()>12){
            alertName();
        }
        else{
            if(option.isSelected()){
                type=FigureType.WEAK;
            }
            else{
                type=FigureType.STRONG;
            }  
            newrelation.setType(type);
            newrelation.setName(enteredNameR);                
        }
        if(optionsCardinality.getValue().equals("Uno a uno")){  
            typeCardinality=Relation.Cardinality.ONE_TO_ONE;
        }
        else if(optionsCardinality.getValue().equals("Uno a muchos")){
            typeCardinality=Relation.Cardinality.ONE_TO_MANY;
        }
        else if(optionsCardinality.getValue().equals("Muchos a muchos")){
            typeCardinality=Relation.Cardinality.MANY_TO_MANY;
        }
        else if(optionsCardinality.getValue().equals("Muchos a uno")){
            typeCardinality=Relation.Cardinality.MANY_TO_ONE;
        }
        newrelation.setTypeCardinality(typeCardinality);
        ((Stage)root.getScene().getWindow()).close(); 
    }
    
    /**
     * Metodo para borrar entidades dentro de la relación.
     */
    @FXML
    public void delete(){
        for(int i=0;i<cbs.size();i++){
            if(cbs.get(i).isSelected()){
                for (int a=0;a<newrelation.getEntities().size();a++) {
                    if(cbs.get(i).getText().equals(newrelation.getEntities().get(a).getName())){
                        if(newrelation.getEntities().size()==1){
                            alertEntitiesEmpty();
                        }
                        else{
                            newrelation.getEntities().remove(newrelation.getEntities().get(a));
                        }
                        break;
                    }
                }
                
            }       
        }
        actualizarRoot();
    }
    
    /**
     * Metodo para agregar entidades dentro de la relación.
     */
    @FXML
    public void agregar(){
        if(newrelation.getEntities().size()<2){
            for(int i=0;i<avaliables.size();i++){
                if(avaliables.get(i).isSelected()){
                    for(int a=0;a<MainController.diagram.getEntities().size();a++) {
                        if(MainController.diagram.getEntities().get(a).getName().equals(avaliables.get(i).getText())){
                            newrelation.getEntities().add(MainController.diagram.getEntities().get(a));  
                            break;
                        }
                    }
                }
            }
        }
        else{
            alertEntities();
        }
        actualizarRoot();
    }
     
    /**
     * Metodo que se encarga de limpiar y actualizar todos los datos para poder editar la relación.
     */
    public void actualizarRoot(){
        rootOptions.getChildren().clear();
        entitiesAvaliables.getChildren().clear();
        cbs.clear();
        avaliables.clear();
        names.clear();
        int tamaño=0;
        for (int i=0; i<newrelation.getEntities().size();i++) {
            CheckBox cb = new CheckBox(newrelation.getEntities().get(i).getName());
            cb.setLayoutY(tamaño);
            tamaño+=20; 
            cbs.add(cb);
            rootOptions.getChildren().add(cb);
        }
        tamaño=0;
        for (Entity entitie1 : MainController.diagram.getEntities()){
            if(!newrelation.getEntities().contains(entitie1)){
                if (!names.contains(entitie1.getName())){
                    CheckBox cb = new CheckBox(entitie1.getName());
                    cb.setLayoutY(tamaño);
                    tamaño+=20;
                    names.add(entitie1.getName());
                    avaliables.add(cb);
                    entitiesAvaliables.getChildren().add(cb);
                }
            }
        }
            
    }
    
    /**
     * Cancela la operación
     */
    public void cancel(){
        ((Stage)root.getScene().getWindow()).close();
    }
    
}
