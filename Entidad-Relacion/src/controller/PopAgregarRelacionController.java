/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alejandra
 */
public class PopAgregarRelacionController implements Initializable {
    @FXML
    private Spinner<Object> spinner = new Spinner<>();
    @FXML
    private AnchorPane root; 

    //private IntegerSpinnerValueFactory numeros= new IntegerSpinnerValueFactory(0, 7, 0);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        spinner.setId("list-spinner");
        List<Object> names = new ArrayList<Object>();
        names.add("1");
        names.add("2");
        names.add("3");
        names.add("4");
        names.add("5");
        names.add("6");
        names.add("7");
        spinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<Object>(FXCollections.observableArrayList(names)));
        spinner.getValueFactory();
    }    
    
}
