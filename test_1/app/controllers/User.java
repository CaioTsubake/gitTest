package controllers;


import java.util.List;

import com.avaje.ebean.Ebean;

import models.UserModel;
import play.Logger;
import play.data.Form;
import play.data.Form.Field;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;
import static play.libs.Json.*;


public class User extends Controller{
	
	public static Result index() {
		return ok(user.render("Welcome to Login"));
		
	}
	public static Result reg() {
		return ok(register.render());
	}
	
	public static Result login() {
		UserModel formUser = Form.form(UserModel.class).bindFromRequest().get();
		
		
		UserModel user = Ebean.find(UserModel.class)
				.where()
					.eq("username", formUser.username)
				.findUnique();
		
		// If there is a user with that username on the database.
		if(user != null){
			// Check if the password is the correct one
			if(formUser.password.equals(user.password)){
				return redirect(routes.Application.index());
			}
		}
		// An error occured while trying to log in.
		return redirect(routes.User.index());
	}
	
	public static Result addUser() {
		UserModel user = Form.form(UserModel.class).bindFromRequest().get();
		// If the password don't match the repeated one.
		if(!user.password.equals(user.repeatPassword)){
			return redirect(routes.User.reg());
		}
		// Saves the user on the database and sends to the login page.
		user.save();
		return redirect(routes.User.login());
	}
	
	public static Result getUsers() {
		List<UserModel> users = new Model.Finder(String.class, UserModel.class).all();
		return ok(toJson(users));		
	}

}
