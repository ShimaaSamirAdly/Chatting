/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattingprojectclientfxml;

import DTO.UserDTO;
import chatting.Chatting;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Nehal
 */
public class ProfileController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button back;
    @FXML
    Scene scene;
    @FXML
    TextField fname;
    @FXML
    TextField lname;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    TextField phone;
    @FXML
    TextField confirmpassword;
    @FXML
    TextField email;
    @FXML
    RadioButton male;
    @FXML
    RadioButton female;
    @FXML
    Button chooseimage;  
    @FXML
    Button update;        
    @FXML
    Label validation;        
    @FXML
    Circle imageView;
    
    File file;
    FileChooser fileChooser = new FileChooser();
    
    
    Image image;
    
    
    String firstname;
    String lastname;
    String emailadd;
    String password1;
    String repassword;
    String username1;
    String phonenumber1;
    
    MainScreenController mcontroller;
    Chatting ch ;
    UserDTO user;
    private FileInputStream fis;
    public void setUser(UserDTO user) {
        this.user = user;
    }
    
    ClientImp client;

    public void setClient(ClientImp client) {
        this.client = client;
    }

    
    
    public void setCh(Chatting ch) {
        this.ch = ch;
    }
    public void setController(MainScreenController controller) {
        this.mcontroller = controller;
        
    }

    public MainScreenController getController() {
        return mcontroller;
    }
    
    
    ToggleGroup group;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        group = new ToggleGroup();
        male.setToggleGroup(group);
        female.setToggleGroup(group);
        
        
//        user = mcontroller.getUser();
//        
//        fname.setText(user.getfName());
//        lname.setText(user.getlName());
//        email.setText(user.getEmail());
//        password.setText(user.getPassWord());
//        confirmpassword.setText(user.getPassWord());
//        username.setText(user.getUserName());
//        phone.setText(user.getPhoneNumber());
//        if(user.getGender() == "male"){
//            male.setSelected(true);
//        }else{
//            female.setSelected(true);
//        }
        
        //changePicture(mcontroller.getUser().getId(), imageView);
        
        
    }   
    
    
    public void changePicture(int id, Circle c){
       
                try {
                    //System.out.println(mainc.getUser().getId());
                    file = ch.downloadImage(id);
                    if(file != null){
                        Image image = new Image(file.toURI().toString(),100,150,true,true);
                        c.setFill(new ImagePattern(image));
                    }else{
                        c.setFill(new ImagePattern(new Image("images/person.png")));
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        //image chooser
    
    public void chooseImage(){
       
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        ch = mcontroller.getCh();
                        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                        file = fileChooser.showOpenDialog(null);
                        if(file.length()>1048500){
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Warning Dialog");
                            alert.setHeaderText("Look, big image!!!");
                            alert.setContentText("please choose an image of size < 1 M");
                            alert.showAndWait();
                            
                        }else{
                            try {
                        BufferedImage bufferedImage = ImageIO.read(file);
                        //image = SwingFXUtils.toFXImage(bufferedImage, null);
                        image = new Image(file.toURI().toString(),100,150,true,true);
                        //imageview.setImage(image);
                        imageView.setFill(new ImagePattern(image));
                        fis = new FileInputStream(file);
                        
                        System.out.println(mcontroller.getUser().getId());
                        System.out.println(fis.toString());
                        ch.uploadImage(mcontroller.getUser().getId(), file);
                        } catch (IOException ex) {
                            //Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("cannot upload the image");
                            ex.printStackTrace();
                        }
                        }
                        
                    }
                });
            }
       
        
        // TODO
    
    public void back(){
       
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                    Parent root = loader.load();
                    MainScreenController controller = loader.getController();
                    controller.setCh(mcontroller.getCh());
                    controller.setUser(mcontroller.getUser());
                    //controller = loader.getController();
                    Stage stage = (Stage) back.getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        public void update(){
        
                firstname = fname.getText();
                lastname = lname.getText();
                emailadd = email.getText();
                password1 = password.getText();
                repassword = confirmpassword.getText();
                username1 = username.getText();
                phonenumber1 = phone.getText();
                
                if(firstname.equals("")){
                     validation.setText("enter firstname");
                }else if(lastname.equals("")){
                     validation.setText("enter lastname");
                }else if(username1.equals("")){
                    validation.setText("enter username");
                }else if(phonenumber1.equals("")){
                    validation.setText("enter phonenumber");
                }else{
                    try {
                        ch = mcontroller.getCh();
                        user = new UserDTO();
                        user.setId(mcontroller.getUser().getId());
                        user.setfName(firstname);
                        user.setlName(lastname);
                        user.setPassWord(password1);
                        user.setUserName(username1);
                        user.setEmail(emailadd);
                        user.setCountry(mcontroller.getUser().getCountry());
                        user.setGender(group.toString());
                        user.setPhoneNumber(phonenumber1);
                        user.setOnOffLine(mcontroller.getUser().getOnOffLine());
                        user.setStatusID(mcontroller.getUser().getStatusID());
                        mcontroller.setUser(user);
                        System.out.println(password1);
                        System.out.println(repassword);
                        ch.updateUser(user);
                        
                    } catch (RemoteException ex) {
                        Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
            }
       
    
}
