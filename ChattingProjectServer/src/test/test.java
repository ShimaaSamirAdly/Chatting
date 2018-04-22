/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DAOImp.AdminDAOImp;
import DAOImp.ChatGroupDAOImp;
import DAOImp.ChatUserDAOImp;
import DAOImp.FriendDAOImp;
import DAOImp.StatusDAOImp;
import DAOImp.UserDAOImp;
import DTO.AdminDTO;
import DTO.ChatGroupDTO;
import DTO.ChatUserDTO;
import DTO.FriendshipDTO;
import DTO.StatusDTO;
import DTO.UserDTO;
import java.util.ArrayList;

/**
 *
 * @author Hazem
 */
public class test {
    public static void main(String[] argus){
        
        //testing the user dao
        UserDAOImp user = new UserDAOImp();
        UserDTO u = null;
        AdminDAOImp admin = new AdminDAOImp();
        AdminDTO ad = new AdminDTO();
        StatusDAOImp status = new StatusDAOImp();
        StatusDTO st = new StatusDTO();
        ChatGroupDTO ch = new ChatGroupDTO(); 
        ChatGroupDAOImp chat = new ChatGroupDAOImp();
        ChatUserDTO chu = new ChatUserDTO();
        ChatUserDAOImp chatgroup = new ChatUserDAOImp();
        FriendshipDTO friend = new FriendshipDTO();
        FriendDAOImp friendship = new FriendDAOImp();
        //testing the read operation which takes an id and return a user object
        
       // u = user.read(1);
      //  user.printuser(u);
       // System.out.println("female: "+user.femaleusers());
     //   System.out.println("male: "+user.maleusers());
        //testing the update operation which takes an user object and dosonet return any thing
        /*
        u.setUserName("zoma2018");
        u.setEmail("koko7o7o");
        user.update(u);
        System.out.println();
        System.out.println();
        System.out.println();
        user.printuser(u);
        */
        //testing the delete operation which takes an user object and return boolean
        /*
        user.delete(u);
        */
        //testing the insert operation which takes an user object and return an id
        /*
        u.setfName("nehal");
        u.setlName("eslam");
        u.setEmail("shosho11");
        u.setUserName("hazem11");
        u.setPhoneNumber("012129911");
        System.out.println();
        System.out.println();
        System.out.println(user.create(u));
        */
        
        ad.setUserName("hes");
        ad.setPassWord("14");
        ad.setAddAdmin(1);
        ad.setAddStatus(1);
        System.out.println(admin.create(ad));
              
        /*
        ad = admin.read(1);
        admin.printAdmin(ad);
        ad.setAddAdmin(0);
        ad.setAddStatus(0);
        ad.setUserName("7amo");
        ad.setPassWord("123456");
        admin.update(ad);
        admin.printAdmin(ad);
        admin.delete(ad);
        */
        /*
        st.setName("iam buzy");
        status.create(st);
        */        
        /*
        st = status.read(4);
        status.printStatus(st);
        st.setName("iam notf here");
        status.update(st);
        status.delete(st);
        */      
        /*
        ch.setChatId(1);
        ch.setChatName("iti");
        ch.setUserId(2);
        chat.create(ch);
        ch.setChatName("hamo");
        ch.setUserId(5);
        chat.update(ch);
        ChatGroupDTO ch1 = new ChatGroupDTO();
        ch1 = chat.read(1);
        System.out.println(ch1.getChatName());
        chat.delete(ch1);
        */
      //  chu.setChatId(4);
      //  chu.setUserId(8);
        //chatgroup.create(chu);
        /*
        ArrayList<ChatUserDTO> hh = chatgroup.read(4);
        for(int i=0;i<hh.size();i++){
            System.out.println(hh.get(i).getUserId());
        }
        *//*
        friend.setUser1(9);
        friend.setUser2(2);
        friend.setFriendStatus(1);
        friend.setfColor("red");
        friend.setfFamily("ariel");
        friend.setfSize(14);
        */
        //friendship.create(friend);
        /*
        ArrayList arr = friendship.friendlist(2);
        for(int i=0;i<arr.size();i++){
            System.out.println(arr.get(i));
        }
        ArrayList<UserDTO> uu = new ArrayList<UserDTO>();
        uu = user.MyFriendList(arr);
        for(int i=0;i<uu.size();i++){
            System.out.println(uu.get(i).getId());
            System.out.println(uu.get(i).getEmail());
            System.out.println(uu.get(i).getCountry());
        }
        
        System.out.println(user.maleusers());
        */
        
        //u = user.findUserByEmail("shosho1");
        //System.out.println(u.getId());
        //friendship.sendRequest(5, 10);
        //friendship.update(5, 10, 0);
       // ch.setChatName("sdfds");
       // ch.setUserId(10);
        //int x = chat.create(ch);
        //System.out.println(x);
        /*
        ArrayList<Integer> xx = new ArrayList<Integer>(); 
        xx.add(8);
        xx.add(9);
        xx.add(10);
        System.out.println(chatgroup.chatGroup(13,xx ));
        */
        /*
        ArrayList<ChatGroupDTO> chhh = chat.myGroups(10);
        for(int i=0;i<chhh.size();i++){
            System.out.println(chhh.get(i).getChatName());
        }
        */
        /*
        ArrayList<UserDTO> uuser = chat.chatusers(13);
        for(int i=0;i<uuser.size();i++){
            System.out.println(uuser.get(i).getfName());
        }
        */
        //System.out.println(friendship.checkFriends(12, 2));
        /*
        friend.setUser1(2);
        friend.setUser2(5);
        //friend.setFriendStatus(1);
        friend = friendship.returnTextStyle(2, 5);
        System.out.println(friend.getfColor());
        System.out.println(friend.getfFamily());
        System.out.println(friend.getfSize());
        
        */
     //   ad = admin.readByUsername("hazem");
      //  System.out.println(ad.getUserName());
       // System.out.println(ad.getPassWord());
    }
}
