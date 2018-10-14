/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    ComboBox<String> options= new ComboBox<String>(); 
    @FXML
    Text title;
    @FXML
    Text information;

    /**
     *Este método muestra lo que obtiene de las opciones del ComboBox
     */
    @FXML
    public void changeOfOption(){
        if("Agregar entidad".equals(options.getValue())){
            title.setText(options.getValue());
            information.setText("Para crear una entidad debes presionar el bóton del lado izquierdo que "
                    + " tiene forma de rectángulo en la sección ''Elementos'', luego se debe escojer dentro de la pantalla de dibujo donde deseas"
                    + " agregar esta entidad, finalmente le debes dar un nombre a la entidad y se creara.");
        }
        if("Agregar relación".equals(options.getValue())){
            title.setText(options.getValue());
            information.setText("Para crear una relación debes presionar el bóton del lado izquierdo que "
                    + " tiene forma de rombo en la sección ''Elementos'', luego se debe escojer dentro de la pantalla las entidades que deseas "
                    + "relacionar, luego debes presionar dentro de la pantalla en blanco para ubicar la relación"
                    + " ,finalmente le debes dar un nombre a la relación y se creara. \n Nota: Debes tener en cuenta"
                    + "que las relaciones no pueden tener mas de 6 entidades relacionadas.");
        }
        if("Mover elementos".equals(options.getValue())){
            title.setText(options.getValue());
            information.setText("Para mover elementos dentro de la pantalla, debes presionar el bóton para mover"
                    + " que se encuentra al lado izquiedo en la sección ''Acciones'', luego debes presionar y arrastrar dentro"
                    + "de la pantalla a la ubicación donde seas reubicar el elemento.");
        }
        if("Marcar puntos".equals(options.getValue())){
            title.setText(options.getValue());
            information.setText("Para marcar los puntos donde se marcan los vértices de las figuras y los puntos"
                    + " de las conexiones, debes presionar el bóton que se encuentra al lado izquierdo en la sección"
                    + " ''Acciones''. Si deseas desmarcarlas, debes volver a presionar el bóton.");
        }
        if("Limpiar la pantalla".equals(options.getValue())){
            title.setText(options.getValue());
            information.setText("Para limpiar la pantalla debes presionar el bóton que se encuentra en"
                    + " la parte superior de la pantalla con el nombre ''Limpiar''.");
        }
        if("Editar elemento".equals(options.getValue())){
            title.setText(options.getValue());
            information.setText("Para editar una entidad o relación debes presionar el bóton que se encuentra en"
                    + " la parte superior de la pantalla con el nombre ''Editar'', al abrir una ventana emergente debes"
                    + " ingresar el nuevo nombre que le deseas dar al elemento.");
        }
        if("Eliminar elemento".equals(options.getValue())){
            title.setText(options.getValue());
            information.setText("Para eliminar una entidad o relación debes presionar el bóton que se encuentra en"
                    + " el costado izquierdo en la sección ''Acciones'' que tiene un simbolo de eliminar, luego puedes"
                    + " presionar una entidad o relación y se eliminara.");
        }
        if("Exportar como imagen o pdf".equals(options.getValue())){
            title.setText(options.getValue());
            information.setText("Para descargar lo que estas dibujando como imagen o pdf en tu computador, debes presionar "
                    + "el bóton que se encuentra en la parte superior de la pantalla con el nombre ''Exportar'', luego debes elegir "
                    + "la ubicación donde deseas guardar y el formato que lo deseas.");
        }
        if("Otros problemas".equals(options.getValue())){
            title.setText(options.getValue());
            information.setText("Si tienes otros problemas debes llamar al Equipo Rocket.");
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        options.getItems().addAll("Agregar entidad","Agregar relación","Mover elementos","Marcar puntos",
                "Limpiar la pantalla","Editar elemento","Eliminar elemento","Exportar como imagen o pdf",
                "Otros problemas");
        options.setValue("Elige una opción: ");
        options.getItems();
        title.setText("Centro de Ayuda");
        information.setText("Bienvenido al Graficador de Entidad-Relación, este graficador te permite dibujar"
                + " de manera interactiva un diagrama entidad-relación, de tal manera que se muestre "
                + "gráficamente. La entidades son rectangulos y los puedes ubicar donde desees dentro de la pantalla"
                + " de dibujo, mientras que las relaciones pueden cambiar dependiendo de la cantidad de entidades"
                + " que estas relacionando.");
        
    }    
    
}
