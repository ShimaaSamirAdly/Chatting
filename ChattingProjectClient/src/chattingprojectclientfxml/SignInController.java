/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattingprojectclientfxml;

import DTO.UserDTO;
import chatting.Chatting;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    Chatting ch;
    ClientImp client;
    
    public void setClient(ClientImp client) {
        this.client = client;
    }

    public void setCh(Chatting Ch) {
        this.ch = Ch;
    }
    
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
    
    
    String user;
    String pass;
    
    UserDTO u;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void signIn() throws Exception{
        user = userName.getText();
        pass = passWord.getText();
        if(user.equals("")){
            verification.setText("please enter username");
        }else if(pass.equals("")){
            verification.setText("please enter password");
        }else{
            u = new UserDTO();
            u = ch.logIn(user);
            if(u.getPassWord().equals(pass) && !user.equals("")){
                verification.setText("login successful");
                
                u.setOnOffLine("on");
                ch.update(u);
                ch.register(client, u);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                Parent root = loader.load();
                MainScreenController controller = loader.getController();
                client.setMainScreen(controller);
                controller.setCh(ch);
                controller.setClient(client);
                controller.setUser(u);
        
                Stage stage = (Stage) passWord.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
            }else{
                verification.setText("incorrect password");
            }
        }
        
        
    }
    public void loadScreen() throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = loader.load();
        SignUpController controller = loader.getController();
        controller.setCh(ch);
        controller.setClient(client);
        //borderPane.getChildren().setAll(root);
        Stage stage = (Stage) passWord.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
