package controller;

import java.awt.image.RenderedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
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

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
/*

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
        //dialog.resizableProperty().setValue(Boolean.FALSE);
        
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
        dialog.setTitle("Editar nombre");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopChangeName.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.resizableProperty().setValue(Boolean.FALSE);
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
        dialog.setTitle("Agregar relación");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAddRelation.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.resizableProperty().setValue(Boolean.FALSE);
        dialog.close();    
        
    }
    
    /**
     *Muestra un mensaje de error al ingresar nombre invalido
     */
    public void alertName(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error al ingresar nombre");
        alert.setHeaderText("Se encontró un error en el nombre,"
                + " es vació o tiene mas de 12 caracteres. Debe ingresar el nombre nuevamente.");
        alert.showAndWait();
    }
    
    public void alertEntities(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exceso de entidades");
        alert.setHeaderText("Puedes seleccionar hasta 7 entidades para relacionarlas");
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
    
    public void saveDiagramPdf(Canvas canvas) throws DocumentException {
        FileChooser fileChooser = new FileChooser();
                 
        //Ingreso de filtro de extensión
        FileChooser.ExtensionFilter extFilter = 
                    new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Mostrar diálogo de guardar archivo
        final Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);

        if(file != null){
            try {
                Document document = new Document();

                PdfWriter.getInstance(document, new FileOutputStream(file));

                //open
                document.open();

                ByteArrayOutputStream  byteOutput = new ByteArrayOutputStream();

                ImageIO.write( SwingFXUtils.fromFXImage( canvas.snapshot(new SnapshotParameters(), null), null ), "png", byteOutput );

                com.itextpdf.text.Image  graph;
                graph = com.itextpdf.text.Image.getInstance( byteOutput.toByteArray() );
                graph.scaleToFit(document.getPageSize());

                graph.setAlignment(Image.ALIGN_CENTER);
                graph.setAlignment(Image.MIDDLE);

                document.add(graph);

                //close
                document.close();

            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void popShowHelp()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Ayuda");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopHelp.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();

    }
    
}
