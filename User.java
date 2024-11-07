package application;

import java.io.Serializable;

public class User implements Serializable {
	private int permission;
	private String userName;
	private String password;
	
	
	User(int permission, String userName, String password){
		this.permission = permission;
		this.userName = userName;
		this.password = password;
		
	}
	User(){
		
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String username) {
		this.userName = username;
	}
	
	public String getPassword() {
		return password;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
}
