/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signup;

import chattingprojectserver.ChattingImp;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import static java.rmi.registry.LocateRegistry.createRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Hazem
 */
public class Signup extends Application {
    
    public static ChattingImp ch;
    static Registry reg;
    //static Boolean flag = true;
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(InetAddress.getLocalHost());
        reg = createRegistry(5005);
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        
        Scene scene = new Scene(root,1000,550);
        stage.setTitle("Server Side Application");
        stage.sizeToScene();
        stage.setHeight(550);
        stage.setWidth(1000);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
     public static void startServer()  {
        Platform.runLater(() -> {
            try {
                
                ch = new ChattingImp();
                reg.rebind("ChattingService", ch);
                System.out.println("server ready");
                //ch.notifyAllUsers("");
                //start.setDisable(true);
                //stop.setDisable(false);
                
               // System.out.println("flag:"+ flag );
            } catch (RemoteException ex) {
                //Logger.getLogger(ChattingProjectServer.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        });
    }
    
    public static void stopServer()  {
        Platform.runLater(() -> {
            try {
                ch.unRegisterAll();
                //ch.notifyAllUsers("Server is Down please try again later....");
                ch = null;
                try {
                    reg.unbind("ChattingService");
                    
                   // reg = null;
                    System.out.println("server stoped");
                 //   flag = false;
                  //  start.setDisable(false);
                   // stop.setDisable(true);
                   // ch.notifyAllUsers();
               //    System.out.println("flag:"+ flag );
                } catch (RemoteException ex) {
                    //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            } catch (NotBoundException ex) {
                //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } catch (RemoteException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }
}
