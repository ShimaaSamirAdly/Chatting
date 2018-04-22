/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.Massage;

import admin.userList.adminUserListController;
import chattingprojectserver.ChattingImp;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import signup.Signup;

/**
 *
 * @author Nehal
 */
public class adminMassageController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private AnchorPane pane;
    @FXML
    private ComboBox box;
    @FXML
    TextField notify;        
    @FXML
    Button sendNotify;        
    
    ChattingImp ch ;
    
    String address="";
     private adminUserListController adminUserListWindow;


    public void setMainWindow(adminUserListController mainWindow){
        this.adminUserListWindow = mainWindow;
    }

    boolean come=true;

    public boolean isCome() {
        return come;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ch = Signup.ch;
         box.getItems().addAll("Log Out");
         box.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {                
                address = t1;   
                System.out.println(".changed()"+address);
                if (address.equals(new String("Log Out")))
                    {Parent root;
                
                    try {
                        come=false;
                        System.out.println(" massage flag: s"+come);
                        root = FXMLLoader.load(getClass().getResource("/signup/SignIn.fxml"));
                        pane.getChildren().setAll(root);
                    } catch (IOException ex) {
                        Logger.getLogger(adminMassageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        }
            }     
        });
    } 
    
    public void userScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/userList/FXMLDocument.fxml"));
        pane.getChildren().setAll(root);
    }
    
     public void statusScreen() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/admin/Status/statusView.fxml"));
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
    public void sendNotify(){
        try {
            
            String st = notify.getText();
            System.out.println(st);
            ch.notifyAllUsers(st);
        } catch (RemoteException ex) {
            //Logger.getLogger(adminMassageController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
     
     
     
}
