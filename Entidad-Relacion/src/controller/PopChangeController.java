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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Diagram;
import static model.Diagram.selectedElement;
import model.Entity;
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
    private AnchorPane rootOpciones = new AnchorPane();
    @FXML
    private AnchorPane entidadesDisponibles= new AnchorPane();   
    @FXML
    public TextField newName;
    
    public int t=0;
    public static String enteredNameR;
    public static Relation newrelation= new Relation((Relation) selectedElement);
    ArrayList<CheckBox> cbs = new ArrayList<>();
    ArrayList<CheckBox> disponibles = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        newName.setText(enteredNameR);
        actualizarRoot();
        
    }    
    
    public void addToScreen(){
        enteredNameR=newName.textProperty().get();
        if(enteredNameR.isEmpty() || enteredNameR.length()>12){
            alertName();
        }
        else{
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
                        newrelation.getEntities().remove(newrelation.getEntities().get(a));
                        break;
                    }
                }
                
            }       
        }
        actualizarRoot();
    }
    
    @FXML
    public void agregar(){
        for(int i=0;i<disponibles.size();i++){
            if(disponibles.get(i).isSelected()){
                for(int a=0;a<MainController.diagram.getEntities().size();a++) {
                    if(MainController.diagram.getEntities().get(a).getName().equals(disponibles.get(i).getText())){
                        newrelation.getEntities().add(MainController.diagram.getEntities().get(a));  
                        break;
                    }
                }
            }
        }
        actualizarRoot();
    }
     
    public void actualizarRoot(){
        rootOpciones.getChildren().clear();
        entidadesDisponibles.getChildren().clear();
        cbs.clear();
        disponibles.clear();
        int tamaño=0;
        for (int i=0; i<newrelation.getEntities().size();i++) {
            CheckBox cb = new CheckBox(newrelation.getEntities().get(i).getName());
            cb.setLayoutY(tamaño);
            tamaño+=20; 
            cbs.add(cb);
            rootOpciones.getChildren().add(cb);
        }
        tamaño=0;
        for (Entity entitie1 : MainController.diagram.getEntities()) {
            if(!newrelation.getEntities().contains(entitie1)){
                if (!disponibles.contains(entitie1.getName())){
                    CheckBox cb = new CheckBox(entitie1.getName());
                    cb.setLayoutY(tamaño);
                    tamaño+=20; 
                    disponibles.add(cb);
                    entidadesDisponibles.getChildren().add(cb);
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
