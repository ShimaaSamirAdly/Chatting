/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattingprojectclientfxml;

import DTO.UserDTO;
import chatting.Chatting;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Shimaa
 */
public class ChattingProjectClientFXML extends Application {
    
    Chatting ch;
    int id;
     
    
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        Parent root = loader.load();
        SignInController controller = loader.getController();

        ClientImp client = new ClientImp(controller);
        Registry reg = LocateRegistry.getRegistry("172.0.0.1", 5005);
        ch = (Chatting) reg.lookup("ChattingService");
        
        controller.setCh(ch);
        controller.setClient(client);  
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Connect...");
        stage.initStyle(StageStyle.UNDECORATED);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
    
   
    
}
