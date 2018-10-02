/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Equipo Rocket
 */
public class PopHelpController implements Initializable {
    
    @FXML
    AnchorPane root;
    @FXML
    ComboBox<String> opciones= new ComboBox<String>(); 
    @FXML
    Text title;
    @FXML
    Text information;

    @FXML
    public void decifrarOpciones(){
        if("Agregar entidad".equals(opciones.getValue())){
            title.setText(opciones.getValue());
            information.setText("Para crear una entidad debes presionar el boton del lado izquierdo que "
                    + " tiene forma de rectangulo en la sección ''Figuras'', luego se debe escojer dentro de la pantalla de dibujo donde deseas"
                    + " agregar esta entidad, finalmente le debes dar un nombre a la entidad y se creara.");
        }
        if("Agregar relación".equals(opciones.getValue())){
            title.setText(opciones.getValue());
            information.setText("Para crear una relación debes presionar el boton del lado izquierdo que "
                    + " tiene forma de rombo en la sección ''Figuras'', luego se debe escojer dentro de la pantalla las entidades que deseas "
                    + "relacionar, luego debes presionar dentro de la pantalla en blanco para ubicar la relación"
                    + " ,finalmente le debes dar un nombre a la relación y se creara. \n Nota: Debes tener en cuenta"
                    + "que las relaciones no pueden tener mas de 7 entidades relacionadas.");
        }
        if("Mover elementos".equals(opciones.getValue())){
            title.setText(opciones.getValue());
            information.setText("Para mover elementos dentro de la pantalla, debes presionar el boton para mover"
                    + " que se encuentra al lado izquiedo en la sección ''Acciones'', luego debes presionar y arrastrar dentro"
                    + "de la pantalla a la ubicación donde seas reubicar el elemento.");
        }
        if("Marcar puntos".equals(opciones.getValue())){
            title.setText(opciones.getValue());
            information.setText("Para marcar los puntos donde se marcan los vertices de las figuras y los puntos"
                    + " de las conexiones, debes presionar el boton que se encuentra al lado izquierdo en la sección"
                    + " ''Acciones''. Si deseas desmarcarlas, debes volver a presionar el boton.");
        }
        if("Limpiar la pantalla".equals(opciones.getValue())){
            title.setText(opciones.getValue());
            information.setText("Para limpiar la pantalla debes presionar el boton que se encuentra en"
                    + " la parte superior de la pantalla con el nombre ''Limpiar''.");
        }
        if("Editar elemento".equals(opciones.getValue())){
            title.setText(opciones.getValue());
            information.setText("Para editar una entidad o relación debes presionar el boton que se encuentra en"
                    + " la parte superior de la pantalla con el nombre ''Editar'', al abrir una ventana emergente debes"
                    + " ingresar el nuevo nombre que le deseas dar al elemento.");
        }
        if("Exportar como imagen".equals(opciones.getValue())){
            title.setText(opciones.getValue());
            information.setText("Para descargar lo que estas dibujando como imagen en tu computador, debes presionar "
                    + "el boton que se encuentra en la parte superior de la pantalla con el nombre ''Exportar'', luego debes elegir "
                    + "la ubicación donde deseas guardar.");
        }
        if("Otros problemas".equals(opciones.getValue())){
            title.setText(opciones.getValue());
            information.setText("Si tienes otros problemas debes llamar al Equipo Rocket.");
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        opciones.getItems().addAll("Agregar entidad","Agregar relacion","Mover elementos","Marcar puntos",
                "Limpiar la pantalla","Editar elemento","Exportar como imagen","Otros problemas");
        opciones.setValue("Elige una opción: ");
        opciones.getItems();
        title.setText("Centro de Ayuda");
        information.setText("Bienvenido al Graficador de Entidad-Relación, este graficador te permite dibujar"
                + " de manera interactiva un diagrama entidad-relación, de tal manera que se muestre "
                + "gráficamente. La entidades son rectangulos y los puedes ubicar donde desees dentro de la pantalla"
                + " de dibujo, mientras que las relaciones pueden cambiar dependiendo de la cantidad de entidades"
                + " que estas relacionando.");
        
    }    
    
}
