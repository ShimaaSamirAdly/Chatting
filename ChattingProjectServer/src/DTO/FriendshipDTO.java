package DTO;

import java.io.Serializable;

public class FriendshipDTO implements Serializable{
    private Integer user1;
    private Integer user2;
    private Integer friendStatus;
    private Integer actionTaker;
    private String fColor;
    private String fFamily;
    private Integer fSize;
    
    public FriendshipDTO(){
        
    }
    
    public FriendshipDTO(Integer u1,Integer u2,Integer friendSt,Integer action,String fCol,String fFam,Integer fSize){
        this.user1 = u1;
        this.user2 = u2;
        this.friendStatus = friendSt;
        this.actionTaker = action;
        this.fColor = fCol;
        this.fFamily = fFam;
        this.fSize = fSize;
        
    }

    public Integer getUser1() {
        return user1;
    }

    public void setUser1(Integer user1) {
        this.user1 = user1;
    }

    public Integer getUser2() {
        return user2;
    }

    public void setUser2(Integer user2) {
        this.user2 = user2;
    }

    public Integer getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(Integer friendStatus) {
        this.friendStatus = friendStatus;
    }

    public Integer getActionTaker() {
        return actionTaker;
    }

    public void setActionTaker(Integer actionTaker) {
        this.actionTaker = actionTaker;
    }

    public String getfColor() {
        return fColor;
    }

    public void setfColor(String fColor) {
        this.fColor = fColor;
    }

    public String getfFamily() {
        return fFamily;
    }

    public void setfFamily(String fFamily) {
        this.fFamily = fFamily;
    }

    public Integer getfSize() {
        return fSize;
    }

    public void setfSize(Integer fSize) {
        this.fSize = fSize;
    }
}