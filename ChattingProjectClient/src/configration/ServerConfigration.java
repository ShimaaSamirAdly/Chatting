/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configration;

/**
 *
 * @author Hazem
 */
public class ServerConfigration {
    private String hostIP;
    private String savedChatAddress;

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public String getHostIP() {
        return hostIP;
    }

    public String getSavedChatAddress() {
        return savedChatAddress;
    }

    public void setSavedChatAddress(String savedChatAddress) {
        this.savedChatAddress = savedChatAddress;
    }
    
}
