/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImp;

import DAOInterface.UserDAOInt;
import DTO.UserDTO;
import databaseConnection.DatabaseConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author Hazem
 */
public class UserDAOImp implements UserDAOInt{
    
    Connection connection = null;
    
    @Override
    public UserDTO create(UserDTO t) {
        UserDTO user = new UserDTO();
        FileInputStream fis = null;
        File file = null;
        ResultSet rs = null;
        int x = 0;
        try {
            file = new File("person.png");
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into users(fname,lname,user_name,email,password,country,gender,phone,onofline,status_id,image) values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, t.getfName());
            pst.setString(2, t.getlName());
            pst.setString(3, t.getUserName());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getPassWord());
            pst.setString(6, t.getCountry());
            pst.setString(7, t.getGender());
            pst.setString(8, t.getPhoneNumber());
            pst.setString(9, t.getOnOffLine());
            pst.setInt(10, t.getStatusID());
            pst.setBinaryStream(11, fis,(int)(file.length()));
            pst.executeUpdate();          
            
            pst = connection.prepareStatement("SELECT MAX(Id) FROM users");
            rs = pst.executeQuery();
            if(rs.next()){
              x =  rs.getInt(1);
            }
            t.setId(x);
            
            pst.close();
            connection.close();
            return t;
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDTO read(int id) {
        UserDTO user = new UserDTO();
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from users where id = ? ");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt(1));
                user.setfName(rs.getString(2));
                user.setlName(rs.getString(3));
                user.setUserName(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassWord(rs.getString(6));
                user.setCountry(rs.getString(7));
                user.setGender(rs.getString(8));
                user.setPhoneNumber(rs.getString(9));
                user.setOnOffLine(rs.getString(10));
                user.setStatusID(rs.getInt(11));
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(UserDTO t) {
        try { 
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("update users set fname= ?,lname=?,user_name=?,email=?,password=?,country=?,gender=?,phone=?,onofline=?,status_id=? where id=?");
            //pst.setInt(1, t.getId());
            pst.setString(1, t.getfName());
            pst.setString(2, t.getlName());
            pst.setString(3, t.getUserName());
            pst.setString(4, t.getEmail());
            pst.setString(5, t.getPassWord());
            pst.setString(6, t.getCountry());
            pst.setString(7, t.getGender());
            pst.setString(8, t.getPhoneNumber());
            pst.setString(9, t.getOnOffLine());
            pst.setInt(10, t.getStatusID());
            pst.setInt(11, t.getId());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        
    }

    @Override
    public boolean delete(UserDTO t) {
        
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from users where id = ?");
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void printuser(UserDTO u){
        System.out.println("user id is: " + u.getId() );
        System.out.println("user fname is: " + u.getfName());
        System.out.println("user lname is: " + u.getlName());
        System.out.println("user username is: " + u.getUserName());
        System.out.println("user password is: " + u.getPassWord());
        System.out.println("user country is: " + u.getCountry());
        System.out.println("user statusid is: " + u.getStatusID());
        System.out.println("user onoff is: " + u.getOnOffLine());
        System.out.println("user gender is: " + u.getGender());
        System.out.println("user email is: " + u.getEmail());
        System.out.println("user phonenumber is: " +u.getPhoneNumber());
    }
 
    
    
    //check if user name is in database
    public UserDTO readByUsername(String username) {
        UserDTO user = null;
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from users where user_name = ? ");
            pst.setString(1, username);
            rs = pst.executeQuery();
            if(rs.next()){
                user = new UserDTO();
                user.setId(rs.getInt(1));
                user.setfName(rs.getString(2));
                user.setlName(rs.getString(3));
                user.setUserName(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassWord(rs.getString(6));
                user.setCountry(rs.getString(7));
                user.setGender(rs.getString(8));
                user.setPhoneNumber(rs.getString(9));
                user.setOnOffLine(rs.getString(10));
                user.setStatusID(rs.getInt(11));
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return user;
    }
    
     @Override
    public int maleusers() {
        int x = 0;
        ArrayList<UserDTO> users = new ArrayList<UserDTO>();
        //UserDTO 
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select count(id) from users where gender = 'female'");
            rs = pst.executeQuery();
            if(rs.next()){
                x = rs.getInt(1);
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return x;
    }

    @Override
    public int femaleusers() {
        int x = 0;
        ArrayList<UserDTO> users = new ArrayList<UserDTO>();
        //UserDTO 
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select count(id) from users where gender = 'female'");
            rs = pst.executeQuery();
            if(rs.next()){
                x = rs.getInt(1);
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return x;
    }
    
    
    public ArrayList<UserDTO> MyFriendList(ArrayList<Integer> users){
        ArrayList<UserDTO> myFriends = new ArrayList<UserDTO>();
        
        ResultSet rs = null;
        PreparedStatement pst= null;
        try {
            connection = DatabaseConnection.getConnection();
            if(users.size() != 0){
            for(int i=0;i<users.size();i++){
                UserDTO user = new UserDTO();
                pst = connection.prepareStatement("select * from users where ( id=?)");
                pst.setInt(1, users.get(i));
                rs = pst.executeQuery();
                while(rs.next()){
                    user.setId(rs.getInt(1));
                    user.setfName(rs.getString(2));
                    user.setlName(rs.getString(3));
                    user.setUserName(rs.getString(4));
                    user.setEmail(rs.getString(5));
                    user.setPassWord(rs.getString(6));
                    user.setCountry(rs.getString(7));
                    user.setGender(rs.getString(8));
                    user.setPhoneNumber(rs.getString(9));
                    user.setOnOffLine(rs.getString(10));
                    user.setStatusID(rs.getInt(11));
                    myFriends.add(user);
                
                }
            }
            

            pst.close();
            }
            connection.close();
            
            //return 1;
        } catch (SQLException ex) {
            System.out.println("cannot return the number of friends");
            ex.printStackTrace();
        }
        return myFriends;
    }

    @Override
    public UserDTO findUserByEmail(String email) {
         UserDTO user = new UserDTO();
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from users where email = ? ");
            pst.setString(1, email);
            rs = pst.executeQuery();
            if(rs.next()){
                user = new UserDTO();
                user.setId(rs.getInt(1));
                user.setfName(rs.getString(2));
                user.setlName(rs.getString(3));
                user.setUserName(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassWord(rs.getString(6));
                user.setCountry(rs.getString(7));
                user.setGender(rs.getString(8));
                user.setPhoneNumber(rs.getString(9));
                user.setOnOffLine(rs.getString(10));
                user.setStatusID(rs.getInt(11));
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void uploadImage(int id, File file) throws FileNotFoundException {
        try {
            InputStream  fis = new FileInputStream(file);
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("update users set img = ? where id = ?");
            pst.setBinaryStream(1, fis,(int)(file.length()));
            pst.setInt(2, id);
            pst.executeUpdate();            
            pst.close();
            connection.close();
            
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
    }

    @Override
    public File downloadImage(int id) throws FileNotFoundException, IOException {
        ResultSet rs = null;
        Image image;
        File file = null;
        try {
            //InputStream  fis = new FileInputStream(file);
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select img from users where id = ?");
            //pst.setBinaryStream(1, fis,(int)(file.length()));
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if(rs.next()){
                InputStream is = rs.getBinaryStream(1);
                
                file = new File("E://photo.png");
                OutputStream os = new FileOutputStream(file);
                byte[] content = new byte[1024];
                
                int size = 0;
                while((size = is.read(content)) != -1){
                    os.write(content,0,size);
                }
             
                os.close();
                is.close();
//              image = new Image ("photo.png",100,150,true,true);
                //file = new File("../../photo1.png");
                
            }
            
            pst.close();
            connection.close();
            
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
        }
        return file;
    }

    @Override
    public int offLineUsers() {
        int x = 0;
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select count(id) from users where onofline = 'off'");
            rs = pst.executeQuery();
            if(rs.next()){
                x = rs.getInt(1);
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return x;
    }

    @Override
    public int onLineUsers() {
         int x = 0;
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select count(id) from users where onofline = 'on'");
            rs = pst.executeQuery();
            if(rs.next()){
                x = rs.getInt(1);
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return x;
    }
    
}
