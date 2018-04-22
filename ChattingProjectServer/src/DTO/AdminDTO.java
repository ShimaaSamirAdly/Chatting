package DTO;

import java.io.Serializable;

public class AdminDTO implements Serializable{
    private Integer id;
    private String userName;
    private String passWord;
    private Integer addAdmin;
    private Integer addStatus;
    
    public AdminDTO(){
        
    }
    public AdminDTO(Integer id, String user, String pass, Integer addA,Integer addS ){
        this.id = id;
        this.userName = user;
        this.passWord = pass;
        this.addAdmin = addA;
        this.addStatus = addS;
        
    }
    public AdminDTO(String user, String pass, Integer addA,Integer addS ){
        this.userName = user;
        this.passWord = pass;
        this.addAdmin = addA;
        this.addStatus = addS;
        
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

    public Integer getAddAdmin() {
        return addAdmin;
    }

    public void setAddAdmin(Integer addAdmin) {
        this.addAdmin = addAdmin;
    }

    public Integer getAddStatus() {
        return addStatus;
    }

    public void setAddStatus(Integer addStatus) {
        this.addStatus = addStatus;
    }
    
}