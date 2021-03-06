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
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
/*

/**
 * Esta clase se encarga de llamar a todos los Pop y mensajes de advertencia.
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
        
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        //dialog.resizableProperty().setValue(Boolean.FALSE);
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();

    }
    
    /**
     * Abre ventana para editar un atributo.
     * @throws IOException
     */
    public void popEditElement()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Editar nombre");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopEditAttribute.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.resizableProperty().setValue(Boolean.FALSE);
        dialog.setResizable(false);
        dialog.close();

    }
    
    /**
    * Abre ventana para editar una relación.
    * @throws java.io.IOException
    */
    public void popEdit()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Editar relación");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopEditRelation.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.resizableProperty().setValue(Boolean.FALSE);
        dialog.setResizable(false);
        dialog.close();

    }
    
    /**
    * Abre ventana para editar una herencia.
    * @throws java.io.IOException
    */
    public void popEditHeritage()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Editar herencia");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopEditHeritage.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.resizableProperty().setValue(Boolean.FALSE);
        dialog.setResizable(false);
        dialog.close();

    }
    
    /**
    * Abre ventana para editar una entidad.
    * @throws java.io.IOException
    */
    public void popEditEntity()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Editar entidad");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopEditEntity.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.resizableProperty().setValue(Boolean.FALSE);
        dialog.setResizable(false);
        dialog.close();

    }
    
    public void popEditConnector()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Editar conector");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopEditConnector.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.setResizable(false);
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
     * Abre ventana para agregar una relación.
     * @throws java.io.IOException
     */
    public void popAddRelation()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Agregar relación");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAddRelation.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.resizableProperty().setValue(Boolean.FALSE);
        dialog.close();    
        
    }
    
    /**
    * Abre ventana para agregar un atributo.
    * @throws java.io.IOException
    */
    public void popAddAttribute()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Agregar relación");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAddAttribute.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.resizableProperty().setValue(Boolean.FALSE);
        dialog.close();    
        
    }
    
     /**
     * Abre ventana para agregar el tipo de una herencia entre entidades.
     * @throws java.io.IOException
     */
    public void popAddHeritage()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Agregar herencia");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAddHeritage.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.resizableProperty().setValue(Boolean.FALSE);
        dialog.close();    
        
    }
    
    /**
     * Abre ventana para crear una agregación.
     * @throws java.io.IOException
     */
    public void popAddAggregation()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Crear agregación");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopAddAggregation.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.resizableProperty().setValue(Boolean.FALSE);
        dialog.close();    
        
    }
    
    /**
     * Muestra un mensaje de error al ingresar nombre invalido.
     */
    public void alertName(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error al ingresar nombre");
        alert.setHeaderText("Se encontró un error en el nombre,"
                + "tiene mas de 12 caracteres o está repetido. Debe ingresar el nombre nuevamente.");
        alert.showAndWait();
    }
    
    /**
     * Muestra un mensaje de error al ingresar nombre invalido.
     * @param oraciones
     */
    public void restricciones(String oraciones){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Restricciones semanticas");
        alert.setHeaderText(oraciones);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de error al intentar seleccionar dos tipos de herencia a la misma vez o ninguno tipo.
     */
    public void alertTypeHeritageIncorrect(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Debe elegir solo un tipo de herencia para poder continuar.");
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de error al intentar agregarle atributos a un atributo que no es del tipo "COMPOUND".
     */
    public void alertOfTypeIncorrect(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Solo los atributos compuestos pueden contener mas atributos.");
        alert.showAndWait();
    }
    
    /**
     * Muestra un mensaje de error al seleccionar más de 6 entidades.
     */
    public void alertEntities(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exceso de entidades");
        alert.setHeaderText("Puede seleccionar hasta 2 entidades para relacionarlas");
        alert.showAndWait();
    }
    
    public void alertParcialKey(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Las entidades debiles deben tener asociado un attributo clave parcial.");
        alert.showAndWait();
    }
    
    /**
     * Muestra un mensaje de error al intentar hacer herencia con una entidad debil o un herencia tipo hijo-padre.
     */
    public void alertInvalid(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Herencia invalida");
        alert.setHeaderText("Un hijo no puede ser hijo de su padre o un padre no puede ser padre de su padre,"
                + "No puede crear herencia con entidades debiles.");
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de error al borrar todas las entidades de una relación por medio del popChangeController.
     */
    public void alertEntitiesEmpty(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Edición invalida");
        alert.setHeaderText("Haz dejado la relación sin entidades");
        alert.showAndWait();
    }

    /**
     * Exportar diagrama en formato "PNG" ´ó "PDF".
     * @param canvas
     * @throws com.itextpdf.text.DocumentException
     */
    public void export (Canvas canvas) throws DocumentException{
        FileChooser fileChooser = new FileChooser();
                 
        //Ingreso de filtro de extensión PNG
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
         
        //Ingreso de filtro de extensión PDF
        extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Mostrar diálogo de guardar archivo
        final Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        
        if(file != null){
            if(file.getName().contains(".png")){
                try {
                    WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), null);
                    canvas.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(file.getName().contains(".pdf") || file.getName().contains(".PDF")){
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
    }
    
    /**
     * Muestra información sobre el funcionamiento del programa.
     * @throws IOException 
     */
    public void popShowHelp()throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Ayuda");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/PopHelp.fxml"));
        
        Scene xscene = new Scene(root);
        
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) root.getScene().getWindow());
        
        dialog.setScene(xscene);
        dialog.showAndWait();
        dialog.setResizable(false);
        dialog.close();

    }
   
}
