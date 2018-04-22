/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.board;

import DAOImp.UserDAOImp;
import DTO.UserDTO;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Nehal
 */
public class adminBoardController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private AnchorPane pane;
    @FXML
    private ComboBox box;
    @FXML
    private Button start;
    @FXML
    private Button stop;

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    UserDAOImp user = new UserDAOImp();
    UserDTO u = null;
    ChattingImp ch;
   // Registry reg;
    static Boolean flag = false;

    String address = "";
    private adminUserListController adminUserListWindow;

    //private admin.userList.adminUserListController adminUserListWindow;

   
    
    public void setCh(ChattingImp ch) {
        this.ch = ch;
    }

    public void setMainWindow(admin.userList.adminUserListController mainWindow) {
        this.adminUserListWindow = mainWindow;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("flag:"+ flag );
        if (flag == true)
        {
            start.setDisable(true);
            stop.setDisable(false);
        }
        if (flag == false)
        {
         start.setDisable(false);
         stop.setDisable(true);
        }
       /* try {
            if (flag == true) {
              reg = createRegistry(5005);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

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
                        Logger.getLogger(adminBoardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                }
            }
        });

        barChart.setTitle("Users Statistics");
        xAxis.setLabel("Data");
        yAxis.setLabel("Numbers");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Until Now");
        series1.getData().add(new XYChart.Data("User", ( user.femaleusers() + user.maleusers())));
        series1.getData().add(new XYChart.Data("Online user", user.onLineUsers()));
        series1.getData().add(new XYChart.Data("Offline user", user.offLineUsers()));
        series1.getData().add(new XYChart.Data("Female user", user.femaleusers()));
        series1.getData().add(new XYChart.Data("Male user", user.maleusers()));

        barChart.getData().addAll(series1);
        barChart.setBarGap(3);
        barChart.setCategoryGap(50);

        // TODO
    }

    public void userScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/userList/FXMLDocument.fxml"));
        pane.getChildren().setAll(root);
    }

    public void statusScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/Status/statusView.fxml"));
        pane.getChildren().setAll(root);
    }

    public void MsgScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/Massage/adminMassage.fxml"));
        pane.getChildren().setAll(root);
    }

    public void HomeScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/board/adminBoard.fxml"));
        pane.getChildren().setAll(root);
    }

    public void adminScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/admin/AdminList/adminList.fxml"));
        pane.getChildren().setAll(root);
    }

    public void start1() throws IOException {
        signup.Signup.startServer();
        flag=true;
        start.setDisable(true);
                stop.setDisable(false);
                
                
       /*Platform.runLater(() -> {
            try {
                
                ch = new ChattingImp();
                reg.rebind("ChattingService", ch);
                System.out.println("server ready");
                ch.notifyAllUsers();
                start.setDisable(true);
                stop.setDisable(false);
                
                System.out.println("flag:"+ flag );
            } catch (RemoteException ex) {
                Logger.getLogger(ChattingProjectServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
    }

    public void stop1() throws IOException {
        signup.Signup.stopServer();
        flag = false;
                    start.setDisable(false);
                    stop.setDisable(true);
      /*  Platform.runLater(() -> {
            try {
                ch = null;
                try {
                    reg.unbind("ChattingService");
                    
                   // reg = null;
                    System.out.println("server stoped");
                    flag = false;
                    start.setDisable(false);
                    stop.setDisable(true);
                   // ch.notifyAllUsers();
                   System.out.println("flag:"+ flag );
                } catch (RemoteException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NotBoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
      */
    }

}
