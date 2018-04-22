/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author Hazem
 */
public class ChatGroupDTO implements Serializable{
    private int creatorId;
    private int chatId;
    private String chatName;
    public ChatGroupDTO(){}
    public ChatGroupDTO(int chatid,int userid,String name){
        chatId = chatid;
        creatorId = userid;
        chatName = name;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public int getUserId() {
        return creatorId;
    }

    public void setUserId(int userId) {
        this.creatorId = userId;
    }
    
}
