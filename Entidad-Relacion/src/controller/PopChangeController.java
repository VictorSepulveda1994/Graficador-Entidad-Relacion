/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static model.Diagram.selectedElement;
import model.Entity;
import model.FigureType;
import model.Relation;
/**
 * FXML Controller class
 *
 * @author Equipo Rocket
 */
public class PopChangeController extends CallPop implements Initializable {
    
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane rootOptions = new AnchorPane();
    @FXML
    private AnchorPane entitiesAvaliables = new AnchorPane();   
    @FXML
    public TextField newName;
    @FXML
    public CheckBox option;
    
    public int t=0;
    public static String enteredNameR;
    public static FigureType type;
    public static Relation newrelation;
    ArrayList<CheckBox> cbs;
    ArrayList<CheckBox> avaliables;
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
    }    
    
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
            ((Stage)root.getScene().getWindow()).close();                  
        }
    }
    
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
    
    @FXML
    public void agregar(){
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
        actualizarRoot();
    }
     
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
