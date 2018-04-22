/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseConnection;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import serverconfigration.ServerConfigration1;

/**
 *
 * @author Hazem
 */
public class DatabaseConnection {
    
    public static Connection getConnection(){
        String databaseDriver = null;
        String databaseScema = null;
        String databaseUserName = null;
        String databasePassWord = null;
        Gson gson = new Gson();
        Reader reader;
        Connection connection = null;
        try {
            reader = new FileReader("serverConfigration.json");
            ServerConfigration1 serverConfig = gson.fromJson(reader,ServerConfigration1.class);
            databaseDriver = serverConfig.getDatabaseDriver();
            databaseScema = serverConfig.getDatabaseScema();
            databaseUserName = serverConfig.getDatabaseUserName();
            databasePassWord = serverConfig.getDatabasePassWord();
            System.out.println(databaseDriver);
            System.out.println(databaseScema);
            System.out.println(databaseUserName);
            System.out.println(databasePassWord);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            Class.forName(databaseDriver);
            connection =  DriverManager.getConnection(databaseScema, databaseUserName, databasePassWord);
        } catch (ClassNotFoundException ex) {
            System.out.println("cannot connect to the database");
        } catch (SQLException ex) {
            System.out.println("sql command error");
        }
        return connection;

    }
}
