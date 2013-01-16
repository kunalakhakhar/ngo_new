package util;

import dao.App_User;

public class GetCurrentUser {
	
	private static App_User appUser;
	
	public static void SetUser(App_User user){
		appUser = user;
	}
	
	public static App_User GetUser(){
		return appUser;
	}

}
