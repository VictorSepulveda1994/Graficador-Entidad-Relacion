package controller;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Equipo Rocket
 */
public class CallPop {
    
    /**
     *
     * Abre ventana para agregar el nombre de la entidad y crear la entidad
     * @throws java.io.IOException
     */
    public void popAddEntity()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Agregar entidad");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAddEntity.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();

    }
    
    /**
     * Edita el nombre de un elemento
     * @throws IOException
     */
    public void popEditElement()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Editar elemento");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopEditElement.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();

    }
    /**
     *
     * Abre ventana para agregar el nombre de la relacion y crear la relacion
     * @throws java.io.IOException
     */
    public void popAddRelation()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Agregar relacion");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAddRelation.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();    
        
    }
    
    /**
     *Muestra un mensaje de error al ingresar nombre invalido
     */
    public void alertName(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error al ingresar nombre");
        alert.setHeaderText("Se encontro un error en el nombre,"
                + " es vacio o tiene mas de 20 caracteres. Debe ingresar el nombre nuevamente.");
        alert.showAndWait();
    }
    
    /**
     *
     * Guardar una imagen como "imagen.png"
     */
    public void saveDiagramPng(Canvas canvas) {
        FileChooser fileChooser = new FileChooser();
                 
        //Ingreso de filtro de extensión
        FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        //Mostrar diálogo de guardar archivo
        final Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);

        if(file != null){
            try {
                WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), null);
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}