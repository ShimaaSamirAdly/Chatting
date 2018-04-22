/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.AdminList;

import DAOImp.AdminDAOImp;
import DTO.AdminDTO;
import admin.userList.adminUserList;
import admin.userList.adminUserListController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 *
 * @author Nehal
 */
public class adminListController implements Initializable {

    @FXML
    private ImageView users;
    @FXML
    private ImageView status;
    @FXML
    private ImageView admins;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button save;
    @FXML
    private ComboBox box;

    @FXML
    private TableView<AdminDTO> adminList;
    @FXML
    private TableColumn<AdminDTO, String> name;
    @FXML
    private TableColumn<AdminDTO, String> password;
    String address = "";
    Parent root;
    int add;
    int adds;
    String name1 ="";
    String pass="";
     final AdminDAOImp admin = new AdminDAOImp();
    AdminDTO ad = new AdminDTO();
    private adminUserList mainApp;
    final AdminDAOImp user = new AdminDAOImp();
    List<AdminDTO> list = new ArrayList<AdminDTO>();

//---window---    
    private admin.Status.StatusController statusWindow;
    private adminUserListController adminUserListWindow;

    public void setMainWindow(admin.Status.StatusController mainWindow) {
        this.statusWindow = mainWindow;
    }

    public void setMainWindow(adminUserListController mainWindow) {
        this.adminUserListWindow = mainWindow;
    }
//---window---

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        box.getItems().addAll("Log Out");

        box.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                address = t1;
                System.out.println(".changed()" + address);
                if (address.equals(new String("Log Out"))) {
                    Parent root;

                    try {
                        root = FXMLLoader.load(getClass().getResource("/signup/SignIn.fxml"));
                        pane.getChildren().setAll(root);
                    } catch (IOException ex) {
                        Logger.getLogger(adminListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        for (int i = 1; i < 50; i++) {
            list.add(user.read(i));
        }
        for (AdminDTO b : list) {
            adminList.getItems().add(b);
        }

        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                event -> event.getRowValue().setUserName(event.getNewValue()));

        name.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getUserName()));

        password.setCellFactory(TextFieldTableCell.forTableColumn());
        password.setOnEditCommit(
                event -> event.getRowValue().setPassWord(event.getNewValue()));
        password.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPassWord()));

        adminList.setEditable(true);

        save.setOnMousePressed(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                for (AdminDTO b : list) {
                    Platform.runLater(() -> {
                        user.update(b);
                        System.out.println("-------");
                        user.printAdmin(b);
                    });
                }
            }
        });

    }

    public void statusScreen() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/admin/Status/statusView.fxml"));
        pane.getChildren().setAll(root);
    }

    public void MsgScreen() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/admin/Massage/adminMassage.fxml"));
        pane.getChildren().setAll(root);
    }

    public void userScreen() throws IOException {
        Platform.runLater(() -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/admin/userList/FXMLDocument.fxml"));
                pane.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(adminListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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

    /*
     ad.setUserName("hesham");
        ad.setPassWord("1234");
        ad.setAddAdmin(1);
        ad.setAddStatus(1);
        System.out.println(admin.create(ad));
     */
    public void Add() throws IOException {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Dialog");

        // Set the button types.
        ButtonType addButtonType = new ButtonType("add", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        CheckBox AddAdmin = new CheckBox();
        CheckBox AddStatus = new CheckBox();

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);
        grid.add(new Label("addAdmin:"), 0, 2);
        grid.add(AddAdmin, 1, 2);
        grid.add(new Label("addStatus:"), 0, 3);
        grid.add(AddStatus, 1, 3);

        // Enable/Disable login button depending on whether a username was entered.
        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                System.out.println("addmin: " + AddAdmin.isSelected() + "status: " + AddStatus.isSelected());
                System.out.println("\"Username=\" " + username.getText() + "Password= " + password.getText());

                if (AddAdmin.isSelected() == true) {
                    add = 1;
                } else if (AddAdmin.isSelected() == false) {
                    add = 0;
                }

                if (AddStatus.isSelected()==true) {
                    adds = 1;
                } else if (AddStatus.isSelected()==false) {
                    adds = 0;
                }
                name1=username.getText();
                pass=password.getText();
                 Platform.runLater(() -> {
              ad.setUserName(name1);
                ad.setPassWord(pass);
                ad.setAddAdmin(add);
                ad.setAddStatus(adds);
                System.out.println(admin.create(ad));});
                
                 return new Pair<>(username.getText(), password.getText());

            }
            return null;
        });

         Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
           
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue() );
        });
    }
}
