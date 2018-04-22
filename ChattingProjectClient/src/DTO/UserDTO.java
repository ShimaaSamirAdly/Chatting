package DTO;

import java.io.Serializable;
import javafx.scene.image.Image;

public class UserDTO implements Serializable{
    private Integer id;
    private String userName;
    private String passWord;
    private String gender;
    private String country;
    private String phoneNumber;
    private String onOffLine;
    private String email;
    private String fName;
    private String lName;
    private Integer statusID;
    private Image img;
    
    public UserDTO(){}
    public UserDTO(Integer id, String user, String pass, String gend, String count, String phone, String onOf, String em,String fName,String lName,Integer statusID ){
        this.id = id;
        this.userName = user;
        this.passWord = pass;
        this.gender = gend;
        this.country = count;
        this.phoneNumber = phone;
        this.onOffLine = onOf;
        this.email = em;
        this.fName = fName;
        this.lName = lName;
        this.statusID =statusID;
    }
    public UserDTO(String user, String pass, String gend, String count, String phone, String onOf, String em,String fName,String lName,Integer statusID ){
        this.userName = user;
        this.passWord = pass;
        this.gender = gend;
        this.country = count;
        this.phoneNumber = phone;
        this.onOffLine = onOf;
        this.email = em;
        this.fName = fName;
        this.lName = lName;
        this.statusID =statusID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOnOffLine() {
        return onOffLine;
    }

    public void setOnOffLine(String onOffLine) {
        this.onOffLine = onOffLine;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }
    
     public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
