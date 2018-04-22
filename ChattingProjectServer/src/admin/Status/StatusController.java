/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.Status;

import DAOImp.StatusDAOImp;
import DAOImp.UserDAOImp;
import DTO.StatusDTO;
import DTO.UserDTO;
import admin.userList.adminUserList;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Nehal
 */
public class StatusController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ImageView users;
    @FXML
    private ImageView admins;
    @FXML
    private Button save;
    @FXML
    private AnchorPane pane;
    @FXML
    private ComboBox box;
     @FXML
    private TableView<StatusDTO> statuslist;
    @FXML
    private TableColumn<StatusDTO, String> name;
    
    String address="";
     private adminUserList mainApp;
 //TableColumn<CustomImage, ImageView> firstColumn = new TableColumn<CustomImage, ImageView>("Images");
    final StatusDAOImp user = new StatusDAOImp();
    //StatusDTO test= new StatusDTO();
    List<StatusDTO> list = new ArrayList<StatusDTO>();

    private admin.userList.adminUserListController adminUserListWindow;

    public void setMainWindow(admin.userList.adminUserListController mainWindow) {
        this.adminUserListWindow = mainWindow;
    }
    private admin.AdminList.adminListController adminListWindow;

    public void setMainWindow(admin.AdminList.adminListController mainWindow) {
        this.adminListWindow = mainWindow;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        box.getItems().addAll("Log Out");
        box.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {                
                address = t1;   
                System.out.println(".changed()"+address);
                if (address.equals(new String("Log Out")))
                    {Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/signup/SignIn.fxml"));
                        pane.getChildren().setAll(root);
                    } catch (IOException ex) {
                        Logger.getLogger(StatusController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        }
            }     
        });
        for (int i = 1; i < 20; i++) {
            list.add(user.read(i));
        }
        for (StatusDTO b : list) {
           statuslist.getItems().add(b);
        }
        
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                event -> event.getRowValue().setName(event.getNewValue()));

        name.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));

        
        statuslist.setEditable(true);
        
       save.setOnMousePressed(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                for (StatusDTO b : list) {
                    Platform.runLater(() -> {
                        user.update(b);
                        System.out.println("-------");
                        user.printStatus(b);
                    });
                }
            }
        });

      
    }

    public void userScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/userList/FXMLDocument.fxml"));
        pane.getChildren().setAll(root);
    }
    
    public void MsgScreen() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/admin/Massage/adminMassage.fxml"));
        pane.getChildren().setAll(root);
    }

    public void adminScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/AdminList/adminList.fxml"));
        pane.getChildren().setAll(root);
    }
     public void HomeScreen() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/admin/board/adminBoard.fxml"));
        pane.getChildren().setAll(root);
        
    }
    public void setMainApp(adminUserList mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        // userList.setItems(getPersonData());
    }

}
