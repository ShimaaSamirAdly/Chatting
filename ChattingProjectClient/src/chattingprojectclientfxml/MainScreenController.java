/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattingprojectclientfxml;

import DTO.ChatGroupDTO;
import DTO.FriendshipDTO;
import DTO.StatusDTO;
import DTO.UserDTO;
import chatting.Chatting;
import com.google.gson.Gson;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import configration.ServerConfigration;
import email.MailAPI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import savechat.FontFamily;
import savechat.MessageType;
import savechat.ObjectFactory;
import savechat.SavedChatType;
import javafx.util.Duration;
import javafx.util.Pair;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Shimaa
 */
public class MainScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Chatting ch;

    UserDTO user = new UserDTO();

    VBox vbox;

    MainScreenController mainc = null;

    File file = null;

    public Chatting getCh() {
        return ch;
    }

    public UserDTO getUser() {
        return user;
    }

    public ClientImp getClient() {
        return client;
    }

    @FXML
    Scene scene;

    int receiver = 0;

    public void setUser(UserDTO user) {
        this.user = user;
    }

    ClientImp client;

    public void setCh(Chatting ch) {
        this.ch = ch;
    }

    public void setClient(ClientImp client) {
        this.client = client;
    }

    int current = 0;
    int currentGroup = 0;

    @FXML
    ListView listView;

    @FXML
    VBox vbox1;

    @FXML
    VBox vbox2;

    @FXML
    MenuButton test;

    @FXML
    BorderPane borderChat;

    @FXML
    VBox vboxChat;

    @FXML
    VBox members;

    @FXML
    Tab tab1;

    @FXML
    Tab tab2;

    @FXML
    TextField send;

    @FXML
    ComboBox fontSize;

    @FXML
    ColorPicker fontColor;

    @FXML
    ComboBox fontFamily;

    @FXML
    Label headerLabel;

    @FXML
    Circle headerImg;

    @FXML
    Label accountName;

    @FXML
    Label checkGroup;

    @FXML
    ComboBox status;

    @FXML
    ToggleButton onOff;

    @FXML
    Circle statusColor;

    @FXML
    TextField groupName;

    @FXML
    ScrollPane scroll;

    @FXML
    Button logOut;

    @FXML
    Button unfriend;

    @FXML
    Button block;

    @FXML
    Button profile;

    @FXML
    ImageView sendFile;

    //hazem
    @FXML
    VBox requestrespond;

    @FXML
    Tab friendrequeststab;

    @FXML
    Button sendRequest;

    @FXML
    TextField findFriends;

    @FXML
    Label request;

    UserDTO user1;

    HBox containner;

    Image image;

    @FXML
    Circle profilePic;

    @FXML
    Circle mail;

    @FXML
    VBox requestRespond;

    @FXML
    ImageView fb;
    
    @FXML
    Button exit;
    Hashtable<Integer, UserDTO> friendsList = new Hashtable<Integer, UserDTO>();

    Hashtable<Integer, HBox> friends = new Hashtable<Integer, HBox>();
    Hashtable<Integer, VBox> singleChat = new Hashtable<Integer, VBox>();

    Hashtable<Integer, HBox> groups = new Hashtable<Integer, HBox>();
    Hashtable<Integer, VBox> multiChat = new Hashtable<Integer, VBox>();

    ArrayList<HBox> recentMsg = new ArrayList<HBox>();

    ArrayList<StatusDTO> statusList = new ArrayList<StatusDTO>();

    ArrayList<Integer> tempGroupMembers = new ArrayList<Integer>();

    boolean isGroup;

    //Nehal
    //ClientInt  imp;
    int port = 1000;

    boolean flag = true;
    boolean flag1 = false;

        JAXBElement element = null;
    SavedChatType chat = null;
    ObjectFactory factory = null;
    JAXBContext context = null;
    Unmarshaller unmarshaller = null;
    ArrayList<MessageType> messages;
    HashMap<Integer,ArrayList<MessageType>> savechats; 
    
    Gson gson; 
    String savedChatAddress;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gson = new Gson();
        Reader reader;
        try {
            reader = new FileReader("serverConfigration.json");
            ServerConfigration serverConfig = gson.fromJson(reader,ServerConfigration.class);
            savedChatAddress = serverConfig.getSavedChatAddress();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //ip = serverConfig.getHostIP();
        
        
        savechats = new HashMap<Integer,ArrayList<MessageType>>();
        messages = new ArrayList<MessageType>();
        
        mainc = this;
        Platform.runLater(new Runnable() {

            public void run() {

//                accountName.setText(user.getUserName());
//                mail.setFill(new ImagePattern(new Image("images/login.png")));
//                changePicture(user.getId(), profilePic);

                onOff.setSelected(true);
                onOff.setText("ON");
                onOff.setTextFill(Color.rgb(114, 105, 105));
                statusColor.setFill(Color.GREEN);
                user.setOnOffLine("on");
                
                 accountName.setText(user.getUserName());
                mail.setFill(new ImagePattern(new Image("images/login.png")));
                changePicture(user.getId(), profilePic);

                try {
                    ch.tellOnOff(user);
                } catch (Exception ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
//                setFriendsChat();
                try {
                    setFriendsList();
                } catch (Exception ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                setGroupList();
                //setFriendsChat();
                try {
                    setStatusList();
                } catch (Exception ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }

                status.setValue(status.getItems().get(0));
                try {
                    setStatus();
                } catch (Exception ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }

                createGroupSide();

                scroll.layout();
                scroll.setVvalue(1);

            }
        });
    }

    public void setFriendsList() throws Exception {

        ArrayList<UserDTO> arr = new ArrayList<UserDTO>();

        try {
            arr = ch.getFriends(user.getId());

        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (arr != null) {
            for (int i = 0; i < arr.size(); i++) {
                savechats.put(arr.get(i).getId(), new ArrayList<MessageType>());
                if (ch.checkFriendsStatus(arr.get(i).getId(), user.getId()) == 1) {
                    friendsList.put(arr.get(i).getId(), arr.get(i));
                    HBox hbox = new HBox();
                    Circle c = new Circle(20);
                    //c.setFill(new ImagePattern(new Image("images/person.png")));
                    changePicture(arr.get(i).getId(), c);
                    c.setStyle("-fx-border-color: transparent; -fx-margin: 5 5 5 5 ;");

                    Text t = new Text(arr.get(i).getUserName());
                    t.setFill(Color.rgb(114, 105, 105));
                    t.setStyle("-fx-margin: 5; -fx-font-size: 20px;");

                    String s = ch.getStatus(arr.get(i).getStatusID()).getName();
                    Text state = new Text(s);
                    state.setFill(Color.rgb(114, 105, 105));
                    state.setStyle("-fx-margin: 5; -fx-font-size: 10px;");

                    Circle fStatus = new Circle(8);
                    fStatus.setStyle("-fx-margin: 20;");
                    if (arr.get(i).getOnOffLine().equals("on")) {
                        fStatus.setFill(Color.GREEN);
                    } else {
                        fStatus.setFill(Color.GRAY);
                    }

                    hbox.setPadding(new Insets(5, 10, 0, 10));
                    hbox.setSpacing(5);
                    hbox.getChildren().addAll(c, t, state, fStatus);

                    hbox.setId("" + arr.get(i).getId());

                    hbox.setStyle("-fx-border-color:  #2Ea1da; -fx-pref-height: 50px; -fx-border-radius: 10; -fx-border-width: 2px;");
                    friends.put(arr.get(i).getId(), hbox);
                    vbox2.getChildren().add(hbox);
                    recentMsg.add(hbox);
                    singleChat.put(arr.get(i).getId(), new VBox());
                    vbox2.setSpacing(2);
                    String ss = new String(arr.get(i).getUserName());
                    //UserDTO us = arr.get(i);
                    hbox.setOnMousePressed(new EventHandler<MouseEvent>() {

                        public void handle(MouseEvent event) {
                            isGroup = false;
                            currentGroup = 0;

//                            if(check == "off"){
//                                send.setVisible(false);
//                                send.setText("ook");
//                            }
                            receiver = Integer.parseInt(hbox.getId());
                            vbox = singleChat.get(Integer.parseInt(hbox.getId()));
                            borderChat.setCenter(vbox);

                            headerLabel.setText(ss);
                            changePicture(receiver, headerImg);
                            headerImg.setStyle("-fx-border-color: transparent;");
                            current = Integer.parseInt(hbox.getId());
                            hbox.setStyle("-fx-border-color:  #2Ea1da; -fx-pref-height: 50px; -fx-border-radius: 10; -fx-border-width: 2px;");

                        }
                    });
                }
            }
        }
    }

    public void setFriendsChat() {

        ArrayList<UserDTO> arr = new ArrayList<UserDTO>();

        try {
            arr = ch.getFriends(user.getId());

        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < arr.size(); i++) {
            HBox hbox = new HBox();
            Text t = new Text(arr.get(i).getUserName());
            t.setFill(Color.rgb(114, 105, 105));
            t.setStyle("-fx-margin: 5; -fx-font-size: 20px;");
            hbox.getChildren().add(t);
            hbox.setId("" + arr.get(i).getId());
            hbox.setPadding(new Insets(5, 10, 0, 10));
            hbox.setSpacing(5);

            hbox.setStyle("-fx-border-color:  #2Ea1da; -fx-pref-height: 50px; -fx-border-radius: 10; -fx-border-width: 2px;");
            friends.put(arr.get(i).getId(), hbox);
            vbox1.getChildren().add(hbox);
            //vbox1.setPadding(new Insets(5, 10, 0, 10));
            vbox1.setSpacing(2);
            recentMsg.add(hbox);
            singleChat.put(arr.get(i).getId(), new VBox());

            String ss = new String(arr.get(i).getUserName());

            hbox.setOnMousePressed(new EventHandler<MouseEvent>() {

                public void handle(MouseEvent event) {
                    isGroup = false;
                    currentGroup = 0;
                    receiver = Integer.parseInt(hbox.getId());
                    vbox = singleChat.get(Integer.parseInt(hbox.getId()));
                    borderChat.setCenter(vbox);

                    headerLabel.setText(ss);
                    //headerImg.setFill(new ImagePattern(new Image("images/person.png")));
                    changePicture(receiver, headerImg);
                    headerImg.setStyle("-fx-border-color: transparent;");
                    current = Integer.parseInt(hbox.getId());
                    hbox.setStyle("-fx-border-color:  #2Ea1da; -fx-pref-height: 50px; -fx-border-radius: 10; -fx-border-width: 2px;");

                }
            });
        }

    }

    public void setGroupList() {

        ArrayList<ChatGroupDTO> gps = new ArrayList<ChatGroupDTO>();
        try {
            gps = ch.getGroups(user.getId());
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int j = 0; j < gps.size(); j++) {
            HBox hbox = new HBox();
            Text t = new Text(gps.get(j).getChatName());
            t.setFill(Color.rgb(114, 105, 105));
            t.setStyle("-fx-margin: 5; -fx-font-size: 20px;");
            hbox.getChildren().add(t);
            hbox.setId("" + gps.get(j).getChatId());
            hbox.setPadding(new Insets(5, 10, 0, 10));
            hbox.setSpacing(5);

            groups.put(gps.get(j).getChatId(), hbox);
            vbox1.getChildren().add(hbox);

            multiChat.put(gps.get(j).getChatId(), new VBox());

            String ss = new String(gps.get(j).getChatName());

            hbox.setStyle("-fx-border-color:  #2Ea1da; -fx-pref-height: 50px; -fx-border-radius: 10; -fx-border-width: 2px;");

            hbox.setOnMousePressed(new EventHandler<MouseEvent>() {

                public void handle(MouseEvent event) {
                    isGroup = true;
                    current = 0;
                    receiver = Integer.parseInt(hbox.getId());
                    vbox = multiChat.get(Integer.parseInt(hbox.getId()));
                    borderChat.setCenter(vbox);

                    headerLabel.setText(ss);
                    headerImg.setFill(new ImagePattern(new Image("images/person.png")));
                    headerImg.setStyle("-fx-border-color: transparent;");
                    currentGroup = Integer.parseInt(hbox.getId());
                    hbox.setStyle("-fx-border-color:  #2Ea1da; -fx-pref-height: 50px; -fx-border-radius: 10; -fx-border-width: 2px;");
                }
            });
        }

    }

    public void sendAction() throws Exception {

        Color c = fontColor.getValue();
        String s = c.toString();
        //int size = Integer.parseInt((String) fontSize.getValue());
        String family = (String) fontFamily.getValue();

        if (!isGroup) {
            ch.sendMessage(send.getText(), receiver, user.getId(), s, 24, family);

        } else {
            ch.sendToGroup(send.getText(), receiver, user.getId(), s, 24, family);
        }

        send.setText("");
    }

    public void setMessage(String msg, int receiver, int sender, String color, int size, String family) {

        Platform.runLater(new Runnable() {

            public void run() {

                HBox h = new HBox();
                Label message = new Label(msg);
                message.setTextFill(Color.web(color));
                message.setFont(Font.font(family, size));
                message.setMaxWidth(300);
                message.setWrapText(true);
                h.setPadding(new Insets(5, 10, 0, 10));
                h.setSpacing(5);
                Circle chatImg = new Circle(15);
                changePicture(sender, chatImg);
                //changePicture(id, Circle c);     
                VBox v = new VBox();
                if (sender == user.getId()) {
                    v = singleChat.get(receiver);

                    message.setStyle("-fx-background-color:CADETBLUE;  -fx-padding: 0 10 0 10; -fx-background-radius: 15;"
                            + "-fx-float: right;");
                    h.setAlignment(Pos.CENTER_RIGHT);
                    h.getChildren().addAll(message, chatImg);
                } else {
                    v = singleChat.get(sender);

                    message.setStyle("-fx-background-color:PALEVIOLETRED; -fx-padding: 0 10 0 10; -fx-background-radius: 15;"
                            + "-fx-float: right;");
                    h.getChildren().addAll(chatImg, message);
                    if (current != sender) {

                        friends.get(sender).setStyle("-fx-background-color:  #2Ea1da; -fx-pref-height: 50px; -fx-background-radius: 10;");
                    }
                }
                try {
                    saveChat(msg, receiver, sender, color, size, family);
                    //System.out.println();
                } catch (JAXBException ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                v.getChildren().add(h);

            }
        });
    }

    public void setMessageGroup(String msg, int groupId, int sender, String color, int size, String family) {

        Platform.runLater(new Runnable() {

            public void run() {

                HBox h = new HBox();
                Label message = new Label(msg);

                message.setTextFill(Color.web(color));
                message.setFont(Font.font(family, size));

                h.setPadding(new Insets(5, 10, 0, 10));
                h.setSpacing(5);
                Circle chatImg = new Circle(15);
                chatImg.setFill(new ImagePattern(new Image("images/person.png")));

                VBox v = new VBox();
                v = multiChat.get(groupId);
                if (sender == user.getId()) {

                    message.setStyle("-fx-background-color:CADETBLUE; -fx-font-size: 12pt; -fx-padding: 0 10 0 10; -fx-background-radius: 15;"
                            + "-fx-float: right;");
                    h.setAlignment(Pos.CENTER_RIGHT);
                    h.getChildren().addAll(message, chatImg);
                } else {

                    message.setStyle("-fx-background-color:PALEVIOLETRED; -fx-font-size: 12pt; -fx-padding: 0 10 0 10; -fx-background-radius: 15;"
                            + "-fx-float: right;");
                    h.getChildren().addAll(chatImg, message);

                    if (currentGroup != groupId) {

                        groups.get(groupId).setStyle("-fx-background-color:  #2Ea1da; -fx-pref-height: 50px; -fx-background-radius: 10;");
                    }
                }

                v.getChildren().add(h);

            }
        });
    }

    public void setOnOff() throws Exception {

        if (onOff.isSelected()) {
            onOff.setText("ON");
            statusColor.setFill(Color.GREEN);
            user.setOnOffLine("on");
        } else {
            onOff.setText("OFF");
            statusColor.setFill(Color.GRAY);
            user.setOnOffLine("off");
        }

        ch.update(user);
        try {
            ch.tellOnOff(user);
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetFriendsList() {

        Platform.runLater(new Runnable() {

            public void run() {

                vbox2.getChildren().clear();
                try {
                    setFriendsList();
                } catch (Exception ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public void setStatusList() throws Exception {

        statusList = ch.getStatus();
        ObservableList<StatusDTO> ob = FXCollections.observableList(statusList);
        ArrayList<String> statusName = new ArrayList<String>();

        for (int i = 0; i < ob.size(); i++) {

            statusName.add(ob.get(i).getName());
        }

        ObservableList<String> names = FXCollections.observableList(statusName);

        status.setItems(names);
    }

    public void setStatus() throws Exception {

        for (int i = 0; i < statusList.size(); i++) {

            if ((status.getValue()).equals(statusList.get(i).getName())) {

                user.setStatusID(statusList.get(i).getId());
                break;
            }

        }

        ch.update(user);
        ch.tellOnOff(user);
    }

    public void createGroupSide() {

        ArrayList<UserDTO> arr = new ArrayList<UserDTO>();

        try {
            arr = ch.getFriends(user.getId());

        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < arr.size(); i++) {
            //HBox hbox = new HBox();
            CheckBox cb = new CheckBox();
            cb.setId("" + arr.get(i).getId());
            cb.setText(arr.get(i).getUserName());
//                    hbox.getChildren().add(new Text(arr.get(i).getUserName()));
//                    hbox.setId("" + arr.get(i).getId());
//
//                    hbox.setStyle("-fx-background-color: #d3c8c8; -fx-pref-height: 50px;");

            members.setSpacing(2);
            members.setPadding(new Insets(5));

            members.getChildren().add(cb);

            //Integer count = new Integer(0);
            Integer count = arr.get(i).getId();
            cb.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {

                    if (cb.isSelected() == true) {
                        tempGroupMembers.add(count);
                    } else {

                        tempGroupMembers.remove(count);
                    }
                }
            });
        }

    }

    public void setGroup() throws Exception {

        if (!groupName.getText().isEmpty() && !tempGroupMembers.isEmpty()) {

            ChatGroupDTO cg = new ChatGroupDTO();
            cg.setChatName(groupName.getText());
            cg.setUserId(user.getId());
            int groupId = ch.createGroup(cg);
            tempGroupMembers.add(user.getId());
            ch.chatGroup(groupId, tempGroupMembers);
            ch.newGroup(user, tempGroupMembers);
            groupName.setText("");
            checkGroup.setText("");
            tempGroupMembers.clear();

        } else if (groupName.getText().isEmpty()) {

            checkGroup.setText("Please Enter Group Name..");
        } else {

            checkGroup.setText("Please Select Group Members..");
        }
    }

    public void resetChat() {

        Platform.runLater(new Runnable() {

            public void run() {

                vbox1.getChildren().clear();
                try {
//                    setFriendsChat();
                    setGroupList();
                } catch (Exception ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public void blockFriend() throws Exception {

        FriendshipDTO fr = new FriendshipDTO();
        fr.setUser1(user.getId());
        fr.setUser2(receiver);
        fr.setFriendStatus(2);

        ch.updateRelations(fr);
        resetFriendsList();

    }

    public void deleteFriend() throws Exception {

        FriendshipDTO fr = new FriendshipDTO();
        fr.setUser1(user.getId());
        fr.setUser2(receiver);

        ch.deleteFriend(fr);
        resetFriendsList();
    }

    public void signOut() throws Exception {

        user.setOnOffLine("off");
        ch.update(user);
        ch.tellOnOff(user);
        ch.unRegister(user);
        loadScreen();

    }

    public void loadScreen() throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        Parent root = loader.load();
        SignInController controller = loader.getController();
        controller.setCh(ch);
        controller.setClient(client);
        //borderPane.getChildren().setAll(root);
        Stage stage = (Stage) vbox1.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

    }

    //nehal File Transfer
    public void sendFile() {

        Platform.runLater(() -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog((Stage) vbox1.getScene().getWindow());

//                    try {
//                        ch.acceptFile(InetAddress.getLocalHost().getHostAddress(), receiver);
//                    } catch (UnknownHostException ex) {
//                        Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ServerSocket serverSocket = new ServerSocket(1000);
//                                bar.setVisible(true);
//                                bar.setProgress(0);
                        serverSocket.setReuseAddress(true);
                        
                        ch.acceptFile(InetAddress.getLocalHost().getHostAddress(), receiver, user.getId());
                        Socket socket = serverSocket.accept();
                        System.out.println("Accepted connection : " + socket);
                        File transferFile = new File(file.getAbsolutePath());
                        byte[] bytearray = new byte[(int) transferFile.length()];
                        FileInputStream fin = new FileInputStream(transferFile);
                        BufferedInputStream bin = new BufferedInputStream(fin);
                        bin.read(bytearray, 0, bytearray.length);
                        OutputStream os = socket.getOutputStream();
                        System.out.println("Sending Files...");
                        // bar.setProgress(0.6);
                        os.write(bytearray, 0, bytearray.length);
                        os.flush();
                        socket.close();
                        serverSocket.close();
//                                bar.setProgress(1);
//                                bar.setVisible(false);
                        System.out.println("File transfer complete");
                    } catch (IOException ex) {
                        Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();

        });
    }

    //----secod client recive.... -----------
    public void downloadFile(String ip, int receiver, int sender) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you ok with this file?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Saving File");
                File path = fileChooser.showSaveDialog((Stage) vbox1.getScene().getWindow());
                // using ip= InetAddress.getLocalHost().getHostAddress();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int filesize = (int) 2.25e+8;
                            int bytesRead;
                            int currentTot = 0;
                            Socket socket = new Socket(ip, 1000);
                            socket.setReuseAddress(true);
//                                    bar.setVisible(true);
//                                    bar.setProgress(0);
                            byte[] bytearray = new byte[filesize];
                            InputStream is = socket.getInputStream();
                            FileOutputStream fos = new FileOutputStream(path.getAbsolutePath());
                            BufferedOutputStream bos = new BufferedOutputStream(fos);
                            bytesRead = is.read(bytearray, 0, bytearray.length);
                            currentTot = bytesRead;
                            //bar.setProgress(.3);
                            do {
                                bytesRead = is.read(bytearray, currentTot, (bytearray.length - currentTot));
                                if (bytesRead >= 0) {
                                    currentTot += bytesRead;
                                }
                            } while (bytesRead > -1);

                            //bar.setProgress(.6);
                            bos.write(bytearray, 0, currentTot);
                            bos.flush();
                            bos.close();
                            socket.close();
//                                    bar.setProgress(1);
//                                    bar.setVisible(false);
                        } catch (IOException ex) {

                            System.out.println("catch download file");
                            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }).start();

            }
        });

    }

    //save Chat islam
    public void saveChat(String msg, int receiver, int sender, String color, int size, String family) throws JAXBException {

        MessageType messageType = new MessageType();
        messageType.setFrom("" + sender);
        messageType.setTo("" + receiver);
        messageType.setColor(color.replace("0x","#"));
        messageType.setFontSize(size);
        messageType.setFontFamily(FontFamily.ARIAL);
        messageType.setFontStyle("regular");
        messageType.setFontWeight("regular");
        messageType.setMessageBody(msg);
        if(sender == user.getId()){
            messageType.setDirection("left");
            messages = savechats.get(receiver);
        }else{
            messageType.setDirection("right");
            messages = savechats.get(sender);
        }
        
        messages.add(messageType);
    }

    //hazem 
    public void profile() {

        try {
            System.out.println("profile pressed");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            Parent root = loader.load();
            ProfileController controller = loader.getController();
            controller.setController(mainc);
            controller.setUser(user);
            controller.setCh(ch);
            controller.setClient(client);

            Stage stage = (Stage) profile.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void changePicture(int id, Circle c) {

        try {
            //System.out.println(mainc.getUser().getId());
            file = ch.downloadImage(id);
            if (file != null) {
                Image image = new Image(file.toURI().toString(), 100, 150, true, true);
                c.setFill(new ImagePattern(image));
            } else {
                c.setFill(new ImagePattern(new Image("images/person.png")));
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendRequest() {

        try {
            System.out.println(findFriends.getText());
            user1 = ch.findUserByEmail(findFriends.getText());

            if (user1.getId() == null) {
                request.setText("there is no user with this email");
            } else {
                int check = ch.checkFriendsStatus(user.getId(), user1.getId());
                if (check == 0) {
                    request.setText("friend request already sent");
                } else if (check == 1) {
                    request.setText("you are friends");
                } else if (check == 2) {
                    request.setText("you are blocked by this user");
                } else {
                    ch.sendRequest(user.getId(), user1.getId());
                    request.setText("friend request sent");
                    ch.requestRespond(user, user1);
                }

                // requestRespond(user1);
            }
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void requestRespond(UserDTO user1) {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                containner = new HBox();
                Button accept = new Button("Accept");
                Button decline = new Button("Decline");
                String sender = user1.getUserName();
                Label message = new Label(sender + " sent friend request");
                containner.getChildren().addAll(message, accept, decline);
                requestRespond.getChildren().add(containner);
                accept.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("freind accepted");
                        FriendshipDTO friends = new FriendshipDTO();
                        friends.setUser1(user1.getId());
                        friends.setUser2(user.getId());
                        friends.setFriendStatus(1);
                        try {
                            ch.acceptRequest(friends);
                            containner.getChildren().clear();
                            ch.tellOnOff(user);
                            resetFriendsList();
                            //ch.tellOnOff(user1);
                        } catch (RemoteException ex) {
                            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                decline.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("freind refused");
                        FriendshipDTO friends = new FriendshipDTO();
                        friends.setUser1(user1.getId());
                        friends.setUser2(user.getId());
                        friends.setFriendStatus(1);
                        try {
                            ch.refuseRequest(friends);
                            containner.getChildren().clear();
                        } catch (RemoteException ex) {
                            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });

            }
        });
    }

//        public void acceptFriendRequest(){
//                
//                        System.out.println("freind accepted");
//                        FriendshipDTO friends = new FriendshipDTO();
//                        friends.setUser1(user1.getId());
//                        friends.setUser2(user.getId());
//                        friends.setFriendStatus(1);
//                        try {
//                            ch.acceptRequest(friends);
//                            containner.getChildren().clear();
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        
//                    }
//
//         
//                public void decline(){
//                
//                        System.out.println("freind refused");
//                        FriendshipDTO friends = new FriendshipDTO();
//                        friends.setUser1(user1.getId());
//                        friends.setUser2(user.getId());
//                        friends.setFriendStatus(1);
//                        try {
//                            ch.refuseRequest(friends);
//                            containner.getChildren().clear();
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        
//                    }
    public void notifyMe(String msg) {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                new notifiy.Notifiy("Title", msg, Duration.minutes(1), Pos.BOTTOM_RIGHT);
            }
        });

    }

    //eslam mail
    public void sendEmail() {

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Email Dialog");
        dialog.setHeaderText("Send Mail");

        ButtonType sendMail = new ButtonType("Send", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(sendMail, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField subject = new TextField();
        subject.setPromptText("Enter Your Subject");
        PasswordField password = new PasswordField();
        password.setPromptText("Enter your Password");
        TextArea message = new TextArea();
        message.setPromptText("Enter Your Message");
        //subject.setText(user.getEmail());
        grid.add(new Label("Password:"), 0, 0);//01 11
        grid.add(password, 1, 0);
        grid.add(new Label("Subject:"), 0, 1);//00 10
        grid.add(subject, 1, 1);
        grid.add(new Label("Message:"), 0, 2);
        grid.add(message, 1, 2);

        Node sendButton = dialog.getDialogPane().lookupButton(sendMail);
        sendButton.setDisable(true);

//        postFace.(new EventHandler<ActionEvent>() {
//
//                    @Override
//                    public void handle(ActionEvent event) {
//                        MailAPI mailAPI = new MailAPI();
//                        mailAPI.setFrom(user.getEmail());
//                        mailAPI.setPassword(password.getText());
//                        mailAPI.setTo(friendsList.get(receiver).getEmail());
//                        mailAPI.setSubject(subject.getText());
//                        mailAPI.setText(message.getText());
//                        mailAPI.postFace(mailAPI);
//                    }
//                    
//        });
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            sendButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == sendMail) {
                return new Pair<>(subject.getText(), password.getText());
            }
            return null;
        });

        dialog.showAndWait();
        System.out.println(password.getText());
        System.out.println(subject.getText());
        System.out.println(message.getText());

        MailAPI mailAPI = new MailAPI();
        mailAPI.setFrom(user.getEmail());
        mailAPI.setPassword(password.getText());
        mailAPI.setTo(friendsList.get(receiver).getEmail());
        mailAPI.setSubject(subject.getText());
        mailAPI.setText(message.getText());
        mailAPI.sendMail(mailAPI);

        Scene scene = new Scene(grid);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void saveXML(){
        //receiver
        messages = savechats.get(receiver);
        
        try {
            JAXBContext context = JAXBContext.newInstance("savechat");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement element = (JAXBElement) unmarshaller.unmarshal(new File("src/xml/SavedChat.xml"));
            SavedChatType chat = (SavedChatType) element.getValue();
            ObjectFactory factory = new ObjectFactory();
            for(int i=0;i<messages.size();i++){
                chat.getMessage().add(messages.get(i));
            }
            
            JAXBElement saveChat = factory.createSavedChat(chat);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marsh.marshal(saveChat, new FileOutputStream(savedChatAddress+ friendsList.get(receiver).getUserName() +".xml"));
        } catch (JAXBException | FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslDoc = new StreamSource("src/xml/SavedChat.xsl");
            Source xmlDoc = new StreamSource(savedChatAddress+ friendsList.get(receiver).getUserName() +".xml");

            String outputFileName = savedChatAddress +friendsList.get(receiver).getUserName()  + ".html";

            OutputStream htmlFile = new FileOutputStream(outputFileName);
            Transformer trasform = tFactory.newTransformer(xslDoc);
            trasform.transform(xmlDoc, new StreamResult(htmlFile));
        } catch (FileNotFoundException | TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        }
        
        
    }

    
    public void faceBook() {

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Facebook Dialog");
        dialog.setHeaderText("Post To Facebook");

        ButtonType postFace = new ButtonType("Post", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(postFace, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextArea message = new TextArea();
        message.setPromptText("Write Your Post");

        grid.add(message, 0, 0);

        Node sendButton = dialog.getDialogPane().lookupButton(postFace);
        sendButton.setDisable(true);

        message.textProperty().addListener((observable, oldValue, newValue) -> {
            sendButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait();
//        System.out.println(password.getText());
//        System.out.println(subject.getText());
        System.out.println(message.getText());

        String accessToken = "EAACEdEose0cBAGb74kZCIgXf9eqZBugNolYCGptSUsCcfMw5NZCeIYkUu0nB38fHZCq0zBMF9P2svgcjI0h324K3xO1UwcjuLQNZBVOzwYKjYdgIOkAZA547OlcDiTvupgcUHGELIRZCW9Rqqzc5EMfir6W0ZBXgFbZBc3NSlcMkh7rZC7FFGOJ5hNWWGQaoY1bDZBHgutvIKUMVz2VWlZCGhdND";
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        FacebookType ft = facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message", message.getText()));
        System.out.println(ft.getId());
//        System.out.println(ft.getMetadata().getConnections().getPosts());

        Scene scene = new Scene(grid);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
    
    
    public void exit() throws RemoteException, Exception{
    
        user.setOnOffLine("off");
        ch.update(user);
        ch.tellOnOff(user);
        ch.userExit(user);
        
        System.exit(0);
    }

}
