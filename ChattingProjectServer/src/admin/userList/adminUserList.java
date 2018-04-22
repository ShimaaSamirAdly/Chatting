/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.userList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nehal
 */
public class adminUserList extends Application {

    adminUserListController control;
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void closeStage() {
        stage.close();
    }

    /*private ObservableList<Person> personData = FXCollections.observableArrayList();

    public adminUserList() {
        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }*/

    //private ObservableList<userListModel> UserList = FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));

        Parent root = loader.load();
        adminUserListController controller = loader.getController();
        controller.setMainApp(this);

        Scene scene = new Scene(root,1000,550);
        
        stage.setScene(scene);
        stage.setTitle("Server Side Application");
        stage.sizeToScene();
        stage.setHeight(550);
        stage.setWidth(1000);
        stage.setResizable(false);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
