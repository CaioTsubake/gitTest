package controllers;

import models.UserModel;

public class PasswordChecker {

	@SuppressWarnings("unused")
	static boolean check(UserModel user, String password){
		
		if(user.password.equals(password)){
			return true;
		}
		
		else {
			return false;
		
		}
	}
}
