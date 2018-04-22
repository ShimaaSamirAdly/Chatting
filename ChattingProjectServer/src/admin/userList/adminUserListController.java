/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.userList;

import DAOImp.UserDAOImp;
import DTO.UserDTO;
import admin.board.adminBoardController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Nehal
 */
public class adminUserListController implements Initializable {

    @FXML
    private ImageView users;
    @FXML
    private ImageView status;
    @FXML
    private ImageView admins;
    @FXML
    private ComboBox userBox;
    @FXML
    private Button save;
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<UserDTO> userList;
    @FXML
    private TableColumn<UserDTO, String> name;
    @FXML
    private TableColumn<UserDTO, String> email;
    @FXML
    private TableColumn<UserDTO, String> country;
    @FXML
    private TableColumn<UserDTO, String> phone;
    @FXML
    private TableColumn<UserDTO, String> gender;
    @FXML
    private ComboBox box;

    private adminUserList mainApp;
    final UserDAOImp user = new UserDAOImp();
    List<UserDTO> list = new ArrayList<UserDTO>();

    //  private ObservableList<UserDTO> personData = FXCollections.observableArrayList();
    //  public ObservableList<UserDTO> getPersonData() {
    //    return personData;
    // }
//-----window----
    Parent root;
    private admin.Status.StatusController statusWindow;

    public void setMainWindow(admin.Status.StatusController mainWindow) {
        this.statusWindow = mainWindow;
    }
    private admin.AdminList.adminListController adminlistWindow;

    public void setMainWindow(admin.AdminList.adminListController mainWindow) {
        this.adminlistWindow = mainWindow;
    }
//---window---
    String address = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        box.getItems().addAll("Log Out");
        box.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                address = t1;
                System.out.println(".changed()" + address);
                if (address.equals(new String("Log Out"))) {
                    
                    try {
                        Parent root;
                        root = FXMLLoader.load(getClass().getResource("/signup/SignIn.fxml"));
                        pane.getChildren().setAll(root);
                    } catch (IOException ex) {
                        Logger.getLogger(adminUserListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        //UserDAOImp user = new UserDAOImp();
        // UserDTO u = null;
        // u = user.read(1);
        // user.printuser(u);
        for (int i = 1; i < (user.femaleusers()+user.maleusers()+1); i++) {
            list.add(user.read(i));
        }
        list.forEach((b) -> {
            userList.getItems().add(b);
        });

        name.setCellFactory(TextFieldTableCell.forTableColumn());

        name.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getUserName()));
       name.setOnEditCommit(
                event -> event.getRowValue().setUserName(event.getNewValue()));     
        
        email.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getEmail()));
        email.setOnEditCommit(
                event -> event.getRowValue().setEmail(event.getNewValue()));     
       
        
        country.setCellFactory(TextFieldTableCell.forTableColumn());

        country.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getCountry()));
        country.setOnEditCommit(
                event -> event.getRowValue().setCountry(event.getNewValue()));     
       
        phone.setCellFactory(TextFieldTableCell.forTableColumn());

        phone.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPhoneNumber()));
       phone.setOnEditCommit(
                event -> event.getRowValue().setPhoneNumber(event.getNewValue()));     
       
        
        
        gender.setCellFactory(TextFieldTableCell.forTableColumn());

        gender.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getGender()));
        gender.setOnEditCommit(
                event -> event.getRowValue().setGender(event.getNewValue()));     
       
        userList.setEditable(true); 
         save.setOnMousePressed(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                for (UserDTO b : list) {
                    Platform.runLater(() -> {
                        user.update(b);
                        System.out.println("-------");
                        user.printuser(b);
                    });
                }
            }
        });
         
 
        //  email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        //    name.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());
        /*  status.setOnMousePressed((Event event) -> {
            System.out.println("status presss");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/Status/statusView.fxml"));
            try {
                Parent root1 = loader.load();
                StatusController controller = loader.getController();
                controller.setMainWindow(adminUserListController.this);
                Platform.runLater(() -> {
                    //stage.close();
                    Stage stage = new Stage();
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(status.getScene().getWindow());
                    Scene scene = new Scene(root1);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                });
            } catch (IOException ex) {
                Logger.getLogger(StatusController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         */
 /*   admins.setOnMousePressed(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("admins presss");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/AdminList/adminList.fxml"));

                try {
                    
                    Parent root = loader.load();
                    adminListController controller = loader.getController();
                    controller.setMainWindow(adminUserListController.this);

                    Platform.runLater(() -> {

                        //stage.close();
                        Stage stage = new Stage();
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(status.getScene().getWindow());
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    });

                } catch (IOException ex) {
                    Logger.getLogger(StatusController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
         */
    }

    public void statusScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/Status/statusView.fxml"));
        pane.getChildren().setAll(root);
    }

    public void MsgScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/Massage/adminMassage.fxml"));
        pane.getChildren().setAll(root);
    }

    public void loadScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/AdminList/adminList.fxml"));
        pane.getChildren().setAll(root);
    }

    public void HomeScreen() throws IOException {
       // Parent root = FXMLLoader.load(getClass().getResource("/admin/board/adminBoard.fxml"));
      //  pane.getChildren().setAll(root);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/board/adminBoard.fxml"));
         Parent root = loader.load();
          pane.getChildren().setAll(root);
        adminBoardController controller = loader.getController();
          controller.setMainWindow(adminUserListController.this);
         
    }

    public void setMainApp(adminUserList mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        // userList.setItems(getPersonData());
    }
}
