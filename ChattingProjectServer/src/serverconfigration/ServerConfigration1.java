/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverconfigration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerConfigration1 {

@SerializedName("databaseDriver")
@Expose
private String databaseDriver;
@SerializedName("databaseScema")
@Expose
private String databaseScema;
@SerializedName("databaseUserName")
@Expose
private String databaseUserName;
@SerializedName("databasePassWord")
@Expose
private String databasePassWord;

public String getDatabaseDriver() {
return databaseDriver;
}

public void setDatabaseDriver(String databaseDriver) {
this.databaseDriver = databaseDriver;
}

public String getDatabaseScema() {
return databaseScema;
}

public void setDatabaseScema(String databaseScema) {
this.databaseScema = databaseScema;
}

public String getDatabaseUserName() {
return databaseUserName;
}

public void setDatabaseUserName(String databaseUserName) {
this.databaseUserName = databaseUserName;
}

public String getDatabasePassWord() {
return databasePassWord;
}

public void setDatabasePassWord(String databasePassWord) {
this.databasePassWord = databasePassWord;
}

}