/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signup;

import DAOImp.AdminDAOImp;
import DAOImp.UserDAOImp;
import DTO.AdminDTO;
import DTO.UserDTO;
import admin.Massage.adminMassageController;
import admin.userList.adminUserListController;
import chattingprojectserver.ChattingImp;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import static java.rmi.registry.LocateRegistry.createRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shimaa
 */

public class SignInController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private BorderPane borderPane;
    @FXML
    TextField userName;
    @FXML
    PasswordField passWord;
    @FXML
    Label verification;        
    @FXML
    Scene scene;
   //  @FXML
   // private Button start;
    //@FXML
    //private Button stop;
    
    //ChattingImp ch;
   // Registry reg;
    //static Boolean flag = true;
    
    
    String user;
    String pass;
    
    AdminDAOImp admindao = new AdminDAOImp() ;
    AdminDTO admin;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        admin = new AdminDTO();
        
        
        // TODO
    }    
    
    public void signIn() throws IOException{
        user = userName.getText();
        pass = passWord.getText();
        if(user.equals("")){
            verification.setText("please enter username");
        }else if(pass.equals("")){
            verification.setText("please enter password");
        }else{
            admin = new AdminDTO();
            admin = admindao.readByUsername(user);
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("admin/admin.userList/FXMLDocument.fxml"));
            //Parent root = loader.load();
            //adminUserListController controller = loader.getController();
            
            //Stage stage = (Stage) passWord.getScene().getWindow();
            //scene = new Scene(root);
            //stage.setScene(scene);
            if(admin.getPassWord().equals(pass) && !user.equals("")){
                Parent root = FXMLLoader.load(getClass().getResource("/admin/board/adminBoard.fxml"));
                borderPane.getChildren().setAll(root);
                verification.setText("login successful");
                
            }else{
                verification.setText("incorrect password");
            }
  
        }
        
        
    }
    
   
  
}
