/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattingprojectclientfxml;

import Country.Country;
import Country.Country_;
import DTO.UserDTO;
import chatting.Chatting;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Hazem
 */
public class SignUpController implements Initializable {
    
    Chatting ch;
    ClientImp client;
    
    public void setClient(ClientImp client) {
        this.client = client;
    }

    public void setCh(Chatting Ch) {
        this.ch = Ch;
    }
    @FXML
    private Scene scene;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField phonenumber;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField repass;
    @FXML
    private Button signup;
    @FXML
    private Label signin;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private Label validation;        
    @FXML
    private ComboBox country;
    @FXML
    private BorderPane borderPane;        
    String firstname;
    String lastname;
    String emailadd;
    String password;
    String repassword;
    String username1;
    String phonenumber1;
    Country countries;
    Gson gson;
    //UserDAOImp user;
    UserDTO u = new UserDTO();
    ToggleGroup group;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //user = new UserDAOImp();
        //u = null;
        
        ObservableList<String> options = FXCollections.observableArrayList();
        gson = new Gson();
        try (Reader reader = new FileReader("countries.json")) {  
            Country_[] countries = gson.fromJson(reader, Country_[].class);
            for(int i=0;i<countries.length;i++){
                options.add(countries[i].getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // TODO
        group = new ToggleGroup();
        male.setToggleGroup(group);
        female.setToggleGroup(group);
        male.setSelected(true);
        
        country.setItems(options);
        country.setVisibleRowCount(3);
        country.setValue("Egypt");
    }
    public void signUp() throws Exception{
        firstname = fname.getText();
        lastname = lname.getText();
        emailadd = email.getText();
        password = pass.getText();
        repassword = repass.getText();
        username1 = username.getText();
        phonenumber1 = phonenumber.getText();
        String passwordpattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        String emailpattern = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        if(firstname.equals("")){
            validation.setText("enter firstname");
        }else if(lastname.equals("")){
            validation.setText("enter lastname");
        }else if(username1.equals("")){
            validation.setText("enter username");
        }else if(phonenumber1.equals("")){
            validation.setText("enter phonenumber");
        }else if(!emailadd.matches(emailpattern)){
            validation.setText("invalid email");
        }
        else if( !password.matches(passwordpattern)){
            validation.setText("invalid password");
        }else if(!password.equals(repassword)){
            validation.setText("password & repassword dismatch");
        }
        else{
            u = new UserDTO(username1, password, group.toString(), country.getId(), phonenumber1, "on", emailadd, firstname, lastname, 1);
            u = ch.singUp(u);
           // validation.setText("signup complete");
           if(u != null){
               ch.register(client,u);
               FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
                Parent root = loader.load();
                MainScreenController controller = loader.getController();
                controller.setCh(ch);
                controller.setClient(client);
                controller.setUser(u);
        
                Stage stage = (Stage) fname.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
           }
        }
    }
    public void loadScreen() throws IOException{
        //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        Parent root = loader.load();
        SignInController controller = loader.getController();
        controller.setCh(ch);
        controller.setClient(client);
        //borderPane.getChildren().setAll(root);
        Stage stage = (Stage) pass.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        
    }
    //1!2Aasde3#
}
